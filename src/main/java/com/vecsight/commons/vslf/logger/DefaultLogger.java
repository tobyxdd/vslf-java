package com.vecsight.commons.vslf.logger;

import com.vecsight.commons.vslf.event.Event;
import com.vecsight.commons.vslf.event.bus.DefaultGlobalEventBus;
import com.vecsight.commons.vslf.event.bus.EventBus;
import com.vecsight.commons.vslf.misc.LoggingLevel;

public class DefaultLogger implements Logger {

    private String name;

    private final EventBus eventBus;

    public DefaultLogger(String name, EventBus eventBus) {
        this.name = name;
        this.eventBus = eventBus;
    }

    public DefaultLogger(String name) {
        this(name, DefaultGlobalEventBus.getEventBus());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void verbose(Object object) {
        eventBus.post(new Event(this, LoggingLevel.VERBOSE, object));
    }

    @Override
    public void debug(Object object) {
        eventBus.post(new Event(this, LoggingLevel.DEBUG, object));
    }

    @Override
    public void info(Object object) {
        eventBus.post(new Event(this, LoggingLevel.INFO, object));
    }

    @Override
    public void warn(Object object) {
        eventBus.post(new Event(this, LoggingLevel.WARN, object));
    }

    @Override
    public void error(Object object) {
        eventBus.post(new Event(this, LoggingLevel.ERROR, object));
    }

    @Override
    public void fatal(Object object) {
        eventBus.post(new Event(this, LoggingLevel.FATAL, object));
    }

}
