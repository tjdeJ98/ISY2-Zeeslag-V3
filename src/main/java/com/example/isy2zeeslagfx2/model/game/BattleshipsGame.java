package com.example.isy2zeeslagfx2.model.game;

import com.example.isy2zeeslagfx2.model.Board.BattleshipsBoard;
import com.example.isy2zeeslagfx2.model.player.LocalHumanPlayer;
import com.example.isy2zeeslagfx2.model.player.Player;
import com.example.isy2zeeslagfx2.model.player.actionprocessor.BattleshipActionProcess;

public class BattleshipsGame implements Game {
    // Game state variables specific to Battleships, such as the board, ships, player turns, etc.
    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn;
    private boolean firstRound;
    private int turnCount;

    public BattleshipsGame()
    {
        player1 = new LocalHumanPlayer("Timo", new BattleshipsBoard(8), new BattleshipActionProcess());
        player2 = new LocalHumanPlayer("Guest", new BattleshipsBoard(8), new BattleshipActionProcess());
        isPlayer1Turn = true;
        firstRound = true;
        turnCount = 0;
    }

    @Override
    public void initialize() {
        turnCount++;
        if (isPlayer1Turn) {
            player1.initialize();
        } else {
            player2.initialize();
            firstRound = false;
        }


        // Handle player input, update the board, check for hits/misses, switch turns, etc.
        // Initialize the board, place ships, set the current player, etc.
    }

    @Override
    public void update() {
        if (turnCount>2) {
            getCurPlayer().makeMove(getOtherPlayer());
        }
        // Handle player input, update the board, check for hits/misses, switch turns, etc.
        isPlayer1Turn = !isPlayer1Turn;
    }

    @Override
    public boolean isGameOver() {
        // Check if all ships of a player are sunk, or other game over condition
        return false; // Replace with actual game over condition
    }

    @Override
    public void endGame() {
        // Handle cleanup at the end of a Battleships game, e.g., declaring the winner, resetting state
    }

    private Player getCurPlayer()
    {
        if (isPlayer1Turn) {
            return player1;
        }
        return player2;
    }

    private Player getOtherPlayer()
    {
        if (isPlayer1Turn) {
            return player2;
        }
        return player1;
    }

    // Additional Battleships-specific methods, such as checking for hits, managing ship placement, etc.
}
