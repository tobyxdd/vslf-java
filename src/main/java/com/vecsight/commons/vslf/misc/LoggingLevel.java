package com.vecsight.commons.vslf.misc;

public enum LoggingLevel {
    VERBOSE(0),
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4),
    FATAL(5);

    private final int id;

    LoggingLevel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        switch (id) {
            case 0:
                return "VERBOSE";
            case 1:
                return "DEBUG";
            case 2:
                return "INFO";
            case 3:
                return "WARN";
            case 4:
                return "ERROR";
            case 5:
                return "FATAL";
            default:
                return "CUSTOM";
        }
    }

}
