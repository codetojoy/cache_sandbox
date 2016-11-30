
package com.acme.flagship.actor;

import com.acme.flagship.cache.*;
import com.acme.flagship.dao.*;

public class DaoAction extends Action {
    private BadActorCache badActorCache;
    private AccountDao accountDao;
    private AccountCache accountCache;

    public DaoAction(BadActorCache badActorCache, AccountCache accountCache, AccountDao accountDao) {
        this.badActorCache = badActorCache;
        this.accountCache = accountCache;
        this.accountDao = accountDao;
    }

    @Override
    public void run() {
        boolean isRequestFromBadActor = badActorCache.isRequestFromBadActor(actorName);

        String dateStr = getNow();

        if (isRequestFromBadActor) {
            // this actor is hammering us, so use a cache
            Account account = accountCache.findAccountByUsername(actorName);
            boolean result = (account != null);
            System.out.println(dateStr + " TRACER '" + actorName + "' is BAD. using cache. result: " + result);
        } else {
            // this actor is making reasonable requests
            Account account = accountDao.findAccountByUsername(actorName);
            boolean result = (account != null);
            System.out.println(dateStr + " TRACER '" + actorName + "' is OK. using dao. result: " + result);
        }
    }
}
