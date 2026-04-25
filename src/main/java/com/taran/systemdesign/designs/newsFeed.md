 Design a **news feed** or timeline system.

The database would be something like

Tables
NEWS
NEWS_DETAILS

NEWS
 - id
 - title
 - author
 - publish_date
 - created_at
 - updated_at


NEWS_DETAILS
 - id
 - news_id
 - subdetails
 - details
 - created_at
 - updated_at


 There can be two solutions for generating a news feed system

 1. Polling: Polling is a mechanism to call an endpoint for updates every given timeframe. 
    * The user first calls last updated at, since it being null, the call returns with the latest news from the starting of the day. The call also returns last updated date, which could be stored in a state or local storage. 
    * The next call to the same endpoint brings the delta from the last updated time and updates the react component.
    * The webservice can be scaled easily as its stateless and we only need to 

2. WebSockets:  Websockets are two way communication protocol which is connected to the backend making it easier to relay messages. With Websockets, I will also include a message queue like SQS, where each submission will trigger a message.
    * The user will authenticate first and then call the websocket service to align themselves.
    * The user sends a request with no last updated time, so he gets the top 100 updates. React component updates.
    * As soon as there is an update, the service will send message to the topic and all the listeners will get updated with the news id and details.
    * The news will be submitted to the websocket and the react component will be updated.



    Here’s a clean, interview‑ready textual answer you can speak or write:

***

I would design a **news feed (timeline) system** around two main flows: **publishing news** and **reading the feed**. The core challenge is generating personalized feeds efficiently at scale, so the main design decision is **how we fan out news to followers**.

### Requirements

First, I’d assume:
- Each user sees a feed of news from the authors they follow.
- Items are ordered by publish time (or by a simple ranking score).
- Pagination is supported.
- The system should handle many readers and authors and stay fast.
- Strong consistency is not required; eventual consistency is fine for live updates.

### Data model

Instead of just `NEWS` and `NEWS_DETAILS`, I would also model feeds and relationships:

- **NEWS**
  - id
  - author_id
  - title
  - summary / body
  - publish_date
  - created_at
  - updated_at

- **NEWS_DETAILS**
  - id
  - news_id
  - subdetails
  - details
  - created_at
  - updated_at

- **USER_FOLLOWING**
  - follower_id
  - followee_id
  - created_at

- **USER_FEED**
  - user_id
  - news_id
  - rank_score or sort_time
  - created_at

This keeps the feed as a lightweight index of news IDs and metadata, while the full content lives in the news tables.

### Feed generation strategy

The main architectural choice is **fan‑out on write vs fan‑out on read**:

- **Fan‑out on write**: when a new story is published, the system immediately pushes it into each follower’s feed. Reads are very fast because the feed is precomputed, but writes are expensive, especially for popular authors.
- **Fan‑out on read**: feeds are built only when the user opens them. Reads are slower, but writes are cheap.
- **Hybrid**: this is usually the best answer. For normal authors, fan out on write to keep reads fast. For very high‑fanout or “celebrity‑like” authors, fan out on read to avoid extreme write amplification.

This hybrid approach balances latency and scalability.

### Write path

When an author publishes news:
1. Persist the news entry into the primary database.
2. Emit an event (or message) to a queue or stream (for example, an SQS‑like queue or Kafka topic).
3. Background workers consume the event.
4. Workers update follower feeds asynchronously:
   - For normal authors: insert the news ID into each follower’s `USER_FEED`.
   - For high‑fanout authors: skip pre‑computing all feeds and instead mark that the follower’s feed needs to be rebuilt on read.
5. Optionally, invalidate or refresh cache entries for hot feeds.

This keeps the publish API fast and non‑blocking.

### Read path

When a user requests the feed:
1. Read the feed index for that user (from cache or `USER_FEED`).
2. Apply **cursor‑based pagination** using a `news_id` or timestamp cursor, not numeric offset, to avoid duplicates or gaps when new items are inserted.
3. Hydrate the items by fetching the full news content from the `NEWS` and `NEWS_DETAILS` tables.
4. Return a paginated list of feed items to the client.

For performance, I would keep the feed index lean and cache the first page for hot feeds.

### Real‑time update: polling vs WebSocket

The choice between polling and WebSocket is about **delivery mechanism**, not the overall architecture:

- **Polling**: the client periodically calls an endpoint asking for news newer than a given timestamp or cursor. This is simple, stateless, and easy to scale, but freshness depends on poll interval.
- **WebSockets**: the client opens a persistent two‑way connection. The server can push notifications when new items are available. This reduces latency but adds connection‑management complexity.
- **Server‑Sent Events (SSE)**: useful if updates are mostly one‑way from server to client; it can be simpler than full WebSockets.

In practice, I would:
- Use **REST + pagination** for the main feed fetch.
- Optionally use **WebSocket or SSE** only to notify the client that new items are available (e.g., “3 new stories since your last fetch”).
- Keep the primary retrieval logic decoupled from the push channel.

### Caching and scalability

To keep the system performant:
- Cache the first page of feeds for active users.
- Cache frequently accessed news content.
- Shard the feed storage by `user_id` to distribute load.
- Keep feed entries lightweight (IDs + metadata) and fetch details only when needed.
- Monitor queue lag, cache hit rate, and feed freshness.

### Additional considerations

I would also briefly mention:
- **Idempotency and deduplication**: publishers or workers might retry operations, so feed inserts should be idempotent.
- **Delete / edit propagation**: when a news item is updated or deleted, I’d clean up or update its appearance in feeds.
- **Ranking**: if the feed is ranked (e.g., based on popularity or recency), the ranking logic can be applied at write time or at read time depending on performance needs.
- **Failure handling**: workers can back off when the queue is backed up; the client can fall back to polling if the push channel fails.

With this design, the system supports personalized timelines, scales via hybrid fan‑out, and keeps reads fast while managing the cost of writes.