package org.codewarrior.rpg.domain.services;

import org.codewarrior.rpg.domain.entities.Player;

public interface PlayerService {
    public void create();

    public Player get();

    public void load(Player player);
}
