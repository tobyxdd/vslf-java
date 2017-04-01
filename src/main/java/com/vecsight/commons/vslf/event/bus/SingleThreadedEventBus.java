package com.vecsight.commons.vslf.event.bus;

import com.vecsight.commons.vslf.event.Event;
import com.vecsight.commons.vslf.handler.Handler;
import com.vecsight.commons.vslf.misc.LoggingLevel;
import com.vecsight.commons.vslf.misc.Utils;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadedEventBus implements EventBus {

    private LoggingLevel loggingLevel;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private Set<Handler> handlers = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public SingleThreadedEventBus(LoggingLevel loggingLevel) {
        this.loggingLevel = loggingLevel;
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
