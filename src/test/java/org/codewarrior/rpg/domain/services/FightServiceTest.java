package org.codewarrior.rpg.domain.services;

import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.FightApi;
import org.codewarrior.rpg.api.GameApi;
import org.codewarrior.rpg.api.MenuApi;
import org.codewarrior.rpg.domain.entities.*;
import org.codewarrior.rpg.domain.services.impl.FightServiceImpl;
import org.codewarrior.rpg.domain.values.*;
import org.codewarrior.rpg.domain.values.ids.CharacterId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.codewarrior.rpg.domain.services.config.CharacterConfiguration.BASE_HP;

@RunWith(PowerMockRunner.class)
public class FightServiceTest {
    private FightService fightService;

    private GameService gameService;
    private GameApi gameApi;
    private MapService mapService;
    private LocationService locationService;
    private CharacterApi characterApi;
    private MenuApi menuApi;
    private FightApi fightApi;

    private Player player;

    private Game game;
    private Enemy enemy;
    private Coordinate enemyCoordinate;
    private Coordinate playerCoordinate;

    public FightServiceTest() {
        gameService = PowerMockito.mock(GameService.class);
        fightApi = PowerMockito.mock(FightApi.class);
        gameApi = PowerMockito.mock(GameApi.class);
        mapService = PowerMockito.mock(MapService.class);
        locationService = PowerMockito.mock(LocationService.class);
        characterApi = PowerMockito.mock(CharacterApi.class);
        menuApi = PowerMockito.mock(MenuApi.class);

        createPlayer();
        game = PowerMockito.mock(Game.class);
        createEnemy();
        playerCoordinate = new Coordinate(5, 7);
        enemyCoordinate = new Coordinate(5, 6);

        fightService = new FightServiceImpl(fightApi, gameService, locationService, mapService, characterApi, menuApi, gameApi);
    }

    private void createPlayer() {
        CharacterId characterId = CharacterId.of("cId");
        Name playerName = new Name("player");
        Name characterName = new Name("character");
        HitPoint hitPoint = new HitPoint(100);
        Health health = new Health(hitPoint);
        Xp xp = new Xp(100);
        Experience experience = new Experience(Level.BEGINNER, xp);
        Complexity complexity = new Complexity(0.2322);

        player = new Player(characterId, playerName, characterName, health, experience, complexity);
    }

    private void createEnemy() {
        CharacterId characterId = CharacterId.of("eId");
        Name characterName = new Name("enemy");
        HitPoint hitPoint = new HitPoint(100);
        Health health = new Health(hitPoint);
        Xp xp = new Xp(100);
        Experience experience = new Experience(Level.BEGINNER, xp);
        Complexity complexity = new Complexity(0.2322);

        enemy = new Enemy(characterId, characterName, CharacterType.ENEMY, health, experience, complexity);
    }

    @Test
    public void attackTest() {
        PowerMockito.when(locationService.get(enemyCoordinate)).thenReturn(enemy);
        PowerMockito.when(gameService.get()).thenReturn(game);
        PowerMockito.when(game.getPlayer()).thenReturn(player);
        PowerMockito.doNothing().when(characterApi).showPlayerDiedWarning();
        PowerMockito.doNothing().when(menuApi).showNewGameMenu(null);
        PowerMockito.doNothing().when(characterApi).showCharacterInfo(player);
        PowerMockito.doNothing().when(characterApi).showCharacterInfo(enemy);
        PowerMockito.doNothing().when(fightApi).showFightMenu(enemyCoordinate);
        PowerMockito.doNothing().when(characterApi).showPlayerKilledEnemyMessage(Mockito.anyString(), Mockito.anyBoolean());
        PowerMockito.doNothing().when(locationService).updatePlayerLocation(playerCoordinate, enemyCoordinate);
        PowerMockito.doNothing().when(mapService).update();
        PowerMockito.when(mapService.allEnemiesDead()).thenReturn(true);
        PowerMockito.doNothing().when(gameApi).showWinMessage();
        PowerMockito.doNothing().when(menuApi).showNewGameMenu(player);
        fightService.attack(enemyCoordinate);
        Assert.assertTrue(enemy.getHealth().getHp().getValue() < BASE_HP);
    }

    @Test
    public void defendTest() {
        PowerMockito.when(locationService.get(enemyCoordinate)).thenReturn(enemy);
        PowerMockito.when(gameService.get()).thenReturn(game);
        PowerMockito.when(game.getPlayer()).thenReturn(player);
        PowerMockito.doNothing().when(characterApi).showPlayerDiedWarning();
        PowerMockito.doNothing().when(menuApi).showNewGameMenu(null);
        PowerMockito.doNothing().when(characterApi).showCharacterInfo(player);
        PowerMockito.doNothing().when(characterApi).showCharacterInfo(enemy);
        PowerMockito.doNothing().when(fightApi).showFightMenu(enemyCoordinate);
        PowerMockito.doNothing().when(characterApi).showPlayerKilledEnemyMessage(Mockito.anyString(), Mockito.anyBoolean());
        PowerMockito.doNothing().when(locationService).updatePlayerLocation(playerCoordinate, enemyCoordinate);
        PowerMockito.doNothing().when(mapService).update();
        PowerMockito.when(mapService.allEnemiesDead()).thenReturn(true);
        PowerMockito.doNothing().when(gameApi).showWinMessage();
        PowerMockito.doNothing().when(menuApi).showNewGameMenu(player);
        fightService.defend(enemyCoordinate);
        Assert.assertTrue(player.getHealth().getHp().getValue() < BASE_HP);
    }

}
