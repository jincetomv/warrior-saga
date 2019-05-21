package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.common.util.StringUtil;
import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.PlayerInfo;
import org.codewarrior.rpg.domain.entities.Character;
import org.codewarrior.rpg.ports.Adapter;

import java.util.HashMap;
import java.util.Map;

public class CharacterApiImpl implements CharacterApi {
    private static final String PLAYER_NAME = "playerName";
    private static final String CHARACTER_NAME = "characterName";

    private final Map<String, String> questions = new HashMap<>();

    private final Adapter adapter;

    CharacterApiImpl(final Adapter adapter) {
        this.adapter = Assert.notNull(adapter, "Adapter");
        questions.put(PLAYER_NAME, "Enter your name");
        questions.put(CHARACTER_NAME, "Enter your character's name");

    }


    @Override
    public PlayerInfo playerInfo() {
        String playerName = adapter.printMessageAndGetInput(questions.get(PLAYER_NAME));

        String characterName = adapter.printMessageAndGetInput(questions.get(CHARACTER_NAME));
        if (StringUtil.isNullOrEmpty(playerName) || StringUtil.isNullOrEmpty(characterName)) {
            adapter.showMessage("Incorrect/Invalid data entered, Try again");
            return playerInfo();
        }
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setCharacterName(characterName);
        playerInfo.setPlayerName(playerName);
        return playerInfo;
    }

    @Override
    public void showPlayerDiedWarning() {
        adapter.showMessage("Oh no.. you have been killed");
    }

    @Override
    public void showPlayerKilledEnemyMessage(String enemyName, boolean leveledUp) {
        adapter.showMessage(String.format("Oh wow.. you have killed enemy %s", enemyName));
        if (leveledUp) {
            adapter.showMessage("You have leveled up too.. Congrats");
        }
    }

    @Override
    public void showCharacterInfo(Character character) {
        adapter.showMessage(character.toString());
    }

}
