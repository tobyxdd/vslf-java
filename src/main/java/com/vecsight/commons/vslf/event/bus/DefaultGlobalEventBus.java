package com.vecsight.commons.vslf.event.bus;

import com.vecsight.commons.vslf.misc.LoggingLevel;

public class DefaultGlobalEventBus {

    private static EventBus eventBus = new SingleThreadedEventBus(LoggingLevel.INFO);

    public static EventBus getEventBus() {
        return eventBus;
    }

    public static void setEventBus(EventBus eventBus) {
        DefaultGlobalEventBus.eventBus = eventBus;
    }
}
