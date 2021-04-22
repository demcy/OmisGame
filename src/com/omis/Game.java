package com.omis;

import com.omis.character.Direction;
import com.omis.character.Enemy;
import com.omis.character.Player;
import com.omis.character.QuestMaster;
import com.omis.item.Dagger;
import com.omis.item.Hammer;
import com.omis.item.Item;
import com.omis.item.Sword;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        System.out.println("Hello World");
        World world = new World(5, 10);
        Player player = new Player("Player");
        Enemy enemy = new Enemy("Enemy");
        QuestMaster questMaster = new QuestMaster("QuestMaster");

        Sword sword = new Sword(10.0, 1);
        Dagger dagger = new Dagger(5.0, 2);
        Hammer hammer = new Hammer(3.0, 5);

        world.setCharacters(Arrays.asList(questMaster, player, enemy));
        world.setItems(Arrays.asList(sword, dagger, hammer));
        checkCharacterCoordinates(player, enemy, questMaster, false);



//        Character character = new Character("Player",  'X', true);
//        world.setCharacters(Collections.singletonList(character));


        world.render();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("end")) {
            switch (input) {
                case "w":
                    player.setDirection(Direction.UP);
                    break;
                case "s":
                    player.setDirection(Direction.DOWN);
                    break;
                case "a":
                    player.setDirection(Direction.LEFT);
                    break;
                case "d":
                    player.setDirection(Direction.RIGHT);
                    break;
            }
            player.move();
            if (player.getxCoord() == questMaster.getxCoord() &&
                    player.getyCoord() == questMaster.getyCoord())
                enemy.setVisible(true);
            if (player.getxCoord() == enemy.getxCoord() &&
                    player.getyCoord() == enemy.getyCoord()) {
                enemy.setVisible(false);
                enemy.setRandomCoordinate();
                checkCharacterCoordinates(player, enemy, questMaster, true);
            }
            world.render();
            input = scanner.nextLine();
        }

    }
    private static void checkCharacterCoordinates(Player player, Enemy enemy, QuestMaster questMaster, boolean playerOnQuestMaster){
        if (player.getxCoord() == questMaster.getxCoord() &&
                player.getyCoord() == questMaster.getyCoord()  && !playerOnQuestMaster ) {
            player.setRandomCoordinate();
            checkCharacterCoordinates(player, enemy, questMaster, playerOnQuestMaster);
        }
        if(player.getxCoord() == enemy.getxCoord() &&
                player.getyCoord() == enemy.getyCoord()) {
            enemy.setRandomCoordinate();

        }
        if (enemy.getxCoord() == questMaster.getxCoord() &&
                enemy.getyCoord() == questMaster.getyCoord() ) {
            enemy.setRandomCoordinate();
            checkCharacterCoordinates(player, enemy, questMaster, playerOnQuestMaster);
        }
    }
}
