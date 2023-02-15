package com.example.dmscoursework.View.Level;

import com.example.dmscoursework.View.Ball.*;
import com.example.dmscoursework.View.Board.GameBoard;
import com.example.dmscoursework.View.Brick.Brick;
import com.example.dmscoursework.View.Paddle.*;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

import java.util.Random;
/**
 * The LevelManager is in charge of the levels and it specifies what
 * themed levels should be generated. It has one responsibility in making sure
 * that the levels work correctly and that one can transition from level to
 * level smoothly.
 *
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class LevelManager {

    private final int BRICK_COUNT = 30;
    private final int LINE_COUNT = 3;
    private final double BRICK_RATIO = 6/2;
    private final int PADDLE_X = 300;
    private final int PADDLE_Y = 430;
    private final int PADDLE_WIDTH = 150;
    private final int PADDLE_HEIGHT = 12;
    private final int MAX_SPEED = 3;

    private Point2D m_StartPoint;

    private LevelBuilder m_LevelBuilder;

    private int m_BallCount;
    private boolean m_BallLost;

    private Ball m_Ball;
    private Paddle m_Player;

    /**
     * Gets the ball.
     *
     * @return a Ball, which is the ball in the level.
     */
    public Ball GetM_Ball() {
        return m_Ball;
    }
    /**
     * Sets the ball.
     *
     * @param ball a Ball, which is the ball in the level.
     */
    private void setM_Ball(Ball ball) {
        this.m_Ball = ball;
    }

    /**
     * Gets the player.
     *
     * @return a Paddle, which is what the user controls.
     */
    public Paddle GetM_Player() {
        return m_Player;
    }
    /**
     * Sets the player
     *
     * @param player a Paddle, which is what the user controls.
     */
    private void setM_Player(Paddle player) {
        this.m_Player = player;
    }

    /**
     * Gets the ball count, effectively getting number of lives in the game.
     *
     * @return an integer, which is the ball count.
     */
    public int GetM_BallCount() {
        return m_BallCount;
    }
    /**
     * Sets the ball count, effectively setting number of lives in the game.
     *
     * @param ballCount an integer, which is number of balls.
     */
    public void SetM_BallCount(int ballCount) {
        this.m_BallCount = ballCount;
    }

    /**
     * Gets whether the ball is lost or not.
     *
     * @return a boolean, which specifies if a ball is lost or not.
     */
    public boolean IsM_BallLost() {
        return m_BallLost;
    }

    /**
     * Sets whether the ball is lost or not.
     *
     * @param ballLost a boolean, which specifies if ball is lost.
     */
    public void SetM_BallLost(boolean ballLost) {
        this.m_BallLost = ballLost;
    }

    /**
     * Gets the level builder which makes the level.
     *
     * @return a LevelBuilder, which contains information regarding the level.
     */
    public LevelBuilder GetM_LevelBuilder() {
        return m_LevelBuilder;
    }
    /**
     * Sets the level builder which makes the level.
     *
     * @param levelBuilder a LevelBuilder, which builds the levels.
     */
    private void setM_LevelBuilder(LevelBuilder levelBuilder) {
        this.m_LevelBuilder = levelBuilder;
    }

    /**
     * Gets the starting point for the ball and paddle.
     *
     * @return a Point2D, which is the coordinates of start point.
     */
    private Point2D getM_StartPoint() {
        return m_StartPoint;
    }
    /**
     * Sets the starting point.
     *
     * @param startPoint a Point2D, which is the coordinates of the startpoint.
     */
    private void setM_StartPoint(Point2D startPoint) {
        this.m_StartPoint = startPoint;
    }

    /**
     * Constructs the LevelManager which takes the gameBoard
     * and makes sure that the gameBoard is set up.
     *
     * @param gameBoard a GameBoard, which is the gameBoard being played on.
     */
    public LevelManager(GameBoard gameBoard) {
        String theme = gameBoard.GetM_Theme();

        Point2D startPoint = new Point2D(PADDLE_X , PADDLE_Y);
        setM_StartPoint(startPoint);

        setTheme(gameBoard , theme);

        ResetBallCount();
        BallReset();
    }

    /**
     * Sets up the gameBoard by making levels, paddles and ball according
     * to the specified theme.
     *
     * @param gameBoard a GameBoard, which is to have a theme.
     * @param theme a String, which is the theme of the gameBoard.
     */
    private void setTheme(GameBoard gameBoard, String theme) {
        Rectangle drawArea = new Rectangle
                (0, 0, gameBoard.GetWidth(), gameBoard.GetHeight());

        LevelBuilder levelBuilder = new LevelBuilder(drawArea, BRICK_COUNT,
                LINE_COUNT, BRICK_RATIO, theme);

        if (theme == "sunset") {
            setM_Player(new OrangePaddle(
                    getM_StartPoint(), PADDLE_WIDTH, PADDLE_HEIGHT, drawArea));
            setM_Ball(new OrangeBall(getM_StartPoint()));
        } else if (theme == "ocean") {
            setM_Player(new BluePaddle(
                    getM_StartPoint(), PADDLE_WIDTH, PADDLE_HEIGHT, drawArea));
            setM_Ball(new BlueBall(getM_StartPoint()));
        } else if (theme == "candy") {
            setM_Player(new PinkPaddle(
                    getM_StartPoint(), PADDLE_WIDTH, PADDLE_HEIGHT, drawArea));
            setM_Ball(new PinkBall(getM_StartPoint()));
        } else {
            setM_Player(new BasicPaddle(
                    getM_StartPoint(), PADDLE_WIDTH, PADDLE_HEIGHT, drawArea));
            setM_Ball(new RubberBall(getM_StartPoint()));
        }
        setM_LevelBuilder(levelBuilder);
    }

    /**
     * Assigns the bricks to next level.
     */
    public void NextLevel() {
        Brick[][] levels = GetM_LevelBuilder().GetM_Levels();
        int level = GetM_LevelBuilder().GetM_Level();
        int brickCount = GetM_LevelBuilder().GetM_BrickCnt();

        GetM_LevelBuilder().SetM_Bricks(levels[level]);
        GetM_LevelBuilder().SetM_Level(level + 1);
        GetM_LevelBuilder().SetM_BrickCnt(brickCount);
    }

    /**
     * Checks if the m_Level exists.
     *
     * @return a boolean, m_Level exists or not.
     */
    public boolean HasLevel() {
        int totalLevels = GetM_LevelBuilder().GetM_Levels().length;
        return GetM_LevelBuilder().GetM_Level() < totalLevels;
    }

    /**
     * Checks to see if m_Level has been completed.
     *
     * @return a boolean, m_Level is done or not.
     */
    public boolean IsLevelDone() {
        return (GetM_LevelBuilder().GetM_BrickCnt() == 0);
    }

    /**
     * Resets the level to its original state.
     */
    public void ResetLevel() {
        int brickCount = GetM_LevelBuilder().GetM_Bricks().length;
        for (Brick b : GetM_LevelBuilder().GetM_Bricks()) {
            b.Repair();
        }
        GetM_LevelBuilder().SetM_BrickCnt(brickCount);
        ResetBallCount();
    }

    /**
     * Checks to see if player has run out of balls.
     *
     * @return a boolean, player has run out of balls or not.
     */
    public boolean BallEnd() {
        return (GetM_BallCount() == 0);
    }

    /**
     * Resets the number of balls.
     */
    public void ResetBallCount() {
        SetM_BallCount(3);
    }

    /**
     * Decreases the number of balls.
     */
    public void ReduceBallCount() {
        if (GetM_BallCount() > 1) {
            SetM_BallCount(GetM_BallCount() - 1);
        }
    }

    /**
     * Increases the number of balls.
     */
    public void IncreaseBallCount() {
        SetM_BallCount(GetM_BallCount() + 1);
    }

    /**
     * Moves the player and the ball.
     */
    public void Move() {
        GetM_Player().Move();
        GetM_Ball().Move();
    }

    /**
     * Resets the ball and player to their original starting position.
     */
    public void BallReset() {
        Random m_Rnd = new Random();
        GetM_Player().MoveTo(getM_StartPoint());
        GetM_Ball().MoveTo(getM_StartPoint());
        int speedX, speedY;
        do {
            speedX = m_Rnd.nextInt(MAX_SPEED) - 1;
        } while (speedX == 0);
        do {
            speedY = -m_Rnd.nextInt(MAX_SPEED);
        } while (speedY == 0);
        GetM_Ball().SetSpeed(speedX, speedY);
        SetM_BallLost(false);
    }
}
