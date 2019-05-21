package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.domain.entities.Character;
import org.codewarrior.rpg.domain.entities.Enemy;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.services.LocationService;
import org.codewarrior.rpg.domain.services.config.MapConfiguration;
import org.codewarrior.rpg.domain.values.Coordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationServiceImpl implements LocationService {

    private final java.util.Map<Coordinate, Character> locations = new HashMap<>();

    public LocationServiceImpl(RandomOperationHelper randomOperationHelper) {
        this.randomOperationHelper = Assert.notNull(randomOperationHelper, "randomOperationHelper");
    }

    private final RandomOperationHelper randomOperationHelper;


    @Override
    public void create(Player player, List<Enemy> enemies) {
        enemies.forEach(character -> {
            Coordinate coordinate = getNewCoordinate();
            locations.put(coordinate, character);
        });
        Coordinate playerCoordinate = getNewCoordinate();
        player.setCurrentCoordinate(playerCoordinate);
        locations.put(playerCoordinate, player);
    }

    @Override
    public java.util.Map<Coordinate, Character> get() {
        return locations;
    }

    @Override
    public Character get(Coordinate coordinate) {
        return locations.get(coordinate);
    }

    private Coordinate getNewCoordinate() {
        //range is from 0-X and 0-Y
        //The addition to min values are because lower value is border of map
        //The subtraction to max values are because higher value is border of map
        Coordinate randomCoordinate = randomOperationHelper.randomCoordinate(MapConfiguration.MIN_X + 1, MapConfiguration.MIN_Y + 1, MapConfiguration.MAX_X - 1, MapConfiguration.MAX_Y - 1);

        if (locations.containsKey(randomCoordinate)) {
            return getNewCoordinate();
        }
        return randomCoordinate;
    }

    @Override
    public void load(Map<Coordinate, Character> locations) {
        this.locations.putAll(locations);
    }

    @Override
    public void updatePlayerLocation(Coordinate oldCoordinate, Coordinate newCoordinate) {
        Player player = (Player) locations.get(oldCoordinate);
        locations.remove(oldCoordinate);
        player.setCurrentCoordinate(newCoordinate);
        locations.put(newCoordinate, player);
    }

}
