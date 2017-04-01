package com.vecsight.commons.vslf.handler;

import com.vecsight.commons.vslf.Constants;
import com.vecsight.commons.vslf.event.Event;
import com.vecsight.commons.vslf.misc.TextLogEventFormatter;

public class ConsoleHandler implements Handler {

    private TextLogEventFormatter formatter;

    public ConsoleHandler(TextLogEventFormatter formatter) {
        this.formatter = formatter;
    }

    public ConsoleHandler() {
        this(new TextLogEventFormatter(Constants.TEXT_FORMAT));
    }

    @Override
    public void handle(Event event) {
        String str = formatter.format(event);
        synchronized (System.out) {
            System.out.println(str);
        }
    }

    @Override
    public void onShutdown() {
        //okay
    }

    @Override
    public boolean isShutdown() {
        return false;
    }
}
