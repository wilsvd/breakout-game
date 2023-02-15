package com.example.dmscoursework.View.Board;

import com.example.dmscoursework.Controller.DebugController;
import com.example.dmscoursework.View.Ball.Ball;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * The DebugConsole contains buttons which can change the game. The
 * DebugConsole is in charge of displaying these buttons and labels.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class DebugConsole {

    private final int MIN_BUTTON_WIDTH = 100;
    private final int HEADING_FONT_SIZE = 30;
    private final int LABEL_FONT_SIZE = 24;
    private final int MAX_SPEED = 3;
    private final double OPACITY = 0.9;
    private final double HALF = 0.5;
    private final double HORIZONTAL_INCREMENT = 100;
    private final int LIVES_LABEL_X_POS = 300;
    private final int LIVES_LABEL_Y_POS = 90;
    private final int LIVES_BUTTON_START_X_POS = 30;
    private final int LIVES_BUTTON_START_Y_POS = 135;
    private final int LEVEL_LABEL_START_X_POS = 30;
    private final int LEVEL_LABEL_START_Y_POS = 90;
    private final int LEVEL_BUTTON_START_X_POS = 390;
    private final int LEVEL_BUTTON_START_Y_POS = 135;
    private final double EXIT_DEBUG_Y_POS = 360;
    private final double EXIT_DEBUG_HEIGHT = 90;
    private final double HORIZONTAL_SLIDER_X_POS = 75;
    private final double SLIDER_Y_POS = 270;

    private GameBoard m_GameBoard;
    private AnchorPane m_DebugPane;
    private Button m_ExitDebug;
    private Button m_RemoveBall;
    private Button m_ResetBall;
    private Button m_AddBall;
    private Button m_NextLevel;

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
     * Get the debug pane
     *
     * @return an AnchorPane, which is the debug pane.
     */
    public AnchorPane GetM_DebugPane() {
        return m_DebugPane;
    }

    /**
     * Set the debug pane.
     *
     * @param debugPane an AnchorPane, which is the debug pane.
     */
    public void SetM_DebugPane(AnchorPane debugPane) {
        if (debugPane != null) {
            debugPane.setBackground(new Background(
                    new BackgroundFill(Color.DARKGREY,
                            null,
                            null)));
            debugPane.setOpacity(OPACITY);
            debugPane.setMinWidth(GetM_GameBoard().GetWidth());
            debugPane.setMinHeight(GetM_GameBoard().GetHeight());
            debugPane.setViewOrder(-1);
        }
        this.m_DebugPane = debugPane;
    }

    /**
     * Get the exit button.
     *
     * @return a Button, which exits the debug console.
     */
    public Button GetM_ExitDebug() {
        return m_ExitDebug;
    }
    /**
     * Set the exit button with its required information.
     *
     * @param exitDebug a Button, which exits the debug console.
     * @param controller a DebugController, which is the controller for this
     *                   class.
     */
    private void setM_ExitDebug(Button exitDebug,
                                DebugController controller) {
        exitDebug.setOnAction(controller);
        exitDebug.setAlignment(Pos.CENTER);
        exitDebug.setLayoutY(EXIT_DEBUG_Y_POS);
        exitDebug.setMinWidth(GetM_GameBoard().GetWidth());
        exitDebug.setMinHeight(EXIT_DEBUG_HEIGHT);
        this.m_ExitDebug = exitDebug;
    }

    /**
     * Get the remove ball button.
     *
     * @return a Button, which removes a ball.
     */
    public Button GetM_RemoveBallButton() {
        return m_RemoveBall;
    }

    /**
     * Set the remove ball button with its required information.
     *
     * @param removeBall a Button, removes a ball.
     * @param controller a DebugController, which is the controller for this
     *      *                   class.
     * @param x an integer, which is the x coordinate.
     * @param y an integer, which is the y coordinate.
     */
    private void setM_RemoveBallButton(Button removeBall,
                                       DebugController controller, int x, int y) {
        removeBall.setOnAction(controller);
        removeBall.setMinWidth(MIN_BUTTON_WIDTH);
        removeBall.setLayoutX(x);
        removeBall.setLayoutY(y);
        this.m_RemoveBall = removeBall;
    }

    /**
     * Get the reset ball button.
     *
     * @return a Button, which resets the number of balls.
     */
    public Button GetM_ResetBallButton() {
        return m_ResetBall;
    }
    /**
     * Set the reset ball button with its required information.
     *
     * @param resetBall a Button, which resets the number of balls.
     * @param controller a DebugController, which is the controller for this
     *      *                   class.
     * @param x an integer, which is the x coordinate.
     * @param y an integer, which is the y coordinate.
     */
    private void setM_ResetBallButton(Button resetBall,
                                      DebugController controller,
                                      int x, int y) {
        resetBall.setOnAction(controller);
        resetBall.setMinWidth(MIN_BUTTON_WIDTH);
        resetBall.setLayoutX(x);
        resetBall.setLayoutY(y);
        this.m_ResetBall = resetBall;
    }

    /**
     * Get the add ball button.
     *
     * @return a Button, which adds to the number of balls.
     */
    public Button GetM_AddBallButton() {
        return m_AddBall;
    }
    /**
     * Set the add ball button with its required information.
     *
     * @param addBall a Button, which adds to the ball count.
     * @param controller a DebugController, which is the controller for this
     *      *                   class.
     * @param x an integer, which is the x coordinate.
     * @param y an integer, which is the y coordinate.
     */
    private void setM_AddBallButton(Button addBall,
                                    DebugController controller,
                                    int x, int y) {
        addBall.setOnAction(controller);
        addBall.setMinWidth(MIN_BUTTON_WIDTH);
        addBall.setLayoutX(x);
        addBall.setLayoutY(y);
        this.m_AddBall = addBall;
    }

    /**
     * Get the next level button.
     *
     * @return a Button, which goes to the next level.
     */
    public Button GetM_NextLevel() {
        return m_NextLevel;
    }
    /**
     * Set the next level button with its required information.
     *
     * @param nextLevel a Button, which goes to next level.
     * @param controller a DebugController, which is the controller for this
     *      *                   class.
     * @param x an integer, which is the x coordinate.
     * @param y an integer, which is the y coordinate.
     */
    private void setM_NextLevel(Button nextLevel, DebugController controller,
                                int x, int y) {
        nextLevel.setOnAction(controller);
        nextLevel.setMinWidth(MIN_BUTTON_WIDTH);
        nextLevel.setLayoutX(x);
        nextLevel.setLayoutY(y);
        this.m_NextLevel = nextLevel;
    }

    /**
     * Constructor of the debugConsole which takes in the gameBoard, so that
     * the debugConsole can take the information from the gameBoard.
     *
     * @param gameBoard a GameBoard, which is the gameBoard being played on.
     */
    public DebugConsole(GameBoard gameBoard) {

        setM_GameBoard(gameBoard);
        SetM_DebugPane(new AnchorPane());

        DebugController debugController = new DebugController(this);

        initialiseDebugConsole(GetM_DebugPane(), debugController);
        initialiseLivesController(GetM_DebugPane(), debugController);
        initialiseLevelController(GetM_DebugPane(), debugController);
        initialiseSpeedController(GetM_DebugPane());

        gameBoard.GetM_RootPane().getChildren().add(GetM_DebugPane());
    }

    /**
     * Creates the debug console.
     *
     * @param debugConsole a AnchorPane, which is the pane the debugConsole
     *                     will be placed on.
     * @param controller a DebugController, which is what controls the
     *                   debugConsole.
     */
    private void initialiseDebugConsole(AnchorPane debugConsole,
                                        DebugController controller) {
        Label debugHeading = new Label("Debug Console");
        debugHeading.setMinWidth(GetM_GameBoard().GetWidth());
        debugHeading.setAlignment(Pos.CENTER);
        debugHeading.setFont(Font.font(HEADING_FONT_SIZE));

        setM_ExitDebug(new Button("Exit Debug Console"), controller);

        debugConsole.getChildren().add(debugHeading);
        debugConsole.getChildren().add(GetM_ExitDebug());
    }

    /**
     * Creates the buttons in charge of the lives.
     *
     * @param debugConsole a AnchorPane, which is the pane the debugConsole
     *                     will be placed on.
     * @param controller a DebugController, which is what controls the
     *                   debugConsole.
     */
    private void initialiseLivesController(AnchorPane debugConsole,
                                           DebugController controller) {
        Label livesControllerLabel = new Label("Lives Controller");
        livesControllerLabel.setFont(Font.font(LABEL_FONT_SIZE));
        livesControllerLabel.setLayoutX(LIVES_LABEL_X_POS);
        livesControllerLabel.setLayoutY(LIVES_LABEL_Y_POS);
        livesControllerLabel.setMinWidth(GetM_GameBoard().GetWidth() * HALF);
        livesControllerLabel.setAlignment(Pos.CENTER);

        int x = LIVES_BUTTON_START_X_POS;
        int y = LIVES_BUTTON_START_Y_POS;
        setM_RemoveBallButton(new Button("Remove a life"), controller, x, y);
        x += HORIZONTAL_INCREMENT;
        setM_ResetBallButton(new Button("Reset lives"), controller, x, y);
        x += HORIZONTAL_INCREMENT;
        setM_AddBallButton(new Button("Add a life"), controller, x, y);

        debugConsole.getChildren().add(livesControllerLabel);
        debugConsole.getChildren().add(GetM_RemoveBallButton());
        debugConsole.getChildren().add(GetM_ResetBallButton());
        debugConsole.getChildren().add(GetM_AddBallButton());
    }

    /**
     * Creates the buttons in charge of controlling the level.
     *
     * @param debugConsole a AnchorPane, which is the pane the debugConsole
     *                     will be placed on.
     * @param controller a DebugController, which is what controls the
     *                   debugConsole.
     */
    private void initialiseLevelController(AnchorPane debugConsole,
                                           DebugController controller) {
        Label levelControllerLabel = new Label("Level Controller");
        levelControllerLabel.setFont(Font.font(LABEL_FONT_SIZE));
        levelControllerLabel.setLayoutX(LEVEL_LABEL_START_X_POS);
        levelControllerLabel.setLayoutY(LEVEL_LABEL_START_Y_POS);
        levelControllerLabel.setMinWidth(GetM_GameBoard().GetWidth() * HALF);
        levelControllerLabel.setAlignment(Pos.CENTER);

        int x = LEVEL_BUTTON_START_X_POS;
        int y = LEVEL_BUTTON_START_Y_POS;

        setM_NextLevel(new Button("Next Level"), controller, x, y);

        debugConsole.getChildren().add(levelControllerLabel);
        debugConsole.getChildren().add(GetM_NextLevel());
    }
    /**
     * Creates the speed controllers.
     *
     * @param debugConsole a AnchorPane, which is the pane the debugConsole
     *                     will be placed on.
     */
    private void initialiseSpeedController(AnchorPane debugConsole) {
        Label speedControllerLabel = new Label("Speed Controller");
        speedControllerLabel.setFont(Font.font(LABEL_FONT_SIZE));
        speedControllerLabel.setLayoutY(GetM_GameBoard().GetHeight() * HALF);
        speedControllerLabel.setMinWidth(GetM_GameBoard().GetWidth());
        speedControllerLabel.setAlignment(Pos.CENTER);

        initialiseVerticalSlider(debugConsole);
        initialiseHorizontalSlider(debugConsole);

        debugConsole.getChildren().add(speedControllerLabel);
    }

    /**
     * Creates the horizontal speed slider.
     *
     * @param debugConsole a AnchorPane, which is the pane the debugConsole
     *                     will be placed on.
     */
    private void initialiseHorizontalSlider(AnchorPane debugConsole) {
        Ball ball = GetM_GameBoard().GetM_LevelManager().GetM_Ball();

        Slider horSpeed = new Slider(-MAX_SPEED, MAX_SPEED, ball.GetXSpeed());

        horSpeed.valueProperty().
            addListener((observableValue, oldNum, newNum) ->
                ball.SetXSpeed(newNum.intValue()));

        horSpeed.setShowTickLabels(true);
        horSpeed.setShowTickMarks(true);
        horSpeed.setLayoutX(HORIZONTAL_SLIDER_X_POS);
        horSpeed.setLayoutY(SLIDER_Y_POS);

        debugConsole.getChildren().add(horSpeed);
    }

    /**
     * Creates the vertical speed slider.
     *
     * @param debugConsole a AnchorPane, which is the pane the debugConsole
     *                     will be placed on.
     */
    private void initialiseVerticalSlider(AnchorPane debugConsole) {
        Ball ball = GetM_GameBoard().GetM_LevelManager().GetM_Ball();

        Slider verSpeed = new Slider(-MAX_SPEED, MAX_SPEED, ball.GetYSpeed());

        verSpeed.valueProperty().
            addListener((observableValue, oldNum, newNum) ->
                ball.SetYSpeed(newNum.intValue()));

        verSpeed.setShowTickLabels(true);
        verSpeed.setShowTickMarks(true);
        verSpeed.setLayoutX((GetM_GameBoard().GetWidth() * HALF));
        verSpeed.setLayoutY(SLIDER_Y_POS);

        debugConsole.getChildren().add(verSpeed);
    }
}
