package com.example.dmscoursework.View.Brick.OrangeBrick;

import com.example.dmscoursework.View.Brick.Brick;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
/**
 * The CheckerOrangeBrick class inherits from Brick, so it has access
 * to all the same methods as Brick. However, it has its own makeBrick method
 * which allows it to have a unique description. It also is different in that
 * it breaks randomly when hit.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class CheckerOrangeBrick extends Brick {

    private static final int BRICK_STRENGTH = 1;
    private final double BREAK_PROBABILITY = 0.4;

    private ImagePattern m_ImagePattern;

    /**
     * Sets an image pattern.
     */
    private void setImagePattern() {
        FileInputStream input = null;
        try {
            input = new FileInputStream(
                    "src/Assets/OrangeAssets/CheckerOrangeBrick.PNG");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        m_ImagePattern = new ImagePattern(image);
    }
    /**
     * Gets the image pattern for the brick.
     *
     * @return an ImagePattern, which is to be drawn onto a brick.
     */
    private ImagePattern getImagePattern() {
        return m_ImagePattern;
    }

    /**
     * Constructs a checkered orange brick whose upper-left corner is
     * specified by the Point2D argument. The width and height are specified
     * by the Dimension2D argument.
     *
     * @param point a Point2D that is the upper-left corner of the Brick
     * @param size a Dimension2D, representing width and height of the Brick.
     */
    public CheckerOrangeBrick(Point2D point, Dimension2D size) {
        super(point, size, BRICK_STRENGTH);
    }

    @Override
    protected Shape makeBrick(Point2D pos, Dimension2D size) {
        Shape brickShape = new Rectangle(pos.getX(), pos.getY(),
                size.getWidth(), size.getHeight());

        setImagePattern();
        brickShape.setFill(getImagePattern());

        return brickShape;
    }

    @Override
    public void impact() {
        Random m_Rnd = new Random();
        if (m_Rnd.nextDouble() < BREAK_PROBABILITY) {
            super.impact();
        }
    }
}
