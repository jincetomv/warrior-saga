package org.codewarrior.rpg.ports.util;

import org.codewarrior.common.Logger;

import java.io.PrintStream;

public class Writer {
    private static final Logger LOGGER = Logger.getInstance(Writer.class);
    private final PrintStream printStream;

    public Writer(final PrintStream printStream) {
        this.printStream = printStream;
    }

    public void write(final String message) {
        LOGGER.info(String.format("Going to write this message to console : %s", message));
        printStream.println(message);
    }
}
