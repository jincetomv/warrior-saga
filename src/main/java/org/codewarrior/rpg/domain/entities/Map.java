package org.codewarrior.rpg.domain.entities;

import org.codewarrior.common.Assert;
import org.codewarrior.rpg.domain.services.config.MapConfiguration;
import org.codewarrior.rpg.domain.values.Coordinate;

import java.io.Serializable;
import java.util.stream.Collectors;

public class Map implements Serializable {


    private static final long serialVersionUID = 7031738761189950658L;

    public void setLocations(java.util.Map<Coordinate, Character> locations) {
        this.locations = locations;
    }

    public java.util.Map<Coordinate, Character> getLocations() {
        return locations;
    }

    private java.util.Map<Coordinate, Character> locations;

    public Map(final java.util.Map<Coordinate, Character> locations) {
        this.locations = Assert.notNull(locations, "locations");
    }

    public int getEnemyCount() {
        return locations.values().stream().filter(value -> value instanceof Enemy).collect(Collectors.toList()).size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Map)) {
            return false;
        }

        Map that = (Map) o;

        return locations.equals(that.locations);
    }

    @Override
    public String toString() {
        StringBuilder mapBuilder = new StringBuilder();
        for (int i = MapConfiguration.MIN_Y; i <= MapConfiguration.MAX_Y; i++) {
            mapBuilder.append("\n");
            for (int j = MapConfiguration.MIN_X; j <= MapConfiguration.MAX_X; j++) {
                if (i == MapConfiguration.MIN_Y || i == MapConfiguration.MAX_Y) {
                    mapBuilder.append("-");
                } else if (j == MapConfiguration.MIN_X || j == MapConfiguration.MAX_X) {
                    mapBuilder.append("|");
                } else {
                    Character character = locations.get(new Coordinate(j, i));
                    if (character != null) {
                        mapBuilder.append(character.getCharacterType().getValue());
                    } else {
                        mapBuilder.append(" ");
                    }

                }
            }

        }

        return mapBuilder.toString();
    }
}
