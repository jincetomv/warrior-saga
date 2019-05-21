package org.codewarrior.rpg.domain.values;

import org.codewarrior.common.domain.Value;

import java.io.Serializable;

public final class HitPoint implements Value, Serializable {
    private static final long serialVersionUID = -4573706745481775165L;

    public int getValue() {
        return value;
    }

    private final int value;

    public HitPoint(final int value) {
        this.value = value;
    }

    public static HitPoint of(final int value) {
        return new HitPoint(value);
    }
}
