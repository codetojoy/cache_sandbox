
package com.acme.flagship.cache;

import com.acme.flagship.dao.*;

import com.google.common.cache.*;
import com.google.common.*;

public class AccountCacheLoader extends CacheLoader<String,Account> {
    private AccountDao accountDao;
    
    public AccountCacheLoader(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account load(String username) {
        Account account = accountDao.findAccountByUsername(username);
        return account;
    }
}
