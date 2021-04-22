package com.omis;

import com.omis.character.Character;
import com.omis.item.Item;

import java.util.List;

public class World {
    private static int height;
    private static int width;
    private List<Character> characters;
    private List<Item> items;

    public World(int height, int width) {
        World.height = height;
        World.width = width;
    }

    public static int getHeight() {
        return height;
    }

//    public void setHeight(int height) {
//        World.height = height;
//    }

    public static int getWidth() {
        return width;
    }

//    public void setWidth(int width) {
//        World.width = width;
//    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void render() {
        for (int y = 0; y <= height; y++) {
            char symbol;
            for (int x = 0; x <= width; x++) {
                if (y == 0 || y == height) {
                    symbol = '-';
                } else if (x == 0 || x == width) {
                    symbol = '|';
                } else {
                    symbol = ' ';
                }

                for (Item i : items) {
                    if(i.getxCoord() == x && i.getyCoord() == y && i.isVisible()){
                        symbol = i.getSymbol();
                    }
                }

                for (Character c : characters) {
                    if(c.getxCoord() == x && c.getyCoord() == y && c.isVisible()){
                        symbol = c.getSymbol();
                    }
                }



                System.out.print(symbol);
            }
            System.out.println();
        }
    }


}
