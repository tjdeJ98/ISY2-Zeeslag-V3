package com.example.isy2zeeslagfx2.controller;

import com.example.isy2zeeslagfx2.model.game.Game;

public abstract class GameController  {
    protected Game game; // The game that this controller will manage

    public GameController(Game game) {
        this.game = game;
    }

    public abstract void initializeGame(); // Set up the game (e.g., board, players)
    public abstract void setupFase();
    public abstract void update(); // Update the game state
    public abstract void render(); // Render the game state (for graphical games)
    public abstract boolean isGameOver(); // Check if the game has ended
    public abstract void endGame(); // Perform any cleanup at the end of the game
    public abstract void switchPlayer();
}
