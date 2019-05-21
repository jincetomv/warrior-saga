package org.codewarrior.rpg.domain.entities;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.domain.services.config.LevelConfiguration;
import org.codewarrior.rpg.domain.values.*;
import org.codewarrior.rpg.domain.values.ids.CharacterId;

public class Player extends Character {


    private static final long serialVersionUID = 3663154560174736994L;
    private Name playerName;
    private Coordinate currentCoordinate;


    private Name getPlayerName() {
        return playerName;
    }

    public Coordinate getCurrentCoordinate() {
        return currentCoordinate;
    }

    public void setCurrentCoordinate(Coordinate currentCoordinate) {
        this.currentCoordinate = currentCoordinate;
    }


    public Player(final CharacterId id, final Name playerName, final Name characterName, final Health health, final Experience experience, final Complexity complexity) {

        super(Assert.notNull(id, "Id"), Assert.notNull(characterName, "characterName"), CharacterType.PLAYER, health, experience, complexity);
        this.playerName = Assert.notNull(playerName, "playerName");
    }

    public void awardReward(int reward) {
        getExperience().setXp(Xp.of(getExperience().getXp().getValue() + reward));
    }

    public boolean addComplexity(double complexity) {
        setComplexity(new Complexity(getComplexity().getValue() + complexity));
        return levelUp();
    }

    private boolean levelUp() {
        Experience experience = getExperience();
        if (getComplexity().getValue() > LevelConfiguration.levelConfigurations.get(experience.getLevel()).getComplexityMax()) {
            experience.setLevel(experience.getLevel().getNextLevel());
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }

        Player that = (Player) o;

        return getPlayerName().getValue().equals(that.getPlayerName().getValue()) &&
                getName().getValue().equals(that.getName().getValue()) &&
                getExperience().getLevel().equals(that.getExperience().getLevel()) &&
                getExperience().getXp().getValue() == that.getExperience().getXp().getValue() &&
                getHealth().getHp().getValue() == that.getHealth().getHp().getValue() &&
                getComplexity().getValue() == that.getComplexity().getValue();

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nPlayer status ").append("\n--------------------");
        builder.append("\nName: ").append(getPlayerName().getValue());
        builder.append("\nCharacter: ").append(getName().getValue());
        builder.append("\nHitPoint: ").append(getHealth().getHp().getValue());
        builder.append("\nExperience Point: ").append(getExperience().getXp().getValue());
        builder.append("\nExperience Level: ").append(getExperience().getLevel().getDisplayName());
        builder.append("\nFight complexity: ").append(getComplexity().getValue());
        return builder.toString();
    }
}
