package org.codewarrior.rpg.api;

import org.codewarrior.rpg.domain.entities.Player;

public interface MenuApi {

    public void showMainMenu();

    public void showNewGameMenu(Player player);
}
