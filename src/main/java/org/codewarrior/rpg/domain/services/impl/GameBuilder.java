package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.domain.entities.Game;
import org.codewarrior.rpg.domain.entities.Map;
import org.codewarrior.rpg.domain.entities.Player;
import org.codewarrior.rpg.domain.values.ids.GameId;

import java.util.UUID;

public class GameBuilder {
    private Player player;
    private Map map;

    GameBuilder addPlayer(final Player player) {
        this.player = Assert.notNull(player, "Player cannot be null");
        return this;
    }

    GameBuilder addMap(final Map map) {
        this.map = Assert.notNull(map, "Map cannot be null");
        return this;
    }

    public Game build() {
        return new Game(GameId.of(UUID.randomUUID().toString()), player, map);
    }
}
