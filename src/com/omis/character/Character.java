package com.omis.character;

import com.omis.World;
import com.omis.WorldObject;

public class Character implements WorldObject {
    private  int xCoord;
    private  int yCoord;
    private final char symbol;
    private boolean isVisible;


    public Character(char symbol, boolean isVisible) {
        setRandomCoordinate();
        this.symbol = symbol;
        this.isVisible = isVisible;
    }

    public void setRandomCoordinate() {
        this.xCoord = (int) (Math.random() * (World.getWidth() - 1) + 1);
        this.yCoord = (int) (Math.random() * (World.getHeight() - 1) + 1);
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
