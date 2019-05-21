package org.codewarrior.rpg.api;

import org.codewarrior.rpg.domain.entities.Map;
import org.codewarrior.rpg.domain.entities.Player;

public interface GameApi {
    public void createAndStartGame(Player player);

    public void showMap(Map map);

    public void showGameMenu();

    public void showPlayerCreatedMessage();

    public void showWinMessage();

    public void showSaveSuccessMessage();

    public void showSaveFailedMessage();


    public void showLoadFailedMessage();

    public void resume();
}
