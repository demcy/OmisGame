package com.omis.character;

import com.omis.World;
import com.omis.WorldObject;

import java.util.Random;

public class Character implements WorldObject {
    private  String name;
    private  int xCoord;
    private  int yCoord;
    private  char symbol;
    private boolean isVisible;


    public Character(String name, char symbol, boolean isVisible) {
        this.name = name;
        setRandomCoordinate();
//        this.xCoord = getRandomCoordinate(World.getWidth());
//        this.yCoord = getRandomCoordinate(World.getHeight());
        this.symbol = symbol;
        this.isVisible = isVisible;
    }

    public void setRandomCoordinate() {
        this.xCoord = (int) (Math.random() * (World.getWidth() - 1) + 1);
        this.yCoord = (int) (Math.random() * (World.getHeight() - 1) + 1);
        //return (int) (Math.random() * (worldParameter - 1) + 1);
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




}
