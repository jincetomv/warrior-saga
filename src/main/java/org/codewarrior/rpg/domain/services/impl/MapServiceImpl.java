package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.domain.entities.Enemy;
import org.codewarrior.rpg.domain.entities.Map;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.services.EnemyService;
import org.codewarrior.rpg.domain.services.LocationService;
import org.codewarrior.rpg.domain.services.MapService;

import java.util.List;

public class MapServiceImpl implements MapService {
    private final EnemyService enemyService;


    private final LocationService locationService;
    private Map currentMap;

    public MapServiceImpl(EnemyService enemyService, LocationService locationService) {
        this.enemyService = Assert.notNull(enemyService, "enemyService");
        this.locationService = Assert.notNull(locationService, "locationService");
    }


    @Override
    public void create(Player player) {
        enemyService.create(player);
        List<Enemy> enemies = enemyService.get();
        locationService.create(player, enemies);
        currentMap = new MapBuilder().addLocations(locationService.get()).build();


    }

    @Override
    public Map get() {
        return currentMap;
    }

    @Override
    public boolean allEnemiesDead() {
        return currentMap.getEnemyCount() == 0;
    }

    @Override
    public void update() {
        currentMap.setLocations(locationService.get());
    }

    @Override
    public void load(Map map) {
        currentMap = map;
    }
}
