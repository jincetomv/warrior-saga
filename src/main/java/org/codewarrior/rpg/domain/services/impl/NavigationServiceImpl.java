package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.NavigationApi;
import org.codewarrior.rpg.domain.entities.Character;
import org.codewarrior.rpg.domain.services.*;
import org.codewarrior.rpg.domain.services.config.MapConfiguration;
import org.codewarrior.rpg.domain.values.Coordinate;

public class NavigationServiceImpl implements NavigationService {
    private final GameService gameService;
    private final LocationService locationService;
    private final MapService mapService;
    private final FightService fightService;
    private final NavigationApi navigationApi;
    private final CharacterApi characterApi;

    public NavigationServiceImpl(final GameService gameService, final LocationService locationService, final MapService mapService, final FightService fightService, final NavigationApi navigationApi, final CharacterApi characterApi) {
        this.gameService = Assert.notNull(gameService, "gameService");
        this.locationService = Assert.notNull(locationService, "locationService");
        this.mapService = Assert.notNull(mapService, "mapService");
        this.fightService = Assert.notNull(fightService, "fightService");
        this.navigationApi = Assert.notNull(navigationApi, "navigationApi");
        this.characterApi = Assert.notNull(characterApi, "characterApi");
    }

    @Override
    public void up() {
        Coordinate currentCoordinate = getPlayerPosition();
        Coordinate newCoordinate = currentCoordinate.up();
        validateAndMove(currentCoordinate, newCoordinate);

    }

    @Override
    public void down() {
        Coordinate currentCoordinate = getPlayerPosition();
        Coordinate newCoordinate = currentCoordinate.down();
        validateAndMove(currentCoordinate, newCoordinate);
    }

    @Override
    public void left() {
        Coordinate currentCoordinate = getPlayerPosition();
        Coordinate newCoordinate = currentCoordinate.left();
        validateAndMove(currentCoordinate, newCoordinate);
    }


    @Override
    public void right() {
        Coordinate currentCoordinate = getPlayerPosition();
        Coordinate newCoordinate = currentCoordinate.right();
        validateAndMove(currentCoordinate, newCoordinate);

    }

    private void validateAndMove(Coordinate currentCoordinate, Coordinate newCoordinate) {
        if (isValid(newCoordinate)) {
            move(currentCoordinate, newCoordinate);
        } else {
            gameService.start();
        }
    }

    private Coordinate getPlayerPosition() {
        return gameService.get().getPlayer().getCurrentCoordinate();
    }

    private boolean isValid(Coordinate coordinate) {
        return (coordinate.getX() > MapConfiguration.MIN_X && coordinate.getX() < MapConfiguration.MAX_X) &&
                (coordinate.getY() > MapConfiguration.MIN_Y && coordinate.getY() < MapConfiguration.MAX_Y);
    }

    private void move(Coordinate currentCoordinate, Coordinate newCoordinate) {
        Character character = locationService.get(newCoordinate);
        if (character != null) {
            navigationApi.showEnemyWarning();
            characterApi.showCharacterInfo(character);
            fightService.fight(newCoordinate);
        } else {
            locationService.updatePlayerLocation(currentCoordinate, newCoordinate);
            mapService.update();
            gameService.start();
        }
    }


}
