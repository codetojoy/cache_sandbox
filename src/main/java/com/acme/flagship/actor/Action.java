
package com.acme.flagship.actor;

import java.util.*;
import java.util.concurrent.*;
import java.text.SimpleDateFormat;

public abstract class Action implements Runnable {
    protected String actorName;

    protected void setActorName(String actorName) {
        this.actorName = actorName;
    }

    protected String getNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr = formatter.format(new Date()); 
        return dateStr;
    }

    @Override
    public abstract void run();
}
