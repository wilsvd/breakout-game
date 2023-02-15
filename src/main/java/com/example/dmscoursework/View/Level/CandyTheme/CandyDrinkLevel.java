package com.example.dmscoursework.View.Level.CandyTheme;

import com.example.dmscoursework.View.Brick.Brick;
import com.example.dmscoursework.View.Level.LevelBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
/**
 * The CandyDrinkLevel is a level which takes multiple bricks and puts them
 * together into the shape of a cup with a pink liquid.
 *
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class CandyDrinkLevel {

    private final double START_X_POS = 150;
    private final double START_Y_POS = 45;

    private final int LEFT_WALL_LENGTH = 8;
    private final int RIGHT_WALL_LENGTH = 8;
    private final int BOTTOM_WALL_LENGTH = 5;

    private final int ROW = 3;
    private final int COL = 3;

    private Brick[] m_Level = null;
    private LevelBuilder m_LevelBuilder;
    private int m_TypeA;
    private int m_TypeB;
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
     * Constructs a level looking like a drink filled with a pink candy liquid
     * by passing in relevant information such as the number of rows, the size
     * of bricks, the type of bricks and general information from the
     * levelBuilder such as the drawArea and brickCount.
     *
     * @param levelBuilder a LevelBuilder, which contains level information.
     * @param lineCount an integer, which is number of rows.
     * @param brickSizeRatio a double, which is the size of bricks.
     * @param typeA an integer, which is the type of brick.
     * @param typeB an integer, which is the type of brick.
     */
    public CandyDrinkLevel(LevelBuilder levelBuilder, int lineCount,
                           double brickSizeRatio,
                           int typeA, int typeB) {
        setM_LevelBuilder(levelBuilder);
        setM_TypeA(typeA);
        setM_TypeB(typeB);
        setLevel(CandyDrinkLevel(levelBuilder, lineCount, brickSizeRatio));
    }

    /**
     * Makes a level that looks like a drink with candy liquid.
     *
     * @param levelBuilder a LevelBuilder, which contains info regarding level.
     * @param lineCount an integer, which is the number of rows.
     * @param brickSizeRatio a double, which is the size of brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] CandyDrinkLevel(LevelBuilder levelBuilder,
                                    int lineCount, double brickSizeRatio) {

        int brickOnLine = levelBuilder.GetM_BrickCnt() / lineCount;
        double brickLength =
                levelBuilder.GetM_DrawArea().getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;

        Brick[] tmpBricks = new Brick[levelBuilder.GetM_BrickCnt()];
        Dimension2D brickSize = new Dimension2D(brickLength,brickHeight);

        setM_X(START_X_POS);
        setM_Y(START_Y_POS);

        tmpBricks = makeLeftWall(tmpBricks, brickSize);

        tmpBricks = fillCentre(tmpBricks, brickSize);

        tmpBricks = makeBottomWall(tmpBricks, brickSize);

        tmpBricks = makeRightWall(tmpBricks, brickSize);

        return tmpBricks;
    }

    /**
     * Makes the left wall of the drink cup.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeLeftWall(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickHeight = brickSize.getHeight();
        int i = 0;
        while (i < LEFT_WALL_LENGTH) {
            Point2D point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_Y(getM_Y() + brickHeight);
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        return tmpBricks;
    }

    /**
     * Makes the liquid inside the drink cup.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] fillCentre(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickHeight = brickSize.getHeight();
        double brickLength = brickSize.getWidth();
        setM_X(getM_X() + brickLength);
        double tempStore = getM_X();
        setM_Y(getM_Y() - (brickHeight + brickHeight + brickHeight));

        int i = 0;
        while (i < ROW) {
            int j = 0;
            while (j < COL) {
                Point2D point = new Point2D(getM_X(), getM_Y());
                tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                        point, brickSize, getM_TypeB());
                setM_X(getM_X() + brickLength);
                setM_Counter(getM_Counter() + 1);
                j++;
            }
            i++;
            setM_X(tempStore);
            setM_Y(getM_Y() + brickHeight);
        }
        return tmpBricks;
    }

    /**
     * Makes the bottom wall of the drink cup.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeBottomWall(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickLength = brickSize.getWidth();
        setM_X(START_X_POS);
        int i = 0;
        while (i < BOTTOM_WALL_LENGTH) {
            Point2D point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_X(getM_X() + brickLength);
            setM_Counter(getM_Counter() + 1);
            i++;
        }
        return tmpBricks;
    }

    /**
     * Makes the right wall of the drink cup.
     *
     * @param tmpBricks a Brick[], which is an array of bricks.
     * @param brickSize a Dimension2D, containing height and length of a brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeRightWall(Brick[] tmpBricks, Dimension2D brickSize) {
        double brickHeight = brickSize.getHeight();
        double brickLength = brickSize.getWidth();
        int i = 0;
        setM_X(getM_X() - brickLength);
        setM_Y(getM_Y() - brickHeight);
        while (i < RIGHT_WALL_LENGTH) {
            Point2D point = new Point2D(getM_X(), getM_Y());
            tmpBricks[getM_Counter()] = getM_LevelBuilder().MakeBrick(
                    point, brickSize, getM_TypeA());
            setM_Y(getM_Y() - brickHeight);
            setM_Counter(getM_Counter() + 1);
            i++;

        }
        return tmpBricks;
    }




}
