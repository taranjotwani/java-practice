Here’s a **complete senior-level interview answer** for designing a URL shortener. It is structured the way you should actually say it in an interview: clarify scope, define APIs, explain architecture, discuss scaling, then call out tradeoffs.

## Requirements

“I’ll start by clarifying scope. The core functional requirements are: users can submit a long URL and get a shortened URL back, users can hit the short URL and get redirected to the original URL, and optionally we may support custom aliases, expiration dates, and click analytics. The key non-functional requirements are very low latency on redirects, high availability, and the ability to scale read traffic much more than write traffic.” [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/bitly)

“For this discussion, I’ll optimize first for the two core flows: create short URL and redirect short URL. I’ll mention analytics, abuse prevention, and custom aliases as extensions once the base system is solid.” [systemdesignhandbook](https://www.systemdesignhandbook.com/guides/design-bitly/)

## APIs

“I’d expose two main APIs. First, `POST /urls` to create a short URL, with a request body like `long_url`, optional `custom_alias`, and optional `expiration_date`, and it returns the final short URL. Second, `GET /{short_code}` which resolves the short code and returns an HTTP redirect, usually `302` by default.” [systemdesignschool](https://systemdesignschool.io/problems/url-shortener/solution)

“I prefer `302` over `301` for most shortener products because I want the server to keep seeing requests for analytics, expiry checks, abuse control, and future destination changes. I’d only use `301` if the redirect is truly permanent and I am okay with browser and SEO caching behavior.” [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/bitly)

## Capacity

“At a high level, this is a read-heavy system. URL creation traffic is usually much lower than redirect traffic, so the redirect path is the hot path and should be heavily optimized with caching and simple key-based lookups.” [algocademy](https://algocademy.com/blog/how-to-design-a-url-shortener-in-a-system-design-interview/)

“If I had to estimate, I’d assume many more reads than writes and design the system so that writes go safely to durable storage while reads are mostly served from cache. That assumption drives most of the architecture decisions.” [geeksforgeeks](https://www.geeksforgeeks.org/system-design/system-design-url-shortening-service/)

## Data model

“The main entity is straightforward: a mapping from `short_code` to `long_url`. A basic table would have `short_code`, `long_url`, `created_at`, optional `expires_at`, optional `user_id`, and maybe flags like `is_custom` or `is_active`, with `short_code` as the primary key or at least a unique indexed column because redirects depend on fast point lookups.” [systemdesignhandbook](https://www.systemdesignhandbook.com/guides/design-bitly/)

“If analytics are needed, I would not keep detailed click events in the same transactional table. I’d emit redirect events asynchronously into a separate analytics pipeline or event log so the redirect flow remains lightweight.” [algocademy](https://algocademy.com/blog/how-to-design-a-url-shortener-in-a-system-design-interview/)

## High-level design

“At a high level, I’d separate the system into a write path and a read path. The write path handles short URL creation, validation, and persistence. The read path handles redirect resolution and is optimized for very low latency.” [algocademy](https://algocademy.com/blog/how-to-design-a-url-shortener-in-a-system-design-interview/)

“The main components would be: load balancer or API gateway, stateless application servers, Redis as a cache for `short_code -> long_url`, PostgreSQL or another durable store for mappings, and an async analytics pipeline for click tracking. If traffic is very high globally, I’d also add a CDN or edge layer for the hottest redirects.” [dev](https://dev.to/sgchris/designing-url-shortener-systems-from-tinyurl-to-bitly-scale-1ip5)

## Write flow

“For the create flow, the client calls `POST /urls` with the long URL. The service validates the URL, checks optional custom alias constraints, generates a short code, stores the mapping in the database, and returns the final short URL.” [dev](https://dev.to/arslan_ah/system-design-interview-question-designing-a-url-shortening-service-4029)

“For custom aliases, I’d first verify that the alias is available and reserve it under a unique constraint. I’d also ensure custom aliases do not collide with system-generated codes, either by namespace separation or by validation rules.” [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/bitly)

## Short-code generation

“This is the core design choice. There are two common approaches: hash-based generation and counter-based generation. Hashing the long URL and truncating the result is easy to explain, but it introduces collision risk and needs collision handling. For a senior-level answer, I usually prefer a globally unique numeric ID plus Base62 encoding because it is simple, compact, and collision-free by construction.” [techinterview](https://www.techinterview.org/post/3233474158/system-design-url-shortener-tinyurl-bitly-base62-encoding-key-generation-analytics-redirection-caching-database/)

“So my preferred design is: generate a unique numeric ID using a DB sequence, Redis atomic counter, or distributed ID generator, then Base62-encode that number into a compact string such as 6–8 characters. Base62 is just the encoding format; the actual uniqueness comes from the ID source.” [youtube](https://www.youtube.com/watch?v=kuoGvLTWXLI)

“If I were forced to use a hash-based strategy, I would rely on a unique DB constraint on `short_code` and retry with a new salt or nonce on collision. But my primary answer in an interview would be counter-based ID generation plus Base62.” [dev](https://dev.to/sgchris/designing-url-shortener-systems-from-tinyurl-to-bitly-scale-1ip5)

## Redirect flow

“For the redirect flow, the browser hits `GET /{short_code}`. The application first checks Redis with a key like `url:{short_code}`. If Redis hits, it immediately returns a `302` with the long URL in the `Location` header.” [systemdesignhandbook](https://www.systemdesignhandbook.com/guides/design-bitly/)

“If Redis misses, the service reads the mapping from the database, checks whether the URL exists and has not expired, writes it back into Redis with a TTL, and then returns the redirect. If the URL is expired, I’d return `410 Gone`; if it does not exist, I’d return `404`.” [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/bitly)

## Redis strategy

“Redis is purely an acceleration layer, not the source of truth. I’d use cache-aside on reads and optionally write-through on create so new mappings are immediately hot in cache.” [oneuptime](https://oneuptime.com/blog/post/2026-03-31-redis-how-to-design-a-url-shortener-using-redis-i/view)

“If Redis goes down or loses cache, the service should still function by falling back to the database, just with higher latency and DB load. That means the DB must remain authoritative, and Redis failure should be a degraded mode, not a system outage.” [leapcell](https://leapcell.io/blog/mastering-redis-cache-invalidation-strategies)

“For invalidation, I’d primarily use TTLs and explicit deletes on updates or deletes. I would not rely on cron for normal cache invalidation of redirect mappings; TTL and event-driven invalidation are simpler and more reliable for this use case.” [redis](https://redis.io/glossary/cache-invalidation/)

## Database choice

“I’m comfortable starting with PostgreSQL because the core access pattern is simple, and the write rate for URL creation is often manageable for a relational database at moderate scale. With the right primary key, indexing, replication, and caching, Postgres can serve as a solid first durable store.” [algocademy](https://algocademy.com/blog/how-to-design-a-url-shortener-in-a-system-design-interview/)

“If scale grows to very large levels, I can move toward sharded storage or a distributed key-value store. But I would not overcomplicate the initial design unless the estimated traffic clearly requires it.” [geeksforgeeks](https://www.geeksforgeeks.org/system-design/system-design-url-shortening-service/)

## Scaling

“For horizontal scaling, all application servers are stateless, so they can scale behind a load balancer. The read path and write path can also be split into separate services because their traffic patterns are different.” [systemdesignhandbook](https://www.systemdesignhandbook.com/guides/design-bitly/)

“For the database, if one instance is not enough, I’d first add read replicas where appropriate, though redirect lookups should ideally be served from Redis most of the time. Beyond that, I’d shard by a stable key such as `short_code` hash or `owner_id`, and use an application routing layer or shard map to locate the correct shard.” [dev](https://dev.to/sgchris/designing-url-shortener-systems-from-tinyurl-to-bitly-scale-1ip5)

“If I use naive modulo sharding, adding shards later causes lots of rebalancing. So at higher scale I’d consider consistent hashing or a directory-based routing layer to reduce movement during resharding.” [dev](https://dev.to/sgchris/designing-url-shortener-systems-from-tinyurl-to-bitly-scale-1ip5)

## Reliability

“I’d make the service highly available using multiple app instances, Redis replication or cluster mode, and a replicated primary database setup with backups. Because redirects are business-critical, graceful degradation matters: if cache is unavailable, we still serve from DB; if analytics pipeline is unavailable, redirect should still work and analytics can be dropped or buffered.” [geeksforgeeks](https://www.geeksforgeeks.org/system-design/system-design-url-shortening-service/)

“I’d also add timeouts, retries with limits, circuit breaking around dependencies, and monitoring on latency, cache hit rate, DB saturation, and error rate. Those operational signals are crucial in a read-heavy product like this.” [algocademy](https://algocademy.com/blog/how-to-design-a-url-shortener-in-a-system-design-interview/)

## Security

“Public URL shorteners attract abuse, so I would validate URLs, rate-limit creation APIs, block known malicious domains, and possibly require auth or CAPTCHA for anonymous users at scale. This is not an optional afterthought because spam and phishing are common in shortener systems.” [geeksforgeeks](https://www.geeksforgeeks.org/system-design/system-design-url-shortening-service/)

“I’d also canonicalize URLs carefully so equivalent inputs can be handled consistently if the product wants deduplication. That decision depends on whether we want the same long URL to always return the same short code or allow multiple short codes per destination.” [systemdesignhandbook](https://www.systemdesignhandbook.com/guides/design-bitly/)

## Tradeoffs

“The biggest tradeoff is ID generation. Counter-based ID generation with Base62 is simpler and avoids collisions, but it may produce predictable patterns unless I obfuscate or randomize the visible code space. Hash-based generation can feel more opaque, but it introduces collisions and retry logic.” [techinterview](https://www.techinterview.org/post/3233474158/system-design-url-shortener-tinyurl-bitly-base62-encoding-key-generation-analytics-redirection-caching-database/)

“Another important tradeoff is `302` vs `301`. I choose `302` by default for flexibility, analytics, and server-side control. I only move to `301` when the mapping is truly permanent and the SEO/caching benefits are more important than operational control.” [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/bitly)

## Extensions

“If I had more time, I’d add custom aliases, expiration, analytics dashboards, bot filtering, spam detection, and possibly CDN edge caching for the hottest links. I’d also consider multi-region deployment if the product is global and redirect latency matters internationally.” [hellointerview](https://www.hellointerview.com/learn/system-design/problem-breakdowns/bitly)

## Final spoken answer

If you want a compact version you can **say in an interview**, use this:

“URL shortener is mainly a read-heavy key-value system where `short_code` maps to a long URL. I’d expose `POST /urls` to create short links and `GET /{short_code}` to return an HTTP `302` redirect. I’d use stateless app servers behind a load balancer, PostgreSQL as the durable source of truth, Redis as a cache for the hot redirect path, and an async analytics pipeline for click tracking. [algocademy](https://algocademy.com/blog/how-to-design-a-url-shortener-in-a-system-design-interview/)

For short-code generation, I’d prefer a globally unique numeric ID from a sequence or distributed ID generator, then Base62-encode it into a compact code. That avoids collision complexity and keeps lookups simple. On redirects, I’d do cache-first lookup in Redis, fall back to the DB on miss, repopulate Redis with TTL, validate expiry, and return the redirect. [techinterview](https://www.techinterview.org/post/3233474158/system-design-url-shortener-tinyurl-bitly-base62-encoding-key-generation-analytics-redirection-caching-database/)

As the system grows, I’d scale stateless services horizontally, separate read and write services, shard the DB by `short_code` hash or tenant ID if needed, and keep Redis optional so DB fallback still works if cache fails. I’d also add rate limiting, malicious URL checks, and explicit cache invalidation for updates, because public shorteners are abuse-prone and highly read-sensitive.” [dev](https://dev.to/sgchris/designing-url-shortener-systems-from-tinyurl-to-bitly-scale-1ip5)

Paste your spoken version next, and I’ll turn it into a **mock interviewer evaluation** with strengths, misses, and likely follow-up questions.