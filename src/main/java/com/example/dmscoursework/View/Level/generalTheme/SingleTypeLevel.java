package com.example.dmscoursework.View.Level.generalTheme;

import com.example.dmscoursework.View.Brick.Brick;
import com.example.dmscoursework.View.Level.LevelBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
/**
 * The SingleTypeLevel is a level which takes multiple bricks and puts them
 * together into a rectangular shape of one type of brick.
 *
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class SingleTypeLevel {

    private Brick[] m_Level;

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
    private void setM_Level(Brick[] level) {
        this.m_Level = level;
    }

    /**
     * Constructs a level of one type by passing in relevant information such
     * as the number of rows, the size of bricks, the type of brick and
     * general information from the levelBuilder such as the drawArea and
     * brickCount.
     *
     * @param levelBuilder a LevelBuilder, which contains level information.
     * @param lineCount an integer, which is number of rows.
     * @param brickSizeRatio a double, which is the size of bricks.
     * @param type an integer, which is the type of brick.
     */
    public SingleTypeLevel(LevelBuilder levelBuilder, int lineCount,
                           double brickSizeRatio,
                           int type) {

        setM_Level(makeSingleTypeLevel(
                levelBuilder, lineCount, brickSizeRatio, type));
    }

    /**
     * Makes a level that has a single type brick.
     *
     * @param levelBuilder a LevelBuilder, which contains info regarding level.
     * @param lineCount an integer, which is the number of rows.
     * @param brickSizeRatio a double, which is the size of brick.
     * @param type an integer, which is a type of brick.
     * @return a Brick[], which is an array of bricks.
     */
    private Brick[] makeSingleTypeLevel(LevelBuilder levelBuilder,
                                        int lineCount, double brickSizeRatio,
                                        int type) {
        Point2D point;

        int brickOnLine = levelBuilder.GetM_BrickCnt() / lineCount;

        double brickLength =
                levelBuilder.GetM_DrawArea().getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;

        Brick[] tmpBricks = new Brick[levelBuilder.GetM_BrickCnt()];
        Dimension2D brickSize = new Dimension2D((int) brickLength,
                (int) brickHeight);

        double x = 0, y = 0;
        for (int i = 0; i < tmpBricks.length; i++) {
            point = new Point2D(x, y);
            tmpBricks[i] = levelBuilder.MakeBrick(point, brickSize, type);
            x += brickLength;
            if (x == levelBuilder.GetM_DrawArea().getWidth()) {
                x = 0;
                y += brickHeight;
            }
        }
        return tmpBricks;
    }
}
