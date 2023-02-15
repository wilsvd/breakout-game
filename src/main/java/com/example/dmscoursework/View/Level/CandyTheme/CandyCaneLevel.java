package com.example.dmscoursework.View.Level.CandyTheme;

import com.example.dmscoursework.View.Brick.Brick;
import com.example.dmscoursework.View.Level.LevelBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
/**
 * The CandyCaneLevel is a level which takes multiple bricks and puts them
 * together into the shape of a candy cane.
 *
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class CandyCaneLevel {

    private final double START_X_POS = 115;
    private final double START_Y_POS = 187.5;
    private final int SHORT_SIDE_NUM_BRICKS = 6;
    private final int BEND_LEFT_NUM_BRICKS = 5;
    private final int BEND_RIGHT_NUM_BRICKS = 4;
    private final int LONG_SIDE_NUM_BRICKS = 15;

    private Brick[] m_Level = null;
    private LevelBuilder m_LevelBuilder;
    private int m_TypeA;
    private int m_Counter = 0;
    private double m_X;
    private double m_Y;

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
     * Constructs a level looking like a candy cane by passing in relevant
     * information such as the number of rows, the size of bricks, the type of
     * bricks and general information from the levelBuilder such as the
     * drawArea and brickCount.
     *
     * @param levelBuilder a LevelBuilder, which contains level information.
     * @param lineCount an integer, which is number of rows.
     * @param brickSizeRatio a double, which is the size of bricks.
     * @param typeA an integer, which is the type of brick.
     */
    public CandyCaneLevel(LevelBuilder levelBuilder, int lineCount,
                          double brickSizeRatio,
                          int typeA) {
        setM_LevelBuilder(levelBuilder);
        setM_TypeA(typeA);
        setLevel(makeCandyCaneLevel(levelBuilder, lineCount, brickSizeRatio));
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
     * Makes a level that looks like a candy cane.
     *
     * @param levelBuilder a LevelBuilder, which contains info regarding level.
     * @param lineCount an integer, which is the number of rows.
     * @param brickSizeRatio a double, which is the size of brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeCandyCaneLevel(
            LevelBuilder levelBuilder, int lineCount, double brickSizeRatio) {

        int brickOnLine = levelBuilder.GetM_BrickCnt() / lineCount;

        double brickLength =
                levelBuilder.GetM_DrawArea().getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;

        Brick[] tmpBricks = new Brick[levelBuilder.GetM_BrickCnt()];
        Dimension2D brickSize = new Dimension2D(brickLength, brickHeight);

        setM_X(START_X_POS);
        setM_Y(START_Y_POS);

        tmpBricks = makeShortSide(tmpBricks, brickSize);
        tmpBricks = bendCandyCane(tmpBricks, brickSize);
        tmpBricks = makeLongSide(tmpBricks, brickSize);

        return tmpBricks;
    }

    /**
     * Makes the short side of the candy cane.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeShortSide(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickHeight = brickSize.getHeight();
        int i = 0;
        while (i < SHORT_SIDE_NUM_BRICKS) {
            Point2D point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());

            setM_Y(getM_Y() - brickHeight);
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        return tmpBricks;
    }

    /**
     * Makes the part of the candy cane that bends.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] bendCandyCane(Brick[] tmpBricks,
                                  Dimension2D brickSize) {
        double brickHeight = brickSize.getHeight();
        double brickLength = brickSize.getWidth();
        Point2D point;
        int i = 0;
        while (i < BEND_LEFT_NUM_BRICKS) {
            if (i == 0 || i == 4) {
                setM_X(getM_X() + brickLength);
                point = new Point2D(getM_X(), getM_Y());
                tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                        point, brickSize, getM_TypeA());
                setM_Counter(getM_Counter() + 1);
            } else {
                setM_Y(getM_Y() - brickHeight);
                point = new Point2D(getM_X(), getM_Y());
                tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                        point, brickSize, getM_TypeA());
                setM_Counter(getM_Counter() + 1);
                if (i == 1) {
                    setM_X(getM_X() + brickLength);
                }
            }
            i++;
        }
        i = 0;
        while (i < BEND_RIGHT_NUM_BRICKS) {
            setM_Y(getM_Y() + brickHeight);
            point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_Counter(getM_Counter() + 1);
            if (isDivisibleByTwo(i)) {
                setM_X(getM_X() + brickLength);
            }
            i++;
        }
        return tmpBricks;
    }

    /**
     * Makes the long side of the candy cane.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeLongSide(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickHeight = brickSize.getHeight();
        int i = 0;
        while (i < LONG_SIDE_NUM_BRICKS) {
            setM_Y(getM_Y() + brickHeight);
            Point2D point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        return tmpBricks;
    }
}
