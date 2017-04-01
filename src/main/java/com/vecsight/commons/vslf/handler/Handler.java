package com.vecsight.commons.vslf.handler;

import com.vecsight.commons.vslf.event.Event;

public interface Handler {

    void handle(Event event);

    void onShutdown();

    boolean isShutdown();

}
