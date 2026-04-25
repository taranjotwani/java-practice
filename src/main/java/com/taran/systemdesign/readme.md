
Interview guides for mid-level and non-FAANG roles emphasize fundamentals like scalability, caching, load balancing, databases, queues, APIs, and trade-offs more than exotic architecture topics.[^1][^2][^3]

## Study list

1. **System design interview framework**
    - Requirements gathering.
    - High-level design.
    - Deep dive on components.
    - Trade-offs and bottlenecks.
    - Failure scenarios and scalability.[^4][^3]
2. **Scalability basics**
    - Vertical vs horizontal scaling.
    - Bottlenecks.
    - Throughput vs latency.
    - Read-heavy vs write-heavy systems.[^2][^5]
3. **Load balancing**
    - Reverse proxy vs load balancer.
    - Round robin, least connections, sticky sessions.
    - Health checks and failover.[^6][^2]
4. **Caching**
    - Browser, CDN, application, and distributed cache.
    - Cache invalidation.
    - Cache-aside pattern.
    - TTL and eviction strategies.[^5][^7]
5. **Databases**
    - SQL vs NoSQL.
    - Indexing.
    - Normalization vs denormalization.
    - Transactions and isolation basics.[^7][^5][^6]
6. **Sharding and replication**
    - Partitioning data.
    - Replica sets.
    - Consistent hashing.
    - Read replicas and write scaling.[^8][^5][^7]
7. **APIs and API design**
    - REST basics.
    - Request/response design.
    - Pagination, filtering, versioning.
    - Idempotency and rate limiting.[^8][^7]
8. **Message queues and async processing**
    - Kafka / RabbitMQ / SQS-style patterns.
    - Retry handling.
    - Dead-letter queues.
    - Decoupling services.[^9][^5][^7]
9. **Authentication and security**
    - Session vs token auth.
    - JWT basics.
    - Authorization.
    - Rate limiting and abuse prevention.[^1][^7]
10. **Microservices vs monolith**

- When to split.
- Service boundaries.
- Communication between services.
- Operational complexity.[^3][^6]

11. **CDN and static asset delivery**

- Edge caching.
- Static content distribution.
- Invalidations and latency reduction.[^10][^6]

12. **Reliability and fault tolerance**

- Timeouts.
- Retries.
- Circuit breakers.
- Graceful degradation.
- Failover.[^7][^1]

13. **Concurrency and consistency**

- Race conditions.
- Eventual consistency.
- Strong consistency.
- CAP theorem basics.[^5][^7]

14. **Logging, monitoring, and observability**

- Metrics, logs, tracing.
- Alerting.
- Debugging production issues.
- Capacity planning.[^3][^1]

15. **Common design patterns**

- Rate limiter.
- Notification system.
- File upload/storage system.
- URL shortener.
- Feed/timeline system.
- Search/autocomplete system.[^11][^12][^10]


## What to prioritize

For your situation, I would prioritize these **first**:

- API design.
- Caching.
- Databases and indexing.
- Load balancing.
- Queues and async processing.
- Authentication/security.
- Microservices vs monolith.[^2][^3][^7]

<div align="center">⁂</div>

[^1]: https://www.tryexponent.com/blog/system-design-interview-guide

[^2]: https://www.designgurus.io/answers/detail/system-design-interview-tips-for-mid-level-software-engineers

[^3]: https://www.designgurus.io/blog/system-design-interview-questions

[^4]: https://bytebytego.com/courses/system-design-interview/a-framework-for-system-design-interviews

[^5]: https://www.designgurus.io/blog/system-design-interview-fundamentals

[^6]: https://www.geeksforgeeks.org/system-design/top-10-system-design-interview-questions-and-answers/

[^7]: https://interviewing.io/guides/system-design-interview/part-two

[^8]: https://www.hellointerview.com/learn/system-design/in-a-hurry/core-concepts

[^9]: https://www.youtube.com/watch?v=Ru54dxzCyD0

[^10]: https://www.shadecoder.com/blogs/system-design-interview-hub

[^11]: https://www.systemdesignhandbook.com/guides/system-design-interview-questions/

[^12]: https://dev.to/somadevtoo/top-50-system-design-interview-questions-for-2024-5dbk

[^13]: https://www.interviewbit.com/system-design-interview-questions/

[^14]: https://www.reddit.com/r/cscareerquestionsEU/comments/uq5zv2/system_design_interview_for_non_faang/

[^15]: https://www.reddit.com/r/leetcode/comments/1j9a8u6/45_system_design_questions_i_curated_for/
