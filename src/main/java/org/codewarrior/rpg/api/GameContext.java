package org.codewarrior.rpg.api;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.domain.services.*;

public class GameContext {
    private final GameService gameService;
    private final PlayerService playerService;
    private final EnemyService enemyService;
    private final LocationService locationService;
    private final MapService mapService;
    private final NavigationService navigationService;
    private final FightService fightService;

    public GameService getGameService() {
        return gameService;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public EnemyService getEnemyService() {
        return enemyService;
    }

    public LocationService getLocationService() {
        return locationService;
    }

    public MapService getMapService() {
        return mapService;
    }

    public NavigationService getNavigationService() {
        return navigationService;
    }

    public FightService getFightService() {
        return fightService;
    }


    public GameContext(GameService gameService, PlayerService playerService, EnemyService enemyService, LocationService locationService, MapService mapService, NavigationService navigationService, FightService fightService) {
        this.gameService = Assert.notNull(gameService, "gameService");
        this.playerService = Assert.notNull(playerService, "playerService");
        this.enemyService = Assert.notNull(enemyService, "enemyService");
        this.locationService = Assert.notNull(locationService, "locationService");
        this.mapService = Assert.notNull(mapService, "mapService");
        this.navigationService = Assert.notNull(navigationService, "navigationService");
        this.fightService = Assert.notNull(fightService, "fightService");
    }


}
