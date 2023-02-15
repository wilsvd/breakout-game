package com.example.dmscoursework.View.Ball;

import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlueBallTest {

    private BlueBall blueBall;
    private Point2D startPoint;
    @Before
    public void setUp() {
        startPoint = new Point2D(300, 300);
        blueBall = new BlueBall(startPoint);
    }

    @Test
    public void getM_BallShape() {
        int result = (int) blueBall.GetM_BallShape().getRadius();
        int correct = 10;
        assertEquals(result, correct, 0);
    }

    @Test
    public void getM_Center() {
        Point2D result = blueBall.GetM_Center();
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
        blueBall.setM_Center(newCenter);
        Point2D result = blueBall.GetM_Center();
        assertEquals(result, newCenter);
    }

    @Test
    public void getUp() {
        Point2D correct = new Point2D(startPoint.getX(),
                startPoint.getX() - blueBall.GetM_BallShape().getRadius());

        Point2D result = blueBall.GetUp();
        assertEquals(correct, result);
    }

    @Test
    public void getDown() {

        Point2D correct = new Point2D(startPoint.getX(),
                startPoint.getY() + blueBall.GetM_BallShape().getRadius());

        Point2D result = blueBall.GetDown();
        assertEquals(correct, result);
    }

    @Test
    public void getLeft() {
        Point2D correct = new Point2D(startPoint.getX() -
                blueBall.GetM_BallShape().getRadius(), startPoint.getY());

        Point2D result = blueBall.GetLeft();
        assertEquals(correct, result);
    }

    @Test
    public void getRight() {
        Point2D correct = new Point2D(startPoint.getX() +
                blueBall.GetM_BallShape().getRadius(), startPoint.getY());

        Point2D result = blueBall.GetRight();
        assertEquals(correct, result);
    }

    @Test
    public void setSpeed() {
        int correctXSpeed = 5;
        int correctYSpeed = 10;
        blueBall.SetSpeed(correctXSpeed, correctYSpeed);
        int resultXSpeed = blueBall.GetXSpeed();
        int resultYSpeed = blueBall.GetYSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void setXSpeed() {
        int correctXSpeed = 5;
        blueBall.SetXSpeed(correctXSpeed);
        int resultXSpeed = blueBall.GetXSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
    }

    @Test
    public void setYSpeed() {
        int correctYSpeed = 5;
        blueBall.SetYSpeed(correctYSpeed);
        int resultYSpeed = blueBall.GetYSpeed();
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void getXSpeed() {
        int correctXSpeed = 5;
        blueBall.SetXSpeed(correctXSpeed);
        int resultXSpeed = blueBall.GetXSpeed();
        assertEquals(correctXSpeed, resultXSpeed);
    }

    @Test
    public void getYSpeed() {
        int correctYSpeed = 5;
        blueBall.SetYSpeed(correctYSpeed);
        int resultYSpeed = blueBall.GetYSpeed();
        assertEquals(correctYSpeed, resultYSpeed);
    }

    @Test
    public void moveTo() {
        Point2D correct = new Point2D(blueBall.GetM_Center().getX(),
        blueBall.GetM_Center().getY() -
                blueBall.GetM_BallShape().getRadius());

        blueBall.MoveTo(startPoint);
        assertEquals(blueBall.GetM_BallShape().getCenterX(), correct.getX(), 0);
        assertEquals(blueBall.GetM_BallShape().getCenterY(), correct.getY(), 0);
    }

    @Test
    public void reverseX() {
        int speed = blueBall.GetXSpeed();
        blueBall.ReverseX();
        int reverseSpeed = -blueBall.GetXSpeed();
        assertEquals(speed, reverseSpeed);
    }

    @Test
    public void reverseY() {
        int speed = blueBall.GetYSpeed();
        blueBall.ReverseY();
        int reverseSpeed = -blueBall.GetYSpeed();
        assertEquals(speed, reverseSpeed);
    }

}