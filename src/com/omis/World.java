package com.omis;

import java.util.List;

public class World {
    private static int height;
    private static int width;
    private List<Character> characters;

    public World(int height, int width, List<Character> characters) {
        World.height = height;
        World.width = width;
        this.characters = characters;
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

                for (Character c : characters) {
                    if(c.getxCoord() == x && c.getyCoord() == y){
                        symbol = c.getSymbol();
                    }
                }

                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
