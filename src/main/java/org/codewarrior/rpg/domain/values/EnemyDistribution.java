package org.codewarrior.rpg.domain.values;

public final class EnemyDistribution {


    public EnemyDistribution(int beginnerEnemyCount, int fighterEnemyCount, int invaderEnemyCount, int achiverEnemyCount) {
        this.beginnerEnemyCount = beginnerEnemyCount;
        this.fighterEnemyCount = fighterEnemyCount;
        this.invaderEnemyCount = invaderEnemyCount;
        this.achiverEnemyCount = achiverEnemyCount;
    }

    private final int beginnerEnemyCount;
    private final int fighterEnemyCount;

    public int getBeginnerEnemyCount() {
        return beginnerEnemyCount;
    }

    public int getFighterEnemyCount() {
        return fighterEnemyCount;
    }

    public int getInvaderEnemyCount() {
        return invaderEnemyCount;
    }

    public int getAchiverEnemyCount() {
        return achiverEnemyCount;
    }

    private final int invaderEnemyCount;
    private final int achiverEnemyCount;
}
