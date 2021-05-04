package com.omis.item;

public class Sword extends Item implements FigthWeapon{
    private static  double strength = 10.0;
    public Sword() {
        super("SWORD", strength, 1);
    }

    @Override
    public double hit() {
        strength = getStrengthFromItemType(strength);
        setLevel(getLevel() + 1);
        return strength;
    }
}