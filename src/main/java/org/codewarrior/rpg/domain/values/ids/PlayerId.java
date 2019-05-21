package org.codewarrior.rpg.domain.values.ids;

import org.codewarrior.common.domain.Id;

public final class PlayerId extends Id<String> {
    private PlayerId(final String value) {
        super(value);
    }

    public static PlayerId of(final String value) {
        return new PlayerId(value);
    }
}
