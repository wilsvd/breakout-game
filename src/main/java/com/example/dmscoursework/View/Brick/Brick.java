package com.example.dmscoursework.View.Brick;


import com.example.dmscoursework.View.Ball.Ball;
import javafx.geometry.Bounds;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.util.Random;

/**
 * The Brick class is an abstract class which contains all the relevant
 * information that a brick may need such as shape, strength, broken as well
 * as the ability to generate a Crack.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
abstract public class Brick {

    protected static final int DEF_CRACK_DEPTH = 1; // Intensity of zigzags.
    protected static final int DEF_STEPS = 35; // num of zigzags/lines in m_Crack.

    /**
     * an integer which represents the up direction brick was impacted.
     */
    public static final int UP_IMPACT = 100;
    /**
     * an integer which represents the down direction brick was impacted.
     */
    public static final int DOWN_IMPACT = 200;
    /**
     * an integer which represents the left direction brick was impacted.
     */
    public static final int LEFT_IMPACT = 300;
    /**
     * an integer which represents the right direction brick was impacted.
     */
    public static final int RIGHT_IMPACT = 400;

    private Shape m_BrickShape;
    private int m_FullStrength;
    private int m_Strength;
    private boolean m_Broken;

    /**
     * Gets the shape of the brick.
     *
     * @return a Shape, which is shape of the brick.
     */
    public Shape GetM_BrickShape() {
        return m_BrickShape;
    }

    /**
     * Sets the shape of the brick.
     *
     * @param brickShape a Shape, which is shape of the brick.
     */
    public void SetM_BrickShape(Shape brickShape) {
        this.m_BrickShape = brickShape;
    }

    /**
     * Gets if a brick is broken.
     *
     * @return a boolean, which is whether brick is broken or not.
     */
    public boolean IsM_NotBroken() {
        return !m_Broken;
    }
    /**
     * Sets a brick to broken or not.
     *
     * @param broken a boolean, which is whether brick is broken or not.
     */
    private void setM_Broken(boolean broken) {
        this.m_Broken = broken;
    }

    /**
     * Gets the maximum strength of a brick.
     *
     * @return an integer, which is the maximum strength of a brick.
     */
    private int getM_FullStrength() {
        return m_FullStrength;
    }
    /**
     * Sets the maximum strength of a brick.
     *
     * @param fullStrength
     */
    private void setM_FullStrength(int fullStrength) {
        this.m_FullStrength = fullStrength;
    }

    /**
     * Gets the current strength of a brick.
     *
     * @return an integer, which is current strength of a brick.
     */
    private int getM_Strength() {
        return m_Strength;
    }
    /**
     * Sets the current strength of a brick.
     *
     * @param strength an integer, which is the current strength of a brick.
     */
    private void setM_Strength(int strength) {
        this.m_Strength = strength;
    }

    /**
     * Constructs a Brick whose upper-left corner is specified by the Point2D
     * argument. The width and height are specified by the Dimension2D argument
     * The number of hits to break the Brick specified by an int argument.
     *
     * @param pos a Point that is the upper-left corner of the Brick.
     * @param size a Dimension, representing width and height of the Brick.
     * @param strength an integer, which is number of impacts before breaking.
     */
    public Brick(Point2D pos, Dimension2D size, int strength) {
        setM_Broken(false);
        SetM_BrickShape(makeBrick(pos, size));
        setM_Strength(strength);
        setM_FullStrength(strength);
    }

    /**
     * Makes a rectangular brick by passing in the brick position as a Point2D
     * argument and the height and width of the brick as a Dimension2D argument.
     *
     * @param pos a Point2D which specifies the position of the brick.
     * @param size a Dimension2D, which specifies height and width of the brick.
     * @return a Rectangle, which is the shape of brick.
     */
    protected abstract Shape makeBrick(Point2D pos, Dimension2D size);

    /**
     * Find where the ball impacted the brick.
     *
     * @param b The ball
     * @return an integer, where brick was impacted.
     */
    public int FindImpact(Ball b) {
        if (!IsM_NotBroken())
            return 0;
        int out = 0;
        if (GetM_BrickShape().contains(b.GetRight()))
            out = LEFT_IMPACT;
        else if (GetM_BrickShape().contains(b.GetLeft()))
            out = RIGHT_IMPACT;
        else if (GetM_BrickShape().contains(b.GetUp()))
            out = DOWN_IMPACT;
        else if (GetM_BrickShape().contains(b.GetDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * Check if a brick was impacted by the ball.
     *
     * @param b a Ball
     * @return a boolean, which is whether the brick was hit or not.
     */
    public boolean IsImpacted(Ball b) {
        if (!IsM_NotBroken())
            return false;
        if (GetM_BrickShape().contains(b.GetRight()))
            return true;
        else if (GetM_BrickShape().contains(b.GetLeft()))
            return true;
        else if (GetM_BrickShape().contains(b.GetUp()))
            return true;
        else if (GetM_BrickShape().contains(b.GetDown()))
            return true;
        return false;
    }

    /**
     * setImpact takes a Point2D argument which is the coordinates of a point
     * on the ball e.g. the TOP, BOTTOM, LEFT or RIGHT of the ball.
     *
     * The direction is specifying the direction the brick needs to crack.
     *
     * @param point a Point2D, coordinate of the ball that collided with brick.
     * @param dir an integer, direction in which brick needs to crack.
     * @return a boolean, whether ball impacted or not.
     */
    public boolean SetImpact(Point2D point, int dir) {
        if (!IsM_NotBroken())
            return false;
        impact();
        return !IsM_NotBroken();
    }

    /**
     * Repairs the brick, so that is not broken and at full strength
     */
    public void Repair() {
        setM_Broken(false);
        setM_Strength(getM_FullStrength());
    }

    /**
     * Reduces the strength of the brick and checks whether brick is broken.
     */
    protected void impact() {
        setM_Strength(getM_Strength() - 1);
        setM_Broken((getM_Strength() == 0));
    }
    /**
     * A Crack is a line that runs down the brick in order to show that it
     * has been impacted and that it is nearly broken.
     */
    public class Crack {

        private final int CRACK_SECTIONS = 3;
        private final double JUMP_PROBABILITY = 0.7;

        /**
         * an integer which represents the left direction crack is generated.
         */
        public static final int LEFT = 10;
        /**
         * an integer which represents the right direction crack is generated.
         */
        public static final int RIGHT = 20;
        /**
         * an integer which represents the up direction crack is generated.
         */
        public static final int UP = 30;
        /**
         * an integer which represents the down direction crack is generated.
         */
        public static final int DOWN = 40;
        /**
         * an integer which represents the vertical boundaries
         */
        public final int VERTICAL = 100;
        /**
         * an integer which represents the horizontal boundaries
         */
        public final int HORIZONTAL = 200;

        private Path m_Crack;
        private int m_CrackDepth;
        private int m_Steps;

        /**
         * Gets the path of the crack.
         *
         * @return a Path, which is the path of the crack.
         */
        public Path GetM_CrackPath() {
            return m_Crack;
        }
        /**
         * Sets the path of the crack.
         *
         * @param crack a Path, which is the path of the crack.
         */
        private void setM_CrackPath(Path crack) {
            this.m_Crack = crack;
        }

        /**
         * Gets the crack depth which is intensity of crack zigzags.
         *
         * @return an integer, which is the crack depth.
         */
        private int getM_CrackDepth() {
            return m_CrackDepth;
        }

        /**
         * Sets the crack depth which is intensity of crack zigzags.
         *
         * @param crackDepth an integer, which is the crack depth.
         */
        private void setM_CrackDepth(int crackDepth) {
            this.m_CrackDepth = crackDepth;
        }

        /**
         * Gets the number of zigzags that crack should have.
         *
         * @return
         */
        private int getM_Steps() {
            return m_Steps;
        }

        /**
         * Sets the number of zigzags that a crack should have.
         * @param steps
         */
        private void setM_Steps(int steps) {
            this.m_Steps = steps;
        }

        /**
         * Constructs a crack whose depth is specified by an integer
         * argument, and height and width are calculated using an integer.
         *
         * @param crackDepth an integer, Intensity of zigzags in the crack.
         * @param steps an integer, Number of zigzags in the crack.
         */
        public Crack(int crackDepth, int steps) {
            setM_CrackPath(new Path());
            setM_CrackDepth(crackDepth);
            setM_Steps(steps);
        }

        /**
         * Resets the crack, so that there is no crack in brick.
         */
        public void Reset() {
            GetM_CrackPath().getElements().clear();
        }

        /**
         * Makes an m_Crack by specifying where the m_Brick was impacted by a
         * Point2D argument and gets the direction the m_Brick should m_Crack
         * with an integer argument.
         *
         * @param point     a Point2D, is the coordinate of where m_Brick was Impact.
         * @param direction an integer, which is direction m_Brick should m_Crack.
         */
        public void MakeCrack(Point2D point, int direction) {
            Bounds bounds = Brick.this.GetM_BrickShape().getLayoutBounds();

            Point2D impact = new Point2D((int) point.getX(), (int) point.getY());
            Point2D start;
            Point2D end;

            switch (direction) {
                case LEFT:
                    start = new Point2D(bounds.getMaxX(), bounds.getMinY());
                    end = new Point2D(bounds.getMaxX(), bounds.getMaxY());
                    Point2D tmp = makeRandomPoint(start, end, VERTICAL);
                    makeCrackPath(impact, tmp);
                    break;
                case RIGHT:
                    start = new Point2D(bounds.getMinX(), bounds.getMinY());
                    end = new Point2D(bounds.getMinX(), bounds.getMaxY());
                    tmp = makeRandomPoint(start, end, VERTICAL);
                    makeCrackPath(impact, tmp);
                    break;
                case UP:
                    start = new Point2D(bounds.getMinX(), bounds.getMaxY());
                    end = new Point2D(bounds.getMaxX(), bounds.getMaxY());
                    tmp = makeRandomPoint(start, end, HORIZONTAL);
                    makeCrackPath(impact, tmp);
                    break;
                case DOWN:
                    start = new Point2D(bounds.getMinX(), bounds.getMinY());
                    end = new Point2D(bounds.getMaxX(), bounds.getMinY());
                    tmp = makeRandomPoint(start, end, HORIZONTAL);
                    makeCrackPath(impact, tmp);
                    break;
            }
        }

        /**
         * Makes the path of the crack by specifying where the m_Crack should
         * start with a Point argument and where it should end with a Point
         * argument.
         *
         * @param start a Point2D, coordinate representing starting point.
         * @param end   a Point2D, coordinate representing end point.
         */
        private void makeCrackPath(Point2D start, Point2D end) {

            Path path = new Path();

            MoveTo moveTo = new MoveTo(start.getX(), start.getY());
            // Starting coordinates of the path.
            path.getElements().add(moveTo);
            // Number of zigzags that fit horizontally in m_Brick.
            // end.x = 15, start.x = 5.
            double w = (end.getX() - start.getX()) / (double) getM_Steps(); // e.g. 10
            // / 2
            // Number of zigzags that fit vertically in m_Brick.
            // end.y = 15, start.y = 5
            double h = (end.getY() - start.getY()) / (double) getM_Steps(); // HEIGHT OF
            // CRACK

            int bound = getM_CrackDepth(); // m_CrackDepth = 1.
            int jump = bound * 5;

            double x, y;

            for (int i = 1; i < getM_Steps(); i++) { // m_Steps is 2

                x = (i * w) + start.getX(); // ( 1 * 5) + 5 = 10
                y = (i * h) + start.getY() + randomInBounds(bound);//(1*5)+5-1=9
                // (10,9)
                // Adds variation to the middle.
                if (inMiddle(i, CRACK_SECTIONS, getM_Steps()))
//                  Adds varying height to the m_Crack line.
                    y += jumps(jump, JUMP_PROBABILITY);

                // WHere it draws the line to.
                LineTo lineTo = new LineTo(x, y);
                path.getElements().add(lineTo);


            }

            LineTo lineTo = new LineTo(end.getX(), end.getY());
            path.getElements().add(lineTo);

            GetM_CrackPath().getElements().addAll(path.getElements());
        }

        /**
         * Returns a random value that is in bounds of the m_Brick.
         *
         * @param bound an integer, which is the m_Crack depth.
         * @return an integer, that is in bounds of the m_Brick.
         */
        private int randomInBounds(int bound) {
            Random rnd = new Random();
            int n = (bound * 2) + 1; // n = 2 + 1 ... n = 3
            return rnd.nextInt(n) - bound; // Lets just say returns - 1.
        }

        /**
         * Path is made up of lots of iterations of x,y coordinates being
         * added to together.
         * Checks if one of those iterations are between the minimum and
         * maximum values.
         *
         * @param i an integer,
         * @param sections an integer, which is the number of crack sections.
         * @param divisions an integer, which is the number of steps (zigzags)
         * @return
         */
        private boolean inMiddle(int i, int sections, int divisions) {
            int low = (sections / divisions); // i=1, low = 3 / 2 = 1.5
            int up = low * (divisions - 1); // i=1, up = 3/2 * (2 -1) = 1.5

            return (i > low) && (i < up);
        }

        /**
         * Returns a varying height which the crack will jump to.
         *
         * @param bound
         * @param probability
         * @return an integer,
         */
        private int jumps(int bound, double probability) {
            Random rnd = new Random();
            if (rnd.nextDouble() > probability)
                return randomInBounds(bound);
            return 0;

        }

        /**
         * Makes a random point for an edge of the brick.
         *
         * @param from      a Point2D, which is a coordinate on the m_Brick.
         * @param to        a Point2D, which is a coordinate on the m_Brick.
         * @param direction an integer, specifying direction the edge of
         *                  brick travels.
         * @return a Point2D, which is the new random coordinate.
         */
        private Point2D makeRandomPoint(Point2D from, Point2D to,
                                       int direction) {
            Random rnd = new Random();
            Point2D out;
            int pos;

            switch (direction) {
                case HORIZONTAL -> {
                    pos = (int) (rnd.nextInt((int) (to.getX() - from.getX())) + from.getX());
                    out = new Point2D(pos, to.getY());
                }
                case VERTICAL -> {
                    pos = (int) (rnd.nextInt((int) (to.getY() - from.getY())) + from.getY());
                    out = new Point2D(to.getX(), pos);
                }
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            }
            return out;
        }

    }

}





