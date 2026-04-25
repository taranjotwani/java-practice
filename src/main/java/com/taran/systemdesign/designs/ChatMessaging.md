Design a **chat / messaging app**.[^11][^12]

For a chat messaging system, if the number of users are low, we can keep a database messaging model or if the number of users are more and the requirement is real time, we can add the messaging queue to get the data.

Scale - Lets say 100k Users
Consistency - Each message should be propogated to the database.
Availability - No Downtime expected.
Latency - Eventual.


Database

USER
ID
UUID
NAME
.. OTHER DETAILS

CHAT_PARTICIPANTS
ID
CHAT_ROOM_ID
USER_ID
CREATED_AT
UPDATED_AT

CHAT_ROOM
ID
CHAT_ROOM_PARTICIPANT_ID
CHAT_MESSAGE_ID
CREATED_AT
UPDATED_AT


CHAT_MESSAGE
CHAT_TEXT
CREATED_AT
UPDATED_AT


The rest web service will be a post webservice which will take the user UUID as body and return the chat room id. Another webservice will be a POST webservice which will be sent to messaging queue to fan out. Each of the participants will be linked via a websocket or polling. The event messages will see the chat room id and will update the database and send the message to participants. We can scale the webservices for sending the messages. In this say we dont need a stateful web service to keep the users attached to their servers.


