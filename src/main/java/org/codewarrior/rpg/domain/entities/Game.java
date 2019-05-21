package org.codewarrior.rpg.domain.entities;

import org.codewarrior.common.domain.Entity;
import org.codewarrior.rpg.domain.values.ids.GameId;

import java.io.Serializable;

public class Game implements Entity<GameId>, Serializable {
    private static final long serialVersionUID = -295496650776554577L;
    private final GameId gameId;
    private final Player player;
    private final Map map;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }

        Game that = (Game) o;


        return player.equals(that.player) && map.equals(that.map);
    }

    public Map getMap() {
        return map;
    }


    public Game(final GameId gameId, final Player player, final Map map) {
        this.gameId = gameId;
        this.player = player;
        this.map = map;
    }

    @Override
    public GameId id() {
        return gameId;
    }

    public Player getPlayer() {
        return player;
    }


}
