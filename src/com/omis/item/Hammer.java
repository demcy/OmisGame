package com.omis.item;

public class Hammer extends Item implements FigthWeapon{
    private static  double strength = 3.0;
    public Hammer() {
        super("HUMMER", strength, 5);
    }

    @Override
    public double hit() {
        strength = getStrengthFromItemType(strength);
        setLevel(getLevel() + 1);
        return strength;
    }
}