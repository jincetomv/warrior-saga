package org.codewarrior.rpg.ports.util;

import org.codewarrior.common.Assert;
import org.codewarrior.common.Logger;

import java.io.InputStream;
import java.util.Scanner;

public class Reader {
    private static final Logger LOGGER = Logger.getInstance(Reader.class);
    private final Scanner scanner;

    public Reader(final InputStream inputStream) {
        scanner = new Scanner(Assert.notNull(inputStream, "Input stream cannot be null"));
    }

    public String read() {
        String input = scanner.nextLine();
        LOGGER.info(String.format("Read input from console : %s", input));
        return input;
    }
}
