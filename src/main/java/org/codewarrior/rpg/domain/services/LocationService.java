package org.codewarrior.rpg.domain.services;

import org.codewarrior.rpg.domain.entities.Character;
import org.codewarrior.rpg.domain.entities.Enemy;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.values.Coordinate;

import java.util.List;

public interface LocationService {

    public void create(Player player, List<Enemy> enemies);

    public java.util.Map<Coordinate, Character> get();

    public Character get(Coordinate coordinate);

    public void updatePlayerLocation(Coordinate oldCoordinate, Coordinate newCoordinate);

    public void load(java.util.Map<Coordinate, Character> locations);


}
