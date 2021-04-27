package com.omis.character;

import com.omis.item.Item;

public class Enemy extends Character{
    private int health;
    public Enemy(String name) {
        super(name, 'Z', false);
        this.health = 10;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void loseHealth(Item item) {
        this.health = this.health - item.getStrength();
    }
}
