package org.codewarrior.rpg.api.impl;

import org.codewarrior.rpg.api.GameContext;
import org.codewarrior.rpg.domain.services.*;

public class GameContextBuilder {
    private GameService gameService;
    private PlayerService playerService;
    private EnemyService enemyService;
    private LocationService locationService;
    private MapService mapService;
    private NavigationService navigationService;
    private FightService fightService;

    GameContextBuilder withGameService(GameService gameService) {
        this.gameService = gameService;
        return this;
    }

    GameContextBuilder withPlayerService(PlayerService playerService) {
        this.playerService = playerService;
        return this;
    }

    GameContextBuilder withEnemyService(EnemyService enemyService) {
        this.enemyService = enemyService;
        return this;
    }

    GameContextBuilder withLocationService(LocationService locationService) {
        this.locationService = locationService;
        return this;
    }

    GameContextBuilder withMapService(MapService mapService) {
        this.mapService = mapService;
        return this;
    }

    GameContextBuilder withNavigationService(NavigationService navigationService) {
        this.navigationService = navigationService;
        return this;
    }

    GameContextBuilder withFightService(FightService fightService) {
        this.fightService = fightService;
        return this;
    }

    public GameContext build() {
        return new GameContext(gameService, playerService, enemyService, locationService, mapService, navigationService, fightService);
    }

}
