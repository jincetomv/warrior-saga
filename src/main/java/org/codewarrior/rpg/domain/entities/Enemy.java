package org.codewarrior.rpg.domain.entities;

import org.codewarrior.rpg.domain.values.CharacterType;
import org.codewarrior.rpg.domain.values.Complexity;
import org.codewarrior.rpg.domain.values.Name;
import org.codewarrior.rpg.domain.values.ids.CharacterId;

public class Enemy extends Character {
    private static final long serialVersionUID = 952534754115763395L;

    public Enemy(CharacterId characterId, Name name, CharacterType characterType, Health health, Experience experience, Complexity complexity) {
        super(characterId, name, characterType, health, experience, complexity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Enemy)) {
            return false;
        }

        Enemy that = (Enemy) o;


        return
                getName().getValue().equals(that.getName().getValue()) &&
                        getExperience().getLevel().equals(that.getExperience().getLevel()) &&
                        getExperience().getXp().getValue() == that.getExperience().getXp().getValue() &&
                        getHealth().getHp().getValue() == that.getHealth().getHp().getValue() &&
                        getComplexity().getValue() == that.getComplexity().getValue();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nEnemy status ").append("\n--------------------");
        builder.append("\nName: ").append(getName().getValue());
        builder.append("\nHitPoint: ").append(getHealth().getHp().getValue());
        builder.append("\nExperience point: ").append(getExperience().getXp().getValue());
        builder.append("\nExperience Level: ").append(getExperience().getLevel().getDisplayName());
        builder.append("\nFight complexity: ").append(getComplexity().getValue());
        return builder.toString();
    }
}
