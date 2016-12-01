
package com.acme.flagship.cache;

import com.google.common.cache.CacheStats;

public class CacheStatsLogger {
    private int fetchCount = 1;
    private static final int LOG_FREQUENCY = 10;

    private String name;

    public CacheStatsLogger(String name) {
        this.name = name;
    } 

    public void fetch(CacheStats cacheStats) {
        fetchCount++;

        if (fetchCount % LOG_FREQUENCY == 0) {
            String hitRateStr = String.format("%.2f", cacheStats.hitRate());
            StringBuilder buffer = new StringBuilder();
            buffer.append(name);
            buffer.append(" hits: " + cacheStats.hitCount()); 
            buffer.append(" misses: " + cacheStats.missCount()); 
            buffer.append(" hitRate: " + hitRateStr);
            buffer.append(" requestCount: " + cacheStats.requestCount());
            System.out.println("TRACER STATS " + buffer.toString());

            if (fetchCount > (Long.MAX_VALUE - 5000)) {
                fetchCount = 1;
            }
        }
    }
}
