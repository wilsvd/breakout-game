package com.example.dmscoursework.Controller.fxml;

import com.example.dmscoursework.View.Board.GameBoard;
import com.example.dmscoursework.StartGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
/**
 * The StartController class controls the startScreen.fxml. The
 * StartController is charge of loading the musicScreen, scoreScreen and
 * levelSelectScreen. It is also in charge of loading the theme that the
 * user selects. It also in charge of prompting to the user for a username
 * when loading the game.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class StartController {

    @FXML
    private ImageView m_BackgroundImage;

    private Stage m_Stage;
    private String m_Theme;
    private int m_Level = 0;
    private String m_Username;
    private boolean m_DialogComplete = false;

    /**
     * Gets the background image.
     *
     * @return an ImageView.
     */
    private ImageView getM_BackgroundImage() {
        return m_BackgroundImage;
    }

    /**
     * Get the username of player.
     *
     * @return a String, which is the username of player.
     */
    public String GetM_Username() {
        return m_Username;
    }

    /**
     * Set the username of player.
     *
     * @param username a String, which is the username of player.
     */
    private void setM_Username(String username) {
        this.m_Username = username;
    }

    /**
     * Get if the dialog box has been created.
     *
     * @return a boolean, if dialog box been created or not.
     */
    private boolean isM_DialogComplete() {
        return m_DialogComplete;
    }

    /**
     * Set if the dialog has been created.
     *
     * @param dialogComplete a boolean, if dialog box has been created or not.
     */
    private void setM_DialogComplete(boolean dialogComplete) {
        this.m_DialogComplete = dialogComplete;
    }

    /**
     * Get the stage.
     *
     * @return a Stage.
     */
    public Stage GetM_Stage() {
        return m_Stage;
    }

    /**
     * Set the stage.
     *
     * @param stage a Stage.
     */
    private void setM_Stage(Stage stage) {
        this.m_Stage = stage;
    }

    /**
     * Gets the theme of the game.
     *
     * @return a String, which is the theme of the game.
     */
    public String GetM_Theme() {
        return m_Theme;
    }
    /**
     * Sets the theme of the game.
     *
     * @param theme a String, which is the theme of the game.
     */
    public void SetM_Theme(String theme) {
        this.m_Theme = theme;
    }

    /**
     * Sets the level of the game.
     *
     * @param level an integer, which is the level of the game.
     */
    public void SetM_Level(int level) {
        m_Level = level;
    }
    /**
     * Get the level of the game.
     *
     * @return an integer, which is the level of the game.
     */
    public int GetM_Level() {
        return m_Level;
    }

    /**
     * Starts the game if the dialog is made.
     *
     * @param event an ActionEvent, which is a mouseclick from the user.
     */
    public void PlayGame(ActionEvent event) {
        makeDialog();
        setM_Stage((Stage) ((Node) event.getSource()).getScene().getWindow());
        if (isM_DialogComplete()) {
            new GameBoard(this);
            setM_DialogComplete(false);
        }
    }



    /**
     * Shows the level selection screen.
     *
     * @param event an ActionEvent, which is a mouseclick from the user.
     * @throws IOException if fxml file can't be loaded.
     */
    public void ShowLevelSelector(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartGame.class.getResource(
                "levelSelectorScreen.fxml"));

        Parent root = loader.load();
        LevelSelector controller = loader.getController();

        controller.SetLevelImages(GetM_Theme());

        if (GetM_Theme() == "sunset") {
            controller.SetSunsetTheme();
        } else if (GetM_Theme() == "ocean") {
            controller.SetOceanTheme();
        } else if (GetM_Theme() == "candy") {
            controller.SetCandyTheme();
        } else {
            controller.SetConstructionTheme();
        }

        Scene scene = new Scene(root);

        setM_Stage((Stage) ((Node) event.getSource()).getScene().getWindow());
        GetM_Stage().setScene(scene);
        GetM_Stage().show();
    }

    /**
     * Shows the leaderboard screen.
     *
     * @param event an ActionEvent, which is a mouseclick from the user.
     * @throws IOException if fxml file can't be loaded.
     */
    public void ShowScores(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartGame.class.getResource(
                "scoreScreen.fxml"));

        Parent root = loader.load();
        Scoreboard controller = loader.getController();

        if (GetM_Theme() == "sunset") {
            controller.SetSunsetTheme();
        } else if (GetM_Theme() == "ocean") {
            controller.SetOceanTheme();
        } else if (GetM_Theme() == "candy") {
            controller.SetCandyTheme();
        } else {
            controller.SetConstructionTheme();
        }
        Scene scene = new Scene(root);

        setM_Stage((Stage) ((Node) event.getSource()).getScene().getWindow());
        GetM_Stage().setScene(scene);
        GetM_Stage().show();
    }

    /**
     * Shows the music settings screen.
     *
     * @param event an ActionEvent, which is a mouseclick from the user.
     * @throws IOException if fxml can't be found.
     */
    public void ShowMusicSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartGame.class.getResource(
                "musicScreen.fxml"));

        Parent root = loader.load();
        MusicScreen controller = loader.getController();

        if (GetM_Theme() == "sunset") {
            controller.SetSunsetTheme();
        } else if (GetM_Theme() == "ocean") {
            controller.SetOceanTheme();
        } else if (GetM_Theme() == "candy") {
            controller.SetCandyTheme();
        } else {
            controller.SetConstructionTheme();
        }
        Scene scene = new Scene(root);

        setM_Stage((Stage) ((Node) event.getSource()).getScene().getWindow());
        GetM_Stage().setScene(scene);
        GetM_Stage().show();
    }

    /**
     * Make a dialog box which asks user to enter a username.
     */
    private void makeDialog() {
        TextInputDialog inputdialog = new TextInputDialog();
        inputdialog.setHeaderText("Go Make History!");
        inputdialog.setContentText("Username: ");
        inputdialog.setGraphic(null);

        Optional<String> result = inputdialog.showAndWait();

        result.ifPresent(string -> {
            setM_DialogComplete(true);
            setM_Username(inputdialog.getEditor().getText());
        });
    }

    /**
     * Set the theme to be the sunset theme..
     */
    public void SetSunsetTheme() {
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(
                    "src/Assets/OrangeAssets/SunsetBackground.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);
        getM_BackgroundImage().setImage(image);
        SetM_Theme("sunset");
    }

    /**
     * Set the theme to be the ocean theme.
     */
    public void SetOceanTheme() {
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(
                    "src/Assets/BlueAssets/OceanBackground.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);
        getM_BackgroundImage().setImage(image);
        SetM_Theme("ocean");
    }

    /**
     * Set the theme to be the candy theme.
     */
    public void SetCandyTheme() {
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(
                    "src/Assets/PinkAssets/CandyBackground.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);
        getM_BackgroundImage().setImage(image);
        SetM_Theme("candy");
    }

    /**
     * Set the theme to the default construction theme.
     */
    public void SetConstructionTheme() {
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(
                    "src/Assets/DefaultAssets/ConstructionBackground.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);
        getM_BackgroundImage().setImage(image);
    }

}