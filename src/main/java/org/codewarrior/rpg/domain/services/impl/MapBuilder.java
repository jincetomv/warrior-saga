package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.rpg.domain.entities.Character;
import org.codewarrior.rpg.domain.entities.Map;
import org.codewarrior.rpg.domain.values.Coordinate;

public class MapBuilder {
    private java.util.Map<Coordinate, Character> locations;

    MapBuilder addLocations(final java.util.Map<Coordinate, Character> locations) {
        this.locations = locations;
        return this;
    }

    public Map build() {
        return new Map(locations);
    }
}
