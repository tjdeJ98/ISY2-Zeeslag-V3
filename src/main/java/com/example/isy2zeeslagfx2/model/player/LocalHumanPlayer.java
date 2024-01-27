package com.example.isy2zeeslagfx2.model.player;

import com.example.isy2zeeslagfx2.model.Board.Board;
import com.example.isy2zeeslagfx2.model.player.actionprocessor.PlayerActionProcessor;
import com.example.isy2zeeslagfx2.model.player.input.ConsoleInputHandler;
import com.example.isy2zeeslagfx2.model.player.input.InputHandler;
import com.example.isy2zeeslagfx2.other.MoveInfo;

public class LocalHumanPlayer implements Player {
    private Board board;
    private String name;
    private PlayerActionProcessor actionProcessor;
    private ConsoleInputHandler consoleInputHandler;

    public LocalHumanPlayer(String name, Board board, PlayerActionProcessor actionProcessor, ConsoleInputHandler consoleInputHandler)
    {
        this.consoleInputHandler = consoleInputHandler;
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
        consoleInputHandler.getInput("coordinate");
        actionProcessor.makeMove(null, null, new MoveInfo("make shot",this ,otherPlayer)); // TODO dit werkt niet voor tictactoe
    }

    public PlayerActionProcessor getActionProcessor()
    {
        return actionProcessor;
    }

    @Override
    public InputHandler getInputHandler() {
        return consoleInputHandler;
    }

    public Board getBoard() {
        return board;
    }
}


