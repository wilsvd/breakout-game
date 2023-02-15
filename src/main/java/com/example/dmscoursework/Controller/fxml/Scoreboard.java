package com.example.dmscoursework.Controller.fxml;

import com.example.dmscoursework.Model.ScoreReader;
import com.example.dmscoursework.View.Board.StartScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * The Scoreboard class controls the scoreScreen.fxml. This allows the user
 * to view the scoreboard. The scoreboard contains a list of the top 10
 * players and their rankings in order.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class Scoreboard implements Initializable {

    private final int WORD_0 = 0;
    private final int WORD_1 = 1;
    private final int WORD_2 = 2;

    @FXML
    private ImageView m_BackgroundImage;
    @FXML
    private Text m_Username;
    @FXML
    private Text m_Score;
    @FXML
    private Text m_Time;

    private String m_Theme;

    /**
     * Gets the background image.
     *
     * @return an ImageView, which is the background image.
     */
    private ImageView getM_BackgroundImage() {
        return m_BackgroundImage;
    }

    /**
     * Gets the username of the player.
     *
     * @return a Text, which is the username of the player.
     */
    private Text getM_Username() {
        return m_Username;
    }

    /**
     * Gets the score of the player.
     *
     * @return a Text, which is the score of the player.
     */
    private Text getM_Score() {
        return m_Score;
    }

    /**
     * Gets the time of the player.
     *
     * @return a Text, which is the time of the player.
     */
    private Text getM_Time() {
        return m_Time;
    }

    /**
     * Gets the theme.
     *
     * @return a String, which is the theme.
     */
    private String getM_Theme() {
        return m_Theme;
    }
    /**
     * Sets the theme.
     *
     * @param theme a String, which is the theme.
     */
    private void setM_Theme(String theme) {
        this.m_Theme = theme;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeScoreBoard();
    }

    /**
     * Makes the scoreboard using the scoreReader which reads the file with the
     * stored information regarding the players, and it breaks the stored
     * information string into its separate substrings and sets the text.
     */
    private void makeScoreBoard() {
        ScoreReader scoreReader = new ScoreReader();
        try {
            scoreReader.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder username = new StringBuilder();
        StringBuilder score = new StringBuilder();
        StringBuilder time = new StringBuilder();

        for (int i = 0; i < scoreReader.GetM_Counter(); i++) {

            String userInformation = scoreReader.GetM_Scores()[i];
            String[] userInfo = userInformation.split("\\.");
            username.append(userInfo[WORD_0]);
            username.append("\n");
            score.append(userInfo[WORD_1]);
            score.append("\n");
            time.append(userInfo[WORD_2]);
            time.append("\n");
        }
        getM_Username().setText(username.toString());
        getM_Score().setText(score.toString());
        getM_Time().setText(time.toString());
    }

    /**
     * Return to the start screen.
     *
     * @param event an ActionEvent, which is a mouse click.
     * @throws IOException if file cannot be found.
     */
    public void ReturnStartScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new StartScreen(stage, getM_Theme());
    }

    /**
     * Set the theme to the sunset theme.
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
        setM_Theme("sunset");
    }

    /**
     * Set the theme to the ocean theme.
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
        setM_Theme("ocean");
    }

    /**
     * Set the theme to the candy theme.
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
        setM_Theme("candy");
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
        setM_Theme(null);

    }
}
