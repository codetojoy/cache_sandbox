
package com.acme;

import com.acme.flagship.dao.*;
import com.acme.flagship.cache.*;

import java.util.*;

public class Runner {
    private AccountCache cache;

    public Runner() {
        AccountDao dao = new AccountDao();
        this.cache = new AccountCache(dao);
    }

    public long run() {
        long beforeInMillis = System.currentTimeMillis();

        final String prefix = "Name_";
        final int NUM_ROWS = 1000;               
    
        for (int i = 1; i <= NUM_ROWS; i++) {
            String username = prefix + i;
            Account account = cache.findAccountByUsername(username);
            if (i % 100 == 0) {
                // System.out.println("account id: " + account.getAccountId() + " username: " + account.getUsername());
            }
        }
        long resultInMillis = System.currentTimeMillis() - beforeInMillis;
        long resultInSeconds = resultInMillis / 1000;
        return resultInSeconds;
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        long elapsed1 = runner.run();
        long elapsed2 = runner.run();
        System.out.println("--------------------------------------");
        System.out.println("TRACER elapsed 1: " + elapsed1 + " seconds");
        System.out.println("TRACER elapsed 2: " + elapsed2 + " seconds");
        System.out.println("Ready.");
    }
}
