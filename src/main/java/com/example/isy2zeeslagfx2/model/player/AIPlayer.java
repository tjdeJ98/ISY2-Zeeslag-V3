package com.example.isy2zeeslagfx2.model.player;

import com.example.isy2zeeslagfx2.model.Board.Board;
import com.example.isy2zeeslagfx2.model.player.actionprocessor.PlayerActionProcessor;
import com.example.isy2zeeslagfx2.model.player.input.AIInputHandler;
import com.example.isy2zeeslagfx2.model.player.input.InputHandler;

public class AIPlayer implements Player {
    private AIInputHandler inputHandler;
    private PlayerActionProcessor actionProcessor;
    private Board board;
    private String name;

    public AIPlayer(String name, Board board, PlayerActionProcessor actionProcessor, AIInputHandler inputHandler)
    {
        this.inputHandler = inputHandler;
        this.actionProcessor = actionProcessor;
        this.board = board;
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void makeMove(Player otherPlayer) {

    }

    @Override
    public Board getBoard() {
        return null;
    }

    @Override
    public PlayerActionProcessor getActionProcessor() {
        return null;
    }

    @Override
    public InputHandler getInputHandler() {
        return inputHandler;
    }
}
