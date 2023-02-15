package com.example.dmscoursework.View.Board;

import com.example.dmscoursework.Controller.PauseController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * The PauseMenu is in charge of displaying the relevant buttons to user such
 * as exiting the game, restarting the game, returning to the start
 * screen and enabling/disabling the AI.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class PauseMenu {

    private final int HEADING_FONT_SIZE = 30;
    private final double OPACITY = 0.9;
    private final double HALF = 0.5;
    private final int MIN_BUTTON_HEIGHT = 50;
    private final int MIN_BUTTON_WIDTH = 100;
    private final double VERTICAL_INCREMENT = 100;
    private final int BUTTON_START_X_POS = 75;
    private final int BUTTON_START_Y_POS = 110;
    private final int RETURN_BUTTON_Y_POS = 400;
    private final int RETURN_BUTTON_HEIGHT = 50;

    private Button m_PlayGame;
    private Button m_RestartGame;
    private Button m_ExitGame;
    private Button m_StartScreen;

    private AnchorPane m_PausePane;
    private GameBoard m_GameBoard;

    private Button m_EnableAI;
    private Button m_DisableAI;

    /**
     * Get the GameBoard
     *
     * @return a GameBoard.
     */
    public GameBoard GetM_GameBoard() {
        return m_GameBoard;
    }
    /**
     * Set the GameBoard
     *
     * @param gameBoard a GameBoard.
     */
    private void setM_GameBoard(GameBoard gameBoard) {
        this.m_GameBoard = gameBoard;
    }

    /**
     * Get the start screen button.
     *
     * @return a Button, which returns user to start screen.
     */
    public Button GetM_StartScreen() {
        return m_StartScreen;
    }
    /**
     * Sets the start screen button.
     *
     * @param startScreen a Button, which returns user to start screen.
     * @param pauseController a PauseController, which is the controller for
     *                        this class.
     */
    private void setM_StartScreen(Button startScreen,
                                 PauseController pauseController) {

        startScreen.setOnAction(pauseController);
        startScreen.setMinHeight(RETURN_BUTTON_HEIGHT);
        startScreen.setMinWidth(GetM_GameBoard().GetWidth());
        startScreen.setAlignment(Pos.CENTER);
        startScreen.setLayoutY(RETURN_BUTTON_Y_POS);
        this.m_StartScreen = startScreen;
    }

    /**
     * Get the pause pane
     *
     * @return an AnchorPane, which is the pause pane.
     */
    public AnchorPane GetM_PausePane() {
        return m_PausePane;
    }
    /**
     * Set the pause pane.
     *
     * @param pauseMenu an AnchorPane, which is the pause pane.
     */
    public void SetM_PausePane(AnchorPane pauseMenu) {
        if (pauseMenu != null) {
            pauseMenu.setBackground(new Background(new BackgroundFill(
                    Color.DARKGREY,
                    null,
                    null)));
            pauseMenu.setOpacity(OPACITY);
            pauseMenu.setMinWidth(GetM_GameBoard().GetWidth());
            pauseMenu.setMinHeight(GetM_GameBoard().GetHeight());
            pauseMenu.setViewOrder(-1);
        }
        this.m_PausePane = pauseMenu;
    }

    /**
     * Get the play game button.
     *
     * @return a Button, which plays the game.
     */
    public Button GetM_PlayGame() {
        return m_PlayGame;
    }
    /**
     * Set the play game button with its required information.
     *
     * @param playGame a Button, which is newly made button with no info.
     * @param pauseController a PauseController, which is the controller for
     *                        this class.
     */
    private void setM_PlayGame(Button playGame,
                              PauseController pauseController, int x, int y) {

        playGame.setOnAction(pauseController);
        playGame.setMinSize(MIN_BUTTON_WIDTH, MIN_BUTTON_HEIGHT);
        playGame.setLayoutX(x);
        playGame.setLayoutY(y);
        this.m_PlayGame = playGame;
    }

    /**
     * Get the restart game button.
     *
     * @return a Button, which restarts the game.
     */
    public Button GetM_RestartGame() {
        return m_RestartGame;
    }
    /**
     * Set the restart game button with its required information.
     *
     * @param restartGame a Button, which restarts the game.
     * @param pauseController a PauseController, which is the controller for
     *                        this class.
     */
    private void setM_RestartGame(Button restartGame,
                                 PauseController pauseController, int x,
                                 int y) {
        restartGame.setOnAction(pauseController);
        restartGame.setMinSize(MIN_BUTTON_WIDTH, MIN_BUTTON_HEIGHT);
        restartGame.setLayoutX(x);
        restartGame.setLayoutY(y);
        this.m_RestartGame = restartGame;
    }

    /**
     * Get the exit game button.
     *
     * @return a Button, which exits the game.
     */
    public Button GetM_ExitGame() {
        return m_ExitGame;
    }
    /**
     * Set the restart game button with its required information.
     *
     * @param exitGame a Button, which exits the game.
     * @param pauseController a PauseController, which is the controller for
     *                        this class.
     */
    private void setM_ExitGame(Button exitGame,
                               PauseController pauseController, int x, int y) {

        exitGame.setOnAction(pauseController);
        exitGame.setMinSize(MIN_BUTTON_WIDTH, MIN_BUTTON_HEIGHT);
        exitGame.setLayoutX(x);
        exitGame.setLayoutY(y);
        this.m_ExitGame = exitGame;
    }

    /**
     * Get the button to enable AI.
     *
     * @return a Button, which enables the AI.
     */
    public Button getM_EnableAI() {
        return m_EnableAI;
    }
    /**
     * Set the AI enable button with its required information.
     *
     * @param enableAi a Button, which enables the AI.
     * @param pauseController a PauseController, which is the controller for
     *                        this class.
     */
    private void setM_EnableAI(Button enableAi, PauseController pauseController,
                               int x, int y) {

        enableAi.setOnAction(pauseController);
        enableAi.setMinSize(MIN_BUTTON_WIDTH, MIN_BUTTON_HEIGHT);
        enableAi.setLayoutX(x);
        enableAi.setLayoutY(y);
        this.m_EnableAI = enableAi;
    }

    /**
     * Get the button to disable AI.
     *
     * @return a Button, which disables the AI.
     */
    public Button getM_DisableAI() {
        return m_DisableAI;
    }
    /**
     * Set the AI disables button with its required information.
     *
     * @param disableAI a Button, which disables the AI.
     * @param pauseController a PauseController, which is the controller for
     *                        this class.
     */
    private void setM_DisableAI(Button disableAI,
                                PauseController pauseController,
                               int x, int y) {

        disableAI.setOnAction(pauseController);
        disableAI.setMinSize(MIN_BUTTON_WIDTH, MIN_BUTTON_HEIGHT);
        disableAI.setLayoutX(x);
        disableAI.setLayoutY(y);
        this.m_DisableAI = disableAI;
    }
    /**
     * Constructor of the pause menu which takes in the gameBoard, so that
     * the pause menu can take the information from the gameBoard.
     *
     * @param gameBoard a GameBoard, which is the gameBoard being played on.
     */
    public PauseMenu(GameBoard gameBoard) {

        setM_GameBoard(gameBoard);
        SetM_PausePane(new AnchorPane());

        Label pauseHeading = new Label("Pause Menu");
        pauseHeading.setMinWidth(gameBoard.GetWidth());
        pauseHeading.setAlignment(Pos.CENTER);
        pauseHeading.setFont(Font.font(HEADING_FONT_SIZE));

        makeButtons(gameBoard);

        GetM_PausePane().getChildren().add(pauseHeading);
        GetM_GameBoard().GetM_RootPane().getChildren().add(GetM_PausePane());
    }

    /**
     * Makes the buttons for the pause menu and uses the gameBoard to make
     * sure the pause menu is the correct width.
     *
     * @param gameBoard a GameBoard.
     */
    private void makeButtons(GameBoard gameBoard) {
        int x = BUTTON_START_X_POS;
        int y = BUTTON_START_Y_POS;

        PauseController pauseController = new PauseController(this);

        setM_PlayGame(new Button("Continue Game"), pauseController, x, y);
        y += VERTICAL_INCREMENT;
        setM_RestartGame(new Button("Restart Game"), pauseController, x, y);
        y += VERTICAL_INCREMENT;
        setM_ExitGame(new Button("Exit Game"), pauseController, x, y);
        x += gameBoard.GetWidth() * HALF;
        setM_EnableAI(new Button("Enable AI"), pauseController, x, y);
        y -= VERTICAL_INCREMENT;
        setM_DisableAI(new Button("Disable AI"), pauseController, x, y);

        setM_StartScreen(new Button("Go to Start Screen"), pauseController);

        GetM_PausePane().getChildren().add(GetM_PlayGame());
        GetM_PausePane().getChildren().add(GetM_RestartGame());
        GetM_PausePane().getChildren().add(GetM_ExitGame());
        GetM_PausePane().getChildren().add(GetM_StartScreen());
        GetM_PausePane().getChildren().add(getM_EnableAI());
        GetM_PausePane().getChildren().add(getM_DisableAI());
    }


}
