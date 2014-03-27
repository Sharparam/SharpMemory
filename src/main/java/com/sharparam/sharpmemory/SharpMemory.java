package com.sharparam.sharpmemory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SharpMemory extends Application {
    private Stage stage;

    private static SharpMemory instance;

    public SharpMemory() {
        instance = this;
    }

    public static SharpMemory getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showMenu();
        stage.show();
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
        Parent page = FXMLLoader.load(SharpMemory.class.getResource("/views/" + fxml + ".fxml"), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 450);
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
