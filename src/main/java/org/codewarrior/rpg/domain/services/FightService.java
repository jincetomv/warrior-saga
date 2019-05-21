package org.codewarrior.rpg.domain.services;

import org.codewarrior.rpg.domain.values.Coordinate;

public interface FightService {
    public void fight(Coordinate enemyCoordinate);

    public void attack(Coordinate enemyCoordinate);

    public void defend(Coordinate enemyCoordinate);

    public void stepBack();
}
