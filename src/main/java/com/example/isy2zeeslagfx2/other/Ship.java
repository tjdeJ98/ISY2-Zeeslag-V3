package com.example.isy2zeeslagfx2.other;

import java.util.List;

public class Ship {
    private final int id;
    private final int size;
    private List<String> coordinates;
    private int health;

    public Ship(int shipId, int size)
    {
        this.id = shipId;
        this.size = size;
        this.health = size;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void takeHit()
    {
        health--;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getHealth() {
        return health;
    }
}
