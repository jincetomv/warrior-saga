package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.rpg.domain.entities.Experience;
import org.codewarrior.rpg.domain.entities.Health;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.values.*;
import org.codewarrior.rpg.domain.values.ids.CharacterId;

import java.util.UUID;

class PlayerBuilder {

    private String playerName;
    private String characterName;
    private double complexity;
    private int hitPoint;
    private int xp;
    private Level level;


    PlayerBuilder addCharacterName(final String characterName) {
        this.characterName = characterName;
        return this;
    }


    PlayerBuilder addComplexity(final double complexity) {
        this.complexity = complexity;
        return this;
    }


    PlayerBuilder addHitPoint(final int hitPoint) {
        this.hitPoint = hitPoint;
        return this;
    }

    PlayerBuilder addXp(final int xp) {
        this.xp = xp;
        return this;
    }

    PlayerBuilder addLevel(final Level level) {
        this.level = level;
        return this;
    }

    PlayerBuilder addPlayerName(final String playerName) {
        this.playerName = playerName;
        return this;
    }


    Player build() {
        return new Player(CharacterId.of(UUID.randomUUID().toString()), new Name(playerName), new Name(characterName), new Health(HitPoint.of(hitPoint)), new Experience(level, Xp.of(xp)), new Complexity(complexity));
    }


}
