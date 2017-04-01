package com.vecsight.commons.vslf;

import com.vecsight.commons.vslf.event.bus.EventBus;
import com.vecsight.commons.vslf.event.bus.ThreadPoolEventBus;
import com.vecsight.commons.vslf.handler.ConsoleHandler;
import com.vecsight.commons.vslf.handler.TextFileHandler;
import com.vecsight.commons.vslf.logger.DefaultLogger;
import com.vecsight.commons.vslf.logger.Logger;
import com.vecsight.commons.vslf.misc.LoggingLevel;
import com.vecsight.commons.vslf.misc.TextLogEventFormatter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        EventBus eventBus = new ThreadPoolEventBus(LoggingLevel.VERBOSE);

        Logger logger = new DefaultLogger("MainLogger", eventBus);

        TextLogEventFormatter formatter = new TextLogEventFormatter("{TIME} [{NAME} - {LEVEL}] {OBJ}");

        eventBus.addHandler(new ConsoleHandler(formatter));
        eventBus.addHandler(new TextFileHandler("log.txt", true));

        for (int i = 0; i < 100; i++) {
            logger.verbose("Wow " + i);
        }

        eventBus.shutdown();

        System.out.println("DONE");

    }

}
