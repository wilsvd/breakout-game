package com.example.dmscoursework.View.Brick.TributeBrick;

import com.example.dmscoursework.View.Brick.Brick;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;
/**
 * The SteelBrick class inherits from Brick, so it has access
 * to all the same methods as Brick. However, it has its own makeBrick method
 * which allows it to have a unique description. It also is different in that
 * it breaks randomly when hit.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class SteelBrick extends Brick {

    private static final Color DEF_INNER = Color.rgb(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private final double BREAK_PROBABILITY = 0.4;

    /**
     * Constructs a steel brick whose upper-left corner is specified by the
     * Point2D argument. The width and height are specified by the Dimension2D
     * argument.
     *
     * @param point a Point2D that is the upper-left corner of the Brick
     * @param size a Dimension2D, representing width and height of the Brick.
     */
    public SteelBrick(Point2D point, Dimension2D size) {
        super(point, size, STEEL_STRENGTH);
    }

    @Override
    protected Shape makeBrick(Point2D pos, Dimension2D size) {

        Shape brickShape = new Rectangle(pos.getX(), pos.getY(),
                size.getWidth() - 1, size.getHeight() - 1);

        brickShape.setFill(DEF_INNER);
        brickShape.setStroke(DEF_BORDER);

        return brickShape;
    }

    @Override
    protected void impact() {
        Random m_Rnd = new Random();
        if (m_Rnd.nextDouble() < BREAK_PROBABILITY) {
            super.impact();
        }
    }

}
