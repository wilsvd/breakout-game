package com.example.dmscoursework.View.Ball;

import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PinkBallTest {

    private PinkBall pinkBall;
    private Point2D startPoint;
    @Before
    public void setUp() {
        startPoint = new Point2D(300, 300);
        pinkBall = new PinkBall(startPoint);
    }

    @Test
    public void getM_BallShape() {
        int result = (int) pinkBall.GetM_BallShape().getRadius();
        int correct = 10;
        assertEquals(result, correct, 0);
    }

    @Test
    public void getM_Center() {
        Point2D result = pinkBall.GetM_Center();
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
        pinkBall.setM_Center(newCenter);
        Point2D result = pinkBall.GetM_Center();
        assertEquals(result, newCenter);
    }

    @Test
    public void getUp() {
        Point2D correct = new Point2D(startPoint.getX(),
                startPoint.getX() - pinkBall.GetM_BallShape().getRadius());

        Point2D result = pinkBall.GetUp();
        assertEquals(correct, result);
    }

    @Test
    public void getDown() {

        Point2D correct = new Point2D(startPoint.getX(),
                startPoint.getY() + pinkBall.GetM_BallShape().getRadius());

        Point2D result = pinkBall.GetDown();
        assertEquals(correct, result);
    }

    @Test
    public void getLeft() {
        Point2D correct = new Point2D(startPoint.getX() -
                pinkBall.GetM_BallShape().getRadius(), startPoint.getY());

        Point2D result = pinkBall.GetLeft();
        assertEquals(correct, result);
    }

    @Test
    public void getRight() {
        Point2D correct = new Point2D(startPoint.getX() +
                pinkBall.GetM_BallShape().getRadius(), startPoint.getY());

        Point2D result = pinkBall.GetRight();
        assertEquals(correct, result);
    }

    @Test
    public void setSpeed() {
        int correctXSpeed = 5;
        int correctYSpeed = 10;
        pinkBall.SetSpeed(correctXSpeed, correctYSpeed);
        int resultXSpeed = pinkBall.GetXSpeed();
        int resultYSpeed = pinkBall.GetYSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void setXSpeed() {
        int correctXSpeed = 5;
        pinkBall.SetXSpeed(correctXSpeed);
        int resultXSpeed = pinkBall.GetXSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
    }

    @Test
    public void setYSpeed() {
        int correctYSpeed = 5;
        pinkBall.SetYSpeed(correctYSpeed);
        int resultYSpeed = pinkBall.GetYSpeed();
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void getXSpeed() {
        int correctXSpeed = 5;
        pinkBall.SetXSpeed(correctXSpeed);
        int resultXSpeed = pinkBall.GetXSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
    }

    @Test
    public void getYSpeed() {
        int correctYSpeed = 5;
        pinkBall.SetYSpeed(correctYSpeed);
        int resultYSpeed = pinkBall.GetYSpeed();
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void moveTo() {
        Point2D correct = new Point2D(pinkBall.GetM_Center().getX(),
                pinkBall.GetM_Center().getY() -
                        pinkBall.GetM_BallShape().getRadius());

        pinkBall.MoveTo(startPoint);
        assertEquals(pinkBall.GetM_BallShape().getCenterX(), correct.getX(), 0);
        assertEquals(pinkBall.GetM_BallShape().getCenterY(), correct.getY(), 0);
    }

    @Test
    public void reverseX() {
        int speed = pinkBall.GetXSpeed();
        pinkBall.ReverseX();
        int reverseSpeed = -pinkBall.GetXSpeed();
        assertEquals(speed, reverseSpeed);
    }

    @Test
    public void reverseY() {
        int speed = pinkBall.GetYSpeed();
        pinkBall.ReverseY();
        int reverseSpeed = -pinkBall.GetYSpeed();
        assertEquals(speed, reverseSpeed);
    }

}