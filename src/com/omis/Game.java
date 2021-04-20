package com.omis;

import java.util.Collections;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        System.out.println("Hello World");

        Character character = new Character("Player", 5, 2, 'X', true);
        World world = new World(5,20, Collections.singletonList(character));
        world.render();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("end")){
            switch (input) {
                case "w" :
                    character.setDirection(Direction.UP);
                    break;
                case "s" :
                    character.setDirection(Direction.DOWN);
                    break;
                case "a" :
                    character.setDirection(Direction.LEFT);
                    break;
                case "d" :
                    character.setDirection(Direction.RIGHT);
                    break;
            }
            character.move();
            world.render();
            input = scanner.nextLine();
        }

    }
}
