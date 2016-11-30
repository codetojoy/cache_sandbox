
package com.acme.flagship.actor;

import java.util.concurrent.*;

public class FastActor extends Actor {
    private static final long INITIAL_DELAY_IN_MILLIS = 0L;
    private static final long DELAY_IN_MILLIS = 200L;  
    private static final TimeUnit UNIT_MILLIS = TimeUnit.MILLISECONDS;

    public FastActor(String name) {
        super(name, INITIAL_DELAY_IN_MILLIS, DELAY_IN_MILLIS, UNIT_MILLIS);
    } 
}
