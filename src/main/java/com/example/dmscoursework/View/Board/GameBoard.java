package com.example.dmscoursework.View.Board;

import com.example.dmscoursework.Controller.GameController;
import com.example.dmscoursework.Controller.fxml.StartController;
import com.example.dmscoursework.View.Brick.Brick;
import com.example.dmscoursework.View.Level.LevelManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * The GameBoard class makes the game board on which the game is played. It
 * draws the relevant features such as the Ball, Paddle, Wall as well the
 * text on the game board.
 *
 * @author  William Michiel Simson van Dijkhuizen
 * @version 1.0
 * @since   04-01-2022
 */
public class GameBoard {

    private final int WIDTH = 600;
    private final int HEIGHT = 450;

    private final double HALF = 0.5;

    private final int FONT_SIZE = 28;

    private AnchorPane m_Root;
    private GameController m_Controller;
    private Stage m_Stage;
    private Scene m_Scene;
    private ImageView m_ImageView;
    private String m_Theme;
    private String m_Username;
    private LevelManager m_LevelManager;

    /**
     * Get the imageView which is the background image of the game.
     *
     * @return a ImageView, which is background image.
     */
    private ImageView getM_ImageView() {
        return m_ImageView;
    }
    /**
     * Sets the background image of the game.
     *
     * @param imageView a ImageView, which is background image.
     */
    private void setM_ImageView(ImageView imageView) {
        this.m_ImageView = imageView;
    }

    /**
     * Gets the level manager.
     *
     * @return a LevelManager, which manages the level.
     */
    public LevelManager GetM_LevelManager() {
        return m_LevelManager;
    }
    /**
     * Sets the level manager
     *
     * @param levelBuilder a LevelManager, which managas the level.
     */
    private void setM_LevelManager(LevelManager levelBuilder) {
        this.m_LevelManager = levelBuilder;
    }

    /**
     * Get the width of the GameBoard
     *
     * @return an integer, which is width of board.
     */
    public int GetWidth() {
        return WIDTH;
    }
    /**
     * Get the height of the GameBoard
     *
     * @return an integer, which is height of board.
     */
    public int GetHeight() {
        return HEIGHT;
    }

    /**
     * Get the pane that the game is played on.
     *
     * @return an AnchorPane, which is the pane the game is played on.
     */
    public AnchorPane GetM_RootPane() {
        return m_Root;
    }
    /**
     * Set the pane that that game is played on.
     *
     * @param root an AnchorPane, which is the pane the game is played on.
     */
    private void setM_RootPane(AnchorPane root) {
        this.m_Root = root;
    }

    /**
     * Gets the stage.
     *
     * @return a Stage.
     */
    public Stage GetM_Stage() {
        return m_Stage;
    }
    /**
     * Sets the stage.
     *
     * @param stage a Stage.
     */
    private void setM_Stage(Stage stage) {
        this.m_Stage = stage;
    }

    /**
     * Gets the scene.
     *
     * @return a Scene.
     */
    public Scene GetM_Scene() {
        return m_Scene;
    }
    /**
     * Sets the scene.
     *
     * @param scene a Scene.
     */
    private void setM_Scene(Scene scene) {
        this.m_Scene = scene;
    }

    /**
     * Get the game controller.
     *
     * @return a GameController, which controls the game.
     */
    public GameController GetController() {
        return m_Controller;
    }
    /**
     * Set the game controller.
     *
     * @param controller a GameController, which controls the game.
     */
    private void setM_Controller(GameController controller) {
        this.m_Controller = controller;
    }

    /**
     * Gets the username of the player.
     *
     * @return a String, which is username of player.
     */
    public String GetM_Username() {
        return m_Username;
    }
    /**
     * Sets the username of the player.
     *
     * @param username a String, which is username of player.
     */
    private void setM_Username(String username) {
        this.m_Username = username;
    }

