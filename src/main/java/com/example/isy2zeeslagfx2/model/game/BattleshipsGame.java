package com.example.isy2zeeslagfx2.model.game;

import com.example.isy2zeeslagfx2.model.Board.BattleshipsBoard;
import com.example.isy2zeeslagfx2.model.player.AIPlayer;
import com.example.isy2zeeslagfx2.model.player.LocalHumanPlayer;
import com.example.isy2zeeslagfx2.model.player.Player;
import com.example.isy2zeeslagfx2.model.player.actionprocessor.BattleshipActionProcess;
import com.example.isy2zeeslagfx2.model.player.input.AIInputHandler;
import com.example.isy2zeeslagfx2.model.player.input.ConsoleInputHandler;
import com.example.isy2zeeslagfx2.other.MoveInfo;

public class BattleshipsGame implements Game {
    // Game state variables specific to Battleships, such as the board, ships, player turns, etc.
    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn;
    private int turnCount;

    public BattleshipsGame()
    {
        player1 = new LocalHumanPlayer("Timo", new BattleshipsBoard(8), new BattleshipActionProcess(), new ConsoleInputHandler());
        player2 = new AIPlayer("AI", new BattleshipsBoard(8), new BattleshipActionProcess(), new AIInputHandler());
        isPlayer1Turn = true;
        turnCount = 0;
    }

    @Override
    public void initialize() {
        turnCount++;
        getCurPlayer().initialize();
        // Handle player input, update the board, check for hits/misses, switch turns, etc.
        // Initialize the board, place ships, set the current player, etc.
    }

    @Override
    public void update() {
        if (turnCount>2) {
            getCurPlayer().makeMove(getOtherPlayer());
        }
        // Handle player input, update the board, check for hits/misses, switch turns, etc.
    }

    @Override
    public boolean isGameOver() {
        // Check if all ships of a player are sunk, or other game over condition
        boolean check = getOtherPlayer().getActionProcessor().checks(null, null, new MoveInfo("check ships sunk"));
        if (check) {
            System.out.println(getCurPlayer().getName() + " is the winner!");
            return true;
        }
        return false;
    }

    @Override
    public void endGame() {
        // Handle cleanup at the end of a Battleships game, e.g., declaring the winner, resetting state
    }

    @Override
    public void switchPlayer() {
        isPlayer1Turn = !isPlayer1Turn;
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
