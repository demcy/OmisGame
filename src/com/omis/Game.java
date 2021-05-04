package com.omis;

import com.omis.character.*;
import com.omis.item.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    static int interval = 0;

    public static void main(String[] args) {
        World world = new World(5, 10);
        Timer timer = new Timer();
        Map<EnemyType, Integer> defeatedEnemies = new HashMap<>();
        Player player = new Player();
        Enemy enemy = new Enemy();
        Healer healer = new Healer();
        QuestMaster questMaster = new QuestMaster();
        Sword sword = new Sword();
        Dagger dagger = new Dagger();
        Hammer hammer = new Hammer();
        Teleport teleport = new Teleport();
        world.setCharacters(Arrays.asList(questMaster, player, enemy, healer));
        world.setItems(Arrays.asList(sword, dagger, hammer, teleport));
        checkCharacterCoordinates(player, enemy, questMaster, healer, false);
//        world.setCharacters(Collections.singletonList(character));
        world.render();
        startTimer(timer);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        interactWithWorld(world, defeatedEnemies, player, enemy, healer, questMaster, sword, dagger, hammer, teleport, scanner, input);
        scanner.close();
        timer.cancel();

    }

    private static void interactWithWorld(World world, Map<EnemyType, Integer> defeatedEnemies, Player player, Enemy enemy, Healer healer, QuestMaster questMaster, Sword sword, Dagger dagger, Hammer hammer, Teleport teleport, Scanner scanner, String input) {
        while (!input.equals("end") && player.getLives() > 0) {
            setPlayerDirection(player, input);
            player.move();
            questMasterSeen(player, enemy, questMaster);
            healerSeen(player, healer);
            enemySeen(defeatedEnemies, player, enemy, healer, questMaster, scanner);
            checkIfPlayerCanGetItem(player, sword);
            checkIfPlayerCanGetItem(player, dagger);
            checkIfPlayerCanGetItem(player, hammer);
            checkIfPlayerCanGetItem(player, teleport);
            world.render();
            input = scanner.nextLine();
        }
    }

    private static void setPlayerDirection(Player player, String input) {
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
    }

    private static void healerSeen(Player player, Healer healer) {
        if (player.getxCoord() == healer.getxCoord() &&
                player.getyCoord() == healer.getyCoord()) {
            player.reBoost();
            System.out.println("HEALER IMPROVE YOUR HEALTH");
        }
    }

    private static void questMasterSeen(Player player, Enemy enemy, QuestMaster questMaster) {
        if (player.getxCoord() == questMaster.getxCoord() &&
                player.getyCoord() == questMaster.getyCoord())
            enemy.setVisible(true);
    }

    private static void enemySeen(Map<EnemyType, Integer> defeatedEnemies, Player player, Enemy enemy, Healer healer, QuestMaster questMaster, Scanner scanner) {
        String input;
        if (player.getxCoord() == enemy.getxCoord() &&
                player.getyCoord() == enemy.getyCoord() && enemy.isVisible()) {
            if (player.getItems().size() < 1) {
                System.out.println("YOU CANT BATTLE");
            } else if (player.getHealth() < 1) {
                System.out.println("YOU CANT BATTLE. YOU HAVE NOT HP");
            } else {
                chooseWeapon(defeatedEnemies, player, enemy, healer, questMaster, scanner);
            }
        }
    }

    private static void chooseWeapon(Map<EnemyType, Integer> defeatedEnemies, Player player, Enemy enemy, Healer healer, QuestMaster questMaster, Scanner scanner) {
        player.showItems();
        System.out.println("CHOOSE YOUR WEAPON: ");
        String input = scanner.nextLine();
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
                    startBattle(defeatedEnemies, player, enemy, healer, questMaster, scanner, (FigthWeapon) finalChosenItem);
                }
            } catch (NumberFormatException e) {
                input = printErrorAndAskAgain(player, scanner, "PUT IN NUMBER!");
            } catch (IndexOutOfBoundsException e) {
                input = printErrorAndAskAgain(player, scanner, "CHOOSE EXISTED WEAPON!");
            }  catch (Exception e) {
                System.out.println("UNWANTED ERROR");
            }
        }
    }

    private static String printErrorAndAskAgain(Player player, Scanner scanner, String error) {
        System.out.println(error);
        player.showItems();
        System.out.println("PUT IN NUMBER BETWEEN 1-3");
        return scanner.nextLine();
    }

    private static void startBattle(Map<EnemyType, Integer> defeatedEnemies, Player player, Enemy enemy, Healer healer, QuestMaster questMaster, Scanner scanner, FigthWeapon finalChosenItem) throws InterruptedException {
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println(player.getHealth() + " - " + enemy.getHealth());
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("PUT IN NUMBER BETWEEN 1-3");
            String userInput = scanner.nextLine();
            int randomNumber = (int) (Math.random() * 3 + 1);
            while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")) {
                userInput = scanner.nextLine();
            }
            System.out.println(randomNumber);
            if (randomNumber == Integer.parseInt(userInput)) {
                enemyHit(defeatedEnemies, player, enemy, healer, questMaster, finalChosenItem);
            } else {
                TimeUnit.MILLISECONDS.sleep(1000);
                player.loseHealth();
                System.out.println("YOUR HP: " + player.getHealth());
                if(player.getHealth() <= 0){
                    player.takeLife();
                    TimeUnit.MILLISECONDS.sleep(1000);
                    if(player.getLives() < 1) {
                        System.out.println("YOU DEAD");
                        defeatedEnemies.forEach((key, value) -> System.out.println(key + "---" + value));
                        System.out.println("TOTAL TIME: " + interval);
                    } else{
                        System.out.println("FIND HEALER");
                    }
                }
            }
        }
    }

    private static void enemyHit(Map<EnemyType, Integer> defeatedEnemies, Player player, Enemy enemy, Healer healer, QuestMaster questMaster, FigthWeapon finalChosenItem) {
        double healthTaken = finalChosenItem.hit();
        enemy.decreaseHealth((int) healthTaken);
        System.out.println("ENEMY HP: " + enemy.getHealth());
        if (enemy.getHealth() < 1) {
            int defeatedCount = defeatedEnemies.getOrDefault(enemy.getEnemyType(), 0);
            defeatedEnemies.put(enemy.getEnemyType(), ++defeatedCount);
            defeatedEnemies.forEach((key, value) -> System.out.println(key + "---" + value));
            enemy.setVisible(false);
            enemy.setRandomCoordinate();
            enemy.reBoost();
            checkCharacterCoordinates(player, enemy, questMaster, healer, true);
        }
    }

    private static void startTimer(Timer timer) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                interval++;
            }
        }, 1000, 1000);
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