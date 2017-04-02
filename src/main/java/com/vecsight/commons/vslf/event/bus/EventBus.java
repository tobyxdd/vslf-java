package com.vecsight.commons.vslf.event.bus;

import com.vecsight.commons.vslf.event.Event;
import com.vecsight.commons.vslf.handler.Handler;
import com.vecsight.commons.vslf.misc.LoggingLevel;

public interface EventBus {

    void post(Event event);

    void shutdown();

    boolean isShutdown();

    void setLoggingLevel(LoggingLevel level);

    LoggingLevel getLoggingLevel();

    boolean addHandler(Handler handler);

    boolean removeHandler(Handler handler);

}
