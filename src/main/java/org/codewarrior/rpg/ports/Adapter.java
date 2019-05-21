package org.codewarrior.rpg.ports;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.ports.util.Reader;
import org.codewarrior.rpg.ports.util.Writer;

public class Adapter {
    private final Reader reader;
    private final Writer writer;

    public Adapter(final Reader reader, final Writer writer) {
        this.reader = Assert.notNull(reader, "Reader cannot be null");
        this.writer = Assert.notNull(writer, "Writer cannot be null");

    }

    public String printMessageAndGetInput(final String message) {
        writer.write(message);
        return reader.read();
    }

    public void showMessage(final String message) {
        writer.write(message);
    }
}
