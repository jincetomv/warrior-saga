package org.codewarrior.rpg.domain.entities;

import org.codewarrior.rpg.domain.values.HitPoint;

import java.io.Serializable;

public class Health implements Serializable {
    private static final long serialVersionUID = 4765088588738212335L;
    private HitPoint hp;

    public Health(HitPoint hp) {
        this.hp = hp;
    }

    public HitPoint getHp() {
        return hp;
    }

    public void setHp(HitPoint hp) {
        this.hp = hp;
    }
}
