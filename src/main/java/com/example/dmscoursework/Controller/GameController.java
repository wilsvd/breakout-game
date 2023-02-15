package com.example.dmscoursework.Controller;

import com.example.dmscoursework.View.Board.*;
import com.example.dmscoursework.Model.ScoreWriter;
import com.example.dmscoursework.View.Level.CollisionChecker;
import com.example.dmscoursework.View.Level.LevelManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.io.IOException;
/**
 * The GameController is in charge of the game logic. The gameController will
 * update the state of the gameBoard every 10 milliseconds by moving the ball
 * as well to check and see if a player has specified whether to move the
 * paddle left or right. The gameController is also in charge of allowing the
 * AI play the game instead of the human player.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class GameController {

    private final int REFRESH_TIME = 10;
    private final double TIME_INCREMENT = 0.01;
    private final int SCORE_DIVIDEND = 1000000;

    private Timeline m_Timer;
    private String m_Message = "Press Space To Play";
    private DebugConsole m_DebugConsole;
    private PauseMenu m_PauseMenu;
    private double m_Stopwatch = 0;
    private final ScoreWriter m_ScoreWriter = new ScoreWriter();
    private boolean m_AI = false;
    int m_TotalScore = 0;

    /**
     * Sets the timeline of the game.
     *
     * @param timer a Timeline, which is the timeline of the game.
     */
    private void setM_Timer(Timeline timer) {
        this.m_Timer = timer;
    }
    /**
     * Gets the timeline of the game.
     *
     * @return a Timeline.
     */
    public Timeline GetM_Timer() {
        return m_Timer;
    }

    /**
     * Sets the message in the game.
     *
     * @param message a String, which is message in the game.
     */
    private void setM_Message(String message) {
        this.m_Message = message;
    }

    /**
     * Gets the message in the game.
     *
     * @return a String.
     */
    public String GetM_Message() {
        return m_Message;
    }

    /**
     * Set the debug console.
     *
     * @param debugConsole a DebugConsole.
     */
    public void SetM_DebugConsole(DebugConsole debugConsole) {
        this.m_DebugConsole = debugConsole;
    }
    /**
     * Get the debug console.
     *
     * @return a DebugConsole.
     */
    private DebugConsole getM_DebugConsole() {
        return m_DebugConsole;
    }

    /**
     * Set the pause menu.
     *
     * @param pauseMenu a PauseMenu.
     */
    private void setM_PauseMenu(PauseMenu pauseMenu) {
        this.m_PauseMenu = pauseMenu;
    }
    /**
     * Get the pause menu.
     *
     * @return a PauseMenu.
     */
    private PauseMenu getM_PauseMenu() {
        return m_PauseMenu;
    }

    /**
     * Get the stopwatch time for the game.
     *
     * @return a double, which tracks time in the game.
     */
    public double GetM_Stopwatch() {
        return m_Stopwatch;
    }

    /**
     * Set the stopwatch time for the game.
     *
     * @param stopwatch a double, which tracks time in the game.
     */
    private void setM_Stopwatch(double stopwatch) {
        this.m_Stopwatch = stopwatch;
    }

    /**
     * Get the score writer
     *
     * @return a ScoreWriter.
     */
    private ScoreWriter getM_ScoreWriter() {
        return m_ScoreWriter;
    }

    /**
     * Get the total score.
     *
     * @return an integer, which is total score.
     */
    private int getM_TotalScore() {
        return m_TotalScore;
    }
    /**
     * Set the total score.
     *
     * @param totalScore an integer, which is the total score.
     */
    private void setM_TotalScore(int totalScore) {
        this.m_TotalScore = totalScore;
    }

    /**
     * Gets if the AI is enabled or not.
     *
     * @return a boolean, which is if AI is enabled or not.
     */
    private boolean isM_AI() {
        return m_AI;
    }
    /**
     * Set if the AI is enabled or not.
     *
     * @param AI a boolean, which is if AI is enabled or not.
     */
    public void SetM_AI(boolean AI) {
        this.m_AI = AI;
    }

    /**
     * Constructs a game controller which takes in the game board where the
     * game is being played on as well as what level is being played on.
     *
     * @param gameBoard a GameBoard.
     * @param level an integer, which is the level being played on.
     */
    public GameController(GameBoard gameBoard, int level) {
        double totalTime = 0;
        LevelManager levelManager = gameBoard.GetM_LevelManager();
        CollisionChecker collisionChecker = new CollisionChecker(gameBoard);
        AiController aiController = new AiController(
                this, levelManager, collisionChecker);

        int i = 0;
        while (i < (level + 1)) {
            levelManager.NextLevel();
            i++;
        }

        setM_Timer(new Timeline(new KeyFrame(Duration.millis(REFRESH_TIME),
                e -> {
                    try {
                        run(gameBoard, collisionChecker, aiController, totalTime);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                })));
        GetM_Timer().setCycleCount(Timeline.INDEFINITE);
        setSceneControls(gameBoard);
    }

    /**
     * Running of the game where the game is checked every 10 milliseconds.
     *
     * @param gameBoard a GameBoard.
     * @param collisionChecker a CollisionChecker.
     * @param aiController a AiController.
     * @param totalTime a double.
     * @throws IOException
     */
    private void run(GameBoard gameBoard, CollisionChecker collisionChecker,
                     AiController aiController,
                     double totalTime) throws IOException {
        String username;
        LevelManager levelManager = gameBoard.GetM_LevelManager();

        setM_Stopwatch(GetM_Stopwatch() + TIME_INCREMENT);
        if (isM_AI()) {
            username = "AI";
            levelManager.GetM_Ball().Move();
            aiController.MovePaddle();
        } else {
            username = gameBoard.GetM_Username();
            levelManager.Move();
        }
        collisionChecker.FindImpacts();
        setM_Message(String.format("Bricks: %d Balls %d",
                levelManager.GetM_LevelBuilder().GetM_BrickCnt(),
                levelManager.GetM_BallCount()));
        if (levelManager.IsM_BallLost()) {
            if (levelManager.BallEnd()) {
                levelManager.ResetLevel();
                setM_Message("Game over");
            }
            levelManager.BallReset();
            GetM_Timer().stop();
            if (isM_AI()) {
                GetM_Timer().play();
            }
        } else if (levelManager.IsLevelDone()) {
            displayScore();
            totalTime += GetM_Stopwatch();
            setM_Stopwatch(0);
            if (levelManager.HasLevel()) {
                setM_Message("Go to Next Level");
                GetM_Timer().stop();
                levelManager.BallReset();
                levelManager.ResetLevel();
                levelManager.NextLevel();
            } else {
                setM_Message("ALL WALLS DESTROYED");
                GetM_Timer().stop();
                System.out.println(totalTime);
                getM_ScoreWriter().writeFile(
                        username, getM_TotalScore(), (int) totalTime);
                new StartScreen(gameBoard.GetM_Stage(), null);
            }
        }
        gameBoard.drawGame();
    }

    /**
     * Displays the score.
     */
    private void displayScore() {
        int score = (int) (SCORE_DIVIDEND /(GetM_Stopwatch()));
        setM_TotalScore(getM_TotalScore() + score);
        new ScorePopup(score);
    }

    /**
     * Sets the controls for the scene, so that the player can interact with
     * the game.
     *
     * @param gameBoard a GameBoard.
     */
    private void setSceneControls(GameBoard gameBoard) {
        LevelManager levelManager = gameBoard.GetM_LevelManager();
        Scene scene = gameBoard.GetM_Scene();
        PauseMenu pauseMenu = getM_PauseMenu();
        DebugConsole debugConsole = getM_DebugConsole();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                levelManager.GetM_Player().MoveRight();
            } else if (e.getCode() == KeyCode.LEFT) {
                levelManager.GetM_Player().MoveLeft();
            } else if (e.getCode() == KeyCode.SPACE) {
                GetM_Timer().play();
            } else if (e.getCode() == KeyCode.ESCAPE) {
                GetM_Timer().pause();
                if (pauseMenu == null || pauseMenu.GetM_PausePane() == null) {
                    setM_PauseMenu(new PauseMenu(gameBoard));
                }
            } else if (e.getCode() == KeyCode.F1) {
                if (e.isAltDown() && e.isShiftDown()) {
                    GetM_Timer().pause();
                    if (debugConsole == null ||
                            debugConsole.GetM_DebugPane() == null) {

                        SetM_DebugConsole(new DebugConsole(gameBoard));
                    }
                }
            }
        });
        scene.setOnKeyReleased(e -> levelManager.GetM_Player().Stop());
    }
}
