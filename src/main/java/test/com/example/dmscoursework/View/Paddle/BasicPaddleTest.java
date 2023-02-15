package com.example.dmscoursework.View.Paddle;

import com.example.dmscoursework.View.Ball.Ball;
import com.example.dmscoursework.View.Ball.RubberBall;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicPaddleTest {
    private final double HALF = 0.5;
    private BasicPaddle basicPaddle;
    private int paddleWidth, paddleHeight;
    private Point2D startPoint;
    private Rectangle container;
    @Before
    public void setUp() {
        paddleWidth = 150;
        paddleHeight = 12;
        startPoint = new Point2D(300, 430);
        container = new Rectangle(600, 450);
        basicPaddle = new BasicPaddle(startPoint, paddleWidth, paddleHeight,
                container);
    }

    @Test
    public void getM_PaddleShape() {
        Rectangle result = basicPaddle.GetM_PaddleShape();
        assertEquals(result.getWidth(), paddleWidth, 0);
        assertEquals(result.getHeight(), paddleHeight, 0);
    }

    @Test
    public void getM_StartPosition() {
        Point2D result = basicPaddle.GetM_StartPosition();
        assertEquals(result.getX(), startPoint.getX(), 0);
        assertEquals(result.getY(), startPoint.getY(), 0);
    }

    @Test
    public void getM_Min() {
        int result = basicPaddle.GetM_Min();
        int correct = 0;
        assertEquals(result, correct, 0);
    }

    @Test
    public void getM_Max() {
        int result = basicPaddle.GetM_Max();
        int correct = (int) (container.getWidth() - paddleWidth);
        assertEquals(result, correct, 0);
    }

    @Test
    public void impact() {
        Ball ball = new RubberBall(startPoint);
        boolean result = basicPaddle.Impact(ball);
        boolean correct = true;
        assertEquals(result, correct);
    }

    @Test
    public void move() {
        int i = 0;
        int numberOfMovements = 100;
        basicPaddle.MoveRight();
        for (i = 0; i < numberOfMovements; i++) {
            basicPaddle.Move();
        }
        int result = (int) basicPaddle.GetM_PaddleShape().getX();
        int correct = basicPaddle.GetM_Max();
        assertEquals(result, correct, 0);
    }

    @Test
    public void moveLeft() {
        int initial = (int) basicPaddle.GetM_PaddleShape().getX();
        int moveAmount = 5;
        basicPaddle.MoveLeft();
        basicPaddle.Move();
        int after = (int) basicPaddle.GetM_PaddleShape().getX();
        assertEquals(initial - moveAmount, after, 0);
    }

    @Test
    public void moveRight() {
        int initial = (int) basicPaddle.GetM_PaddleShape().getX();
        int moveAmount = 5;
        basicPaddle.MoveRight();
        basicPaddle.Move();
        int after = (int) basicPaddle.GetM_PaddleShape().getX();
        assertEquals(initial + moveAmount, after, 0);
    }

    @Test
    public void stop() {
        int initial = (int) basicPaddle.GetM_PaddleShape().getX();
        int moveAmount = 0;
        basicPaddle.Stop();
        basicPaddle.Move();
        int after = (int) basicPaddle.GetM_PaddleShape().getX();
        assertEquals(initial - moveAmount, after, 0);
    }

    @Test
    public void moveTo() {
        Point2D point = new Point2D(300, 300);
        basicPaddle.MoveTo(point);

        int correct = (int) (point.getX() - paddleWidth * HALF);
        assertEquals(basicPaddle.GetM_PaddleShape().getX(), correct, 0);
    }

}