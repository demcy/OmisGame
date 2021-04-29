package com.omis.item;

import com.omis.character.Enemy;

public class Sword extends Item implements FigthWeapon{
    private static  double strength = 10.0;
    public Sword() {
        super("SWORD", strength, 1);
    }

    @Override
    public void hit() {
        Enemy.killed();
        setLevel(getLevel() + 1);
        strength = getStrengthFromItemType(strength);
    }
}
