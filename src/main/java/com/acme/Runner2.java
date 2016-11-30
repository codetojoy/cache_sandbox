
package com.acme;

import com.acme.flagship.dao.*;
import com.acme.flagship.actor.*;
import com.acme.flagship.cache.*;

import java.util.*;
import com.google.common.cache.CacheStats;

public class Runner2 {
    public void go() {
        AccountDao accountDao = new AccountDao();
        AccountCache accountCache = new AccountCache(accountDao);
        BadActorCache badActorCache = new BadActorCache();

        final String prefix = "Name_";
        Actor slowActor1 = new SlowActor(prefix + "11");
        Actor slowActor2 = new SlowActor(prefix + "12");
        Actor slowActor3 = new SlowActor(prefix + "13");

        slowActor1.go(new DaoAction(badActorCache, accountCache, accountDao));
        slowActor2.go(new DaoAction(badActorCache, accountCache, accountDao));
        slowActor3.go(new DaoAction(badActorCache, accountCache, accountDao));

        Actor fastActorA = new FastActor(prefix + "99");

        fastActorA.go(new DaoAction(badActorCache, accountCache, accountDao));

        while (true) {
            try { Thread.sleep(10 * 1000); } catch(Exception ex) {} 
            System.out.println("running ...");
        }
    }

    public static void main(String... args) {
        Runner2 runner = new Runner2();
        runner.go();
    }
}
