package com.vecsight.commons.vslf.misc;

/*
 * {NAME}   Logger name
 * {TIME}   Current date & time
 * {LEVEL}  Logging level
 * {OBJ}    Content
 * !!! Case sensitive !!!
 */

import com.vecsight.commons.vslf.event.Event;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TextLogEventFormatter {

    private String template;

    private DateTimeFormatter dateTimeFormatter;

    public TextLogEventFormatter(String template, DateTimeFormatter dateTimeFormatter) {
        this.template = template;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public TextLogEventFormatter(String template) {
        this(template, DateTimeFormatter.ISO_OFFSET_TIME);
    }

    public String format(Event event) {
        return template.replace("{NAME}", event.getLogger().getName())
                .replace("{TIME}", dateTimeFormatter.format(ZonedDateTime.now()))
                .replace("{LEVEL}", event.getLevel().toString())
                .replace("{OBJ}", event.getEventObject().toString());
    }

}
