package com.vecsight.commons.vslf.handler;

import com.vecsight.commons.vslf.Constants;
import com.vecsight.commons.vslf.event.Event;
import com.vecsight.commons.vslf.misc.TextLogEventFormatter;

import java.io.FileWriter;
import java.io.IOException;

public class TextFileHandler implements Handler {

    private boolean down = false;

    private TextLogEventFormatter formatter;

    private final FileWriter writer;

    public TextFileHandler(TextLogEventFormatter formatter, String filename, boolean append) throws IOException {
        this.formatter = formatter;
        writer = new FileWriter(filename, append);
    }

    public TextFileHandler(String filename, boolean append) throws IOException {
        this(new TextLogEventFormatter(Constants.TEXT_FORMAT), filename, append);
    }


    @Override
    public void handle(Event event) {
        String str = formatter.format(event);
        synchronized (writer) {
            try {
                writer.write(str);
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onShutdown() {
        if (!down) {
            synchronized (writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            down = true;
        }
    }

    @Override
    public boolean isShutdown() {
        return down;
    }
}
