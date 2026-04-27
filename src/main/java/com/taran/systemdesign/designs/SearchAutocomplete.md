Design a **search autocomplete / dictionary** system.[^10][^12]


# Search Autocomplete Dictionary Design

This note is a simple and interview-friendly write-up for designing a **Search Autocomplete Dictionary** system, also called a **typeahead system**. The main idea is to return useful suggestions as the user types a prefix such as `app`, `iph`, or `bea`.[1][2][3]

## Goal

The goal of autocomplete is to show the top few suggestions for a prefix quickly, usually while the user is still typing. The system should be low-latency, highly available, and able to rank suggestions by popularity, recency, and sometimes personalization.[2][4][5]

Examples:

- `app` -> `apple`, `application`, `apply`
- `iph` -> `iphone 15`, `iphone charger`
- `bea` -> `beach`, `beautiful`, `beats headphones`

## Functional Requirements

- Return top `k` suggestions for a typed prefix.[3][6]
- Update suggestions after every few typed characters.[4][7]
- Rank results by frequency or other signals.[2][8]
- Support updates as search behavior changes over time.[9][8]

## Non-Functional Requirements

- Very low latency, often under 100 ms.[2][5]
- High read throughput because each keystroke can trigger a request.[1][7]
- High availability because autocomplete is part of the main search experience.[9]
- Scalable storage and serving strategy.[1][10]

## Core Idea

The best core data structure for autocomplete is usually a **Trie**, also called a **prefix tree**. A Trie stores characters level by level, so all words with the same prefix share the same path.[1][11][2]

This makes prefix lookup efficient because the system walks only the characters of the typed prefix instead of scanning all words in the dictionary.[1][11] To make reads even faster, each Trie node stores the **top `k` suggestions** for that prefix.[6][8]

## Trie Example

Suppose the dictionary contains:

- `app` with score 80
- `apple` with score 100
- `application` with score 60
- `apply` with score 40
- `ape` with score 20

The Trie would roughly look like this:

```text
root
 └── a
      └── p
           ├── p   -> top suggestions: [apple, app, application, apply]
           │    ├── l
           │    │    ├── e -> apple
           │    │    ├── i -> application
           │    │    └── y -> apply
           └── e   -> ape
```

If the user types `app`, the service walks three characters in the Trie and returns the cached top suggestions from that node.[6][8]

## High-Level Architecture

A simple architecture looks like this:

```text
Client
  -> Load Balancer / API Gateway
  -> Autocomplete Service
  -> In-memory Trie / Suggestion Engine
  -> Persistent Store + Update Pipeline
```

### Main components

- **Client**: sends the typed prefix.[5][7]
- **Load Balancer / API Gateway**: routes requests to autocomplete servers.[12][9]
- **Autocomplete Service**: serves suggestions from cache or Trie.[1][8]
- **In-memory Trie**: stores prefixes and top suggestions.[1][6]
- **Persistent store**: stores raw queries, scores, and logs.[2][9]
- **Update pipeline**: refreshes rankings from search logs and trends.[9][8]

## Request Flow

When a user types `app`, the flow is:

1. The browser waits for a short debounce period, often around 100 to 200 ms, before sending a request.[5][7]
2. The API receives the prefix `app`.[12]
3. The service checks cache for that prefix.[12][9]
4. If cache hits, return suggestions immediately.[12]
5. If cache misses, walk the Trie for `a -> p -> p`.[1][6]
6. Return the node's top suggestions.[6][8]
7. Optionally store the result back in cache.[9]

This is fast because the service avoids recalculating suggestions from the full dataset for each request.[6][8]

## Ranking Strategy

Suggestions should be ranked, not just alphabetically sorted. A practical design ranks suggestions using a combination of:

- **frequency**: how often the query is searched,[2][6]
- **recency**: whether the query is trending right now,[9][8]
- **personalization**: optional signals such as user history or region.[4][8]

A simple way to explain this is:

$$
score = \alpha \cdot frequency + \beta \cdot recency + \gamma \cdot personalization
$$

In interviews, the exact formula is less important than showing that suggestions are ranked using multiple signals and the top results are precomputed.[8]

## Data Model

The persistent store does not have to be the serving layer. A simple source-of-truth model might store:

