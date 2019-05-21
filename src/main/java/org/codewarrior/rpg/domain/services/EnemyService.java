package org.codewarrior.rpg.domain.services;

import org.codewarrior.rpg.domain.entities.Enemy;
import org.codewarrior.rpg.domain.entities.Player;

import java.util.List;

public interface EnemyService {
    public void create(Player player);

    public List<Enemy> get();
}
