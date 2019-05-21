package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.rpg.domain.entities.Enemy;
import org.codewarrior.rpg.domain.entities.Experience;
import org.codewarrior.rpg.domain.entities.Health;
import org.codewarrior.rpg.domain.values.*;
import org.codewarrior.rpg.domain.values.ids.CharacterId;

import java.util.UUID;

public class EnemyBuilder {
    private String name;
    private double complexity;
    private int hitPoint;
    private int xp;
    private Level level;

    EnemyBuilder addName(final String name) {
        this.name = name;
        return this;
    }

    EnemyBuilder addComplexity(final double complexity) {
        this.complexity = complexity;
        return this;
    }


    EnemyBuilder addHitPoint(final int hitPoint) {
        this.hitPoint = hitPoint;
        return this;
    }

    EnemyBuilder addXp(final int xp) {
        this.xp = xp;
        return this;
    }

    EnemyBuilder addLevel(final Level level) {
        this.level = level;
        return this;
    }

    public Enemy build() {
        return new Enemy(CharacterId.of(UUID.randomUUID().toString()), new Name(name), CharacterType.ENEMY, new Health(HitPoint.of(hitPoint)), new Experience(level, Xp.of(xp)), new Complexity(complexity));
    }

}
