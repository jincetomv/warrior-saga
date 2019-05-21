package org.codewarrior.rpg.domain.values;

import java.io.Serializable;

public final class Xp implements Serializable {
    private static final long serialVersionUID = -564357620222208931L;

    public int getValue() {
        return value;
    }

    private final int value;

    public Xp(final int value) {
        this.value = value;
    }

    public static Xp of(final int value) {
        return new Xp(value);
    }
}
