package com.omis.item;

public class Dagger extends Item implements FigthWeapon{
    private static double strength = 5.0;
    public Dagger() {
        super("DAGGER", strength, 2);
    }

    @Override
    public double hit() {
        strength = getStrengthFromItemType(strength);
        setLevel(getLevel() + 1);
        return strength;
    }
}