package org.codewarrior.rpg.domain.services.impl;

import org.codewarrior.rpg.domain.values.Coordinate;

import java.util.Random;

public class RandomOperationHelper {
    private final Random random = new Random();

    Coordinate randomCoordinate(final int minX, final int minY, final int maxX, final int maxY) {

        final int x = random.nextInt(maxX - minX + 1) + minX;
        final int y = random.nextInt(maxY - minY + 1) + minY;
        return new Coordinate(x, y);
    }

    public double randomComplexity(final double min, final double max) {


        return min + (max - min) * random.nextDouble();

    }

    public int randomInteger(final int min, final int max) {


        return random.nextInt(max - min + 1) + min;
    }
}
