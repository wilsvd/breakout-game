package com.example.dmscoursework;

import com.example.dmscoursework.Controller.MusicController;
import com.example.dmscoursework.View.Board.StartScreen;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * The StartGame program implements an application that will run the game
 * BreakOut using JavaFX.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class StartGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new StartScreen(stage, null);
    }

    @Override
    public void stop() {
        MusicController.getInstance().PauseMedia();
    }

    /**
     * Launches the game.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}