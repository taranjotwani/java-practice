Here’s a **curated list of 30 system design questions** you can use as your practice set. They cover the most common patterns you’ll see at mid‑senior and lead‑level interviews (URL shortener, feed, chat, marketplace, distributed services, etc.). After you answer each, you can paste your answer here and I’ll give you a detailed, line‑by‑line review.

***

### Core classic products

1. **Design a URL shortener (like TinyURL / Bitly)**  
   Focus: high‑QPS redirects, short‑code generation, caching, analytics.

2. **Design a social media feed (like Twitter / X‑style timeline)**  
   Focus: timeline generation (fan‑in vs fan‑out), write‑scaling, personalization, TTL.

3. **Design a chat / messaging app (like WhatsApp / Slack)**  
   Focus: delivery guarantees, online/offline, sync, read receipts, 1:1 vs group.

4. **Design a ride‑hailing service (like Uber / Lyft)**  
   Focus: rider‑driver matching, ETA, surge pricing, maps, geohashing.

5. **Design a marketplace (like Amazon / eBay)**  
   Focus: catalog, search, inventory, order mgmt, consistency vs availability.

6. **Design a hotel‑booking / appointment‑booking system**  
   Focus: availability, race conditions, overbooking, cancellations, check‑in.

7. **Design a price‑alert or stock‑ticker system**  
   Focus: streams, delta updates, low‑latency feeds, notification load.

8. **Design a “like” / reaction counter for a social network**  
   Focus: high‑QPS counters, sharding, eventual consistency, strong‑read‑required.

9. **Design a recommendation / personalization system (e.g., “You may like…”)**  
   Focus: models, online features, A/B‑test, low‑latency scoring.

10. **Design a search engine for a large document repository (e.g., Notion / Google Docs)**  
   Focus: crawling, indexing, tokenization, ranking, result caching.

***

### Storage, data, and batch

11. **Design a distributed key‑value store (like Redis / Dynamo‑style)**  
   Focus: consistency model, replication, sharding, partition tolerance.

12. **Design a distributed file storage / cloud drive (like Dropbox / Google Drive)**  
   Focus: chunking, dedup, versioning, sharing, sync, offline‑mode.

13. **Design a distributed logging and metrics system (like ELK / Datadog‑style)**  
   Focus: ingestion, buffering, indexing, rollups, retention.

14. **Design a batch job scheduler (like Airflow / cron‑cluster)**  
   Focus: DAGs, retries, resource isolation, scheduling, UI.

15. **Design a system to sort / process very large datasets (e.g., MapReduce‑style)**  
   Focus: split‑sort‑merge, shuffling, fault tolerance, recovery.

16. **Design a top‑K elements/aggregation system (e.g., Amazon “top‑seller”)**  
   Focus: streaming aggregations, heavy‑hitters, approximate vs exact.

17. **Design a distributed tracing system (like Zipkin / Jaeger)**  
   Focus: spans, sampling, storage, low‑impact tracing, query latency.

18. **Design a photo‑sharing platform (like Google Photos / Flickr)**  
   Focus: upload, thumbnail generation, album, sharing, search.

19. **Design a document collaboration system (Google Docs / Notion‑style)**  
   Focus: real‑time CRDT / OT, concurrency, conflict‑resolution, undo.

20. **Design a distributed file‑sharing / torrent‑like system**  
   Focus: peer discovery, chunking, incentives, NAT traversal, availability.

***

### Real‑time, streams, and events

21. **Design a notification service at scale (push + email + in‑app)**  
   Focus: dedup, rate limiting, retry, QoS, read‑receipts.

22. **Design a live‑comment / live‑chat feature for a newsfeed (e.g., Facebook Live comments)**  
   Focus: message ordering, latency, fan‑out, throttling, moderation.

23. **Design a stream‑processing pipeline (like Kafka + consumers)**  
   Focus: partitioning, ordering, at‑least‑once vs exactly‑once, backpressure.

24. **Design a location‑based proximity service (e.g., Yelp / Uber “nearby”)**  
   Focus: spatial indexing, geohashing, updates, privacy, load.

25. **Design a rate‑limiter at scale (per‑IP, per‑user, per‑endpoint)**  
   Focus: distributed counters, Redis / Token bucket, burst vs leaky‑bucket.

26. **Design a web crawler (for a search engine)**  
   Focus: scheduling, politeness, dedup, fresh‑vs‑deep crawl, crashes.

27. **Design an A/B testing / feature‑flagging system**  
   Focus: consistency, rollout strategy, metrics, security.

28. **Design a distributed locking / leader‑election system**  
   Focus: ZooKeeper / etcd‑style, leases, fencing, failure‑handling.

29. **Design a distributed caching layer (multi‑shard, multi‑region)**  
   Focus: cache invalidation, TTL, LRU, consistency with DB, write‑through vs write‑back.

30. **Design a code deployment / CI/CD pipeline system**  
   Focus: staging, canarying, rollbacks, secrets, security, observability.

***

### How to use this list

- **For each question**:
  1. State scope / constraints.
  2. Define APIs and data model.
  3. Draw a high‑level component diagram.
  4. Sketch the critical path (e.g., short URL creation, message delivery).
  5. Discuss scaling, sharding, replication, caching, tradeoffs.
- **Next step**:  
  Pick one of these 30, write your answer in your own words, then paste it here and I’ll:
  - Tell you what’s correct and where you’re strong.
  - Point out missing pieces (e.g., consistency model, edge‑case, metrics).
  - Suggest how to tighten it to senior / lead‑level.

Start with #1 (URL shortener) if you want continuity with what we just discussed, or tell me which one you want to tackle first and I’ll give you a 1‑sentence “ideal direction” before you write.  

Which of these 30 should we start with?