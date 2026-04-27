5. Design a **notification system**.[^2][^8]


Backend
This notification system is kind of a sub system like the chat messaging system. It depends on the number of users and type of sync needed for the users. We will use postgres for the database. The tables are listed below. The fan out strategy we will be using here is fan out as the message is provided by the author. Each message will be a new row in the notifications table, which can be filtered out via the last login time, or via an event on the frontend side to update the seen notifications time. 


Caching
The notification for the users can be cached on redis for faster access. 

Delivery 
And if live, the message can be sent to the topic which will be then consumed by the SQS and send to the user via a websocket or polling.


Database Tables
user
id, name, last_login_at, last_notification_at.

notifications
id, author_id, user_id, message, created_at, updated_at


Answer

# Notification System Design

This note is a quick-recall guide for designing a notification system in system design interviews and real applications. It focuses on the core ideas, trade-offs, and mental models that are easiest to forget later.

## Goal

A notification system informs users when something relevant happens, such as a like, comment, mention, follow, order update, payment event, or system alert. A good design balances reliability, low latency, scalability, storage efficiency, and a clean user experience.[1][2]

## Main Requirements

Typical functional requirements are:

- Show notifications in an inbox or dropdown.
- Support unread and read states.
- Optionally push live updates in real time.
- Support multiple channels such as in-app, email, push, or SMS.
- Respect user preferences and notification settings.[1][2]

Typical non-functional requirements are:

- High availability, because missed notifications hurt trust.
- Scalability, because some events may fan out to many users.
- Low read latency, especially for the notifications panel.
- Durability, so events are not lost during failures.[1][3][2]

## Core Building Blocks

A notification system is usually built from these components:

- **Event producer**: the service that generates the event, such as likes, comments, orders, or chat.
- **Message broker / event bus**: decouples producers from consumers, often using SNS, Kafka, RabbitMQ, or a similar system.[3][4]
- **Queue / worker layer**: processes events asynchronously and performs fan-out, retries, enrichment, or formatting.[3][4]
- **Database**: stores notification records and read state.
- **Cache**: stores unread counts or recent notifications for faster reads.
- **Realtime gateway**: pushes live updates through WebSocket, SSE, or long polling.
- **Preference service**: decides whether the user wants this notification and on which channel.[1][2]

## End-to-End Flow

A common end-to-end flow looks like this:

1. A business event occurs, such as `Bob liked Alice's post`.
2. The producer publishes the event to a topic or event stream.
3. A notification worker consumes the event.
4. The worker decides recipients, checks preferences, and prepares the notification payload.
5. The system either stores one central event or creates per-user notification records, depending on the fan-out strategy.[5][1]
6. Redis may update unread count or recent notification cache.
7. If the recipient is online, a realtime service pushes the notification immediately.
8. Later, when the user opens the app, the API returns notifications from storage or cache.[1][3][2]

## Fan-Out Strategies

The most important concept in notification design is **fan-out**, which means distributing one event to one or many recipients.[5][6]

### Fan-out on write

In fan-out-on-write, the system creates notification records for recipients as soon as the event happens. For example, if Alice publishes a post and 500 followers should see it, the worker writes 500 rows, one for each user.[5][1]

**Why teams use it:**

- Reads become simple and fast.
- The inbox query is easy: `WHERE user_id = ? ORDER BY created_at DESC`.
- Read state is easy to manage with fields like `read_at`.[1][2]

**Weakness:**

- Writes become expensive for users with huge fan-out, such as celebrities or large groups.[5][6]

### Fan-out on read

In fan-out-on-read, the system stores the event once and computes each user's notification view when the user opens the app. It does **not** pre-create one notification row per recipient.[5][6]

Example:

- Bob likes Alice's post.
- The system stores the like event once.
- When Alice opens notifications, the API joins or aggregates the relevant source tables and builds the notification list dynamically.[5][6]

**Why teams use it:**

- Writes stay cheap.
- It works well for very large fan-out cases.
- It avoids creating millions of rows that many users may never read.[5][6]

**Weakness:**

- Reads are more expensive.
- Read-time personalization logic becomes more complex.
- Features like stable ordering, pagination, and read state can be harder to manage cleanly.[1][2]

### Hybrid strategy

Real systems often use a hybrid approach:

- Fan-out-on-write for normal users or small recipient sets.
- Fan-out-on-read for celebrity or very high fan-out cases.[5][6]

This is often the best interview answer because it shows trade-off awareness instead of forcing a single strategy everywhere.[5]

## Quick Comparison

| Topic | Fan-out on write | Fan-out on read |
|---|---|---|
| Write cost | High for large recipient sets [5] | Low, usually one event write [5] |
| Read cost | Low [1] | Higher, computed at read time [6] |
| Storage | Higher due to per-user rows [1] | Lower, stores central events [6] |
| Unread/read tracking | Easy [2] | Harder, often needs extra state [2] |
| Best fit | Frequently read inboxes [1] | Very high fan-out workloads [5] |

## Recommended Data Model

For a practical in-app notification system, PostgreSQL can store the durable records. A useful schema is:

### Users

