package com.fierced.ezgame;

import java.util.Random;

/**
 * Created by Fierced on 3/7/2018.
 */

public enum RuneType {
    CRYSTAL, DARK, EARTH, FIRE, LEAF, MECH,
    MIRROR, SNOW, STAR, TRASH, VOLT, WATER, WIND;

    public static RuneType getRandomType(Random random) {
        return values()[random.nextInt(values().length)];
    }

    public static RuneType getRandomAttackType(Random random) {
        RuneType retType = values()[random.nextInt(values().length)];

        while (retType == MIRROR || retType == MECH || retType == TRASH || retType == CRYSTAL)
            retType = values()[random.nextInt(values().length)];

        return retType;
    }
}