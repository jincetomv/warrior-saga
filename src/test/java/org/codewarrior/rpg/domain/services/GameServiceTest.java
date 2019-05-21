package org.codewarrior.rpg.domain.services;

import org.codewarrior.common.util.FileUtil;
import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.GameApi;
import org.codewarrior.rpg.api.MenuApi;
import org.codewarrior.rpg.domain.entities.Character;
import org.codewarrior.rpg.domain.entities.Game;
import org.codewarrior.rpg.domain.entities.Map;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.services.impl.GameServiceImpl;
import org.codewarrior.rpg.domain.values.Coordinate;
import org.codewarrior.rpg.domain.values.ids.GameId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GameServiceImpl.class, FileUtil.class})
public class GameServiceTest {
    private GameService gameService;
    private GameApi gameApi;
    private PlayerService playerService;
    private MapService mapService;
    private LocationService locationService;
    private CharacterApi characterApi;
    private MenuApi menuApi;

    public GameServiceTest() {
        gameApi = PowerMockito.mock(GameApi.class);
        playerService = PowerMockito.mock(PlayerService.class);
        mapService = PowerMockito.mock(MapService.class);
        locationService = PowerMockito.mock(LocationService.class);
        characterApi = PowerMockito.mock(CharacterApi.class);
        menuApi = PowerMockito.mock(MenuApi.class);
        gameService = new GameServiceImpl(gameApi, playerService, mapService, locationService, characterApi, menuApi);


    }


    @Test
    public void createAndGetTestWithPlayer() {

        UUID uuid = UUID.randomUUID();
        PowerMockito.mockStatic(UUID.class);
        Player player = PowerMockito.mock(Player.class);
        Map map = PowerMockito.mock(Map.class);
        Game game = new Game(new GameId(uuid.toString()), player, map);
        PowerMockito.doNothing().when(gameApi).showPlayerCreatedMessage();
        PowerMockito.doNothing().when(characterApi).showCharacterInfo(player);
        PowerMockito.doNothing().when(mapService).create(player);
        PowerMockito.when(mapService.get()).thenReturn(map);
        PowerMockito.when(UUID.randomUUID()).thenReturn(uuid);
        gameService.create(player);
        Assert.assertEquals(gameService.get(), game);
    }

    @Test
    public void createAndGetTestWithoutPlayer() {

        UUID uuid = UUID.randomUUID();
        PowerMockito.mockStatic(UUID.class);
        Player player = PowerMockito.mock(Player.class);
        Map map = PowerMockito.mock(Map.class);
        Game game = new Game(new GameId(uuid.toString()), player, map);
        PowerMockito.doNothing().when(gameApi).showPlayerCreatedMessage();
        PowerMockito.doNothing().when(characterApi).showCharacterInfo(player);
        PowerMockito.doNothing().when(mapService).create(player);
        PowerMockito.when(mapService.get()).thenReturn(map);
        PowerMockito.when(playerService.get()).thenReturn(player);
        PowerMockito.when(UUID.randomUUID()).thenReturn(uuid);
        gameService.create();
        Assert.assertEquals(gameService.get(), game);
    }

    @Test
    public void resumeAndGetTest() {

        UUID uuid = UUID.randomUUID();
        PowerMockito.mockStatic(FileUtil.class);
        Player player = PowerMockito.mock(Player.class);
        Map map = PowerMockito.mock(Map.class);
        java.util.Map<Coordinate, Character> locations = new HashMap<>();
        map.setLocations(locations);
        Game game = new Game(new GameId(uuid.toString()), player, map);
        PowerMockito.doNothing().when(gameApi).showPlayerCreatedMessage();
        PowerMockito.doNothing().when(locationService).load(locations);
        PowerMockito.doNothing().when(playerService).load(player);
        PowerMockito.doNothing().when(mapService).load(map);
        PowerMockito.doNothing().when(gameApi).showMap(map);
        PowerMockito.doNothing().when(gameApi).showGameMenu();
        try {
            PowerMockito.when(FileUtil.loadSavedObject("games/saved-game.ser", Game.class)).thenReturn(game);
            gameService.resume();
            Assert.assertEquals(gameService.get(), game);
        } catch (ClassNotFoundException | IOException e) {
            Assert.assertFalse(e != null);
        }
    }

   
}
