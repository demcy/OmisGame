package com.omis.character;

public enum EnemyType {
    THIEF, WARRIOR, ARCHER, GOBLIN, ORC, WIZARD, DRAGON;

    public static EnemyType getRandomEnemyType() {
        return values()[(int) (Math.random() * values().length)];
    }
}
