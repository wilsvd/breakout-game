package com.example.dmscoursework.View.Ball;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 * The RubberBall class inherits from Ball, so it has access to all the same
 * methods as Ball. However, it has its own makeBall method which allows it
 * to have a unique description.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class RubberBall extends Ball {

    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR =
            Color.rgb(255, 219, 88);
    private static final Color DEF_BORDER_COLOR =
            DEF_INNER_COLOR.darker().darker();

    /**
     * Constructor of the RubberBall which takes in a position as a Point2D
     * argument.
     *
     * @param center a Point2D, which is the position of the ball.
     */
    public RubberBall(Point2D center) {
        super(center, DEF_RADIUS, DEF_RADIUS);
    }

    @Override
    protected Circle makeBall(Point2D center, int radiusA, int radiusB) {

        Circle ball = new Circle();
        ball.setRadius(radiusA);
        ball.setFill(DEF_INNER_COLOR);
        ball.setStroke(DEF_BORDER_COLOR);

        return ball;
    }
}
