package org.codewarrior.rpg.domain.services;

import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.NavigationApi;
import org.codewarrior.rpg.domain.entities.Experience;
import org.codewarrior.rpg.domain.entities.Game;
import org.codewarrior.rpg.domain.entities.Health;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.services.impl.NavigationServiceImpl;
import org.codewarrior.rpg.domain.values.*;
import org.codewarrior.rpg.domain.values.ids.CharacterId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class NavigationServiceTest {
    private NavigationService navigationService;
    private GameService gameService;
    private LocationService locationService;
    private MapService mapService;
    private FightService fightService;
    private NavigationApi navigationApi;
    private CharacterApi characterApi;

    private Player player;
    private Game game;

    public NavigationServiceTest() {
        gameService = PowerMockito.mock(GameService.class);
        locationService = PowerMockito.mock(LocationService.class);
        mapService = PowerMockito.mock(MapService.class);
        fightService = PowerMockito.mock(FightService.class);
        navigationApi = PowerMockito.mock(NavigationApi.class);
        characterApi = PowerMockito.mock(CharacterApi.class);
        navigationService = new NavigationServiceImpl(gameService, locationService, mapService, fightService, navigationApi, characterApi);
        game = PowerMockito.mock(Game.class);
        createPlayer();
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
        Coordinate playerCoordinate = new Coordinate(7, 8);
        player = new Player(characterId, playerName, characterName, health, experience, complexity);
        player.setCurrentCoordinate(playerCoordinate);

    }

    @Test
    public void upTest() {
        PowerMockito.when(gameService.get()).thenReturn(game);
        PowerMockito.when(game.getPlayer()).thenReturn(player);
        navigationService.up();
        Mockito.verify(gameService, Mockito.times(1)).start();
    }

    @Test
    public void downTest() {
        PowerMockito.when(gameService.get()).thenReturn(game);
        PowerMockito.when(game.getPlayer()).thenReturn(player);
        navigationService.down();
        Mockito.verify(gameService, Mockito.times(1)).start();
    }

    @Test
    public void leftTest() {
        PowerMockito.when(gameService.get()).thenReturn(game);
        PowerMockito.when(game.getPlayer()).thenReturn(player);
        navigationService.left();
        Mockito.verify(gameService, Mockito.times(1)).start();
    }

    @Test
    public void rightTest() {
        PowerMockito.when(gameService.get()).thenReturn(game);
        PowerMockito.when(game.getPlayer()).thenReturn(player);
        navigationService.right();
        Mockito.verify(gameService, Mockito.times(1)).start();
    }
}
