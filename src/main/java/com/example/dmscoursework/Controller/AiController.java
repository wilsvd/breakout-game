package com.example.dmscoursework.Controller;

import com.example.dmscoursework.View.Brick.Brick;
import com.example.dmscoursework.View.Level.CollisionChecker;
import com.example.dmscoursework.View.Level.LevelManager;
import com.example.dmscoursework.View.Paddle.Paddle;
import javafx.scene.shape.Rectangle;

import java.util.Random;
/**
 * The AiController is the logic behind the AI. The AIController will move
 * the paddle according to the position of the ball. It will check the time
 * between bricks hit, if a brick has not been hit in a certain amount of
 * time then the assumption is made that the ball is moving to same location
 * and the ball needs to be reset so that the AI can start again. The AI has
 * been able to complete all levels with the three specified lives.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */

public class AiController {

    private final double HALF = 0.5;
    private final int DELAY = 20;
    private final int BOARD_WIDTH = 600;

    private double m_HitTime = 0;
    private LevelManager m_LevelManager;
    private GameController m_GameController;
    private CollisionChecker m_CollisionChecker;

    /**
     * Get the time taken between hitting bricks.
     *
     * @return a double, which is time taken to hit a brick.
     */
    private double getM_HitTime() {
        return m_HitTime;
    }

    /**
     * Set the time taken between hitting bricks.
     *
     * @param hitTime a double, which is time taken
     */
    private void setM_HitTime(double hitTime) {
        this.m_HitTime = hitTime;
    }

    /**
     * Gets the level manager.
     *
     * @return a LevelManager.
     */
    private LevelManager getM_LevelManager() {
        return m_LevelManager;
    }
    /**
     * Sets the level manager.
     *
     * @param levelManager a LevelManager.
     */
    private void setM_LevelManager(LevelManager levelManager) {
        this.m_LevelManager = levelManager;
    }

    /**
     * Gets the game controller.
     *
     * @return a GameController.
     */
    private GameController getM_GameController() {
        return m_GameController;
    }

    /**
     * Sets the game controller.
     *
     * @param gameController a GameController.
     */
    private void setM_GameController(GameController gameController) {
        this.m_GameController = gameController;
    }

    /**
     * Get the collision checker.
     *
     * @return a CollisionChecker.
     */
    private CollisionChecker getM_CollisionChecker() {
        return m_CollisionChecker;
    }

    /**
     * Sets the collision checker
     *
     * @param collisionChecker a CollisionChecker.
     */
    private void setM_CollisionChecker(CollisionChecker collisionChecker) {
        this.m_CollisionChecker = collisionChecker;
    }

    /**
     * Constructs an Ai which controls the movement of the paddle by using
     * information from the game controller, level manager and the collision
     * checker.
     *
     * @param gameController a GameController, which controls the game.
     * @param levelManager a levelManager, which manages the level.
     * @param collisionChecker a CollisionChecker, which checks for collisions.
     */
    public AiController(GameController gameController,
                        LevelManager levelManager,
                        CollisionChecker collisionChecker) {
        setM_GameController(gameController);
        setM_LevelManager(levelManager);
        setM_CollisionChecker(collisionChecker);
    }

    /**
     * Moves the paddle if a brick been hit recently, but if not AI will not
     * move the ball as it means the ball is bouncing in the same location,
     * and it needs to let ball drop to allow reset. If the AI is activated
     * and the player comes back, the AI will let the player play only in this
     * period where a brick hasn't been hit.
     */
    public void MovePaddle() {
        if (hitBrickRecently()) {
            moveToX(getM_LevelManager().GetM_Ball().GetM_Center().getX());
        } else {
            getM_LevelManager().GetM_Player().Move();
        }
    }

    /**
     * Moves the paddle to an x coordinate which is the same x coordinate as
     * the ball.
     *
     * @param x a double, which is the x coordinate of the ball.
     */
    private void moveToX(double x) {
        Paddle paddle = getM_LevelManager().GetM_Player();
        Rectangle paddleShape = paddle.GetM_PaddleShape();

        Random rnd = new Random();
        int modifier = (int) ((rnd.nextInt((int) (paddleShape.getWidth()))) -
                (paddleShape.getWidth() * HALF));

        int min = paddle.GetM_Min();
        int max = paddle.GetM_Max();

        if (x - paddleShape.getWidth() * HALF + modifier < min ||
            x - paddleShape.getWidth() * HALF + modifier > max) {
            return;
        }
        else if ((x - paddleShape.getWidth() * HALF) <
                    (paddleShape.getWidth() * HALF - 1)) {
            paddleShape.setX(0);
        } else if ((x + paddleShape.getWidth() * HALF) >
                    (BOARD_WIDTH - paddleShape.getWidth() * HALF + 1)) {
            paddleShape.setX(BOARD_WIDTH - paddleShape.getWidth());
        } else {
            paddleShape.setX((x - paddleShape.getWidth() * HALF) + modifier);
        }
    }

    /**
     * Checks if a brick has been hit recently by checking if a brick has
     * been hit and getting the time on impact and then checking if a brick
     * has not been hit again in 20 seconds.
     *
     * @return
     */
    private boolean hitBrickRecently() {
        for (Brick b : getM_LevelManager().GetM_LevelBuilder().GetM_Bricks()) {
            if (b.IsImpacted(getM_LevelManager().GetM_Ball())) {
                setM_HitTime(getM_GameController().GetM_Stopwatch());
                return true;
            }
        }
        if (getM_CollisionChecker().DodgePaddle()) {
            setM_HitTime(getM_GameController().GetM_Stopwatch());
            getM_GameController().GetM_Timer().pause();
            return true;
        } else return !((getM_HitTime() + DELAY) <= getM_GameController().GetM_Stopwatch());
    }

}
