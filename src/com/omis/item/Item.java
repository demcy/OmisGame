package com.omis.item;

import com.omis.World;
import com.omis.WorldObject;

public class Item implements WorldObject {
    private String name;
    private double strength;
    private int durability;
    private  int xCoord;
    private  int yCoord;
    private  char symbol;
    private boolean isVisible;
    private int level;
    private ItemType itemType;

    public Item(String name, double strength, int durability) {
        this.name = name;
        this.strength = strength;
        this.durability = durability;
        setRandomCoordinate();
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
        if (this.level < 3){
            this.itemType = ItemType.SILVER;
        } else if (this.level < 5) {
            this.itemType = ItemType.GOLD;
        } else if (this.level < 7) {
            this.itemType = ItemType.PLATINUM;
        } else {
            this.itemType = ItemType.TITANIUM;
        }
    }

    public double getStrengthFromItemType(double strength) {
        switch (itemType){
            case SILVER:
                strength *= 0.75;
                break;
            case GOLD:
                strength *= 1;
                break;
            case PLATINUM:
                strength *= 1.25;
                break;
            case TITANIUM:
                strength *= 1.5;
                break;
        }
        return strength;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item: " +
                "name=" + name +
                ", strength=" + strength +
                ", durability=" + durability;
    }
}
