package com.example.dmscoursework.View.Ball;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * The OrangeBall class inherits from Ball, so it has access to all the same
 * methods as Ball. However, it has its own makeBall method which allows it
 * to have a unique description.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class OrangeBall extends Ball {

    private static final int DEF_RADIUS = 10;

    /**
     * Constructor of the OrangeBall which takes in a position as a Point2D
     * argument.
     *
     * @param center a Point2D, which is the position of the ball.
     */
    public OrangeBall(Point2D center) {
        super(center, DEF_RADIUS, DEF_RADIUS);
    }

    @Override
    protected Circle makeBall(Point2D center, int radiusA, int radiusB) {

        Circle m_Ball = new Circle();
        m_Ball.setRadius(radiusA);

        FileInputStream input = null;
        try {
            input = new FileInputStream(
                    "src/Assets/OrangeAssets/OrangeBall.PNG");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        ImagePattern imagePattern = new ImagePattern(image);

        m_Ball.setFill(imagePattern);

        return m_Ball;
    }

}
