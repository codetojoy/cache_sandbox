
package com.acme.flagship.actor;

import java.util.concurrent.*;

public class SlowActor extends Actor {
    private static final long INITIAL_DELAY_IN_SECONDS = 0L;
    private static final long DELAY_IN_SECONDS = 30L;  
    private static final TimeUnit UNIT_SECONDS = TimeUnit.SECONDS;

    public SlowActor(String name) {
        super(name, INITIAL_DELAY_IN_SECONDS, DELAY_IN_SECONDS, UNIT_SECONDS);
    }
}
