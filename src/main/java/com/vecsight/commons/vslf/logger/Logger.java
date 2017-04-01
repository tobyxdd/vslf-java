package com.vecsight.commons.vslf.logger;

public interface Logger {

    String getName();

    void setName(String name);

    void verbose(Object object);

    void debug(Object object);

    void info(Object object);

    void warn(Object object);

    void error(Object object);

    void fatal(Object object);

}
