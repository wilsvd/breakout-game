package com.example.dmscoursework.Controller;

import com.example.dmscoursework.View.Board.GameBoard;
import com.example.dmscoursework.View.Board.PauseMenu;
import com.example.dmscoursework.View.Board.StartScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * The PauseController is in charge of the pause screen where a user has
 * access to a few buttons such as exiting game, restarting game, returning
 * to start screen, enabling AI, disabling AI. The PauseController will carry
 * out the actions the user wants.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class PauseController implements EventHandler<ActionEvent> {

    private PauseMenu m_PauseMenu;

    /**
     * Gets the pause menu.
     *
     * @return a PauseMenu.
     */
    private PauseMenu getM_PauseMenu() {
        return m_PauseMenu;
    }

    /**
     * Sets the pause menu.
     *
     * @param pauseMenu a PauseMenu.
     */
    private void setM_PauseMenu(PauseMenu pauseMenu) {
        this.m_PauseMenu = pauseMenu;
    }

    /**
     * Constructs a pause controller which takes inputs from buttons pressed
     * on the pause menu.
     *
     * @param pauseMenu a PauseMenu, which contains all the buttons.
     */
    public PauseController(PauseMenu pauseMenu) {
        setM_PauseMenu(pauseMenu);
    }

    @Override
    public void handle(ActionEvent event) {
        PauseMenu pauseMenu = getM_PauseMenu();
        GameBoard gameBoard = pauseMenu.GetM_GameBoard();

        if (event.getSource() == pauseMenu.GetM_PlayGame()) {
            pauseMenu.GetM_PausePane().setVisible(false);
            gameBoard.GetController().GetM_Timer().play();
            pauseMenu.SetM_PausePane(null);
        } else if (event.getSource() == pauseMenu.GetM_RestartGame()) {
            gameBoard.GetM_LevelManager().BallReset();
            gameBoard.GetM_LevelManager().ResetLevel();
            gameBoard.drawGame();
            pauseMenu.GetM_PausePane().setVisible(false);
            pauseMenu.SetM_PausePane(null);
        } else if (event.getSource() == pauseMenu.GetM_ExitGame()) {
            gameBoard.GetM_Stage().close();
        } else if (event.getSource() == pauseMenu.getM_EnableAI()) {
            gameBoard.GetController().SetM_AI(true);
        }
        else if (event.getSource() == pauseMenu.getM_DisableAI()) {
            gameBoard.GetController().SetM_AI(false);
        }
        else if (event.getSource() == pauseMenu.GetM_StartScreen()) {
            MusicController.getInstance().PauseMedia();
            Stage stage
                    = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                new StartScreen(stage, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
