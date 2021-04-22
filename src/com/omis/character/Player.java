package com.omis.character;

import com.omis.World;

public class Player extends Character{
    private Direction direction;
    private int health;

    public Player(String name) {
        super(name, 'X', true);
        this.direction = Direction.UP;
        //this.health = 3;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        switch (this.direction) {
            case UP :
                if(getyCoord() > 1)
                    setyCoord(getyCoord() - 1);
                break;
            case DOWN :
                if(getyCoord() < World.getHeight() - 1)
                    setyCoord(getyCoord() + 1);
                break;
            case LEFT :
                if(getxCoord() > 1)
                    setxCoord(getxCoord() - 1);
                break;
            case RIGHT :
                if(getxCoord() < World.getWidth() - 1)
                    setxCoord(getxCoord() + 1);
                break;
        }
    }
}
