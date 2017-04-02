package com.vecsight.commons.vslf.event.bus;


import com.vecsight.commons.vslf.event.Event;
import com.vecsight.commons.vslf.handler.Handler;
import com.vecsight.commons.vslf.misc.LoggingLevel;
import com.vecsight.commons.vslf.misc.Utils;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolEventBus implements EventBus {

    private boolean down = false;

    private LoggingLevel loggingLevel;

    private final ThreadPoolExecutor executor;

    private Set<Handler> handlers = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public ThreadPoolEventBus(LoggingLevel loggingLevel, ThreadPoolExecutor executor) {
        this.loggingLevel = loggingLevel;
        this.executor = executor;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!isShutdown()) {
                shutdown();
            }
        }));
    }

    public ThreadPoolEventBus(LoggingLevel loggingLevel) {
        this(loggingLevel, new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(), 10 * 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()));
    }

    @Override
    public void post(Event event) {
        if (event.getLevel().getId() >= loggingLevel.getId()) {
            for (Handler handler : handlers) {
                if (!handler.isShutdown()) {
                    executor.execute(() -> handler.handle(event));
                }
            }
        }
    }

    @Override
    public void shutdown() {
        executor.shutdown();
        Utils.waitExecutorTerm(executor);
        for (Handler handler : handlers) {
            if (!handler.isShutdown()) {
                handler.onShutdown();
            }
        }
        down = true;
    }

    @Override
    public boolean isShutdown() {
        return down;
    }

    @Override
    public LoggingLevel getLoggingLevel() {
        return loggingLevel;
    }

    @Override
    public boolean addHandler(Handler handler) {
        return handlers.add(handler);
    }

    @Override
    public boolean removeHandler(Handler handler) {
        return handlers.remove(handler);
    }

    @Override
    public void setLoggingLevel(LoggingLevel loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

}
