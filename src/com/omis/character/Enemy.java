package com.omis.character;

public class Enemy extends Character{
    private int health;
    public Enemy(String name) {
        super(name, 'Z', false);
        this.health = 3;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
