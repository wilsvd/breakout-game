package com.example.dmscoursework.View.Paddle;

import com.example.dmscoursework.View.Ball.Ball;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
/**
 * The Paddle class is an abstract class which contains all the relevant
 * information that a paddle may need such as movementAmount, shape, location
 * and methods which will make changes to these parameters.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
abstract public class Paddle {

    private final int DEF_MOVE_AMOUNT = 5;

    private final double HALF = 0.5;

    private Rectangle m_PaddleShape;
    private Point2D m_StartPosition;
    private int m_MoveAmount;
    private int m_Min;
    private int m_Max;

    /**
     * Gets the rectangle of the paddle.
     *
     * @return a Rectangle, which is the shape of the paddle.
     */
    public Rectangle GetM_PaddleShape() {
        return m_PaddleShape;
    }
    /**
     * Sets the rectangle of the paddle.
     *
     * @return a Rectangle, which is the shape of the paddle.
     */
    private void setM_PaddleShape(Rectangle paddleShape) {
        this.m_PaddleShape = paddleShape;
    }

    /**
     * Gets the coordinate of the ball.
     *
     * @return a Point2D, which is a coordinate.
     */
    public Point2D GetM_StartPosition() {
        return m_StartPosition;
    }
    /**
     * Sets the coordinate of the ball.
     *
     * @param startPosition a Point2D, which is a coordinate.
     */
    private void setM_StartPosition(Point2D startPosition) {
        this.m_StartPosition = startPosition;
    }

    /**
     * Gets the amount the paddle moves.
     *
     * @return an integer, which is the amount paddle moves.
     */
    private int getM_MoveAmount() {
        return m_MoveAmount;
    }
    /**
     * Sets the amount the paddle moves.
     *
     * @param moveAmount an integer, which is the amount paddle moves.
     */
    private void setM_MoveAmount(int moveAmount) {
        this.m_MoveAmount = moveAmount;
    }

    /**
     * Gets the minimum x point where the paddle can collide with container.
     *
     * @return an integer, which is minimum x coordinate.
     */
    public int GetM_Min() {
        return m_Min;
    }
    /**
     * Sets the minimum x point where the paddle can collide with container.
     *
     * @param min an integer, which is minimum x coordinate.
     */
    private void setM_Min(int min) {
        this.m_Min = min;
    }

    /**
     * Gets the maximum x point where the paddle can collide with container.
     *
     * @return an integer, which is maximum x coordinate.
     */
    public int GetM_Max() {
        return m_Max;
    }
    /**
     * Sets the maximum x point where the paddle can collide with container.
     *
     * @param max an integer, which is maximum x coordinate.
     */
    private void setM_Max(int max) {
        this.m_Max = max;
    }

    /**
     * Constructs a paddle which has a position as a Point argument, and has
     * width and height as an integer argument. It also specifies the
     * container in which the paddle can move inside with the Rectangle
     * argument.
     *
     * @param startPosition a Point, which is the starting position.
     * @param width an integer, which is the width of the paddle.
     * @param height an integer, which is the height of the paddle.
     * @param container a Rectangle, specifies limits the paddle can move.
     */
    public Paddle(Point2D startPosition, int width, int height,
                  Rectangle container) {

        setM_StartPosition(startPosition);
        setM_MoveAmount(0);
        setM_PaddleShape(makePaddle(width, height));
        setM_Min((int) container.getX());
        setM_Max((int) (container.getWidth() - GetM_PaddleShape().getWidth()));
    }

    /**
     * Makes the paddle which is a rectangle.
     *
     * @param width an integer, which is the width of the paddle.
     * @param height an integer, which is the height of the paddle.
     * @return a Rectangle, which is the paddle.
     */
    protected abstract Rectangle makePaddle(int width, int height);

    /**
     * Checks if the ball has impacted the paddle.
     *
     * @param ball a Ball
     * @return a boolean, to check if ball impacted or not.
     */
    public boolean Impact(Ball ball) {
        return GetM_PaddleShape().contains(ball.GetDown());
    }

    /**
     * Logic for moving the paddle and making sure it is in the bounds of the
     * container.
     */
    public void Move() {
        double x = GetM_PaddleShape().getX() + getM_MoveAmount();
        if (x < GetM_Min() || x > GetM_Max()) {
            return;
        }
        GetM_PaddleShape().setX(x);
    }

    /**
     * Sets the movement amount to a negative number.
     */
    public void MoveLeft() {
        setM_MoveAmount(-DEF_MOVE_AMOUNT);
    }

    /**
     * Sets the movement amount to a positive number.
     */
    public void MoveRight() {
        setM_MoveAmount(DEF_MOVE_AMOUNT);
    }

    /**
     * Sets the movement amount to zero which stops paddle from moving.
     */
    public void Stop() {
        setM_MoveAmount(0);
    }

    /**
     * Moves the paddle to a specific location.
     *
     * @param point a Point2D, which is a specific coordinate.
     */
    public void MoveTo(Point2D point) {
        GetM_PaddleShape().setX(point.getX() -
                GetM_PaddleShape().getWidth() * HALF);
    }

}
