package com.example.isy2zeeslagfx2.model.player;

import com.example.isy2zeeslagfx2.model.Board.Board;
import com.example.isy2zeeslagfx2.model.game.Game;
import com.example.isy2zeeslagfx2.model.player.actionprocessor.PlayerActionProcessor;
import com.example.isy2zeeslagfx2.other.ConsoleHandler;
import com.example.isy2zeeslagfx2.other.MoveInfo;
import com.example.isy2zeeslagfx2.other.Ship;

import java.util.ArrayList;
import java.util.List;

public class LocalHumanPlayer implements Player {
    private Board board;
    private String name;
    private PlayerActionProcessor actionProcessor;

    public LocalHumanPlayer(String name, Board board, PlayerActionProcessor actionProcessor)
    {
        this.name = name;
        this.board = board;
        this.actionProcessor = actionProcessor;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void initialize() {
        actionProcessor.initialize(this);
    }

    @Override
    public void makeMove(Player otherPlayer) {
        actionProcessor.makeMove(null, null, new MoveInfo("makeMove", otherPlayer));
    }

    public PlayerActionProcessor getActionProcessor()
    {
        return actionProcessor;
    }

    public Board getBoard() {
        return board;
    }
}


