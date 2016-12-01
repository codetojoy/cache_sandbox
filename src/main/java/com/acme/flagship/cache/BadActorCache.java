
package com.acme.flagship.cache;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.cache.*;

public class BadActorCache {
    private Cache<String, AtomicInteger> cache;
    private int threshold;

    private CacheStatsLogger cacheStatsLogger = new CacheStatsLogger("BadActor");
 
    public BadActorCache() {
        threshold = 10;
        cache = CacheBuilder.newBuilder()
                .maximumSize(50)
                .expireAfterAccess(20, TimeUnit.SECONDS)
                .recordStats()
                .build();
    }

    public boolean isRequestFromBadActor(String actorName) {
        boolean result = false;

        try {
            AtomicInteger count = cache.getIfPresent(actorName);

            if (count != null) {
                int value = count.getAndIncrement();

                if (value >= threshold) {
                    result = true;
                } 

                System.out.println("TRACER BAD CACHE " + actorName + " " + value + " " + result);
            } else {
                count = new AtomicInteger(1);
                cache.put(actorName, count);
                System.out.println("TRACER BAD CACHE NEW actor: " + actorName);
            }

            cacheStatsLogger.fetch(cache.stats());
        } catch(Exception ex) {
            System.err.println("ERROR: caught exception");
        }

        return result;
    }
}
