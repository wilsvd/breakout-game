package com.example.dmscoursework.View.Level.generalTheme;

import com.example.dmscoursework.View.Brick.Brick;
import com.example.dmscoursework.View.Level.LevelBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
/**
 * The ChessBoardLevel is a level which takes multiple bricks and puts them
 * together into a rectangular shape making a checkered pattern using two
 * different types of bricks.
 *
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class ChessBoardLevel {

    private Brick[] m_Level = null;

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
     * Constructs a level that has 2 different types of bricks by passing in
     * relevant information such as the number of rows, the size of bricks,
     * the type of bricks and general information from the levelBuilder such
     * as the drawArea and brickCount.
     *
     * @param levelBuilder a LevelBuilder, which contains level information.
     * @param lineCount an integer, which is number of rows.
     * @param brickSizeRatio a double, which is the size of bricks.
     * @param typeA an integer, which is the type of brick.
     * @param typeB an integer, which is the type of brick.
     */
    public ChessBoardLevel(LevelBuilder levelBuilder, int lineCount,
                           double brickSizeRatio,
                           int typeA, int typeB) {

        setLevel(makeChessboardLevel(levelBuilder, lineCount, brickSizeRatio,
                typeA, typeB));
    }

    /**
     * Makes a level that has a checkered look.
     *
     * @param levelBuilder a LevelBuilder, which contains info regarding level.
     * @param lineCount an integer, which is the number of rows.
     * @param brickSizeRatio a double, which is the size of brick.
     * @param typeA an integer, which is a type of brick.
     * @param typeB an integer, which is a type of brick.
     * @return a Brick[], which is an array of bricks.
     */
    public Brick[] makeChessboardLevel(LevelBuilder levelBuilder,
                                       int lineCount, double brickSizeRatio,
                                       int typeA, int typeB) {

        Point2D point;
        int brickOnLine = levelBuilder.GetM_BrickCnt() / lineCount;
        double brickLength =
                levelBuilder.GetM_DrawArea().getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;

        Brick[] tmpBricks = new Brick[levelBuilder.GetM_BrickCnt()];
        Dimension2D brickSize = new Dimension2D(brickLength, brickHeight);
        double x = 0, y = 0;
        boolean type1 = true;
        for (int i = 0; i < tmpBricks.length; i++) {
            point = new Point2D(x, y);
            x += brickLength;
            if (type1 == true) {
                tmpBricks[i] = levelBuilder.MakeBrick(point, brickSize, typeA);
                type1 = false;
            } else {
                tmpBricks[i] = levelBuilder.MakeBrick(point, brickSize, typeB);
                type1 = true;
            }
            if (x == levelBuilder.GetM_DrawArea().getWidth()) {
                x = 0;
                y += brickHeight;
                type1 = !type1;
            }
        }
        return tmpBricks;
    }

}
