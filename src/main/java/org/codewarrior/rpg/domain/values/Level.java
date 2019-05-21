package org.codewarrior.rpg.domain.values;

import org.codewarrior.common.domain.Value;

import java.io.Serializable;

public enum Level implements Value, Serializable {
    BEGINNER(1, "Beginner"), FIGHTER(2, "Fighter"), INVADER(3, "Invader"), ACHIEVER(4, "Achiever");

    final int value;


    final String displayName;


    public Level getNextLevel() {
        for (Level level : Level.values()) {
            if (level.value == value + 1) {
                return level;
            }
        }
        return null;
    }

    public String getDisplayName() {
        return displayName;
    }

    Level(final int value, final String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

}
