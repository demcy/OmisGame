package com.omis.character;

import com.omis.World;
import com.omis.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player extends Character{
    private Direction direction;
    private int health;
    private List<Item> items = new ArrayList<>();

    public Player(String name) {
        super(name, 'X', true);
        this.direction = Direction.UP;
        this.health = 10;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }

    public void showItems() {
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getDurability() > 0 )
                System.out.println(i + 1 + ": " + items.get(i).toString());
        }
    }

    public List<Item> getItems() {
        return items.stream()
                .filter(i -> i.getDurability() > 0)
                .collect(Collectors.toList());
    }

    public void addItems(Item item) {
        if(!items.contains(item)){
            this.items.add(item);
            System.out.println(item.getName());
        } else {
            this.items.stream()
                    .filter(i -> i.getName().equals(item.getName()))
                    .findFirst()
                    .ifPresent(i -> i.setDurability(i.getDurability() + 1));
            System.out.println(item.getName() + item.getDurability() + "+");
        }
    }

    public void move() {
        switch (this.direction) {
            case UP :
                if(getyCoord() > 1)
                    setyCoord(getyCoord() - 1);
                break;
            case DOWN :
                if(getyCoord() < World.getHeight() - 1)
                    setyCoord(getyCoord() + 1);
                break;
            case LEFT :
                if(getxCoord() > 1)
                    setxCoord(getxCoord() - 1);
                break;
            case RIGHT :
                if(getxCoord() < World.getWidth() - 1)
                    setxCoord(getxCoord() + 1);
                break;
        }
    }

    public void loseHealth() {
        this.health--;
    }
}
