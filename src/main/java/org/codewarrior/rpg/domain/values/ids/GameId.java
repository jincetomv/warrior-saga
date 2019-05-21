package org.codewarrior.rpg.domain.values.ids;

import org.codewarrior.common.domain.Id;

import java.io.Serializable;

public final class GameId extends Id<String> implements Serializable {
    private static final long serialVersionUID = 2348972380333222920L;

    public GameId(final String value) {
        super(value);
    }

    public static GameId of(final String value) {
        return new GameId(value);
    }
}
