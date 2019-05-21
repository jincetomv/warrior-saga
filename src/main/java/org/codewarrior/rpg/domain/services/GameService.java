package org.codewarrior.rpg.domain.services;

import org.codewarrior.rpg.domain.entities.Game;
import org.codewarrior.rpg.domain.entities.Player;

public interface GameService {
    public void create(Player player);

    public void create();

    public Game get();

    public void start();

    public void save();

    public void resume();
}
