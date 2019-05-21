package org.codewarrior.rpg.domain.values;

import java.io.Serializable;

public final class Complexity implements Serializable {
    private static final long serialVersionUID = 8785194888365315358L;

    public double getValue() {
        return value;
    }

    private final double value;

    public Complexity(final double value) {
        this.value = value;
    }
}
