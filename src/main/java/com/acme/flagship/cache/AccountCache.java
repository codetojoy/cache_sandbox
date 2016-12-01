
package com.acme.flagship.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.*;

import com.acme.flagship.dao.*;

class AccountRemovalListener implements RemovalListener<String,Account> {
    @Override
    public void onRemoval(RemovalNotification<String,Account> notification) {
        System.out.println("TRACER removing: " + notification.getKey());
    }
}

public class AccountCache {
    private LoadingCache<String, Account> cache;
    private CacheStatsLogger cacheStatsLogger = new CacheStatsLogger("Account");    

    public AccountCache(AccountDao accountDao) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(500)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .recordStats()
                // .removalListener(new AccountRemovalListener()) 
                .build(new AccountCacheLoader(accountDao));
    }

    public CacheStats getStats() {
        return cache.stats();
    }

    public Account findAccountByUsername(String username) {
        Account account = null;

        try {
            account = cache.get(username);
        } catch(Exception ex) {
            System.err.println("ERROR: caught exception");
        }

        cacheStatsLogger.fetch(cache.stats());

        return account;
    }
}