```sql
users(
  id,
  name,
  last_login_at,
  last_notification_seen_at
)
```

### Notifications

```sql
notifications(
  id,
  user_id,
  actor_id,
  type,
  entity_type,
  entity_id,
  payload_json,
  created_at,
  read_at
)
```

### Notification preferences

```sql
notification_preferences(
  user_id,
  notification_type,
  channel,
  enabled
)
```

### Delivery tracking

```sql
notification_deliveries(
  id,
  notification_id,
  channel,
  status,
  delivered_at,
  failed_at
)
```

This schema supports personalization, read state, multi-channel delivery, retries, and debugging more cleanly than a minimal `message` field alone.[7][2]

## Why `read_at` Matters

A common mistake is using `last_login_at` to detect unread notifications. That is not enough, because login state and notification state are different concepts.[7][2]

A user may:

- log in without opening notifications,
- open notifications on one device but not another,
- mark individual items as read,
- or read some items but not all.

Because of this, systems usually track `read_at` per notification, or maintain a separate last-seen cursor plus unread count logic.[7][2]

## Read Path

For fan-out-on-write systems, the read path is simple:

```sql
SELECT *
FROM notifications
WHERE user_id = ?
ORDER BY created_at DESC
LIMIT 50;
```

Unread count:

```sql
SELECT COUNT(*)
FROM notifications
WHERE user_id = ? AND read_at IS NULL;
```

This is a strong design for notification dropdowns because reads are predictable and easy to cache.[1][2]

## Write Path

A typical write path for fan-out-on-write is:

1. Event occurs.
2. Publish event to topic.
3. Worker consumes event.
4. Worker determines recipients.
5. Worker inserts rows into `notifications`.
6. Worker updates unread count cache.
7. Realtime service pushes to online users.[1][3][4]

This design is preferred when the product expects frequent notification reads and needs fast inbox loading.[1][2]

## Realtime Delivery

Realtime delivery should be thought of as a separate layer from storage. A queue like SQS helps decouple and buffer work, but it is not the browser delivery mechanism by itself.[3][8]

A better mental model is:

```text
Event -> Topic / Stream -> Queue -> Worker -> DB / Cache -> WebSocket Gateway -> Client
```

This separation matters because:

- the database is the source of truth,
- the queue provides durability and retries,
- and the WebSocket gateway handles active client connections.[3][8]

## Role of Redis

Redis is useful for hot-path reads and counters, but not as the source of truth. Common Redis patterns include:

- `user:{id}:unread_count`
- `user:{id}:recent_notifications`
- `user:{id}:last_seen_id`

This improves response time for dropdowns and notification badges while keeping PostgreSQL as the durable system of record.[1][2]

## Example: Like Notification

Suppose Bob likes Alice's photo.

### Fan-out on write version

1. Like service emits an event.
2. Notification worker receives it.
3. Worker creates one row for Alice in `notifications`.
4. Redis increments Alice's unread count.
5. If Alice is online, WebSocket pushes a live message.
6. When Alice opens notifications, the API reads from `notifications`.[1][3][2]

This is simple, fast on reads, and easy to reason about.

### Fan-out on read version

1. Like service emits or stores the event once.
2. No per-user notification row is created immediately.
3. When Alice opens notifications, the API computes relevant events for her from source tables.
4. The result may be cached briefly.
5. Read state needs separate handling, such as a cursor or aggregated state.[5][6][2]

This saves write amplification but shifts complexity to reads.

## Example: Celebrity Problem

If a user with 20 million followers posts something, fan-out-on-write can be very expensive because the system may need millions of writes right away.[5][6]

In that case, a hybrid approach works well:

- normal users: fan-out-on-write,
- celebrity users: fan-out-on-read or delayed asynchronous fan-out.[5][6]

This is one of the most important scalability examples to remember for interviews.

## Common Design Mistakes

- Using `last_login_at` as unread state.[7][2]
- Not separating storage from live delivery.[3][8]
- Ignoring user preferences and notification opt-outs.[1][2]
- Storing only plain message text instead of type + entity references + payload.[7][2]
- Assuming one fan-out strategy fits every scale level.[5][6]
- Forgetting idempotency and deduplication for retries and duplicate events.[3][2]

## Interview Recall Version

If this topic comes up in an interview, a strong short answer is:

> A notification system usually has producers, an event bus, async workers, durable storage, caching, and optional realtime delivery. For moderate scale, fan-out-on-write is usually better because it makes reads fast and supports simple unread tracking. For very large fan-out cases, fan-out-on-read or a hybrid model works better because it reduces write amplification. PostgreSQL can store durable notification records, Redis can cache unread counts and recent items, and WebSocket can push live updates while the database remains the source of truth.[5][1][3][2]

## Mental Model to Remember

When designing notifications, always think in this order:

1. **What event happened?**
2. **Who should receive it?**
3. **Should it be persisted per user now, or computed later?**
4. **How will unread state be tracked?**
5. **How will live delivery work?**
6. **What changes when fan-out becomes huge?**

If these six questions are answered clearly, most notification system designs become easy to explain and defend.[5][1][2]