package org.codewarrior.rpg.api;

import org.codewarrior.rpg.domain.entities.Character;

public interface CharacterApi {
    public PlayerInfo playerInfo();

    public void showCharacterInfo(Character character);

    public void showPlayerDiedWarning();

    public void showPlayerKilledEnemyMessage(String enemyName, boolean leveledUp);

}
