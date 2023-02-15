package com.example.dmscoursework.View.Level.OceanTheme;

import com.example.dmscoursework.View.Brick.Brick;
import com.example.dmscoursework.View.Level.LevelBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
/**
 * The FishLevel is a level which takes multiple bricks and puts them
 * together into the shape of a fish.
 *
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class FishLevel {

    private final int START_Y_POS = 120;

    private final int TAIL_TOP_LENGTH = 4;
    private final int TAIL_BOTTOM_LENGTH = 4;
    private final int TAIL_BACK_HEIGHT = 6;

    private final int BODY_BOTTOM_DOWN_BEND = 3;
    private final int BODY_BOTTOM_UP_BEND = 3;
    private final int BODY_TOP_DOWN_BEND = 3;
    private final int BODY_TOP_UP_BEND = 3;

    private Brick[] m_Level = null;
    private LevelBuilder m_LevelBuilder;
    private int m_TypeA;
    private int m_TypeB;
    private int m_Counter = 0;
    private double m_X;
    private double m_Y;
    double connectBodyXPos;
    double connectBodyYPos;

    /**
     * Gets the level.
     *
     * @return a Brick[], which contains the array of bricks.
     */
    public Brick[] GetLevel() {
        return m_Level;
    }
    /**
     * Sets the level
     *
     * @param level a Brick[], which is an array of bricks.
     */
    private void setLevel(Brick[] level) {
        this.m_Level = level;
    }

    /**
     * Gets the level builder which makes the level.
     *
     * @return a LevelBuilder, which contains information regarding the level.
     */
    private LevelBuilder getM_LevelBuilder() {
        return m_LevelBuilder;
    }
    /**
     * Sets the level builder which makes the level.
     *
     * @param levelBuilder a LevelBuilder, which contains info of the levels.
     */
    private void setM_LevelBuilder(LevelBuilder levelBuilder) {
        this.m_LevelBuilder = levelBuilder;
    }

    /**
     * Gets a brick of a specific type A.
     *
     * @return an integer, which specifies type of brick.
     */
    private int getM_TypeA() {
        return m_TypeA;
    }

    /**
     * Sets the type of brick.
     *
     * @param typeA an integer, which specifies type of brick.
     */
    private void setM_TypeA(int typeA) {
        this.m_TypeA = typeA;
    }

    /**
     * Gets a brick of a specific type B.
     *
     * @return an integer, which specifies type of brick.
     */
    private int getM_TypeB() {
        return m_TypeB;
    }
    /**
     * Sets the type of brick.
     *
     * @param typeB an integer, which specifies type of brick.
     */
    private void setM_TypeB(int typeB) {
        this.m_TypeB = typeB;
    }

    /**
     * Gets the counter which is the index in the Brick Array.
     *
     * @return an integer, which is the index in the brick array.
     */
    private int getM_Counter() {
        return m_Counter;
    }
    /**
     * Sets the counter which is the index in the brick array.
     *
     * @param counter an integer, which is the index in the brick array.
     */
    private void setM_Counter(int counter) {
        this.m_Counter = counter;
    }

    /**
     * Gets the x coordinate of the brick.
     *
     * @return a double, which is the x coordinate of the brick.
     */
    private double getM_X() {
        return m_X;
    }

    /**
     * Sets the x coordinate of the brick.
     *
     * @param x a double, which is the x coordinate of the brick.
     */
    private void setM_X(double x) {
        this.m_X = x;
    }

    /**
     * Gets the y coordinate of the brick.
     *
     * @return a double, which is the x coordinate of the brick.
     */
    private double getM_Y() {
        return m_Y;
    }
    /**
     * Sets the x coordinate of the brick.
     *
     * @param y a double, which is the y coordinate of the brick.
     */
    private void setM_Y(double y) {
        this.m_Y = y;
    }

    /**
     * Get the x coordinate of where the tail connects to the body.
     *
     * @return a double, which is the coordinate of where tail meets body.
     */
    private double getConnectBodyXPos() {
        return connectBodyXPos;
    }
    /**
     * Set the x coordinate of where tail meets body.
     *
     * @param connectBodyXPos a double, which is where tail meets body.
     */
    private void setConnectBodyXPos(double connectBodyXPos) {
        this.connectBodyXPos = connectBodyXPos;
    }

    /**
     * Get the y coordinate of where the tail connects to the body.
     *
     * @return a double, which is the coordinate of where tail meets body.
     */
    private double getConnectBodyYPos() {
        return connectBodyYPos;
    }
    /**
     * Set the y coordinate of where tail meets body.
     *
     * @param connectBodyYPos a double, which is where tail meets body.
     */
    private void setConnectBodyYPos(double connectBodyYPos) {
        this.connectBodyYPos = connectBodyYPos;
    }

    /**
     * Constructs a level looking like a fish that has 2 different types
     * of bricks by passing in relevant information such as the number of
     * rows, the size of bricks, the type of bricks and general information
     * from the levelBuilder such as the drawArea and brickCount.
     *
     * @param levelBuilder a LevelBuilder, which contains level information.
     * @param lineCount an integer, which is number of rows.
     * @param brickSizeRatio a double, which is the size of bricks.
     * @param typeA an integer, which is the type of brick.
     * @param typeB an integer, which is the type of brick.
     */
    public FishLevel(LevelBuilder levelBuilder, int lineCount,
                     double brickSizeRatio,
                     int typeA, int typeB) {
        setM_LevelBuilder(levelBuilder);
        setM_TypeA(typeA);
        setM_TypeB(typeB);
        setLevel(makeFishLevel(levelBuilder, lineCount, brickSizeRatio));
    }

    /**
     * Checks if a number is divisible by two.
     *
     * @param number an integer, which is checked to be divisible by two.
     * @return a boolean, whether number is divisible by two or not.
     */
    private boolean isDivisibleByTwo(int number) {
        return ((number % 2) == 0);
    }

    /**
     * Makes a level that looks like a fish.
     *
     * @param levelBuilder a LevelBuilder, which contains info regarding level.
     * @param lineCount an integer, which is the number of rows.
     * @param brickSizeRatio a double, which is the size of brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeFishLevel(LevelBuilder levelBuilder,
                                  int lineCount, double brickSizeRatio) {
        int brickOnLine = levelBuilder.GetM_BrickCnt() / lineCount;
        double brickLength =
                levelBuilder.GetM_DrawArea().getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;
        Brick[] tmpBricks = new Brick[levelBuilder.GetM_BrickCnt()];
        Dimension2D brickSize = new Dimension2D(brickLength, brickHeight);

        setM_X(0);
        setM_X(START_Y_POS);

        tmpBricks = makeTail(tmpBricks, brickSize);
        tmpBricks = makeBottomBody(tmpBricks, brickSize);
        tmpBricks = makeTopBody(tmpBricks, brickSize);
        tmpBricks = makeEye(tmpBricks, brickSize);
        tmpBricks = makeFin(tmpBricks, brickSize);

        return tmpBricks;
    }

    /**
     * Makes the tail of the fish.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeTail(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickHeight = brickSize.getHeight();
        double brickLength = brickSize.getWidth();
        Point2D point;
        setM_X(0);
        int i = 0;
        while (i < TAIL_TOP_LENGTH) {
            point = new Point2D(getM_X(), getM_Y());
            if (isDivisibleByTwo(i)) {
                setM_X(getM_X() + brickLength);
            }
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_Y(getM_Y() + brickHeight);
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        setConnectBodyXPos(getM_X());
        setConnectBodyYPos(getM_Y());
        i = 0;
        while (i < TAIL_BOTTOM_LENGTH) {
            point = new Point2D(getM_X(), getM_Y());
            if (isDivisibleByTwo(i)) {
                setM_X(getM_X() - brickLength);
            }
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_Y(getM_Y() + brickHeight);
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        setM_Y(getM_Y() - (brickHeight + brickHeight));
        i = 0;
        while (i < TAIL_BACK_HEIGHT) {
            point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_Y(getM_Y() - brickHeight);
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        return tmpBricks;
    }

    /**
     * Makes the bottom of the fish.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeBottomBody(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickHeight = brickSize.getHeight();
        double brickLength = brickSize.getWidth();
        Point2D point;
        int i = 0;
        setM_X(getConnectBodyXPos() + brickLength);
        setM_Y(getConnectBodyYPos() + brickHeight);
        while (i < BODY_BOTTOM_DOWN_BEND) {
            point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_X(getM_X() + brickLength);
            setM_Y(getM_Y() + brickHeight);
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        setM_Y(getM_Y() - brickHeight);
        point = new Point2D(getM_X(), getM_Y());
        tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                point, brickSize, getM_TypeA());
        setM_Counter(getM_Counter() + 1);
        i = 0;
        while (i < BODY_BOTTOM_UP_BEND) {
            setM_X(getM_X() + brickLength);
            setM_Y(getM_Y() - brickHeight);
            point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        return tmpBricks;
    }

    /**
     * Makes the top of the fish.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeTopBody(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickHeight = brickSize.getHeight();
        double brickLength = brickSize.getWidth();
        Point2D point;
        int i = 0;
        setM_X(getConnectBodyXPos() + brickLength);
        setM_Y(getConnectBodyYPos() - (brickHeight + brickHeight));
        while (i < BODY_TOP_UP_BEND) {
            point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_X(getM_X() + brickLength);
            setM_Y(getM_Y() - brickHeight);
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        setM_Y(getM_Y() + brickHeight);
        point = new Point2D(getM_X(), getM_Y());
        tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                point, brickSize, getM_TypeA());
        setM_Counter(getM_Counter() + 1);
        i = 0;
        while (i < BODY_TOP_DOWN_BEND) {
            setM_X(getM_X() + brickLength);
            setM_Y(getM_Y() + brickHeight);
            point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        return tmpBricks;
    }

    /**
     * Makes the eye of the fish.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeEye(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickLength = brickSize.getWidth();
        setM_X(getM_X() - (brickLength + brickLength));
        Point2D point = new Point2D(getM_X(), getM_Y());
        tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                point, brickSize, getM_TypeB());
        setM_Counter(getM_Counter() + 1);

        return tmpBricks;
    }

    /**
     * Makes the fin of the fish.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeFin(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickHeight = brickSize.getHeight();
        double brickLength = brickSize.getWidth();
        setM_X(getM_X() - (brickLength + brickLength));
        setM_Y(getM_Y()-(brickHeight+brickHeight+brickHeight+brickHeight));
        Point2D point = new Point2D(getM_X(), getM_Y());
        tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                point, brickSize, getM_TypeA());

        return tmpBricks;
    }

}
