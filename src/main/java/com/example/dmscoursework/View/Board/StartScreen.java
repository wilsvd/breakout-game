package com.example.dmscoursework.View.Board;

import com.example.dmscoursework.Controller.MusicController;
import com.example.dmscoursework.Controller.fxml.StartController;
import com.example.dmscoursework.StartGame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * The StartScreen is the first screen that the user sees. This allows the
 * user to navigate the start screen. The user can navigate to the
 * musicScreen, scoreScreen, levelSelectorScreen and the gameBoard though the
 * start screen. The user can also select what theme they want the game to be
 * in whether that is the default construction theme, the sunset theme, the
 * ocean theme or the candy theme.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class StartScreen {

    /**
     * Constructs a start screen using information from the FXML document.
     *
     * @param stage a Stage, which is what the start screen will be displayed
     *             on.
     * @param theme a String, which is the theme of the start screen.
     * @throws IOException if fxml file can't be found.
     */
    public StartScreen(Stage stage, String theme) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartGame.class.getResource(
                "startScreen.fxml"));

        Parent root = loader.load();
        StartController controller = loader.getController();
        MusicController.getInstance().CancelTimer();
        if (MusicController.getInstance().GetMediaPlayer().getStatus() !=
                MediaPlayer.Status.PAUSED) {
            MusicController.getInstance().PlayMedia();
        }

        if (theme == "sunset") {
            controller.SetSunsetTheme();
        } else if (theme == "ocean") {
            controller.SetOceanTheme();
        } else if (theme == "candy") {
            controller.SetCandyTheme();
        } else {
            controller.SetConstructionTheme();
        }
        Scene scene = new Scene(root);
        stage.setTitle("B R E A K O U T!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
}
