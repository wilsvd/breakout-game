package com.example.dmscoursework.View.Brick.PinkBrick;

import com.example.dmscoursework.View.Brick.Brick;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * The StripePinkBrick class inherits from Brick, so it has access
 * to all the same methods as Brick. However, it has its own makeBrick method
 * which allows it to have a unique description. It also is different in that
 * it can generate a crack in its brick when hit as well as repair itself.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class StripePinkBrick extends Brick {

    private static final int CEMENT_STRENGTH = 2;

    private Crack m_Crack;
    private Shape m_Brick;
    private ImagePattern m_ImagePattern;

    /**
     * Get the Crack
     *
     * @return a Crack, which is the crack in the brick.
     */
    private Crack getM_Crack() {
        return m_Crack;
    }

    /**
     * Set the Crack
     *
     * @param crack a Crack, which is the crack in the brick.
     */
    private void setM_Crack(Crack crack) {
        this.m_Crack = crack;
    }

    /**
     * Get the brick shape.
     *
     * @return a Shape, which is the shape of the brick.
     */
    public Shape GetM_BrickShape() {
        return m_Brick;
    }
    /**
     * Get the brick shape.
     *
     * @param brick a Shape, which is the shape of the brick.
     */
    private void setM_BrickShape(Shape brick) {
        this.m_Brick = brick;
    }

    /**
     * Sets an image pattern.
     */
    private void setImagePattern() {
        FileInputStream input = null;
        try {
            input = new FileInputStream(
                    "src/Assets/PinkAssets/StripePinkBrick.PNG");

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
     * Constructs a striped pink brick whose upper-left corner is specified by
     * the Point2D argument. The width and height are specified by the
     * Dimension2D argument.
     *
     * @param point a Point2D that is the upper-left corner of the Brick
     * @param size a Dimension2D, representing width and height of the Brick.
     */
    public StripePinkBrick(Point2D point, Dimension2D size) {
        super(point, size, CEMENT_STRENGTH);
        setM_Crack(new Crack(DEF_CRACK_DEPTH, DEF_STEPS));
        setM_BrickShape(super.GetM_BrickShape());
    }

    @Override
    protected Shape makeBrick(Point2D pos, Dimension2D size) {
        setM_BrickShape(new Rectangle(pos.getX(), pos.getY(), size.getWidth(),
                size.getHeight()));

        setImagePattern();
        GetM_BrickShape().setFill(getImagePattern());

        return GetM_BrickShape();
    }

    @Override
    public boolean SetImpact(Point2D point, int dir) {
        if (!super.IsM_NotBroken())
            return false;
        super.impact();
        if (super.IsM_NotBroken()) {
            getM_Crack().MakeCrack(point, dir);
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * Updates the brick by checking if it is not broken and draws the crack
     * onto the brick.
     */
    private void updateBrick() {
        if (super.IsM_NotBroken()) {
            Path gp = getM_Crack().GetM_CrackPath();
            setM_BrickShape(Path.subtract(GetM_BrickShape(), gp));
            GetM_BrickShape().setFill(getImagePattern());
        }
    }

    @Override
    public void Repair() {
        super.Repair();
        getM_Crack().Reset();
        setM_BrickShape(super.GetM_BrickShape());
    }
}
