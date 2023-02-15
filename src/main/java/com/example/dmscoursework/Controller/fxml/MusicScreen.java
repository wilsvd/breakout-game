package com.example.dmscoursework.Controller.fxml;

import com.example.dmscoursework.Controller.MusicController;
import com.example.dmscoursework.View.Board.StartScreen;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * The MusicScreen class controls the musicScreen.fxml. This allows the user
 * to make changes to the music being played. The user can select buttons to
 * play music, pause music, reset music as well as change song to the next
 * song or the previous song in the playlist. The user can also change the
 * volume of the music that is playing.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class MusicScreen implements Initializable {

    private final int VOLUME_CONVERTER = 100;

    @FXML
    private ImageView m_BackgroundImage;
    @FXML
    private Slider m_VolumeSlider;

    private MusicController m_MusicController;
    private String m_Theme;

    /**
     * Get the background image.
     *
     * @return an ImageView.
     */
    private ImageView getM_BackgroundImage() {
        return m_BackgroundImage;
    }

    /**
     * Get the volume slider.
     *
     * @return a Slider, which controls the volume.
     */
    private Slider getM_VolumeSlider() {
        return m_VolumeSlider;
    }

    /**
     * Get the music controller.
     *
     * @return a MusicController, which controls the music.
     */
    private MusicController getM_MusicController() {
        return m_MusicController;
    }

    /**
     * Sets the music controller.
     *
     * @param musicController a MusicController, which controls the music.
     */
    private void setM_MusicController(MusicController musicController) {
        this.m_MusicController = musicController;
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
        setM_MusicController(MusicController.getInstance());
        MediaPlayer mediaPlayer = getM_MusicController().GetMediaPlayer();

        Platform.runLater(() -> {
            getM_VolumeSlider().setValue(
                    getM_MusicController().GetM_Volume() * VOLUME_CONVERTER);

            getM_VolumeSlider().valueProperty().addListener(
                    (observableValue, number, t1) -> mediaPlayer.setVolume(
                            getM_VolumeSlider().getValue() / VOLUME_CONVERTER));
        });
    }

    /**
     * Returns to the start screen.
     *
     * @param event an ActionEvent, which is a mouse click.
     * @throws IOException if file can't be found.
     */
    public void ReturnStartScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new StartScreen(stage, getM_Theme());
    }

    /**
     * Plays the media.
     */
    public void PlayMedia() {
        MediaPlayer mediaPlayer = getM_MusicController().GetMediaPlayer();
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            getM_MusicController().PlayMedia();
        }
    }

    /**
     * Pauses the media.
     */
    public void PauseMedia() {
        MediaPlayer mediaPlayer = getM_MusicController().GetMediaPlayer();
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            getM_MusicController().PauseMedia();
        }
    }

    /**
     * Resets the media.
     */
    public void ResetMedia() {
        getM_MusicController().ResetMedia();
    }

    /**
     * Goes to the previous song.
     */
    public void PreviousMedia() {
        getM_MusicController().PreviousMedia();
    }

    /**
     * Goes to next song.
     */
    public void NextMedia() {
        getM_MusicController().NextMedia();
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