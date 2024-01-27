package com.example.isy2zeeslagfx2;

import com.example.isy2zeeslagfx2.controller.BattleshipsGameController;
import com.example.isy2zeeslagfx2.controller.GameController;
import com.example.isy2zeeslagfx2.model.game.BattleshipsGame;
import com.example.isy2zeeslagfx2.model.gameloop.GameLoop;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameController battleshipsController = new BattleshipsGameController(new BattleshipsGame());
        GameLoop gameLoop = new GameLoop(battleshipsController);
        gameLoop.run(); // Run Battleships game

//        FXMLLoader fxmlLoader = new FXMLLoader(GameApp.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(game.getView(), 800, 600);
//        stage.setTitle("Game Application!");
//        stage.setScene(scene);
//        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}