
package com.acme.flagship.cache;

import com.google.common.cache.*;

import com.acme.flagship.dao.*;

public class AccountCache {
    private LoadingCache<String, Account> cache;
    
    public AccountCache(AccountDao accountDao) {
        cache = CacheBuilder.newBuilder()
                .build(new AccountCacheLoader(accountDao));
    }

    public Account findAccountByUsername(String username) {
        Account account = null;

        try {
            account = cache.get(username);
        } catch(Exception ex) {
            System.err.println("ERROR: caught exception");
        }

        return account;
    }
}
