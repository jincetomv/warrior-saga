package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.FightApi;
import org.codewarrior.rpg.api.GameApi;
import org.codewarrior.rpg.api.MenuApi;
import org.codewarrior.rpg.domain.entities.Character;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.services.FightService;
import org.codewarrior.rpg.domain.services.GameService;
import org.codewarrior.rpg.domain.services.LocationService;
import org.codewarrior.rpg.domain.services.MapService;
import org.codewarrior.rpg.domain.services.config.LevelConfiguration;
import org.codewarrior.rpg.domain.values.Coordinate;
import org.codewarrior.rpg.domain.values.HitPoint;
import org.codewarrior.rpg.domain.values.Level;

import static org.codewarrior.rpg.domain.services.config.CharacterConfiguration.*;

public class FightServiceImpl implements FightService {
    private final FightApi fightApi;
    private final GameService gameService;
    private final LocationService locationService;
    private final MapService mapService;
    private final CharacterApi characterApi;
    private final MenuApi menuApi;
    private final GameApi gameApi;

    public FightServiceImpl(final FightApi fightApi, final GameService gameService, final LocationService locationService, final MapService mapService, final CharacterApi characterApi, final MenuApi menuApi, final GameApi gameApi) {
        this.fightApi = Assert.notNull(fightApi, "fightApi");
        this.gameService = Assert.notNull(gameService, "gameService");
        this.locationService = Assert.notNull(locationService, "locationService");
        this.mapService = Assert.notNull(mapService, "mapService");
        this.characterApi = Assert.notNull(characterApi, "characterApi");
        this.menuApi = Assert.notNull(menuApi, "menuApi");
        this.gameApi = Assert.notNull(gameApi, "gameApi");
    }

    @Override
    public void fight(Coordinate enemyCoordinate) {
        fightApi.showFightMenu(enemyCoordinate);
    }

    @Override
    public void attack(Coordinate enemyCoordinate) {
        Character enemy = locationService.get(enemyCoordinate);
        Player player = gameService.get().getPlayer();
        player.attack(enemy);
        operationsAfterAttackOrDefence(player, enemy, enemyCoordinate);

    }

    @Override
    public void defend(Coordinate enemyCoordinate) {
        Character enemy = locationService.get(enemyCoordinate);
        Player player = gameService.get().getPlayer();
        enemy.attack(player);
        operationsAfterAttackOrDefence(player, enemy, enemyCoordinate);
    }

    @Override
    public void stepBack() {
        gameService.start();
    }


    private void operationsAfterAttackOrDefence(Player player, Character enemy, Coordinate enemyCoordinate) {

        if (player.isDead()) {
            characterApi.showPlayerDiedWarning();
            menuApi.showNewGameMenu(null);
        } else if (enemy.isDead()) {
            doEnemyDeadOperations(player, enemy, enemyCoordinate);
            doAllEnemiesDeadOperations(player);
        } else

        {
            characterApi.showCharacterInfo(player);
            characterApi.showCharacterInfo(enemy);
            fight(enemyCoordinate);
        }

    }

    private void doEnemyDeadOperations(Player player, Character enemy, Coordinate enemyCoordinate) {
        player.awardReward(getKillRewardForLevel(enemy.getExperience().getLevel()));
        addBoosterHitPoints(player);
        boolean leveledUp = player.addComplexity(getComplexityGrowthFactorForLevel(enemy.getExperience().getLevel()));
        characterApi.showPlayerKilledEnemyMessage(enemy.getName().getValue(), leveledUp);
        locationService.updatePlayerLocation(player.getCurrentCoordinate(), enemyCoordinate);
        mapService.update();
        characterApi.showCharacterInfo(player);

    }

    private void addBoosterHitPoints(Player player) {
        int currentHp = player.getHealth().getHp().getValue();

        if (currentHp <= BASE_HP - KILL_HP_BOOSTER) {
            currentHp = currentHp + KILL_HP_BOOSTER;
        } else {
            int delta = BASE_HP - currentHp;
            currentHp = currentHp + delta;
        }
        player.getHealth().setHp(new HitPoint(currentHp));

    }

    private void doAllEnemiesDeadOperations(Player player) {
        if (mapService.allEnemiesDead()) {
            gameApi.showWinMessage();
            player.awardReward(WIN_REWARD);
            characterApi.showCharacterInfo(player);
            menuApi.showNewGameMenu(player);
        } else {
            gameService.start();
        }
    }

    private int getKillRewardForLevel(Level level) {
        return LevelConfiguration.levelConfigurations.get(level).getKillReward();
    }

    private double getComplexityGrowthFactorForLevel(Level level) {
        return LevelConfiguration.levelConfigurations.get(level).getComplexityGrowthFactor();
    }

}
