package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.PlayerInfo;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.services.PlayerService;
import org.codewarrior.rpg.domain.services.config.CharacterConfiguration;
import org.codewarrior.rpg.domain.services.config.LevelConfiguration;
import org.codewarrior.rpg.domain.values.Level;

public class PlayerServiceImpl implements PlayerService {
    private final CharacterApi characterApi;
    private final RandomOperationHelper randomOperationHelper;
    private Player currentPlayer;

    public PlayerServiceImpl(final CharacterApi characterApi, final RandomOperationHelper randomOperationHelper) {
        this.characterApi = Assert.notNull(characterApi, "characterApi");
        this.randomOperationHelper = Assert.notNull(randomOperationHelper, "randomOperationHelper");

    }


    @Override
    public Player get() {
        return currentPlayer;
    }

    @Override
    public void load(Player player) {
        currentPlayer = player;
    }

    @Override
    public void create() {
        PlayerInfo playerInfo = characterApi.playerInfo();
        LevelConfiguration levelConfiguration = LevelConfiguration.levelConfigurations.get(Level.BEGINNER);
        currentPlayer = new PlayerBuilder().
                addPlayerName(playerInfo.getPlayerName()).
                addCharacterName(playerInfo.getCharacterName())
                .addHitPoint(CharacterConfiguration.BASE_HP).addXp(getRandomXPForLevel(levelConfiguration)).addLevel(levelConfiguration.getLevel()).addComplexity(getComplexityForLevel(levelConfiguration)).build();
    }


    private double getComplexityForLevel(LevelConfiguration levelConfiguration) {
        return randomOperationHelper.randomComplexity(levelConfiguration.getComplexityMin(), levelConfiguration.getComplexityMax());
    }

    private int getRandomXPForLevel(LevelConfiguration levelConfiguration) {
        return randomOperationHelper.randomInteger(levelConfiguration.getXpMin(), levelConfiguration.getXpMax());
    }
}