Your answer shows the **right direction**—you identified real-time delivery, WebSocket/polling, queue-based fanout, and persistence—but for a senior-level system design answer it is still **too incomplete and a bit structurally shaky**. I’d score it as a good starting outline, but not yet strong enough for a full interview answer. [bytebytego](https://bytebytego.com/courses/system-design-interview/design-a-chat-system)

## What you got right

You correctly recognized that a chat system changes a lot when the product requires **real-time messaging**, and that WebSockets are a better fit than normal request-response for low-latency delivery. You also correctly brought in a **message queue** for asynchronous delivery and fanout, which is a common design choice once traffic or concurrency grows. [youtube](https://www.youtube.com/watch?v=rcc7DznaHVM)

You also understood that the system should avoid depending on a stateful app server for all message delivery logic. That is a good instinct, because in distributed chat systems, connection state is usually separated from durable message processing, often with a routing or presence layer rather than a single sticky application node being the only source of truth. [systemdesignhandbook](https://www.systemdesignhandbook.com/guides/design-a-chat-system/)

## Main issues

The biggest issue is that the answer does not clearly separate the system into **core flows**: connection management, send message flow, delivery flow, persistence, and offline retrieval. A chat app is not just “store in DB or add queue”; the interviewer expects you to explain how a message moves from sender to server to durable storage to recipient devices, and how ordering, retries, and offline delivery are handled. [educative](https://www.educative.io/blog/chat-system-design)

The second issue is the **data model**. Your schema is incomplete and slightly off: `CHAT_ROOM` should not directly hold one participant ID and one message ID like a current pointer unless you clearly define why; usually you have `CHAT_ROOM`, `CHAT_PARTICIPANT`, and `MESSAGE` tables, where messages belong to a room and participants belong to a room. Also, `CHAT_MESSAGE` should have at least `id`, `chat_room_id`, `sender_user_id`, `message_text`, `created_at`, and often a sequence number or server-side ordering key. [dev](https://dev.to/hexshift/implementing-message-persistence-in-real-time-chat-applications-18eo)

## Missing senior-level concerns

A strong answer for chat should explicitly mention:
- **Ordering**, usually per conversation or room. [ably](https://ably.com/blog/chat-architecture-reliable-message-ordering)
- **Online/offline delivery**, since recipients may be disconnected. [dev](https://dev.to/gaurav_bansal_2e1ca573623/designing-a-scalable-and-real-time-messaging-system-4664)
- **Acknowledgements and retries**, so you can distinguish sent, delivered, and read states. [dev](https://dev.to/hexshift/implementing-message-persistence-in-real-time-chat-applications-18eo)
- **Presence / connection registry**, meaning how you know which server currently owns a user’s WebSocket connection. [dev](https://dev.to/sgchris/building-a-chat-system-like-whatsapp-real-time-at-scale-1o2g)
- **Group chat fanout strategy**, since 1:1 and group chats scale differently. [coudo](https://www.coudo.ai/blog/designing-a-real-time-distributed-chat-application-a-step-by-step-guide-1)

Without those, the answer sounds more like a rough event-driven notification system than a full chat system. [systemdesignhandbook](https://www.systemdesignhandbook.com/guides/design-a-chat-system/)

## Better structure

A stronger senior answer would be:

“First I’d define scope: 1:1 chat, group chat, online/offline messaging, message history, and low-latency delivery. Clients maintain persistent WebSocket connections to chat gateway servers. When a user sends a message, the chat service authenticates the sender, assigns a server-side sequence number for the conversation, persists the message durably, publishes delivery tasks to a queue, and routes the message to recipients’ active connection servers using a connection registry. [youtube](https://www.youtube.com/watch?v=rcc7DznaHVM)

For storage, I’d keep normalized tables or collections such as `users`, `chat_rooms`, `chat_participants`, and `messages`, where `messages` contain `message_id`, `room_id`, `sender_id`, payload, timestamps, and ordering metadata. If recipients are online, their WebSocket server pushes the message immediately; if they are offline, the message remains in durable storage and is fetched or replayed on reconnect. [dev](https://dev.to/gaurav_bansal_2e1ca573623/designing-a-scalable-and-real-time-messaging-system-4664)

At scale, I’d separate real-time connection handling from durable message processing, use a queue like Kafka for reliable fanout, and maintain a distributed presence map from `user_id -> connection server`. I’d also design for at-least-once delivery with idempotent processing and sequence-based ordering per room.” [stackoverflow](https://stackoverflow.com/questions/61405784/how-to-build-a-scalable-realtime-chat-messaging-with-websocket)

## Specific corrections

A few concrete fixes to your current version:
- “If users are low, keep DB messaging; if more, add queue” is too binary. Even small chat apps need clear real-time delivery design if low latency is required; the queue is about decoupling and reliability, not just user count. [rst](https://www.rst.software/blog/chat-app-architecture)
- “Each message should be propagated to the database” should really be “each accepted message must be durably persisted and then delivered.” Durability and delivery are related but separate concerns. [systemdesignhandbook](https://www.systemdesignhandbook.com/guides/design-a-chat-system/)
- “Latency - eventual” is too vague. Chat systems aim for **low real-time delivery latency**, while some side-effects like analytics or push notifications can be eventually consistent. The actual message send/receive path should not be described casually as just “eventual.” [youtube](https://www.youtube.com/watch?v=rcc7DznaHVM)
- Polling is acceptable as fallback, but for a modern chat answer, **WebSocket should be your primary transport**. [rst](https://www.rst.software/blog/chat-app-architecture)

## Score

I’d score this version roughly:
- **Mid-level:** 6/10, because the main building blocks are visible. [coudo](https://www.coudo.ai/blog/designing-a-real-time-distributed-chat-application-a-step-by-step-guide-1)
- **Senior:** 4.5/10, because the answer is missing message ordering, delivery semantics, presence routing, and a cleaner data model. [educative](https://www.educative.io/blog/chat-system-design)

## Counter questions

If I were the interviewer, I would immediately ask:
- How do you guarantee message ordering inside one chat room? [ably](https://ably.com/blog/chat-architecture-reliable-message-ordering)
- How do you know which server currently holds User B’s WebSocket connection? [stackoverflow](https://stackoverflow.com/questions/61405784/how-to-build-a-scalable-realtime-chat-messaging-with-websocket)
- What happens if the recipient is offline? [dev](https://dev.to/gaurav_bansal_2e1ca573623/designing-a-scalable-and-real-time-messaging-system-4664)
- How do you support multi-device sync for the same user? [stackoverflow](https://stackoverflow.com/questions/61405784/how-to-build-a-scalable-realtime-chat-messaging-with-websocket)
- How do you distinguish sent, delivered, and read receipts? [dev](https://dev.to/hexshift/implementing-message-persistence-in-real-time-chat-applications-18eo)
- How does group fanout work for a room with 10,000 members? [stackoverflow](https://stackoverflow.com/questions/68693285/system-design-question-ordered-message-delivery-in-a-messaging-app)

Your direction is good, but now you need to turn it into a **proper message lifecycle answer**. Send me your next version for chat system design, and I’ll review it as strictly as I did for URL shortener.