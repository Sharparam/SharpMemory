package com.sharparam.sharpmemory;

import com.sharparam.sharpmemory.controllers.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main application class.
 *
 * @author Sharparam
 */
public class SharpMemory extends Application {
    /**
     * The current stage.
     */
    private Stage stage;

    /**
     * Difficulty to use when starting a new game.
     */
    private Difficulty difficulty = Difficulty.EASY;

    /**
     * Latest initialized instance of this class.
     */
    private static SharpMemory instance;

    /**
     * Current controller.
     */
    private Object controller;

    /**
     * Initializes a new instance of SharpMemory.
     */
    public SharpMemory() {
        // Initialize the instance field.
        instance = this;
    }

    /**
     * Gets the SharpMemory instance.
     * @return Instance of SharpMemory.
     */
    public static SharpMemory getInstance() {
        return instance;
    }

    /**
     * Gets the current difficulty.
     * @return The configured difficulty.
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty to use for new games.
     * @param difficulty Difficulty to set.
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Application start entry point.
     * @param primaryStage Stage object.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showMenu();
        stage.show();
    }

    /**
     * Stops the application and exits.
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        if (controller != null && controller instanceof GameController)
            ((GameController) controller).cancelGame();
        super.stop();
    }

    /**
     * Shows the game menu.
     */
    public void showMenu() {
        try {
            replaceSceneContent("menu");
            stage.setTitle("SharpMemory - Menu");
        } catch (Exception ex) {
            Logger.getLogger(SharpMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Shows the game itself, using the configured difficulty.
     */
    public void showGame() {
        try {
            replaceSceneContent("game");
            stage.setTitle("SharpMemory - Game");
        } catch (Exception ex) {
            Logger.getLogger(SharpMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Helper method to set a new view from an FXML file.
     * @param fxml FXML file to load from.
     * @return Parent object for the scene.
     * @throws Exception
     */
    private Parent replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + fxml + ".fxml"), null, new JavaFXBuilderFactory());
        Parent page = (Parent) loader.load();
        controller = loader.getController();
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 600, 500);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }

    /**
     * Main entry point.
     * @param args Commandline arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
