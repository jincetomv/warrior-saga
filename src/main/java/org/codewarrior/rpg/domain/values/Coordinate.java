package org.codewarrior.rpg.domain.values;


import org.codewarrior.common.domain.Value;

import java.io.Serializable;
import java.util.Objects;

public final class Coordinate implements Value, Serializable {
    private static final long serialVersionUID = 33495374349864990L;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private final int x;
    private final int y;

    public Coordinate up() {
        return new Coordinate(x, y - 1);
    }

    public Coordinate down() {
        return new Coordinate(x, y + 1);
    }

    public Coordinate left() {
        return new Coordinate(x - 1, y);
    }

    public Coordinate right() {
        return new Coordinate(x + 1, y);
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coordinate)) {
            return false;
        }

        Coordinate that = (Coordinate) o;

        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
