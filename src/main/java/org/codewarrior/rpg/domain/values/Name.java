package org.codewarrior.rpg.domain.values;

import org.codewarrior.common.Assert;
import org.codewarrior.common.domain.Value;

import java.io.Serializable;

public final class Name implements Value, Serializable {
    private static final long serialVersionUID = 7017956384549030462L;
    private final String value;

    public Name(final String value) {
        this.value = Assert.notNull(value, "Value");
    }

    public String getValue() {
        return value;
    }

}
