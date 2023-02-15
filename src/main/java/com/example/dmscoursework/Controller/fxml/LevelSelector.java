package com.example.dmscoursework.Controller.fxml;

import com.example.dmscoursework.View.Board.StartScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * The LevelSelector class controls the levelSelectorScreen.fxml. This
 * allows the user to select any playable level. The levels that are displayed
 * depend on the theme that the user has chosen. The user is able to go
 * directly into the game by selecting on one of the levels.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class LevelSelector {

    private final int LEVEL_ONE = 0;
    private final int LEVEL_TWO = 1;
    private final int LEVEL_THREE = 2;
    private final int LEVEL_FOUR = 3;
    private final int LEVEL_FIVE = 4;
    private final int LEVEL_SIX = 5;

    @FXML
    private ImageView m_BackgroundImage;
    @FXML
    private ImageView m_ImageView1;
    @FXML
    private ImageView m_ImageView2;
    @FXML
    private ImageView m_ImageView3;
    @FXML
    private ImageView m_ImageView4;
    @FXML
    private ImageView m_ImageView5;
    @FXML
    private ImageView m_ImageView6;

    private String m_Theme;
    private StartController m_StartController = new StartController();

    /**
     * Get the background image.
     *
     * @return an ImageView, which is the background image.
     */
    private ImageView getM_BackgroundImage() {
        return m_BackgroundImage;
    }

    /**
     * Get the image representing level one.
     *
     * @return an ImageView, which shows level one.
     */
    private ImageView getM_ImageView1() {
        return m_ImageView1;
    }
    /**
     * Get the image representing level two.
     *
     * @return an ImageView, which shows level two.
     */
    private ImageView getM_ImageView2() {
        return m_ImageView2;
    }
    /**
     * Get the image representing level three.
     *
     * @return an ImageView, which shows level three.
     */
    private ImageView getM_ImageView3() {
        return m_ImageView3;
    }
    /**
     * Get the image representing level four.
     *
     * @return an ImageView, which shows level four.
     */
    private ImageView getM_ImageView4() {
        return m_ImageView4;
    }
    /**
     * Get the image representing level five.
     *
     * @return an ImageView, which shows level five.
     */
    private ImageView getM_ImageView5() {
        return m_ImageView5;
    }
    /**
     * Get the image representing level six.
     *
     * @return an ImageView, which shows level six.
     */
    private ImageView getM_ImageView6() {
        return m_ImageView6;
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

    /**
     * Get the start controller
     *
     * @return a StartController, which controls the start screen.
     */
    private StartController getM_StartController() {
        return m_StartController;
    }

    /**
     * Return to start screen.
     *
     * @param event an ActionEvent, which is a mouse click.
     * @throws IOException if file can't be found.
     */
    public void ReturnStartScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new StartScreen(stage, getM_Theme());
    }

    /**
     * Sets the level to one.
     *
     * @param event an ActionEvent, which is a mouse click.
     */
    public void SetLevelOne(ActionEvent event) {
        getM_StartController().SetM_Theme(getM_Theme());
        getM_StartController().SetM_Level(LEVEL_ONE);
        getM_StartController().PlayGame(event);
    }

    /**
     * Sets the level to two.
     *
     * @param event an ActionEvent, which is a mouse click.
     */
    public void SetLevelTwo(ActionEvent event) {
        getM_StartController().SetM_Theme(getM_Theme());
        getM_StartController().SetM_Level(LEVEL_TWO);
        getM_StartController().PlayGame(event);
    }

    /**
     * Sets the level to three.
     *
     * @param event an ActionEvent, which is a mouse click.
     */
    public void SetLevelThree(ActionEvent event) {
        getM_StartController().SetM_Theme(getM_Theme());
        getM_StartController().SetM_Level(LEVEL_THREE);
        getM_StartController().PlayGame(event);
    }

    /**
     * Sets the level to four.
     *
     * @param event an ActionEvent, which is a mouse click.
     */
    public void SetLevelFour(ActionEvent event) {
        getM_StartController().SetM_Level(LEVEL_FOUR);
        getM_StartController().SetM_Theme(getM_Theme());
        getM_StartController().PlayGame(event);
    }

    /**
     * Sets the level to five.
     *
     * @param event an ActionEvent, which is a mouse click.
     */
    public void SetLevelFive(ActionEvent event) {
        getM_StartController().SetM_Level(LEVEL_FIVE);
        getM_StartController().SetM_Theme(getM_Theme());
        getM_StartController().PlayGame(event);
    }

    /**
     * Sets the level to six.
     *
     * @param event an ActionEvent, which is a mouse click.
     */
    public void SetLevelSix(ActionEvent event) {
        getM_StartController().SetM_Level(LEVEL_SIX);
        getM_StartController().SetM_Theme(getM_Theme());
        getM_StartController().PlayGame(event);
    }

    /**
     * Sets the theme to the sunset theme.
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
     * Sets the theme to the ocean theme.
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
     * Sets the theme to the candy theme.
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
     * Sets the theme to the construction theme.
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

    /**
     * Sets the theme of level images.
     *
     * @param theme a String, which is specified theme.
     */
    public void SetLevelImages(String theme) {
        FileInputStream[] inputstreams = new FileInputStream[6];
        if (theme == "sunset") {
            String basePath = "src/Assets/OrangeAssets/SunsetLevel";
            inputstreams = createInputStream(basePath, inputstreams);
        }
        else if (theme == "ocean") {
            String basePath = "src/Assets/BlueAssets/OceanLevel";
            inputstreams = createInputStream(basePath, inputstreams);
        }
        else if (theme == "candy") {
            String basePath = "src/Assets/PinkAssets/CandyLevel";
            inputstreams = createInputStream(basePath, inputstreams);
        }
        else {
            String basePath = "src/Assets/DefaultAssets/DefaultLevel";
            inputstreams = createInputStream(basePath, inputstreams);
        }
        Image[] images = new Image[inputstreams.length];

        for (int i = 0; i < inputstreams.length; i++) {
            images[i] = new Image(inputstreams[i]);
        }
        getM_ImageView1().setImage(images[0]);
        getM_ImageView2().setImage(images[1]);
        getM_ImageView3().setImage(images[2]);
        getM_ImageView4().setImage(images[3]);
        getM_ImageView5().setImage(images[4]);
        getM_ImageView6().setImage(images[5]);
    }

    /**
     * Creates an array of input streams which contain the location of all
     * the image files.
     *
     * @param basePath a String, which contains the common name in file paths.
     * @param inputstreams a FileInputStream[], which is an array of empty
     *                     streams.
     * @return a FileInputStream[], which is not empty and contains path to
     * images.
     */
    private FileInputStream[] createInputStream(String basePath,
                                          FileInputStream[] inputstreams) {
        try {
            for (int i = 0; i < inputstreams.length; i++) {
                String level = checkCounter(i);
                inputstreams[i] = new FileInputStream(
                                                basePath+level+".PNG");
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputstreams;
    }

    /**
     * Check the counter and use it to return a word representing a number.
     *
     * @param i an integer, which represents index in the FileInputStream[]
     * @return a String, which is a word representing a number.
     */
    private String checkCounter(int i) {
        switch (i) {
            case LEVEL_ONE :
                return ("One");
            case LEVEL_TWO:
                return ("Two");
            case LEVEL_THREE:
                return ("Three");
            case LEVEL_FOUR:
                return ("Four");
            case LEVEL_FIVE:
                return ("Five");
            case LEVEL_SIX:
                return ("Six");
        }
        return null;
    }
}

