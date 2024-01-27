package com.example.isy2zeeslagfx2.model.Board.Cell;

public class Cell {
    private int shipId;
    private boolean isHit;
    private String coordinate;

    public Cell(String coordinate) {
        this.shipId = -1;
        this.isHit = false;
        this.coordinate = coordinate;
    }

    // Getters and setters
    public int getShipid() {
        return shipId;
    }

    public void setHasShip(int shipId) {
        this.shipId = shipId;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit() {
        isHit = true;
    }

    public void setShipId(int shipId)
    {
        this.shipId = shipId;
    }

    public String getCoordinate() {
        return coordinate;
    }

    // Other methods as needed...
}