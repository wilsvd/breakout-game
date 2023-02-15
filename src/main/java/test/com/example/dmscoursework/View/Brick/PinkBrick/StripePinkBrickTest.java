package com.example.dmscoursework.View.Brick.PinkBrick;

import com.example.dmscoursework.View.Ball.Ball;
import com.example.dmscoursework.View.Ball.RubberBall;
import com.example.dmscoursework.View.Brick.BlueBrick.StripeBlueBrick;
import com.example.dmscoursework.View.Brick.Brick;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StripePinkBrickTest {

    private StripePinkBrick stripePinkBrick;
    private Dimension2D dimension2D;
    private Point2D startPoint;
    @Before
    public void setUp() {
        dimension2D = new Dimension2D(60, 20);
        startPoint = new Point2D(300, 300);
        stripePinkBrick = new StripePinkBrick(startPoint, dimension2D);
    }

    @Test
    public void getM_BrickShape() {
        Shape result = stripePinkBrick.GetM_BrickShape();
        int resultWidth = (int) result.getBoundsInLocal().getWidth();
        int resultHeight = (int) result.getBoundsInLocal().getHeight();
        int correctWidth = (int) dimension2D.getWidth();
        int correctHeight = (int) dimension2D.getHeight();
        assertEquals(resultWidth, correctWidth, 0);
        assertEquals(resultHeight, correctHeight, 0);
    }

    @Test
    public void setM_BrickShape() {
        Shape correct = new Rectangle(startPoint.getX() + 10, startPoint.getY(),
                dimension2D.getWidth(), dimension2D.getHeight());
        stripePinkBrick.SetM_BrickShape(correct);
        int correctWidth = (int) correct.getBoundsInLocal().getWidth();
        int correctHeight = (int) correct.getBoundsInLocal().getHeight();

        Shape result = stripePinkBrick.GetM_BrickShape();
        int resultWidth = (int) result.getBoundsInLocal().getWidth();
        int resultHeight = (int) result.getBoundsInLocal().getHeight();
        assertEquals(resultWidth, correctWidth, 0);
        assertEquals(resultHeight, correctHeight, 0);
    }

    @Test
    public void isM_NotBroken() {
        boolean result = stripePinkBrick.IsM_NotBroken();
        assertEquals(result, true);
    }

    @Test
    public void findImpact() {
        Point2D collisionPoint = new Point2D(
                startPoint.getX(),
                startPoint.getY() + dimension2D.getHeight()/2);

        Ball ball = new RubberBall(collisionPoint);
        int result = stripePinkBrick.FindImpact(ball);
        int correct = Brick.LEFT_IMPACT;
        assertEquals(result, correct, 0);
    }

    @Test
    public void isImpacted() {
        Point2D collisionPoint = new Point2D(
                startPoint.getX(),
                startPoint.getY() + dimension2D.getHeight()/2);

        Ball ball = new RubberBall(collisionPoint);
        boolean result = stripePinkBrick.IsImpacted(ball);
        boolean correct = true;
        assertEquals(result, correct);
    }

    @Test
    public void setImpact() {
        stripePinkBrick.SetImpact(startPoint ,Brick.Crack.LEFT);
        boolean result = stripePinkBrick.IsM_NotBroken(); // Is true.
        boolean correct = true;
        assertEquals(result, correct);
    }

    @Test
    public void repair() {
        boolean initial = stripePinkBrick.IsM_NotBroken();
        stripePinkBrick.SetImpact(startPoint ,Brick.Crack.LEFT);
        stripePinkBrick.Repair();
        boolean after = stripePinkBrick.IsM_NotBroken();
        assertEquals(initial, after);
    }

}