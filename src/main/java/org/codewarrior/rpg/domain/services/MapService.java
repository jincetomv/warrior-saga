package org.codewarrior.rpg.domain.services;

import org.codewarrior.rpg.domain.entities.Map;
import org.codewarrior.rpg.domain.entities.Player;

public interface MapService {
    public void create(Player player);

    public Map get();

    public void update();

    public boolean allEnemiesDead();

    public void load(Map map);
}
