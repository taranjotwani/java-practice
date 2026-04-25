A good system design answer for a rate limiter starts by defining **who** is being limited, **what** the limit is, and **where** enforcement happens; then you pick an algorithm, usually **Token Bucket** for burst-friendly APIs or **Sliding Window** for stricter fairness. In distributed systems, a common production design is stateless gateway instances sharing limiter state in Redis, with atomic updates to avoid race conditions. [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter)
## Requirements
A rate limiter controls request volume for a client identity such as **user ID, API key, tenant, or IP**, and usually enforces policies like “100 requests per minute” or “10 requests per second with burst 20.” It should reject over-limit traffic with **HTTP 429**, and many implementations also return headers like remaining quota and reset time so clients can back off intelligently. [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter)

Key non-functional goals are low latency, horizontal scalability, high availability, and predictable behavior across many service instances. In interviews, it also helps to mention policy flexibility, because real systems often need different limits for free users, paid plans, admin APIs, and sensitive endpoints like login. [arxiv](https://arxiv.org/html/2602.11741)
## Placement
The most common place to put a rate limiter is at the **API gateway or middleware layer**, before requests hit the core application servers. That lets you protect backend capacity early and apply consistent policies across all services. [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter)

You can also enforce limits inside individual services for endpoint-specific protection, but for a top-level interview answer, gateway-level enforcement is usually the cleanest default. In a distributed setup, all gateway instances must consult shared state, otherwise a client can bypass limits by sending requests to different servers. [youtube](https://www.youtube.com/watch?v=SgWb6tWx3S8)
## Algorithms
You should know the main choices:
- **Fixed window**: simple and cheap, but has boundary bursts. [arxiv](https://arxiv.org/html/2602.11741)
- **Sliding window log/counter**: fairer and more accurate, but more memory or complexity. [arxiv](https://arxiv.org/html/2602.11741)
- **Token bucket**: allows short bursts while enforcing a long-term average, and is a very common production choice. [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter)
- **Leaky bucket**: smooths traffic output and is useful when burstiness should be suppressed. [reddit](https://www.reddit.com/r/softwarearchitecture/comments/1s7i2pr/rate_limiting_system_design_algorithms_tradeoffs/)

For most API system design interviews, I’d choose **Token Bucket** first because it is simple, memory-efficient, and handles burst traffic naturally. If the interviewer wants stricter fairness over an exact rolling interval, I’d move to a sliding-window design. [dev](https://dev.to/middleware/overcoming-hard-rate-limits-efficient-rate-limiting-with-token-bucketing-and-redis-4gcb)
## Design
At a high level, the architecture is: client -> load balancer / API gateway -> rate limiter logic -> backend services, with Redis storing shared rate-limit state. Each incoming request is mapped to a key such as `ratelimit:user:123` or `ratelimit:apikey:abc:/search`, and the limiter checks whether the request should be allowed before forwarding it downstream. [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter)

With **Token Bucket**, each key stores:
- `capacity`: maximum burst allowed.
- `refill_rate`: tokens added per second or minute.
- `tokens`: current tokens remaining.
- `last_refill_time`: timestamp used to compute replenishment. [dev](https://dev.to/middleware/overcoming-hard-rate-limits-efficient-rate-limiting-with-token-bucketing-and-redis-4gcb)

For every request, the limiter:
1. Loads the bucket state for the key. [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter)
2. Calculates how many tokens should have been refilled since the last request. [dev](https://dev.to/middleware/overcoming-hard-rate-limits-efficient-rate-limiting-with-token-bucketing-and-redis-4gcb)
3. Caps tokens at the bucket capacity. [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter)
4. If at least one token is available, consumes it and allows the request. [dev](https://dev.to/middleware/overcoming-hard-rate-limits-efficient-rate-limiting-with-token-bucketing-and-redis-4gcb)
5. Otherwise rejects with 429. [tryexponent](https://www.tryexponent.com/blog/rate-limiter-system-design)

In a real distributed system, that read-calculate-update sequence must be **atomic**, because multiple gateway nodes may process requests for the same client at the same time. Redis is a popular choice here because it is fast and supports atomic operations, and many production-grade designs use **Lua scripts** so the entire check-and-update logic runs as one atomic server-side operation. [arxiv](https://arxiv.org/html/2602.11741)
## Redis concerns
Redis works well because limiter state is small, frequently updated, and shared across many app instances. It also supports expiration, so inactive buckets can be cleaned up automatically with TTL rather than requiring a heavy cleanup process. [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter)

If Redis becomes unavailable, you need a fallback policy. For low-risk APIs, teams may choose **fail-open**, allowing requests temporarily to preserve availability; for sensitive endpoints like login or payment abuse protection, teams may choose **fail-closed** so protection remains strict. The right choice depends on product risk, and calling out that tradeoff is a strong senior-level signal. [arxiv](https://arxiv.org/html/2602.11741)
## Scaling
Because gateway instances are stateless, they scale horizontally behind a load balancer, while Redis acts as the shared source of limiter state. This architecture keeps enforcement consistent even when traffic is distributed across many nodes. [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter)

At higher scale, you also need to think about **hot keys**, because one abusive tenant or IP can hammer the same Redis key repeatedly. Common mitigations include sharding Redis, using efficient key design, and limiting the number of dimensions in the limiter key so you do not explode cardinality. [arxiv](https://arxiv.org/html/2602.11741)
## Interview answer
A compact senior-style answer would be:

“Rate limiter is a control system that limits how many requests a client such as a user, IP, or API key can make in a time window. I’d usually place it at the API gateway so requests are filtered before they hit backend services, and I’d store shared limiter state in Redis so enforcement remains consistent across multiple gateway instances. [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter)

For the algorithm, I’d choose Token Bucket by default because it supports bursts while keeping the long-term request rate bounded. Each key would store capacity, refill rate, current tokens, and last refill timestamp; on every request, I’d atomically refill tokens based on elapsed time, consume one token if available, and reject with HTTP 429 otherwise. [dev](https://dev.to/middleware/overcoming-hard-rate-limits-efficient-rate-limiting-with-token-bucketing-and-redis-4gcb)

To avoid race conditions in distributed environments, I’d do the whole decision in Redis using a Lua script or another atomic mechanism. I’d also define fail-open versus fail-closed behavior for Redis outages depending on endpoint sensitivity, and for stricter fairness requirements I’d switch to a sliding-window design.” [arxiv](https://arxiv.org/html/2602.11741)

Would you like the **Java implementation** next, either as simple in-memory Token Bucket or as a Redis-backed version?