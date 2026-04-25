package com.taran.systemdesign;

import java.time.Clock;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter {
    private final int capacity;
    private final int tokensPerPeriod;
    private final long periodMillis;
    private final Clock clock;
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(int capacity, int tokensPerPeriod, Duration period, Clock clock) {
        this.capacity = capacity;
        this.tokensPerPeriod = tokensPerPeriod;
        this.periodMillis = period.toMillis();
        this.clock = clock;
    }

    public boolean allow(String key) {
        // Create or get existing bucket for the key
        Bucket bucket = buckets.computeIfAbsent(
            key,
            k -> new Bucket(clock.millis(), capacity)
        );

        synchronized (bucket) {
            refill(bucket);
            if (bucket.tokens <= 0) {
                return false;
            }
            bucket.tokens--;
            return true;
        }
    }

    /**
     * Refills the bucket based on the elapsed time since the last refill. Find the last time request was made 
     * and calculate how many tokens to add based on the elapsed time and tokensPerPeriod. 
     * Update the lastRefillTime accordingly.
    */
    private void refill(Bucket bucket) {
        long now = clock.millis();
        long elapsed = now - bucket.lastRefillTime;
        if (elapsed <= 0) return;

        long periodsElapsed = elapsed / periodMillis;
        if (periodsElapsed > 0) {
            long tokensToAdd = periodsElapsed * tokensPerPeriod;
            bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
            bucket.lastRefillTime += periodsElapsed * periodMillis;
        }
    }

    private static class Bucket {
        long lastRefillTime;
        long tokens;

        Bucket(long lastRefillTime, long tokens) {
            this.lastRefillTime = lastRefillTime;
            this.tokens = tokens;
        }
    }

     public static void main(String[] args) {
        TokenBucketRateLimiter limiter =
            new TokenBucketRateLimiter(10, 10, Duration.ofSeconds(1), Clock.systemUTC());

        String userId = "user-123";

        for (int i = 0; i < 15; i++) {
            boolean allowed = limiter.allow(userId);
            System.out.println("Request " + i + ": " + allowed);
        }
    }
}