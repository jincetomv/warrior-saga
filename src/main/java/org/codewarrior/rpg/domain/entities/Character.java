package org.codewarrior.rpg.domain.entities;

import org.codewarrior.common.Assert;
import org.codewarrior.common.domain.Entity;
import org.codewarrior.rpg.domain.services.config.CharacterConfiguration;
import org.codewarrior.rpg.domain.values.CharacterType;
import org.codewarrior.rpg.domain.values.Complexity;
import org.codewarrior.rpg.domain.values.HitPoint;
import org.codewarrior.rpg.domain.values.Name;
import org.codewarrior.rpg.domain.values.ids.CharacterId;

import java.io.Serializable;

public class Character implements Entity<CharacterId>, Serializable {
    private static final long serialVersionUID = -3224987399463900120L;
    private final CharacterId characterId;


    private final Name name;

    private boolean dead;

    CharacterType getCharacterType() {
        return characterType;
    }

    private final CharacterType characterType;
    private Health health;

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    private Experience experience;

    void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    Complexity getComplexity() {
        return complexity;
    }

    private Complexity complexity;

    public Name getName() {
        return name;
    }

    Character(CharacterId characterId, Name name, CharacterType characterType, Health health, Experience experience, Complexity complexity) {
        this.characterId = Assert.notNull(characterId, "characterId");
        this.name = Assert.notNull(name, "name");
        this.characterType = Assert.notNull(characterType, "characterType");
        this.health = Assert.notNull(health, "health");
        this.experience = Assert.notNull(experience, "experience");
        this.complexity = Assert.notNull(complexity, "complexity");
    }

    @Override
    public CharacterId id() {
        return characterId;
    }

    private void die() {
        if (health.getHp().getValue() <= 0) {
            dead = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Character)) {
            return false;
        }

        Character that = (Character) o;


        return
                getName().getValue().equals(that.getName().getValue()) &&
                        getExperience().getLevel().equals(that.getExperience().getLevel()) &&
                        getExperience().getXp().getValue() == that.getExperience().getXp().getValue() &&
                        getHealth().getHp().getValue() == that.getHealth().getHp().getValue() &&
                        getComplexity().getValue() == that.getComplexity().getValue();
    }


    public boolean isDead() {
        return dead;
    }

    public void attack(Character enemy) {
        int defensiveDamage = enemy.damage(calculateAttackDamage());
        updateHp(health.getHp().getValue(), defensiveDamage);

    }

    private int damage(int damage) {
        updateHp(health.getHp().getValue(), damage);
        return calculateDefensiveDamage();

    }

    private void updateHp(int currentHp, int damage) {
        health.setHp(HitPoint.of(currentHp - damage));
        die();
    }

    private int calculateAttackDamage() {
        return (int) (complexity.getValue() * CharacterConfiguration.BASE_HP) / CharacterConfiguration.ATTACK_DAMAGE_FACTOR;
    }

    private int calculateDefensiveDamage() {
        return (int) (complexity.getValue() * CharacterConfiguration.BASE_HP) / CharacterConfiguration.DEFENSIVE_DAMAGE_FACTOR;
    }


}
