package com.vecsight.commons.vslf.event;

import com.vecsight.commons.vslf.logger.Logger;
import com.vecsight.commons.vslf.misc.LoggingLevel;

public class Event {

    private Logger logger;

    private LoggingLevel level;

    private Object eventObject;

    public Event(Logger logger, LoggingLevel level, Object eventObject) {
        this.logger = logger;
        this.level = level;
        this.eventObject = eventObject;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public LoggingLevel getLevel() {
        return level;
    }

    public void setLevel(LoggingLevel level) {
        this.level = level;
    }

    public Object getEventObject() {
        return eventObject;
    }

    public void setEventObject(Object eventObject) {
        this.eventObject = eventObject;
    }

}
