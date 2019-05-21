package org.codewarrior.common;

public final class Assert {
    private static final Logger LOGGER = Logger.getInstance(Assert.class);

    private Assert() {
    }

    public static <T> T notNull(T object, String message) {
        if (object == null) {
            IllegalArgumentException ex = new IllegalArgumentException(message);
            LOGGER.error(ex.getMessage(), ex);
            throw ex;
        }

        return object;
    }
}
