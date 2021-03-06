package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.api.ApplicationApi;
import org.codewarrior.rpg.api.GameApi;
import org.codewarrior.rpg.api.GameContext;
import org.codewarrior.rpg.api.NavigationApi;
import org.codewarrior.rpg.domain.entities.Map;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.ports.Adapter;

import static org.codewarrior.rpg.api.config.Messages.*;

public class GameApiImpl implements GameApi {

    GameApiImpl(Adapter adapter, ApplicationApi applicationApi, DisplayMenuHelper displayMenuHelper, GameMenu gameMenu, NavigationApi navigationApi) {
        this.adapter = Assert.notNull(adapter, "adapter");
        this.applicationApi = Assert.notNull(applicationApi, "applicationApi");
        this.displayMenuHelper = Assert.notNull(displayMenuHelper, "displayMenuHelper");
        this.gameMenu = Assert.notNull(gameMenu, "gameMenu");
        this.navigationApi = Assert.notNull(navigationApi, "navigationApi");

    }

    private final Adapter adapter;
    private final ApplicationApi applicationApi;
    private final DisplayMenuHelper displayMenuHelper;
    private final GameMenu gameMenu;
    private final NavigationApi navigationApi;


    @Override
    public void createAndStartGame(Player player) {
        createAndStart(player);
    }


    private void createAndStart(Player player) {
        applicationApi.createNewGameContext();
        final GameContext currentGameContext = applicationApi.getCurrentGameContext();
        if (player == null) {
            currentGameContext.getGameService().create();
        } else {
            currentGameContext.getGameService().create(player);
        }
        currentGameContext.getGameService().start();
    }

    @Override
    public void showGameMenu() {
        switch (displayMenuHelper.show(gameMenu)) {
            case UP:
                navigationApi.up();
                break;
            case DOWN:
                navigationApi.down();
                break;
            case LEFT:
                navigationApi.left();
                break;
            case RIGHT:
                navigationApi.right();
                break;
            case SAVE:
                applicationApi.getCurrentGameContext().getGameService().save();
                break;
            case QUIT:
                applicationApi.shutdown();
                break;
            default:
                //This should never be executed
                break;
        }
    }

    @Override
    public void showMap(Map map) {
        adapter.showMessage(map.toString());
    }

    @Override
    public void showPlayerCreatedMessage() {
        adapter.showMessage(PLAYER_CREATED);

    }

    @Override
    public void showSaveFailedMessage() {
        adapter.showMessage(GAME_SAVE_FAILED);
    }

    @Override
    public void showSaveSuccessMessage() {
        adapter.showMessage(GAME_SAVE_SUCCESS);
    }

    @Override
    public void showWinMessage() {
        adapter.showMessage(PLAYER_WON);
    }

    @Override
    public void resume() {
        applicationApi.clearCurrentGameContext();
        applicationApi.createNewGameContext();
        final GameContext currentGameContext = applicationApi.getCurrentGameContext();
        currentGameContext.getGameService().resume();
    }

    @Override
    public void showLoadFailedMessage() {
        adapter.showMessage(FAILED_TO_LOAD);

    }
}
