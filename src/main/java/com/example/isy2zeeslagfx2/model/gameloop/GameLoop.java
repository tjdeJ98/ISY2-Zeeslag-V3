package com.example.isy2zeeslagfx2.model.gameloop;

import com.example.isy2zeeslagfx2.controller.GameController;
import javafx.application.Platform;

public class GameLoop {
    private boolean running = false;
    private GameController gameController;

    public GameLoop(GameController gameController)
    {
        this.gameController = gameController;
    }

    public void run()
    {
        this.running = true;

        Thread gameThread = new Thread(() -> {
            while (running) {
                gameController.initializeGame();
                gameController.update(); // just game logic goes here, what needs to be done

                Platform.runLater(() -> {
                    // update UI and stuff like controller.render()
                });

                try {
                    Thread.sleep(100); // 60 updates per second possible
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    };

    public void stopGame() {
        running = false;
    }
}
