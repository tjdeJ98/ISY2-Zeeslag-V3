package com.example.isy2zeeslagfx2.controller;

import com.example.isy2zeeslagfx2.model.game.BattleshipsGame;

public class BattleshipsGameController extends GameController {
    public BattleshipsGameController(BattleshipsGame game) {
        super(game);
    }

    @Override
    public void initializeGame() {
        // Initialize Battleships-specific components like ships, board, etc.
        game.initialize();
    }

    @Override
    public void setupFase() {
        // Check if it is the setupFase of the game or not.
    }

    @Override
    public void update() {
        // Update Battleships game state: process moves, check for hits/misses, etc.
        game.update();

        // Here you can handle additional logic related to user interaction, UI updates, etc.
        // Example: get user input for the next move, update the display of the game board
    }

    @Override
    public void render() {
        // Render the game state to the user interface
        // Example: display the current state of the game board, show messages to the player
    }

    @Override
    public boolean isGameOver() {
        return game.isGameOver();
        // Determine if the game has ended, e.g., all ships sunk
    }

    @Override
    public void endGame() {
        // Handle the end of the game: delegate to the game's endGame method
        game.endGame();
        // Additional logic for ending the game, such as displaying the winner, cleanup
    }

    @Override
    public void switchPlayer() {
        game.switchPlayer();
    }

}
