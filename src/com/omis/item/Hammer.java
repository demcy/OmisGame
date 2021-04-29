package com.omis.item;

import com.omis.character.Enemy;

public class Hammer extends Item implements FigthWeapon{
    private static  double strength = 3.0;
    public Hammer() {
        super("HUMMER", strength, 5);
    }

    @Override
    public void hit() {
        Enemy.decreaseHealth(strength);
        setLevel(getLevel() + 1);
        strength = getStrengthFromItemType(strength);
    }
}
