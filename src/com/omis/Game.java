package com.omis;

import com.omis.character.*;
import com.omis.item.*;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        World world = new World(5, 10);
        Player player = new Player("Player");
        Enemy enemy = new Enemy("Enemy");
        Healer healer = new Healer();
        QuestMaster questMaster = new QuestMaster("QuestMaster");

        Sword sword = new Sword();
        Dagger dagger = new Dagger();
        Hammer hammer = new Hammer();
        Teleport teleport = new Teleport();

        world.setCharacters(Arrays.asList(questMaster, player, enemy, healer));
        world.setItems(Arrays.asList(sword, dagger, hammer, teleport));
        checkCharacterCoordinates(player, enemy, questMaster, healer, false);


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
                    player.getyCoord() == enemy.getyCoord() && enemy.isVisible()) {
                if (player.getItems().size() < 1) {
                    System.out.println("YOU CANT BATTLE");
                } else if (player.getHealth() < 1) {
                    System.out.println("YOU CANT BATTLE. YOU HAVE NOT HP");
                } else {
                    player.showItems();
                    System.out.println("CHOOSE YOUR WEAPON: ");
                    input = scanner.nextLine();
                    Item chosenItem = null;
                    while (chosenItem == null) {
                        try {
                            chosenItem = player.getItems().get(Integer.parseInt(input) - 1);
                            Item finalChosenItem = chosenItem;
                            player.getItems().stream()
                                    .filter(i -> i.getName().equals(finalChosenItem.getName()))
                                    .findFirst()
                                    .ifPresent(i -> i.setDurability(i.getDurability() - 1));
                            if (finalChosenItem.getName().equals("Teleport")) {
                                player.setRandomCoordinate();
                                checkCharacterCoordinates(player, enemy, questMaster, healer, false);
                            } else {
                                while (player.getHealth() > 0 && Enemy.getHealth() > 0) {
                                    System.out.println("PUT IN NUMBER BETWEEN 1-3");
                                    input = scanner.nextLine();
                                    int randomNumber = (int) (Math.random() * 3 + 1);
                                    while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                                        input = scanner.nextLine();
                                    }
                                    System.out.println(randomNumber);
                                    if (randomNumber == Integer.parseInt(input)) {
                                        ((FigthWeapon) finalChosenItem).hit();
                                        System.out.println(Enemy.getHealth());
                                        if (Enemy.getHealth() < 1) {
                                            enemy.setVisible(false);
                                            enemy.setRandomCoordinate();
                                            Enemy.reBoost();
                                            checkCharacterCoordinates(player, enemy, questMaster, healer, true);
                                        }
                                    } else {
                                        player.loseHealth();
                                        System.out.println(player.getHealth());
                                    }
                                }
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("PUT IN NUMBER!");
                            input = scanner.nextLine();
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("CHOOSE EXISTED WEAPON!");
                            input = scanner.nextLine();
                        }
                    }
                }
            }

            checkIfPlayerCanGetItem(player, sword);
            checkIfPlayerCanGetItem(player, dagger);
            checkIfPlayerCanGetItem(player, hammer);
            checkIfPlayerCanGetItem(player, teleport);


            world.render();
            input = scanner.nextLine();
        }

    }

    private static void checkIfPlayerCanGetItem(Player player, Item item) {
        if (player.getxCoord() == item.getxCoord() &&
                player.getyCoord() == item.getyCoord()) {
            player.addItems(item);
            item.setRandomCoordinate();
        }
    }

    private static void checkCharacterCoordinates(Player player, Enemy enemy, QuestMaster questMaster, Healer healer, boolean playerOnQuestMaster) {
        if (player.getxCoord() == questMaster.getxCoord() &&
                player.getyCoord() == questMaster.getyCoord() && !playerOnQuestMaster) {
            player.setRandomCoordinate();
            checkCharacterCoordinates(player, enemy, questMaster, healer, false);
        }
        if (player.getxCoord() == enemy.getxCoord() &&
                player.getyCoord() == enemy.getyCoord()) {
            enemy.setRandomCoordinate();
            checkCharacterCoordinates(player, enemy, questMaster, healer, playerOnQuestMaster);
        }
        if (healer.getxCoord() == questMaster.getxCoord() &&
                healer.getyCoord() == questMaster.getyCoord()) {
            healer.setRandomCoordinate();
            checkCharacterCoordinates(player, enemy, questMaster, healer, playerOnQuestMaster);
        }
        if (enemy.getxCoord() == questMaster.getxCoord() &&
                enemy.getyCoord() == questMaster.getyCoord()) {
            enemy.setRandomCoordinate();
            checkCharacterCoordinates(player, enemy, questMaster, healer, playerOnQuestMaster);
        }
    }
}