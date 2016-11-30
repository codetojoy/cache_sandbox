
package com.acme.flagship.actor;

public class SimpleAction extends Action {
    @Override
    public void run() {
        String dateStr = getNow();
        System.out.println(dateStr + " TRACER hello from " + actorName);
    }
}
