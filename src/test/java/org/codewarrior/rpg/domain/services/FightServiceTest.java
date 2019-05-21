package org.codewarrior.rpg.domain.services;

import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.FightApi;
import org.codewarrior.rpg.api.GameApi;
import org.codewarrior.rpg.api.MenuApi;
import org.codewarrior.rpg.domain.entities.Enemy;
import org.codewarrior.rpg.domain.entities.Game;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.services.impl.FightServiceImpl;
import org.codewarrior.rpg.domain.values.Coordinate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

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

    public FightServiceTest() {
        gameService = PowerMockito.mock(GameService.class);
        fightApi = PowerMockito.mock(FightApi.class);
        gameApi = PowerMockito.mock(GameApi.class);
        mapService = PowerMockito.mock(MapService.class);
        locationService = PowerMockito.mock(LocationService.class);
        characterApi = PowerMockito.mock(CharacterApi.class);
        menuApi = PowerMockito.mock(MenuApi.class);

        player = PowerMockito.mock(Player.class);
        game = PowerMockito.mock(Game.class);
        enemy = PowerMockito.mock(Enemy.class);
        enemyCoordinate = new Coordinate(5, 6);

        fightService = new FightServiceImpl(fightApi, gameService, locationService, mapService, characterApi, menuApi, gameApi);
    }

    @Test
    public void test() {

    }

//    @Test
//    public void attackTest() {
//        PowerMockito.when(locationService.get(enemyCoordinate)).thenReturn(enemy);
//        PowerMockito.when(gameService.get()).thenReturn(game);
//        PowerMockito.when(game.getPlayer()).thenReturn(player);
//        PowerMockito.doNothing().when(characterApi).showPlayerDiedWarning();
//        PowerMockito.doNothing().when(menuApi).showNewGameMenu(null);
//        PowerMockito.doNothing().when(characterApi).showCharacterInfo(player);
//        PowerMockito.doNothing().when(characterApi).showCharacterInfo(enemy);
//        PowerMockito.doNothing().when(fightApi).showFightMenu(enemyCoordinate);
//        PowerMockito.doNothing().when(characterApi).showPlayerKilledEnemyMessage(Mockito.anyString(), Mockito.anyBoolean());
//        PowerMockito.doNothing().when(locationService).updatePlayerLocation(Mockito.any(Coordinate.class), enemyCoordinate);
//        PowerMockito.doNothing().when(mapService).update();
//        PowerMockito.when(mapService.allEnemiesDead()).thenReturn(true);
//        PowerMockito.doNothing().when(gameApi).showWinMessage();
//        PowerMockito.doNothing().when(menuApi).showNewGameMenu(player);
//        fightService.attack(enemyCoordinate);
//
//    }

}
