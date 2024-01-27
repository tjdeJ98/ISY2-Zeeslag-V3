package com.example.isy2zeeslagfx2.other;

public class Ship {
    private final int id;
    private final int size;
    private int health;

    public Ship(int shipId, int size)
    {
        this.id = shipId;
        this.size = size;
        this.health = size;
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
}
