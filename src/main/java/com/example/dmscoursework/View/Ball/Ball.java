package com.example.dmscoursework.View.Ball;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
/**
 * The Ball class is an abstract class which contains all the relevant
 * information that a ball may need such as speed, shape, location and
 * methods which will make changes to these parameters.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
abstract public class Ball {

    private Circle m_BallShape;
    private Point2D m_Center;

    private int m_SpeedX;
    private int m_SpeedY;

    /**
     * Gets the shape of the ball.
     * @return a Circle, which is the shape of the ball.
     */
    public Circle GetM_BallShape() {
        return m_BallShape;
    }

    /**
     * Sets the shape of the ball.
     *
     * @param ballShape a Circle, which is the shape of the ball.
     */
    private void setM_BallShape(Circle ballShape) {
        this.m_BallShape = ballShape;
    }

    /**
     * Gets the center position of the ball.
     *
     * @return Point2D, which is the center position of the ball.
     */
    public Point2D GetM_Center() {
        return m_Center;
    }

    /**
     *
     * @param center Point2D, which is a coordinate of center position of the ball.
     */
    public void setM_Center(Point2D center) {
        this.m_Center = center;
    }

    /**
     * Gets coordinate of the top of the ball
     * @return a Point2D which is the coordinate of the top of the ball.
     */
    public Point2D GetUp() {
        return new Point2D(GetM_Center().getX(),
                GetM_Center().getY() - GetM_BallShape().getRadius());
    }
    /**
     * Gets coordinate of the bottom of the ball
     * @return a Point2D, which is the  coordinate of the bottom of the ball.
     */
    public Point2D GetDown() {
        return new Point2D(GetM_Center().getX(),
                GetM_Center().getY() + GetM_BallShape().getRadius());
    }
    /**
     * Gets coordinate of the left of the ball
     * @return a Point2D, which is the coordinate of the left of the ball.
     */
    public Point2D GetLeft() {
        return new Point2D(GetM_Center().getX() - GetM_BallShape().getRadius(),
                GetM_Center().getY());
    }
    /**
     * Gets coordinate of the right of the ball
     * @return a Point2D, which is the coordinate of the right of the ball.
     */
    public Point2D GetRight() {
        return new Point2D(GetM_Center().getX() + GetM_BallShape().getRadius(),
                GetM_Center().getY());
    }

    /**
     * Sets the speed of the ball.
     * @param x horizontal speed of the ball.
     * @param y vertical speed of the ball.
     */
    public void SetSpeed(int x, int y) {
        SetXSpeed(x);
        SetYSpeed(y);
    }

    /**
     * Sets the horizontal speed of the ball.
     * @param s horizontal speed of ball.
     */
    public void SetXSpeed(int s) {
        m_SpeedX = s;
    }
    /**
     * Gets the horizontal speed of ball.
     * @return the horizontal speed of ball.
     */
    public int GetXSpeed() {
        return m_SpeedX;
    }
    /**
     * Sets the vertical speed of the ball.
     * @param s vertical speed of ball.
     */
    public void SetYSpeed(int s) {
        m_SpeedY = s;
    }
    /**
     * Gets the vertical speed of ball.
     * @return the vertical speed of ball.
     */
    public int GetYSpeed() {
        return m_SpeedY;
    }

    /**
     * Constructs a ball whose starting position is specified by Point2D
     * argument. The horizontal and vertical radius are specified by Int
     * arguments.
     *
     * @param center a Point2D which represents position of the ball.
     * @param radiusA the horizontal radius of the ball.
     * @param radiusB the vertical radius of the ball.
     */
    public Ball(Point2D center, int radiusA, int radiusB) {
        setM_Center(new Point2D(center.getX(), center.getY()));
        setM_BallShape(makeBall(center, radiusA, radiusB));
        SetSpeed(0, 0);
    }

    /**
     * Makes a circle by passing in the ball position as a Point2D argument,
     * as well as the horizontal and vertical radius.
     *
     * @param center a Point2D which represents position of the ball.
     * @param radiusA the horizontal radius of the ball.
     * @param radiusB the vertical radius of the ball.
     * @return a Circle.
     */
    protected abstract Circle makeBall(Point2D center, int radiusA,
                                       int radiusB);
    /**
     * Moves the ball to a specific point.
     *
     * @param point a Point2D, which is a coordinate where ball is placed.
     */
    public void MoveTo(Point2D point) {
        setM_Center(new Point2D(point.getX(), point.getY()));

        GetM_BallShape().setCenterX(GetM_Center().getX());
        GetM_BallShape().setCenterY(
                GetM_Center().getY() - GetM_BallShape().getRadius());
    }

    /**
     * Moves the ball around the screen by combining its speed and centre
     * position to produce a new coordinate which is the new position.
     */
    public void Move() {
        setM_Center(new Point2D(GetM_BallShape().getCenterX() + GetXSpeed(),
                GetM_BallShape().getCenterY() + GetYSpeed()));

        GetM_BallShape().setCenterX(GetM_Center().getX());
        GetM_BallShape().setCenterY(GetM_Center().getY());
    }

    /**
     * Reverses the ball to make it move in the opposite horizontal direction.
     */
    public void ReverseX() {
        SetXSpeed(GetXSpeed() * -1);
    }

    /**
     * Reverses the ball to make it move in the opposite vertical direction.
     */
    public void ReverseY() {
        SetYSpeed(GetYSpeed() * -1);
    }


}
