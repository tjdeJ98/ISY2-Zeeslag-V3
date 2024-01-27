package com.example.isy2zeeslagfx2.model.player;

import com.example.isy2zeeslagfx2.model.Board.Board;
import com.example.isy2zeeslagfx2.model.player.actionprocessor.PlayerActionProcessor;
import com.example.isy2zeeslagfx2.model.player.input.InputHandler;

public interface Player {
    String getName();
    void initialize();
    void makeMove(Player otherPlayer);
    Board getBoard();
    PlayerActionProcessor getActionProcessor();
    InputHandler getInputHandler();

}
