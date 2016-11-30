
package com.acme.flagship.actor;

import java.util.concurrent.*;

public abstract class Actor {
    private final long initialDelay;
    private final long delay;
    private final TimeUnit unit;
    private final String name;

    protected Actor(String name, long initialDelay, long delay, TimeUnit unit) {
        this.initialDelay = initialDelay;
        this.delay = delay;
        this.unit = unit; 
        this.name = name;
    }

    public void go(Action action) {
        action.setActorName(name);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(action, initialDelay, delay, unit);
    }
}
