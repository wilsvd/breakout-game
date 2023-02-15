package com.example.dmscoursework.View.Ball;

import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrangeBallTest {

    private OrangeBall orangeBall;
    private Point2D startPoint;
    @Before
    public void setUp() {
        startPoint = new Point2D(300, 300);
        orangeBall = new OrangeBall(startPoint);
    }

    @Test
    public void getM_BallShape() {
        int result = (int) orangeBall.GetM_BallShape().getRadius();
        int correct = 10;
        assertEquals(result, correct, 0);
    }

    @Test
    public void getM_Center() {
        Point2D result = orangeBall.GetM_Center();
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
        orangeBall.setM_Center(newCenter);
        Point2D result = orangeBall.GetM_Center();
        assertEquals(result, newCenter);
    }

    @Test
    public void getUp() {
        Point2D correct = new Point2D(startPoint.getX(),
                startPoint.getX() - orangeBall.GetM_BallShape().getRadius());

        Point2D result = orangeBall.GetUp();
        assertEquals(correct, result);
    }

    @Test
    public void getDown() {

        Point2D correct = new Point2D(startPoint.getX(),
                startPoint.getY() + orangeBall.GetM_BallShape().getRadius());

        Point2D result = orangeBall.GetDown();
        assertEquals(correct, result);
    }

    @Test
    public void getLeft() {
        Point2D correct = new Point2D(startPoint.getX() -
                orangeBall.GetM_BallShape().getRadius(), startPoint.getY());

        Point2D result = orangeBall.GetLeft();
        assertEquals(correct, result);
    }

    @Test
    public void getRight() {
        Point2D correct = new Point2D(startPoint.getX() +
                orangeBall.GetM_BallShape().getRadius(), startPoint.getY());

        Point2D result = orangeBall.GetRight();
        assertEquals(correct, result);
    }

    @Test
    public void setSpeed() {
        int correctXSpeed = 5;
        int correctYSpeed = 10;
        orangeBall.SetSpeed(correctXSpeed, correctYSpeed);
        int resultXSpeed = orangeBall.GetXSpeed();
        int resultYSpeed = orangeBall.GetYSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void setXSpeed() {
        int correctXSpeed = 5;
        orangeBall.SetXSpeed(correctXSpeed);
        int resultXSpeed = orangeBall.GetXSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
    }

    @Test
    public void setYSpeed() {
        int correctYSpeed = 5;
        orangeBall.SetYSpeed(correctYSpeed);
        int resultYSpeed = orangeBall.GetYSpeed();
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void getXSpeed() {
        int correctXSpeed = 5;
        orangeBall.SetXSpeed(correctXSpeed);
        int resultXSpeed = orangeBall.GetXSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
    }

    @Test
    public void getYSpeed() {
        int correctYSpeed = 5;
        orangeBall.SetYSpeed(correctYSpeed);
        int resultYSpeed = orangeBall.GetYSpeed();
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void moveTo() {
        Point2D correct = new Point2D(orangeBall.GetM_Center().getX(),
                orangeBall.GetM_Center().getY() -
                        orangeBall.GetM_BallShape().getRadius());

        orangeBall.MoveTo(startPoint);
        assertEquals(orangeBall.GetM_BallShape().getCenterX(), correct.getX(), 0);
        assertEquals(orangeBall.GetM_BallShape().getCenterY(), correct.getY(), 0);
    }

    @Test
    public void reverseX() {
        int speed = orangeBall.GetXSpeed();
        orangeBall.ReverseX();
        int reverseSpeed = -orangeBall.GetXSpeed();
        assertEquals(speed, reverseSpeed);
    }

    @Test
    public void reverseY() {
        int speed = orangeBall.GetYSpeed();
        orangeBall.ReverseY();
        int reverseSpeed = -orangeBall.GetYSpeed();
        assertEquals(speed, reverseSpeed);
    }

}