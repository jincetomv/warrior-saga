package org.codewarrior.rpg.domain.values.ids;

import org.codewarrior.common.domain.Id;

import java.io.Serializable;

public final class CharacterId extends Id<String> implements Serializable {
    private static final long serialVersionUID = 530975780164398650L;

    public CharacterId(final String value) {
        super(value);
    }

    public static CharacterId of(final String value) {
        return new CharacterId(value);
    }
}
