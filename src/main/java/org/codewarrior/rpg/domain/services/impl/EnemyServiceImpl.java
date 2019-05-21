package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.domain.entities.Enemy;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.services.EnemyService;
import org.codewarrior.rpg.domain.services.config.CharacterConfiguration;
import org.codewarrior.rpg.domain.services.config.LevelConfiguration;
import org.codewarrior.rpg.domain.values.EnemyDistribution;
import org.codewarrior.rpg.domain.values.EnemyName;
import org.codewarrior.rpg.domain.values.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnemyServiceImpl implements EnemyService {
    private final RandomOperationHelper randomOperationHelper;
    private List<EnemyName> enemyNames = Arrays.asList(EnemyName.values());
    private List<EnemyName> selectedNames = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();

    public EnemyServiceImpl(RandomOperationHelper randomOperationHelper) {
        this.randomOperationHelper = Assert.notNull(randomOperationHelper, "randomOperationHelper");
    }

    @Override
    public List<Enemy> get() {
        return enemies;
    }

    @Override
    public void create(Player player) {

        Level playerLevel = player.getExperience().getLevel();
        LevelConfiguration playerLevelConfiguration = LevelConfiguration.levelConfigurations.get(playerLevel);

        EnemyDistribution distribution = createEnemyDistribution(playerLevelConfiguration);
        createEnemies(Level.BEGINNER, distribution.getBeginnerEnemyCount());
        createEnemies(Level.FIGHTER, distribution.getFighterEnemyCount());
        createEnemies(Level.INVADER, distribution.getInvaderEnemyCount());
        createEnemies(Level.ACHIEVER, distribution.getAchiverEnemyCount());


    }

    private EnemyDistribution createEnemyDistribution(LevelConfiguration levelConfiguration) {


        int beginnerEnemyCount = getCountBasedOnPercentage(levelConfiguration.getBeginnerDistPercentage(), CharacterConfiguration.ENEMY_COUNT);
        int fighterEnemyCount = getCountBasedOnPercentage(levelConfiguration.getFighterDistPercentage(), CharacterConfiguration.ENEMY_COUNT);
        int invaderEnemyCount = getCountBasedOnPercentage(levelConfiguration.getInvaderDistPercentage(), CharacterConfiguration.ENEMY_COUNT);
        int achieverEnemyCount = getCountBasedOnPercentage(levelConfiguration.getAchieverDistPercentage(), CharacterConfiguration.ENEMY_COUNT);

        return new EnemyDistribution(beginnerEnemyCount, fighterEnemyCount, invaderEnemyCount, achieverEnemyCount);
    }

    private int getCountBasedOnPercentage(int percentage, int total) {
        return (int) (total * ((float) percentage / 100.0f));
    }

    private void createEnemies(Level level, int count) {
        LevelConfiguration levelConfiguration = LevelConfiguration.levelConfigurations.get(level);

        for (int i = 0; i < count; i++) {
            enemies.add(create(getRandomName().toString(), CharacterConfiguration.BASE_HP, randomOperationHelper.randomInteger(levelConfiguration.getXpMin(), levelConfiguration.getXpMax()),
                    levelConfiguration.getLevel(), randomOperationHelper.randomComplexity(levelConfiguration.getComplexityMin(), levelConfiguration.getComplexityMax())));
        }

    }

    private Enemy create(String name, int hp, int xp, Level level, double complexity) {
        return new EnemyBuilder().addName(name).addHitPoint(hp).addXp(xp).addLevel(level).addComplexity(complexity).build();
    }

    private EnemyName getRandomName() {
        final EnemyName name = enemyNames.get(randomOperationHelper.randomInteger(0, enemyNames.size() - 1));
        if (selectedNames.contains(name)) {
            return getRandomName();
        }
        selectedNames.add(name);
        return name;
    }
}
