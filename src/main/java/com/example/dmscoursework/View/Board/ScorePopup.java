package com.example.dmscoursework.View.Board;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
/**
 * The ScorePopup is in charge on creating a popup which displays the score
 * at the end of each round.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class ScorePopup {

    /**
     * Constructs a popup which displays the score at the end of each round.
     *
     * @param score an integer, which is the score of the player.
     */
    public ScorePopup(int score) {
        Alert alertType=new Alert(Alert.AlertType.CONFIRMATION);
        alertType.setContentText("Your round score: " + score);
        alertType.initStyle(StageStyle.UNDECORATED);
        alertType.setGraphic(null);
        alertType.getButtonTypes().removeAll(ButtonType.CANCEL);
        alertType.show();
    }
}
