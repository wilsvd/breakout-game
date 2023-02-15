package com.example.dmscoursework.Controller;

import com.example.dmscoursework.View.Board.DebugConsole;
import com.example.dmscoursework.View.Board.GameBoard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * The DebugController takes the buttons that user clicked on in the
 * debugConsole and changes the game according to the buttons clicked. The
 * debug controller can add lives, remove lives, reset lives or switch levels.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class DebugController implements EventHandler<ActionEvent> {

    private DebugConsole m_DebugConsole;

    /**
     * Sets the debug console.
     *
     * @param debugConsole a DebugConsole.
     */
    private void setM_DebugConsole(DebugConsole debugConsole) {
        m_DebugConsole = debugConsole;
    }

    /**
     * Gets the debug console.
     *
     * @return a DebugConsole.
     */
    private DebugConsole getM_DebugConsole() {
        return m_DebugConsole;
    }

    /**
     * Constructs a debug controller which takes inputs from buttons pressed
     * on the debug console.
     *
     * @param debugConsole a DebugConsole, which contains all the buttons.
     */
    public DebugController(DebugConsole debugConsole) {
        setM_DebugConsole(debugConsole);
    }

    @Override
    public void handle(ActionEvent event) {
        DebugConsole debugConsole = getM_DebugConsole();
        GameBoard gameBoard = debugConsole.GetM_GameBoard();

        if (event.getSource() == debugConsole.GetM_ExitDebug()) {
            debugConsole.GetM_DebugPane().setVisible(false);
            debugConsole.SetM_DebugPane(null);
        }
        else if (event.getSource() == debugConsole.GetM_RemoveBallButton()) {
            gameBoard.GetM_LevelManager().ReduceBallCount();
        }
        else if (event.getSource() == debugConsole.GetM_ResetBallButton()) {
            gameBoard.GetM_LevelManager().ResetBallCount();
        }
        else if (event.getSource() == debugConsole.GetM_AddBallButton()) {
            gameBoard.GetM_LevelManager().IncreaseBallCount();
        }
        else if (event.getSource() == debugConsole.GetM_NextLevel()) {
            if (gameBoard.GetM_LevelManager().HasLevel()) {
                gameBoard.GetM_LevelManager().NextLevel();
                gameBoard.drawGame();
            }
        }
    }
}
