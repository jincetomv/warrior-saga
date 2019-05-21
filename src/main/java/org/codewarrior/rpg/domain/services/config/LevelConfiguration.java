package org.codewarrior.rpg.domain.services.config;

import org.codewarrior.rpg.domain.values.Level;

import java.util.EnumMap;

public class LevelConfiguration {
    private final int xpMin;
    private final int xpMax;
    private final Level level;
    private final double complexityMin;
    private final double complexityMax;
    private final int beginnerDistPercentage;
    private final int fighterDistPercentage;
    private final int invaderDistPercentage;
    private final int achieverDistPercentage;
    private final int killReward;
    private final double complexityGrowthFactor;
    public static final EnumMap<Level, LevelConfiguration> levelConfigurations = new EnumMap<>(Level.class);

    static {
        LevelConfiguration beginnerConfig = new LevelConfiguration(Beginner.XP_MIN, Beginner.XP_MAX, Beginner.LEVEL, Beginner.COMPLEXITY_MIN, Beginner.COMPLEXITY_MAX, Beginner.BEGINNER_DIST_PERCENTAGE, Beginner.FIGHTER_DIST_PERCENTAGE,
                Beginner.INVADER_DIST_PERCENTAGE, Beginner.ACHIEVER_DIST_PERCENTAGE, Beginner.KILL_REWARD, Beginner.COMPLEXITY_GROWTH_FACTOR);
        levelConfigurations.put(Beginner.LEVEL, beginnerConfig);
        LevelConfiguration fighterConfig = new LevelConfiguration(Fighter.XP_MIN, Fighter.XP_MAX, Fighter.LEVEL, Fighter.COMPLEXITY_MIN, Fighter.COMPLEXITY_MAX, Fighter.BEGINNER_DIST_PERCENTAGE, Fighter.FIGHTER_DIST_PERCENTAGE,
                Fighter.INVADER_DIST_PERCENTAGE, Fighter.ACHIEVER_DIST_PERCENTAGE, Fighter.KILL_REWARD, Fighter.COMPLEXITY_GROWTH_FACTOR);
        levelConfigurations.put(Fighter.LEVEL, fighterConfig);

        LevelConfiguration invaderConfig = new LevelConfiguration(Invader.XP_MIN, Invader.XP_MAX, Invader.LEVEL, Invader.COMPLEXITY_MIN, Invader.COMPLEXITY_MAX, Invader.BEGINNER_DIST_PERCENTAGE, Invader.FIGHTER_DIST_PERCENTAGE,
                Invader.INVADER_DIST_PERCENTAGE, Invader.ACHIEVER_DIST_PERCENTAGE, Invader.KILL_REWARD, Invader.COMPLEXITY_GROWTH_FACTOR);
        levelConfigurations.put(Invader.LEVEL, invaderConfig);

        LevelConfiguration achieverConfig = new LevelConfiguration(Achiever.XP_MIN, Achiever.XP_MAX, Achiever.LEVEL, Achiever.COMPLEXITY_MIN, Achiever.COMPLEXITY_MAX, Achiever.BEGINNER_DIST_PERCENTAGE, Achiever.FIGHTER_DIST_PERCENTAGE,
                Achiever.INVADER_DIST_PERCENTAGE, Achiever.ACHIEVER_DIST_PERCENTAGE, Achiever.KILL_REWARD, Achiever.COMPLEXITY_GROWTH_FACTOR);
        levelConfigurations.put(Achiever.LEVEL, achieverConfig);
    }

