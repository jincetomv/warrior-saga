package org.codewarrior.rpg.domain.entities;

import org.codewarrior.rpg.domain.values.Level;
import org.codewarrior.rpg.domain.values.Xp;

import java.io.Serializable;

public class Experience implements Serializable {
    private static final long serialVersionUID = 20896069589953955L;
    private Level level;
    private Xp xp;

    public Experience(Level level, Xp xp) {
        this.level = level;
        this.xp = xp;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


    Xp getXp() {
        return xp;
    }

    void setXp(Xp xp) {
        this.xp = xp;
    }


}
