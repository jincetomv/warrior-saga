package org.codewarrior.common.util;

public final class StringUtil {
    private StringUtil() {

    }

    public static boolean isNullOrEmpty(final String value) {
        return value == null || value.isEmpty();
    }
}