    private LevelConfiguration(int xpMin, int xpMax, Level level, double complexityMin, double complexityMax, int beginnerDistPercentage, int fighterDistPercentage, int invaderDistPercentage, int achieverDistPercentage, int killReward, double complexityGrowthFactor) {
        this.xpMin = xpMin;
        this.xpMax = xpMax;
        this.level = level;
        this.complexityMin = complexityMin;
        this.complexityMax = complexityMax;
        this.beginnerDistPercentage = beginnerDistPercentage;
        this.fighterDistPercentage = fighterDistPercentage;
        this.invaderDistPercentage = invaderDistPercentage;
        this.achieverDistPercentage = achieverDistPercentage;
        this.killReward = killReward;
        this.complexityGrowthFactor = complexityGrowthFactor;

    }

    public int getXpMin() {
        return xpMin;
    }

    public int getXpMax() {
        return xpMax;
    }

    public Level getLevel() {
        return level;
    }

    public double getComplexityMin() {
        return complexityMin;
    }

    public double getComplexityMax() {
        return complexityMax;
    }

    public int getBeginnerDistPercentage() {
        return beginnerDistPercentage;
    }

    public int getFighterDistPercentage() {
        return fighterDistPercentage;
    }

    public int getInvaderDistPercentage() {
        return invaderDistPercentage;
    }

    public int getAchieverDistPercentage() {
        return achieverDistPercentage;
    }

    public int getKillReward() {
        return killReward;
    }

    public double getComplexityGrowthFactor() {
        return complexityGrowthFactor;
    }


    private static class Beginner {
        static final int XP_MIN = 100;
        static final int XP_MAX = 250;
        static final Level LEVEL = Level.BEGINNER;
        static final double COMPLEXITY_MIN = 0.10;
        static final double COMPLEXITY_MAX = 0.25;
        static final int BEGINNER_DIST_PERCENTAGE = 50;
        static final int FIGHTER_DIST_PERCENTAGE = 30;
        static final int INVADER_DIST_PERCENTAGE = 10;
        static final int ACHIEVER_DIST_PERCENTAGE = 10;
        static final int KILL_REWARD = 10;
        static final double COMPLEXITY_GROWTH_FACTOR = 0.04;
    }

    private static class Fighter {
        static final int XP_MIN = 251;
        static final int XP_MAX = 500;
        static final Level LEVEL = Level.FIGHTER;
        static final double COMPLEXITY_MIN = 0.26;
        static final double COMPLEXITY_MAX = 0.50;
        static final int BEGINNER_DIST_PERCENTAGE = 20;
        static final int FIGHTER_DIST_PERCENTAGE = 50;
        static final int INVADER_DIST_PERCENTAGE = 20;
        static final int ACHIEVER_DIST_PERCENTAGE = 10;
        static final int KILL_REWARD = 20;
        static final double COMPLEXITY_GROWTH_FACTOR = 0.03;
    }

    private static class Invader {
        static final int XP_MIN = 501;
        static final int XP_MAX = 750;
        static final Level LEVEL = Level.INVADER;
        static final double COMPLEXITY_MIN = 0.51;
        static final double COMPLEXITY_MAX = 0.75;
        static final int BEGINNER_DIST_PERCENTAGE = 10;
        static final int FIGHTER_DIST_PERCENTAGE = 10;
        static final int INVADER_DIST_PERCENTAGE = 50;
        static final int ACHIEVER_DIST_PERCENTAGE = 30;
        static final int KILL_REWARD = 30;
        static final double COMPLEXITY_GROWTH_FACTOR = 0.02;
    }

    private static class Achiever {
        static final int XP_MIN = 751;
        static final int XP_MAX = 999;
        static final Level LEVEL = Level.ACHIEVER;
        static final double COMPLEXITY_MIN = 0.76;
        static final double COMPLEXITY_MAX = 0.99;
        static final int BEGINNER_DIST_PERCENTAGE = 5;
        static final int FIGHTER_DIST_PERCENTAGE = 15;
        static final int INVADER_DIST_PERCENTAGE = 30;
        static final int ACHIEVER_DIST_PERCENTAGE = 50;
        static final int KILL_REWARD = 40;
        static final double COMPLEXITY_GROWTH_FACTOR = 0.01;
    }

}
