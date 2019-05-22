package org.codewarrior.rpg.api.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.common.Logger;
import org.codewarrior.rpg.api.*;
import org.codewarrior.rpg.domain.services.*;
import org.codewarrior.rpg.domain.services.impl.*;
import org.codewarrior.rpg.ports.Adapter;

import static org.codewarrior.rpg.api.config.Messages.BYE;
import static org.codewarrior.rpg.api.config.Messages.WELCOME;


public class ApplicationApiImpl implements ApplicationApi {
    private static final Logger LOGGER = Logger.getInstance(ApplicationApiImpl.class);

    private final AppContext appContext;
    private final Adapter adapter;
    private GameContext currentGameContext;


    ApplicationApiImpl(final AppContext appContext, final Adapter adapter) {
        this.appContext = Assert.notNull(appContext, "appContext");
        this.adapter = Assert.notNull(adapter, "adapter");
    }

    @Override
    public void run() {
        LOGGER.info("Application start requested");
        adapter.showMessage(WELCOME);
        MenuApi menuApi = appContext.getBean(MenuApi.class);
        menuApi.showMainMenu();
    }

    @Override
    public void shutdown() {
        LOGGER.info("ApplicationApiImpl shutdown requested");
        adapter.showMessage(BYE);
        appContext.clear();
        System.exit(1);
    }

    @Override
    public void createNewGameContext() {
        final RandomOperationHelper randomOperationHelper = new RandomOperationHelper();
        CharacterApi characterApi = appContext.getBean(CharacterApi.class);
        GameApi gameApi = appContext.getBean(GameApi.class);
        FightApi fightApi = appContext.getBean(FightApi.class);
        MenuApi menuApi = appContext.getBean(MenuApi.class);
        PlayerService playerService = new PlayerServiceImpl(characterApi, randomOperationHelper);
        EnemyService enemyService = new EnemyServiceImpl(randomOperationHelper);
        LocationService locationService = new LocationServiceImpl(randomOperationHelper);
        MapService mapService = new MapServiceImpl(enemyService, locationService);
        GameService gameService = new GameServiceImpl(gameApi, playerService, mapService, locationService, characterApi, menuApi);
        FightService fightService = new FightServiceImpl(fightApi, gameService, locationService, mapService, characterApi, menuApi, gameApi);
        NavigationService navigationService = new NavigationServiceImpl(gameService, locationService, mapService, fightService, appContext.getBean(NavigationApi.class), characterApi);
        currentGameContext = new GameContextBuilder().withGameService(gameService).
                withEnemyService(enemyService).withPlayerService(playerService).
                withLocationService(locationService).withMapService(mapService).withNavigationService(navigationService).withFightService(fightService).build();
    }

    @Override
    public void clearCurrentGameContext() {
        currentGameContext = null;
    }

    @Override
    public GameContext getCurrentGameContext() {
        return currentGameContext;
    }
}
