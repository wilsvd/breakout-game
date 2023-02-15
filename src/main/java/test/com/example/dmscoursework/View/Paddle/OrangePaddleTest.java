package com.example.dmscoursework.View.Paddle;

import com.example.dmscoursework.View.Ball.Ball;
import com.example.dmscoursework.View.Ball.RubberBall;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrangePaddleTest {
    private final double HALF = 0.5;
    private OrangePaddle orangePaddle;
    private int paddleWidth, paddleHeight;
    private Point2D startPoint;
    private Rectangle container;
    @Before
    public void setUp() {
        paddleWidth = 150;
        paddleHeight = 12;
        startPoint = new Point2D(300, 430);
        container = new Rectangle(600, 450);
        orangePaddle = new OrangePaddle(startPoint, paddleWidth, paddleHeight,
                container);
    }

    @Test
    public void getM_PaddleShape() {
        Rectangle result = orangePaddle.GetM_PaddleShape();
        assertEquals(result.getWidth(), paddleWidth, 0);
        assertEquals(result.getHeight(), paddleHeight, 0);
    }

    @Test
    public void getM_StartPosition() {
        Point2D result = orangePaddle.GetM_StartPosition();
        assertEquals(result.getX(), startPoint.getX(), 0);
        assertEquals(result.getY(), startPoint.getY(), 0);
    }

    @Test
    public void getM_Min() {
        int result = orangePaddle.GetM_Min();
        int correct = 0;
        assertEquals(result, correct, 0);
    }

    @Test
    public void getM_Max() {
        int result = orangePaddle.GetM_Max();
        int correct = (int) (container.getWidth() - paddleWidth);
        assertEquals(result, correct, 0);
    }

    @Test
    public void impact() {
        Ball ball = new RubberBall(startPoint);
        boolean result = orangePaddle.Impact(ball);
        boolean correct = true;
        assertEquals(result, correct);
    }

    @Test
    public void move() {
        int i = 0;
        int numberOfMovements = 100;
        orangePaddle.MoveRight();
        for (i = 0; i < numberOfMovements; i++) {
            orangePaddle.Move();
        }
        int result = (int) orangePaddle.GetM_PaddleShape().getX();
        int correct = orangePaddle.GetM_Max();
        assertEquals(result, correct, 0);
    }

    @Test
    public void moveLeft() {
        int initial = (int) orangePaddle.GetM_PaddleShape().getX();
        int moveAmount = 5;
        orangePaddle.MoveLeft();
        orangePaddle.Move();
        int after = (int) orangePaddle.GetM_PaddleShape().getX();
        assertEquals(initial - moveAmount, after, 0);
    }

    @Test
    public void moveRight() {
        int initial = (int) orangePaddle.GetM_PaddleShape().getX();
        int moveAmount = 5;
        orangePaddle.MoveRight();
        orangePaddle.Move();
        int after = (int) orangePaddle.GetM_PaddleShape().getX();
        assertEquals(initial + moveAmount, after, 0);
    }

    @Test
    public void stop() {
        int initial = (int) orangePaddle.GetM_PaddleShape().getX();
        int moveAmount = 0;
        orangePaddle.Stop();
        orangePaddle.Move();
        int after = (int) orangePaddle.GetM_PaddleShape().getX();
        assertEquals(initial - moveAmount, after, 0);
    }

    @Test
    public void moveTo() {
        Point2D point = new Point2D(300, 300);
        orangePaddle.MoveTo(point);

        int correct = (int) (point.getX() - paddleWidth * HALF);
        assertEquals(orangePaddle.GetM_PaddleShape().getX(), correct, 0);
    }
}