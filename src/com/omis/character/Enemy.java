package com.omis.character;

public class Enemy extends Character{
    private static int health;
    private EnemyType enemyType;
    public Enemy(String name) {
        super(name, 'Z', false);
        reBoost();
        enemyType = EnemyType.getRandomEnemyType();
    }

    public static int getHealth() {
        return health;
    }

    public static void decreaseHealth(double healthTaken) {
        health -= healthTaken;
    }

    public static void killed(){
        health = 0;
    }

    public static void reBoost() {
        health = (int) (Math.random() * 10 + 1);
    }
}
