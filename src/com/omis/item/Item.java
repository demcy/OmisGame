package com.omis.item;

import com.omis.World;
import com.omis.WorldObject;

public class Item implements WorldObject {
    private double strength;
    private int durability;
    private  int xCoord;
    private  int yCoord;
    private  char symbol;
    private boolean isVisible;
    private int level;
    private ItemType itemType;

    public Item(double strength, int durability) {
        this.strength = strength;
        this.durability = durability;
        //setRandomCoordinate();
        this.symbol = 'I';
        this.isVisible = true;
        this.level = 0;
        this.itemType = ItemType.SILVER;
    }

    public void setRandomCoordinate() {
        this.xCoord = (int) (Math.random() * (World.getWidth() - 1) + 1);
        this.yCoord = (int) (Math.random() * (World.getHeight() - 1) + 1);
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
