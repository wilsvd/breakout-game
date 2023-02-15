package com.example.dmscoursework.View.Paddle;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * The PinkPaddle class inherits from Paddle, so it has access to all the same
 * methods as Paddle. However, it has its own makePaddle method which allows it
 * to have a unique description.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class PinkPaddle extends Paddle {

    private final double HALF = 0.5;
    /**
     * Constructs a pink paddle whose upper-left corner is specified by
     * the Point2D argument. The width and height are specified by the
     * integers and the container is area that paddle can move inside.
     *
     * @param center a Point2D, which is the position of the paddle.
     * @param width an integer, which is the width of the paddle.
     * @param height an integer, which is the height of the paddle.
     * @param container a Rectangle, specifies limits the paddle can move.
     */
    public PinkPaddle(Point2D center, int width, int height, Rectangle container) {
        super(center, width, height, container);
    }

    @Override
    protected Rectangle makePaddle(int width, int height) {

        Point2D pos = new Point2D(
                (int) (GetM_StartPosition().getX() - (width * HALF)),
                (int) GetM_StartPosition().getY());

        Rectangle paddle = new Rectangle(pos.getX(), pos.getY(), width, height);

        FileInputStream input = null;
        try {
            input = new FileInputStream(
                    "src/Assets/PinkAssets/PinkPaddle.PNG");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        ImagePattern image_pattern = new ImagePattern(image);
        paddle.setFill(image_pattern);

        return paddle;
    }
}
