package com.omis.item;

import com.omis.character.Enemy;

public class Dagger extends Item implements FigthWeapon{
    private static double strength = 5.0;
    public Dagger() {
        super("DAGGER", strength, 2);
    }

    @Override
    public void hit() {
        Enemy.decreaseHealth(strength);
        setLevel(getLevel() + 1);
        strength = getStrengthFromItemType(strength);
    }
}