```sql
query_stats(
  query_text,
  frequency,
  last_searched_at,
  language,
  region
)
```

This data can be used to build or refresh the Trie periodically.[2][9]

## Update Pipeline

Autocomplete suggestions should evolve as user behavior changes. A common design uses two update paths:

### Batch updates

A scheduled job aggregates historical search logs and rebuilds or updates the Trie periodically.[9][8]

This is useful for:

- stable long-term rankings,
- removing stale suggestions,
- rebuilding the top `k` lists for nodes.[9][8]

### Streaming updates

A streaming pipeline updates trending queries more quickly so sudden popular searches appear in autocomplete without waiting for a full rebuild.[9]

This is useful for:

- trending news queries,
- breaking events,
- fast-changing product demand.[9][4]

## Caching

Caching is very important because the same prefixes are requested repeatedly. Popular prefixes such as `a`, `ap`, `iph`, and `sam` appear again and again.[12][9]

A practical cache strategy is:

- **local in-memory cache** on each server for hottest prefixes,[9]
- **Redis cache** for shared prefix responses across servers,[9]
- optional **edge caching** for extremely hot public prefixes.[9]

## Scaling Strategy

For smaller systems, the entire Trie can be loaded into memory on every autocomplete server. This is simple and gives very fast reads.[10]

For larger systems, the Trie can be partitioned across servers.

### Prefix-based sharding

A common strategy is to shard by prefix range, for example:

- Server 1: `a-f`
- Server 2: `g-l`
- Server 3: `m-r`
- Server 4: `s-z`

This works well because a prefix query usually maps to one shard.[10][13]

### Why hash sharding is weaker

Hash-based partitioning is not ideal for autocomplete because prefixes that should live together may be scattered across many shards, which would force multi-shard reads and aggregation.[13]

## Storage vs Serving

A good point to mention in interviews is that the system often separates the **source of truth** from the **serving layer**.

- The database or log store keeps raw query frequency data.[2][9]
- The Trie is the optimized read structure used for low-latency serving.[1][6]

This separation keeps writes flexible and reads fast.[2][6]

## Trade-Offs

| Choice | Benefit | Cost |
|---|---|---|
| Trie | Fast prefix lookup [1][11] | Uses more memory [6] |
| Top `k` per node | Very fast reads [6][8] | Extra storage and update cost [8] |
| Full replication | Simple serving model [10] | Memory duplication across servers [10] |
| Prefix sharding | Scales reads better [13] | Can create hotspots on common prefixes [10] |
| Streaming ranking updates | Better freshness [9] | More operational complexity [9] |

## Example Interview Answer

A short interview-ready answer is:

> Design search autocomplete as a low-latency typeahead service. Use a Trie as the core data structure because it supports efficient prefix lookup. Store the top `k` ranked suggestions at each Trie node so reads are very fast. Keep the Trie in memory for serving, and use search logs as the source of truth for ranking updates. Add Redis caching for hot prefixes and refresh rankings through batch jobs plus streaming updates for trending queries. For scale, replicate the Trie for small systems or shard by prefix range for larger ones.[1][6][9][8][13]

## Common Mistakes

- Using only a relational database query like `WHERE word LIKE 'app%'` as the main serving design at scale.[2][6]
- Returning alphabetical results instead of ranked suggestions.[2][4]
- Ignoring update pipelines and assuming ranking never changes.[9][8]
- Using hash sharding without considering prefix locality.[13]
- Forgetting debounce on the client and overloading the backend with keystroke traffic.[5][7]

## Mental Model to Remember

When solving this problem, think in this order:

1. **What prefix is the user typing?**
2. **How can prefix lookup be fast?** -> Trie.[1][11]
3. **How can results be returned instantly?** -> Top `k` suggestions at each node.[6][8]
4. **How are suggestions ranked?** -> frequency, recency, personalization.[2][8]
5. **How are rankings updated?** -> batch + streaming pipeline.[9][8]
6. **How does the system scale?** -> cache, replication, prefix sharding.[10][9][13]

If these six points are covered, the answer usually sounds complete and interview-ready.[1][9][8]
EOF && ls -l output/search-autocomplete-system-design.md