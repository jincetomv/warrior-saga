package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.common.util.StringUtil;
import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.PlayerInfo;
import org.codewarrior.rpg.api.config.Messages;
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
        questions.put(PLAYER_NAME, Messages.PLAYER_NAME);
        questions.put(CHARACTER_NAME, Messages.CHARACTER_NAME);

    }


    @Override
    public PlayerInfo playerInfo() {
        String playerName = adapter.printMessageAndGetInput(questions.get(PLAYER_NAME));

        String characterName = adapter.printMessageAndGetInput(questions.get(CHARACTER_NAME));
        if (StringUtil.isNullOrEmpty(playerName) || StringUtil.isNullOrEmpty(characterName)) {
            adapter.showMessage(Messages.INCORRECT_DATA);
            return playerInfo();
        }
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setCharacterName(characterName);
        playerInfo.setPlayerName(playerName);
        return playerInfo;
    }

    @Override
    public void showPlayerDiedWarning() {
        adapter.showMessage(Messages.PLAYER_DIED);
    }

    @Override
    public void showPlayerKilledEnemyMessage(String enemyName, boolean leveledUp) {
        adapter.showMessage(String.format(Messages.ENEMY_DIED, enemyName));
        if (leveledUp) {
            adapter.showMessage(Messages.LEVELED_UP);
        }
    }

    @Override
    public void showCharacterInfo(Character character) {
        adapter.showMessage(character.toString());
    }

}
