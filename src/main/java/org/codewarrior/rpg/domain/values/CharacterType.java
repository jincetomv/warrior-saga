package org.codewarrior.rpg.domain.values;

import java.io.Serializable;

public enum CharacterType implements Serializable {
    PLAYER("P"), ENEMY("E");

    public String getValue() {
        return value;
    }

    private final String value;

    CharacterType(String value) {
        this.value = value;
    }

}
