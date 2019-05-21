package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.common.Logger;
import org.codewarrior.rpg.api.ApplicationApi;
import org.codewarrior.rpg.api.GameApi;
import org.codewarrior.rpg.api.MenuApi;
import org.codewarrior.rpg.domain.entities.Player;


public class MenuApiImpl implements MenuApi {
    private static final Logger LOGGER = Logger.getInstance(MenuApiImpl.class);
    private final ApplicationApi applicationApi;
    private final GameApi gameApi;
    private final MainMenu mainMenu;
    private final NewGameMenu newGameMenu;
    private final DisplayMenuHelper displayMenuHelper;

    MenuApiImpl(final ApplicationApi applicationApi, final GameApi gameApi, final MainMenu mainMenu, final NewGameMenu newGameMenu, final DisplayMenuHelper displayMenuHelper) {
        this.applicationApi = Assert.notNull(applicationApi, "applicationApi");
        this.gameApi = Assert.notNull(gameApi, "gameApi");
        this.mainMenu = Assert.notNull(mainMenu, "mainMenu");
        this.newGameMenu = Assert.notNull(newGameMenu, "newGameMenu");
        this.displayMenuHelper = Assert.notNull(displayMenuHelper, "displayMenuHelper");

    }


    @Override
    public void showNewGameMenu(Player player) {
        switch (displayMenuHelper.show(newGameMenu)) {
            case PLAY_AGAIN:
                gameApi.createAndStartGame(player);
                break;
            case RESUME:
                gameApi.resume();
                break;
            case QUIT:
                applicationApi.shutdown();
                break;
            default:
                break;
        }
    }

    @Override
    public void showMainMenu() {

        switch (displayMenuHelper.show(mainMenu)) {
            case PLAY:
                gameApi.createAndStartGame(null);
                break;
            case RESUME:
                gameApi.resume();
                break;
            case QUIT:
                applicationApi.shutdown();
                break;
            default:
                break;
        }

    }
}
