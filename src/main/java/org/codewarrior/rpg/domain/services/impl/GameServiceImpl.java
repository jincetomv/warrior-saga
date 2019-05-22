package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.common.Logger;
import org.codewarrior.common.util.FileUtil;
import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.GameApi;
import org.codewarrior.rpg.api.MenuApi;
import org.codewarrior.rpg.domain.entities.Game;
import org.codewarrior.rpg.domain.entities.Map;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.services.GameService;
import org.codewarrior.rpg.domain.services.LocationService;
import org.codewarrior.rpg.domain.services.MapService;
import org.codewarrior.rpg.domain.services.PlayerService;

import java.io.IOException;

import static org.codewarrior.rpg.domain.services.config.FileSystemLocations.GAME_SAVE_DIR;
import static org.codewarrior.rpg.domain.services.config.FileSystemLocations.GAME_SAVE_FILE;

public class GameServiceImpl implements GameService {
    private static final Logger LOGGER = Logger.getInstance(GameServiceImpl.class);
    private final PlayerService playerService;
    private final MapService mapService;
    private final LocationService locationService;
    private final GameApi gameApi;
    private final CharacterApi characterApi;
    private final MenuApi menuApi;


    private Game currentGame;

    public GameServiceImpl(final GameApi gameApi, final PlayerService playerService, final MapService mapService, final LocationService locationService, final CharacterApi characterApi, MenuApi menuApi) {
        this.gameApi = Assert.notNull(gameApi, "gameApi");
        this.playerService = Assert.notNull(playerService, "playerService");
        this.mapService = Assert.notNull(mapService, "mapService");
        this.locationService = Assert.notNull(locationService, "locationService");
        this.characterApi = Assert.notNull(characterApi, "characterApi");
        this.menuApi = Assert.notNull(menuApi, "menuApi");

    }

    @Override
    public void create(Player player) {
        gameApi.showPlayerCreatedMessage();
        characterApi.showCharacterInfo(player);
        mapService.create(player);
        Map map = mapService.get();
        currentGame = new GameBuilder().addPlayer(player).addMap(map).build();
    }

    @Override
    public void create() {
        playerService.create();
        Player player = playerService.get();
        create(player);


    }

    @Override
    public Game get() {
        return currentGame;
    }

    @Override
    public void start() {
        gameApi.showMap(currentGame.getMap());
        gameApi.showGameMenu();
    }

    @Override
    public void save() {
        try {
            FileUtil.saveObject(currentGame, GAME_SAVE_DIR, GAME_SAVE_FILE);
            gameApi.showSaveSuccessMessage();
            menuApi.showMainMenu();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            gameApi.showSaveFailedMessage();
            start();
        }

    }


    @Override
    public void resume() {
        try {
            Game savedGame = FileUtil.loadSavedObject(GAME_SAVE_DIR.concat("/").concat(GAME_SAVE_FILE), Game.class);
            load(savedGame);
            start();
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.error(ex.getMessage(), ex);
            gameApi.showLoadFailedMessage();
            menuApi.showMainMenu();
        }

    }

    private void load(Game savedGame) {
        playerService.load(savedGame.getPlayer());
        mapService.load(savedGame.getMap());
        locationService.load(savedGame.getMap().getLocations());
        currentGame = savedGame;

    }


}
