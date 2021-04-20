package com.omis;

public class Character {
    private  String name;
    private  int xCoord;
    private  int yCoord;
    private  char symbol;
    private boolean isVisible;
    private Direction direction;

    public Character(String name, int xCoord, int yCoord, char symbol, boolean isVisible) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.symbol = symbol;
        this.isVisible = isVisible;
        this.direction = Direction.UP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        switch (direction) {
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
