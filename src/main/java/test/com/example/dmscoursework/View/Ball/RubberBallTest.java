package com.example.dmscoursework.View.Ball;

import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RubberBallTest {

    private RubberBall rubberBall;
    private Point2D startPoint;
    @Before
    public void setUp() {
        startPoint = new Point2D(300, 300);
        rubberBall = new RubberBall(startPoint);
    }

    @Test
    public void getM_BallShape() {
        int result = (int) rubberBall.GetM_BallShape().getRadius();
        int correct = 10;
        assertEquals(result, correct, 0);
    }

    @Test
    public void getM_Center() {
        Point2D result = rubberBall.GetM_Center();
        double resultX = result.getX();
        double resultY = result.getY();
        double correctX = startPoint.getX();
        double correctY = startPoint.getY();
        assertEquals(resultX, correctX, 0);
        assertEquals(resultY, correctY, 0);
    }

    @Test
    public void setM_Center() {
        Point2D newCenter = new Point2D(400, 400);
        rubberBall.setM_Center(newCenter);
        Point2D result = rubberBall.GetM_Center();
        assertEquals(result, newCenter);
    }

    @Test
    public void getUp() {
        Point2D correct = new Point2D(startPoint.getX(),
                startPoint.getX() - rubberBall.GetM_BallShape().getRadius());

        Point2D result = rubberBall.GetUp();
        assertEquals(correct, result);
    }

    @Test
    public void getDown() {

        Point2D correct = new Point2D(startPoint.getX(),
                startPoint.getY() + rubberBall.GetM_BallShape().getRadius());

        Point2D result = rubberBall.GetDown();
        assertEquals(correct, result);
    }

    @Test
    public void getLeft() {
        Point2D correct = new Point2D(startPoint.getX() -
                rubberBall.GetM_BallShape().getRadius(), startPoint.getY());

        Point2D result = rubberBall.GetLeft();
        assertEquals(correct, result);
    }

    @Test
    public void getRight() {
        Point2D correct = new Point2D(startPoint.getX() +
                rubberBall.GetM_BallShape().getRadius(), startPoint.getY());

        Point2D result = rubberBall.GetRight();
        assertEquals(correct, result);
    }

    @Test
    public void setSpeed() {
        int correctXSpeed = 5;
        int correctYSpeed = 10;
        rubberBall.SetSpeed(correctXSpeed, correctYSpeed);
        int resultXSpeed = rubberBall.GetXSpeed();
        int resultYSpeed = rubberBall.GetYSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void setXSpeed() {
        int correctXSpeed = 5;
        rubberBall.SetXSpeed(correctXSpeed);
        int resultXSpeed = rubberBall.GetXSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
    }

    @Test
    public void setYSpeed() {
        int correctYSpeed = 5;
        rubberBall.SetYSpeed(correctYSpeed);
        int resultYSpeed = rubberBall.GetYSpeed();
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void getXSpeed() {
        int correctXSpeed = 5;
        rubberBall.SetXSpeed(correctXSpeed);
        int resultXSpeed = rubberBall.GetXSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
    }

    @Test
    public void getYSpeed() {
        int correctYSpeed = 5;
        rubberBall.SetYSpeed(correctYSpeed);
        int resultYSpeed = rubberBall.GetYSpeed();
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void moveTo() {
        Point2D correct = new Point2D(rubberBall.GetM_Center().getX(),
                rubberBall.GetM_Center().getY() -
                        rubberBall.GetM_BallShape().getRadius());

        rubberBall.MoveTo(startPoint);
        assertEquals(rubberBall.GetM_BallShape().getCenterX(), correct.getX(), 0);
        assertEquals(rubberBall.GetM_BallShape().getCenterY(), correct.getY(), 0);
    }

    @Test
    public void reverseX() {
        int speed = rubberBall.GetXSpeed();
        rubberBall.ReverseX();
        int reverseSpeed = -rubberBall.GetXSpeed();
        assertEquals(speed, reverseSpeed);
    }

    @Test
    public void reverseY() {
        int speed = rubberBall.GetYSpeed();
        rubberBall.ReverseY();
        int reverseSpeed = -rubberBall.GetYSpeed();
        assertEquals(speed, reverseSpeed);
    }

}