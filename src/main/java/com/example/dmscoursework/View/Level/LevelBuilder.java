package com.example.dmscoursework.View.Level;

import com.example.dmscoursework.View.Brick.BlueBrick.BlueBrick;
import com.example.dmscoursework.View.Brick.BlueBrick.CheckerBlueBrick;
import com.example.dmscoursework.View.Brick.BlueBrick.StripeBlueBrick;
import com.example.dmscoursework.View.Brick.Brick;
import com.example.dmscoursework.View.Brick.OrangeBrick.CheckerOrangeBrick;
import com.example.dmscoursework.View.Brick.OrangeBrick.OrangeBrick;
import com.example.dmscoursework.View.Brick.OrangeBrick.StripeOrangeBrick;
import com.example.dmscoursework.View.Brick.PinkBrick.CheckerPinkBrick;
import com.example.dmscoursework.View.Brick.PinkBrick.PinkBrick;
import com.example.dmscoursework.View.Brick.PinkBrick.StripePinkBrick;
import com.example.dmscoursework.View.Brick.TributeBrick.CementBrick;
import com.example.dmscoursework.View.Brick.TributeBrick.ClayBrick;
import com.example.dmscoursework.View.Brick.TributeBrick.SteelBrick;
import com.example.dmscoursework.View.Level.CandyTheme.CandyCaneLevel;
import com.example.dmscoursework.View.Level.CandyTheme.CandyDrinkLevel;
import com.example.dmscoursework.View.Level.CandyTheme.LollipopLevel;
import com.example.dmscoursework.View.Level.OceanTheme.FishHookLevel;
import com.example.dmscoursework.View.Level.OceanTheme.FishLevel;
import com.example.dmscoursework.View.Level.OceanTheme.WaveLevel;
import com.example.dmscoursework.View.Level.SunsetTheme.FireTreeLevel;
import com.example.dmscoursework.View.Level.SunsetTheme.OrangeFoodLevel;
import com.example.dmscoursework.View.Level.SunsetTheme.SunLevel;
import com.example.dmscoursework.View.Level.generalTheme.ChessBoardLevel;
import com.example.dmscoursework.View.Level.generalTheme.SingleTypeLevel;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
/**
 * The LevelBuilder is in charge of creating levels. The levels that are
 * needed to be beat are walls that are needed to broken, so the LevelBuilder
 * will build walls according to theme specified for the level.
 *
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class LevelBuilder {

    private final int CLAY = 1;
    private final int STEEL = 2;
    private final int CEMENT = 3;
    private final int BLUE_BRICK = 4;
    private final int STRIPE_BLUE_BRICK = 5;
    private final int CHECKER_BLUE_BRICK = 6;
    private final int ORANGE_BRICK = 7;
    private final int STRIPE_ORANGE_BRICK = 8;
    private final int CHECKER_ORANGE_BRICK = 9;
    private final int PINK_BRICK = 10;
    private final int STRIPE_PINK_BRICK = 11;
    private final int CHECKER_PINK_BRICK = 12;

    private final int LEVELS_COUNT = 6;
    private final int LEVEL_0 = 0;
    private final int LEVEL_1 = 1;
    private final int LEVEL_2 = 2;
    private final int LEVEL_3 = 3;
    private final int LEVEL_4 = 4;
    private final int LEVEL_5 = 5;

    private Brick[][] m_Levels;
    private Brick[] m_Bricks;
    private int m_Level;

    private Rectangle m_DrawArea;
    private int m_BrickCnt;

    /**
     * Get the levels.
     *
     * @return a Brick[][], which contains the levels.
     */
    public Brick[][] GetM_Levels() {
        return m_Levels;
    }

    /**
     * Sets the levels.
     *
     * @param levels a Brick[][], which contains the levels.
     */
    private void setM_Levels(Brick[][] levels) {
        this.m_Levels = levels;
    }

    /**
     * Gets the level.
     *
     * @return an integer, which is the level.
     */
    public int GetM_Level() {
        return m_Level;
    }
    /**
     * Sets the level
     *
     * @param level an integer, which is the level.
     */
    public void SetM_Level(int level) {
        this.m_Level = level;
    }

    /**
     * Gets the area in which the levels are created.
     *
     * @return a Rectangle, which is the area.
     */
    public Rectangle GetM_DrawArea() {
        return m_DrawArea;
    }
    /**
     * Sets the area in which the levels are created.
     *
     * @param drawArea a Rectangle, which is the area.
     */
    private void setM_DrawArea(Rectangle drawArea) {
        this.m_DrawArea = drawArea;
    }

    /**
     * Gets an array of bricks.
     *
     * @return a Brick[], which contains an array of bricks.
     */
    public Brick[] GetM_Bricks() {
        return m_Bricks;
    }
    /**
     * Sets an array of bricks.
     *
     * @param bricks a Brick[], which contains an array of bricks.
     */
    public void SetM_Bricks(Brick[] bricks) {
        this.m_Bricks = bricks;
    }

    /**
     * Gets the number of bricks in a level.
     *
     * @return an integer, which is the number of bricks.
     */
    public int GetM_BrickCnt() {
        return m_BrickCnt;
    }
    /**
     * Sets the number of bricks in a level.
     *
     * @param brickCnt an integer, which is the number of bricks.
     */
    public void SetM_BrickCnt(int brickCnt) {
        this.m_BrickCnt = brickCnt;
    }

    /**
     * Constructs the levelBuilder class by specifying area with a Rectangle
     * area, and specifying the number of bricks as well as the number of
     * rows as an integer argument, also specifies the size of the brick with
     * a double argument as well as the theme of the level.
     *
     * @param drawArea a Rectangle, where bricks for level will be created.
     * @param brickCount an integer, which is number of bricks.
     * @param lineCount an integer, which is number of rows of bricks.
     * @param brickDimensionRatio a double, which is size of bricks.
     * @param theme a String, which is theme of the level.
     */
    public LevelBuilder(Rectangle drawArea, int brickCount, int lineCount,
                        double brickDimensionRatio, String theme) {
        setM_DrawArea(drawArea);
        SetM_BrickCnt(brickCount);

        setM_Levels(makeLevels(lineCount, brickDimensionRatio, theme));

        SetM_Level(0);
    }

    /**
     * Makes the levels of the game according to the theme specified.
     *
     * @param lineCount an integer, which is number of rows in a level.
     * @param brickDimensionRatio a double, which is size of bricks.
     * @param theme a String, which is the theme of the levels.
     * @return
     */
    private Brick[][] makeLevels(int lineCount, double brickDimensionRatio,
                           String theme) {

        Brick[][] tmpLevel;

        if (theme == "sunset") {
            tmpLevel = makeSunsetLevels(lineCount, brickDimensionRatio);
        } else if (theme == "ocean") {
            tmpLevel = makeOceanLevels(lineCount, brickDimensionRatio);
        } else if (theme == "candy") {
            tmpLevel = makeCandyLevels(lineCount, brickDimensionRatio);
        } else {
            tmpLevel = makeBasicLevels(lineCount, brickDimensionRatio);
        }
        return tmpLevel;
    }

    /**
     * Makes levels that are the default theme.
     *
     * @param lineCount an integer, which is number of rows in a level.
     * @param brickDimensionRatio a double, which is size of bricks.
     * @return
     */
    private Brick[][] makeBasicLevels(int lineCount, double brickDimensionRatio) {
        Brick[][] tmpLevel = new Brick[LEVELS_COUNT][];

        tmpLevel[LEVEL_0] = new SingleTypeLevel(this,
                lineCount, brickDimensionRatio, CLAY).GetLevel();

        tmpLevel[LEVEL_1] = new ChessBoardLevel(this,
                lineCount, brickDimensionRatio, CLAY, CEMENT).GetLevel();

        tmpLevel[LEVEL_2] = new ChessBoardLevel(this, lineCount,
                brickDimensionRatio, STEEL, CLAY).GetLevel();

        tmpLevel[LEVEL_3] = new ChessBoardLevel(this, lineCount,
                brickDimensionRatio, CEMENT, STEEL).GetLevel();

        tmpLevel[LEVEL_4] = new ChessBoardLevel(this, lineCount,
                brickDimensionRatio, CEMENT, CLAY).GetLevel();

        tmpLevel[LEVEL_5] = new ChessBoardLevel(this, lineCount,
                brickDimensionRatio, STEEL, CEMENT).GetLevel();

        return tmpLevel;
    }

    /**
     * Makes levels that are the ocean themed.
     *
     * @param lineCount an integer, which is number of rows in a level.
     * @param brickDimensionRatio a double, which is size of bricks.
     * @return
     */
    private Brick[][] makeOceanLevels(int lineCount,
                                      double brickDimensionRatio) {
        Brick[][] tmpLevel = new Brick[LEVELS_COUNT][];

        tmpLevel[LEVEL_0] = new SingleTypeLevel(this,
                lineCount, brickDimensionRatio, BLUE_BRICK).GetLevel();

        tmpLevel[LEVEL_1] = new ChessBoardLevel(this,
                lineCount, brickDimensionRatio, BLUE_BRICK, STRIPE_BLUE_BRICK).GetLevel();

        tmpLevel[LEVEL_2] = new ChessBoardLevel(this, lineCount,
                brickDimensionRatio, CHECKER_BLUE_BRICK, BLUE_BRICK).GetLevel();

        tmpLevel[LEVEL_3] = new WaveLevel(this,
                lineCount, brickDimensionRatio, BLUE_BRICK).GetLevel();

        tmpLevel[LEVEL_4] = new FishHookLevel(this,
                lineCount, brickDimensionRatio, BLUE_BRICK).GetLevel();

        tmpLevel[LEVEL_5] = new FishLevel(this, lineCount,
                brickDimensionRatio, BLUE_BRICK, CHECKER_BLUE_BRICK).GetLevel();;

        return tmpLevel;
    }

    /**
     * Makes levels that are the sunset themed.
     *
     * @param lineCount an integer, which is number of rows in a level.
     * @param brickDimensionRatio a double, which is size of bricks.
     * @return
     */
    private Brick[][] makeSunsetLevels(int lineCount,
                                       double brickDimensionRatio) {
        Brick[][] tmpLevel = new Brick[LEVELS_COUNT][];

        tmpLevel[LEVEL_0] = new SingleTypeLevel(this,
                lineCount, brickDimensionRatio, ORANGE_BRICK).GetLevel();

        tmpLevel[LEVEL_1] = new ChessBoardLevel(this,
                lineCount, brickDimensionRatio, ORANGE_BRICK, STRIPE_ORANGE_BRICK).GetLevel();

        tmpLevel[LEVEL_2] = new ChessBoardLevel(this, lineCount,
                brickDimensionRatio, CHECKER_ORANGE_BRICK, ORANGE_BRICK).GetLevel();

        tmpLevel[LEVEL_3] = new OrangeFoodLevel(this,
                lineCount, brickDimensionRatio, ORANGE_BRICK).GetLevel();

        tmpLevel[LEVEL_4] = new SunLevel(this,
                lineCount, brickDimensionRatio, ORANGE_BRICK, STRIPE_ORANGE_BRICK).GetLevel();

        tmpLevel[LEVEL_5] = new FireTreeLevel(this, lineCount,
                brickDimensionRatio, ORANGE_BRICK, STRIPE_ORANGE_BRICK).GetLevel();;

        return tmpLevel;
    }

    /**
     * Makes levels that are the candy themed.
     *
     * @param lineCount an integer, which is number of rows in a level.
     * @param brickDimensionRatio a double, which is size of bricks.
     * @return
     */
    private Brick[][] makeCandyLevels(int lineCount,
                                      double brickDimensionRatio) {
        Brick[][] tmpLevel = new Brick[LEVELS_COUNT][];

        tmpLevel[LEVEL_0] = new SingleTypeLevel(this,
                lineCount, brickDimensionRatio, PINK_BRICK).GetLevel();

        tmpLevel[LEVEL_1] = new ChessBoardLevel(this,
                lineCount, brickDimensionRatio, PINK_BRICK, STRIPE_PINK_BRICK).GetLevel();

        tmpLevel[LEVEL_2] = new ChessBoardLevel(this, lineCount,
                brickDimensionRatio, CHECKER_PINK_BRICK, PINK_BRICK).GetLevel();

        tmpLevel[LEVEL_3] = new LollipopLevel(this,
                lineCount, brickDimensionRatio, STRIPE_PINK_BRICK, PINK_BRICK).GetLevel();

        tmpLevel[LEVEL_4] = new CandyCaneLevel(this,
                lineCount, brickDimensionRatio, PINK_BRICK).GetLevel();

        tmpLevel[LEVEL_5] = new CandyDrinkLevel(this, lineCount,
                brickDimensionRatio, CHECKER_PINK_BRICK, PINK_BRICK).GetLevel();;

        return tmpLevel;
    }

    /**
     * Makes a brick by specifying its position with a Point2D argument, and
     * specifying the size with a Dimension2D argument and type of brick
     * with an int argument.
     *
     * @param point a Point, position of the brick.
     * @param size  a Dimension, height and width of the brick.
     * @param type  an integer, type of brick.
     * @return a Brick of a specific type.
     */
    public Brick MakeBrick(Point2D point, Dimension2D size, int type) {
        return switch (type) {
            case CLAY -> new ClayBrick(point, size);
            case STEEL -> new SteelBrick(point, size);
            case CEMENT -> new CementBrick(point, size);
            case BLUE_BRICK -> new BlueBrick(point, size);
            case STRIPE_BLUE_BRICK -> new StripeBlueBrick(point, size);
            case CHECKER_BLUE_BRICK -> new CheckerBlueBrick(point, size);
            case ORANGE_BRICK -> new OrangeBrick(point, size);
            case STRIPE_ORANGE_BRICK -> new StripeOrangeBrick(point, size);
            case CHECKER_ORANGE_BRICK -> new CheckerOrangeBrick(point, size);
            case PINK_BRICK -> new PinkBrick(point, size);
            case STRIPE_PINK_BRICK -> new StripePinkBrick(point, size);
            case CHECKER_PINK_BRICK -> new CheckerPinkBrick(point, size);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        };
    }

}

