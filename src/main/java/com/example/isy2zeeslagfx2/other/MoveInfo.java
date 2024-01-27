package com.example.isy2zeeslagfx2.other;

import com.example.isy2zeeslagfx2.model.player.Player;

public class MoveInfo {
    private String moveType;
    private Ship ship;
    private Player curPlayer;
    private Player otherPlayer;

    public MoveInfo(String moveType, Ship ship)
    {
        this.moveType = moveType;
        this.ship = ship;
    }

    public MoveInfo(String moveType, Player player)
    {
        this.moveType = moveType;
        this.otherPlayer = player;
    }

    public MoveInfo(){}

    public MoveInfo (String moveType, Player curPlayer, Player otherPlayer) {
        this.moveType = moveType;
        this.curPlayer = curPlayer;
        this.otherPlayer = otherPlayer;
    }

    public MoveInfo(String moveType)
    {
        this.moveType = moveType;
    }

    public MoveInfo(Ship ship)
    {
        this.ship = ship;
    }

    public String getMoveType() {
        return moveType;
    }

    public Ship getShip() {
        return ship;
    }

    public Player getCurPlayer()
    {
        return curPlayer;
    }

    public Player getOtherPlayer()
    {
        return otherPlayer;
    }
}
