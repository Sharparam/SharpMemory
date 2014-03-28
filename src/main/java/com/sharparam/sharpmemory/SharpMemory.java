package com.sharparam.sharpmemory;

import com.sharparam.sharpmemory.controllers.GameController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SharpMemory extends Application {
    private Stage stage;
    private Difficulty difficulty = Difficulty.EASY;

    private static SharpMemory instance;

    private Object controller;

    public SharpMemory() {
        instance = this;
    }

    public static SharpMemory getInstance() {
        return instance;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showMenu();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if (controller != null && controller instanceof GameController)
            ((GameController) controller).cancelGame();
        super.stop();
    }

    public void showMenu() {
        try {
            replaceSceneContent("menu");
            stage.setTitle("SharpMemory - Menu");
        } catch (Exception ex) {
            Logger.getLogger(SharpMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showGame() {
        try {
            replaceSceneContent("game");
            stage.setTitle("SharpMemory - Game");
        } catch (Exception ex) {
            Logger.getLogger(SharpMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + fxml + ".fxml"), null, new JavaFXBuilderFactory());
        Parent page = (Parent) loader.load();
        controller = loader.getController();
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 600, 500);
            //scene.getStylesheets().add(SharpMemory.class.getResource("/views/sample.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