    /**
     * Gets the theme of the game.
     *
     * @return a String, which is the theme.
     */
    public String GetM_Theme() {
        return m_Theme;
    }
    /**
     * Set the theme of the game.
     *
     * @param theme a String, which is the theme.
     */
    private void setM_Theme(String theme) {
        this.m_Theme = theme;
    }

    /**
     * Constructs a game board using the information passed to it from the
     * startController such as the theme, the username of the player and the
     * stage that the gameBoard needs to be placed on.
     *
     * @param startController a StartController, which is controller from
     *                        startScreen.
     */
    public GameBoard(StartController startController) {
        Stage stage = startController.GetM_Stage();
        setM_Theme(startController.GetM_Theme());
        setM_Username(startController.GetM_Username());
        setM_Stage(stage);
        stage.setTitle("B R E A K O U T");
        int level = startController.GetM_Level();

        setM_RootPane(new AnchorPane());
        GetM_RootPane().minHeight(HEIGHT);
        GetM_RootPane().maxHeight(HEIGHT);
        GetM_RootPane().minWidth(WIDTH);
        GetM_RootPane().maxWidth(WIDTH);
        setM_Scene(new Scene(GetM_RootPane(), WIDTH, HEIGHT));
        setM_LevelManager(new LevelManager(this));
        setM_Controller(new GameController(this, level));

        try {
            selectTheme();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        drawGame();
        stage.setScene(GetM_Scene());
        stage.show();
    }

    /**
     * Selects the theme and selects the background image using images from
     * assets folder.
     *
     * @throws FileNotFoundException When image cannot be found.
     */
    private void selectTheme() throws FileNotFoundException {
        FileInputStream inputstream;

        if (GetM_Theme() == ("sunset")) {
            inputstream = new FileInputStream(
                    "src/Assets/OrangeAssets/SunsetBackground.jpg");
        } else if (GetM_Theme() == ("ocean")) {
            inputstream = new FileInputStream(
                    "src/Assets/BlueAssets/OceanBackground.jpg");
        } else if (GetM_Theme() == ("candy")) {
            inputstream = new FileInputStream(
                    "src/Assets/PinkAssets/CandyBackground.jpg");
        } else {
            inputstream = new FileInputStream(
                    "src/Assets/DefaultAssets/ConstructionBackground.jpg");
        }
        Image image = new Image(inputstream);
        setM_ImageView(new ImageView());
        getM_ImageView().setFitWidth(WIDTH);
        getM_ImageView().setFitHeight(HEIGHT + 1);
        getM_ImageView().setPreserveRatio(false);
        getM_ImageView().setViewOrder(1);
        getM_ImageView().setImage(image);
    }

    /**
     * Draws the game with the Paddle, Ball, Bricks and text that needs to be
     * displayed.
     */
    public void drawGame() {
        clearGame();

        Label label = new Label();
        label.setText(GetController().GetM_Message());
        label.setFont(Font.font(FONT_SIZE));
        label.setLayoutY(HEIGHT * HALF);
        label.setMinWidth(WIDTH);
        label.setAlignment(Pos.CENTER);
        label.setViewOrder(-1);
        GetM_RootPane().getChildren().add(label);

        GetM_RootPane().getChildren().add(
                GetM_LevelManager().GetM_Ball().GetM_BallShape());

        GetM_RootPane().getChildren().add(
                GetM_LevelManager().GetM_Player().GetM_PaddleShape());

        for (Brick m_Brick :
                GetM_LevelManager().GetM_LevelBuilder().GetM_Bricks()) {
            if (m_Brick.IsM_NotBroken())
                GetM_RootPane().getChildren().add(m_Brick.GetM_BrickShape());
        }
        GetM_RootPane().getChildren().add(getM_ImageView());
    }

    /**
     * Clears all the children nodes from the anchorPane.
     */
    private void clearGame() {
        GetM_RootPane().getChildren().clear();
        GetController().SetM_DebugConsole(null);
    }
}


