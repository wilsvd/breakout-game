package com.example.dmscoursework.View.Level;

import com.example.dmscoursework.View.Ball.Ball;
import com.example.dmscoursework.View.Board.GameBoard;
import com.example.dmscoursework.View.Brick.Brick;
import com.example.dmscoursework.View.Paddle.Paddle;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
/**
 * The CollisionChecker is in charge of checking all collisions that take
 * place in the game. The CollisionChecker makes sure that the game responds
 * appropriately to specific events in the game such as impacting a brick,
 * impacting a paddle etc.
 *
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class CollisionChecker {

    private GameBoard m_GameBoard;

    /**
     * Gets the gameboard.
     *
     * @return a GameBoard, which is board being played on.
     */
    private GameBoard getM_GameBoard() {
        return m_GameBoard;
    }
    /**
     * Sets the gameboard, so it can be called from anywhere in file.
     *
     * @param gameBoard a GameBoard, which is board being played on.
     */
    private void setM_GameBoard(GameBoard gameBoard) {
        this.m_GameBoard = gameBoard;
    }

    /**
     * Constructs a collision checker which will check for collisions in
     * the gameBoard.
     *
     * @param gameBoard a GameBoard, which is the gameBoard played on.
     */
    public CollisionChecker(GameBoard gameBoard) {
        setM_GameBoard(gameBoard);
    }

    /**
     * Finds the impact of the ball for all cases e.g. Wall, Paddle, Container.
     */
    public void FindImpacts() {
        LevelManager levelManager = getM_GameBoard().GetM_LevelManager();
        Ball ball = levelManager.GetM_Ball();
        Paddle paddle = levelManager.GetM_Player();

        if (paddle.Impact(ball)) {
            changeBallMovement(ball, paddle);
        } else if (ImpactWall()) {
            // for efficiency reverse is done into method impactWall because for every m_Brick program checks for horizontal and vertical impacts
            levelManager.GetM_LevelBuilder().SetM_BrickCnt(levelManager.GetM_LevelBuilder().GetM_BrickCnt() - 1);

        } else if (impactBorder()) {
            ball.ReverseX();

        } else if (impactTop()) {
            ball.ReverseY();

        } else if (DodgePaddle()) {
            levelManager.SetM_BallCount(levelManager.GetM_BallCount() - 1);
            levelManager.SetM_BallLost(true);
        }
    }

    /**
     * Checks if ball has impacted the top of the container.
     *
     * @return a boolean, which is whether ball hit top or not.
     */
    private boolean impactTop() {
        LevelManager levelManager = getM_GameBoard().GetM_LevelManager();
        Ball ball = levelManager.GetM_Ball();
        return ball.GetUp().getY() <= levelManager.GetM_LevelBuilder().GetM_DrawArea().getY();
    }

    /**
     * Changes the movement of the ball according to where it collides on the
     * paddle.
     *
     * @param ball a Ball, which is the ball that collides with the paddle.
     * @param paddle a Paddle, which is being collided with.
     */
    private void changeBallMovement(Ball ball, Paddle paddle) {
        int paddleSegment1 = (int) (paddle.GetM_PaddleShape().getWidth() * 0.25);
        int paddleSegment2 = (int) (paddle.GetM_PaddleShape().getWidth() * 0.50);
        int paddleSegment3 = (int) (paddle.GetM_PaddleShape().getWidth() * 0.75);

        int paddleStart = (int) paddle.GetM_PaddleShape().getX();
        int relativeLeftCentreMidPos = paddleSegment1 + paddleStart;
        int relativeCentrePos = paddleSegment2 + paddleStart;
        int relativeRightCentreMidPos = paddleSegment3 + paddleStart;
        int paddleEnd = (int) paddle.GetM_PaddleShape().getWidth() + paddleStart;

        if (ball.GetDown().getX() >= paddleStart &&
                ball.GetDown().getX() <= relativeLeftCentreMidPos){
            if (ball.GetXSpeed() > 0) {
                if (ball.GetYSpeed() > 1 && ball.GetXSpeed() < 3) {
                    ball.SetYSpeed(ball.GetYSpeed() - 1);
                    ball.SetXSpeed(ball.GetXSpeed() + 1);
                }
                ball.ReverseX();
            }
        }
        else if (ball.GetDown().getX() >= relativeLeftCentreMidPos &&
                ball.GetDown().getX() <= relativeCentrePos ){
            if (ball.GetXSpeed() > 0) {
                if (ball.GetYSpeed() < 3 && ball.GetXSpeed() < 3) {
                    ball.SetYSpeed(ball.GetYSpeed() + 1);
                    ball.SetXSpeed(ball.GetXSpeed());
                }
                ball.ReverseX();
            }
        }
        else if (ball.GetDown().getX() >= relativeCentrePos &&
                ball.GetDown().getX() <= relativeRightCentreMidPos ){
            if (ball.GetXSpeed() < 0) {
                if (ball.GetYSpeed() < 3 && ball.GetXSpeed() > -3) {
                    ball.SetYSpeed(ball.GetYSpeed() + 1);
                    ball.SetXSpeed(ball.GetXSpeed());
                }
                ball.ReverseX();
            }
        }
        else if (ball.GetDown().getX() >= relativeRightCentreMidPos &&
                ball.GetDown().getX() <= paddleEnd){
            if (ball.GetXSpeed() < 0) {
                if (ball.GetYSpeed() > 1 && ball.GetXSpeed() > -3) {
                    ball.SetYSpeed(ball.GetYSpeed() - 1);
                    ball.SetXSpeed(ball.GetXSpeed() - 1);
                }
                ball.ReverseX();
            }
        }
        ball.ReverseY();
    }

    /**
     * Checks if the ball has impacted the wall.
     *
     * @return a boolean, which is whether ball hit the wall or not.
     */
    public boolean ImpactWall() {
        LevelManager levelManager = getM_GameBoard().GetM_LevelManager();
        Ball ball = levelManager.GetM_Ball();

        for (Brick b : levelManager.GetM_LevelBuilder().GetM_Bricks()) {
            switch (b.FindImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT -> {
                    ball.ReverseY();
                    return b.SetImpact(ball.GetDown(), Brick.Crack.UP);
                }
                case Brick.DOWN_IMPACT -> {
                    ball.ReverseY();
                    return b.SetImpact(ball.GetUp(), Brick.Crack.DOWN);
                }
                //Horizontal Impact
                case Brick.LEFT_IMPACT -> {
                    ball.ReverseX();
                    return b.SetImpact(ball.GetRight(), Brick.Crack.LEFT);
                }
                case Brick.RIGHT_IMPACT -> {
                    ball.ReverseX();
                    return b.SetImpact(ball.GetLeft(), Brick.Crack.RIGHT);
                }
            }
        }
        return false;
    }

    /**
     * Checks if the ball impacts the horizontal borders.
     *
     * @return a boolean, which checks if ball hit border or not.
     */
    private boolean impactBorder() {
        LevelManager levelManager = getM_GameBoard().GetM_LevelManager();
        Ball ball = levelManager.GetM_Ball();
        Rectangle drawArea = levelManager.GetM_LevelBuilder().GetM_DrawArea();

        Point2D left = ball.GetLeft();
        Point2D right = ball.GetRight();

        return (
                (left.getX() <= drawArea.getX()) ||
                (right.getX() >= drawArea.getX() + drawArea.getWidth())
        );
    }

    /**
     * Checks if the ball impacts the paddle.
     *
     * @return a boolean, which checks if the ball hit paddle or not.
     */
    public boolean DodgePaddle() {
        LevelManager levelManager = getM_GameBoard().GetM_LevelManager();
        Ball ball = levelManager.GetM_Ball();
        Point2D ballPosition = ball.GetM_Center();
        Rectangle drawArea = levelManager.GetM_LevelBuilder().GetM_DrawArea();

        return ballPosition.getY() >= drawArea.getY() + drawArea.getHeight();
    }

}
