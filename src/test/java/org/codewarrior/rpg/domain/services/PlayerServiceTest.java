package org.codewarrior.rpg.domain.services;

import org.codewarrior.rpg.api.CharacterApi;
import org.codewarrior.rpg.api.PlayerInfo;
import org.codewarrior.rpg.domain.entities.Experience;
import org.codewarrior.rpg.domain.entities.Health;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.services.config.LevelConfiguration;
import org.codewarrior.rpg.domain.services.impl.PlayerServiceImpl;
import org.codewarrior.rpg.domain.services.impl.RandomOperationHelper;
import org.codewarrior.rpg.domain.values.*;
import org.codewarrior.rpg.domain.values.ids.CharacterId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class PlayerServiceTest {
    private CharacterApi characterApi;
    private RandomOperationHelper randomOperationHelper;
    private PlayerService playerService;
    private PlayerInfo playerInfo;
    private Player player;


    public PlayerServiceTest() {
        characterApi = PowerMockito.mock(CharacterApi.class);
        randomOperationHelper = PowerMockito.mock(RandomOperationHelper.class);
        playerService = new PlayerServiceImpl(characterApi, randomOperationHelper);
        createPlayerInfo();
        createPlayer();
    }

    private void createPlayerInfo() {
        playerInfo = new PlayerInfo();
        playerInfo.setPlayerName("player");
        playerInfo.setCharacterName("character");
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

    @Test
    public void createAndGetTest() {
        PowerMockito.when(characterApi.playerInfo()).thenReturn(playerInfo);
        LevelConfiguration levelConfiguration = LevelConfiguration.levelConfigurations.get(Level.BEGINNER);
        PowerMockito.when(randomOperationHelper.randomComplexity(levelConfiguration.getComplexityMin(), levelConfiguration.getComplexityMax())).thenReturn(0.2322);
        PowerMockito.when(randomOperationHelper.randomInteger(levelConfiguration.getXpMin(), levelConfiguration.getXpMax())).thenReturn(100);
        playerService.create();
        Assert.assertEquals(playerService.get(), player);
    }

    @Test
    public void loadAndGetTest() {
        playerService.load(player);
        Assert.assertEquals(playerService.get(), player);
    }


}
