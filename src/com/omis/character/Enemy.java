package com.omis.character;

public class Enemy extends Character implements FightCharacter{
    private static int health;
    private EnemyType enemyType;
    public Enemy() {
        super('Z', false);
        reBoost();
        enemyType = EnemyType.getRandomEnemyType();
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(double healthTaken) {
        health -= healthTaken;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    private void healthByCharacterType() {
        switch (enemyType) {
            case THIEF:
                health = (int) (Math.random() * 3 + 1);
                break;
            case WARRIOR:
                health = (int) (Math.random() * 13 + 1);
                break;
            case ARCHER:
                health = (int) (Math.random() * 5 + 1);
                break;
            case GOBLIN:
                health = (int) (Math.random() * 9 + 1);
                break;
            case ORC:
                health = (int) (Math.random() * 11 + 1);
                break;
            case WIZARD:
                health = (int) (Math.random() * 7 + 1);
                break;
            case DRAGON:
                health = (int) (Math.random() * 15 + 1);
                break;
        }
    }

    public void reBoost() {
        enemyType = EnemyType.getRandomEnemyType();
        this.healthByCharacterType();
    }
}