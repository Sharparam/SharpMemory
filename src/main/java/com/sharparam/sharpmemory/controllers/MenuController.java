package com.sharparam.sharpmemory.controllers;

import com.sharparam.sharpmemory.Difficulty;
import com.sharparam.sharpmemory.SharpMemory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created on 2014-03-27.
 *
 * @author Sharparam
 */
public class MenuController implements Initializable {
    @FXML
    private ToggleGroup diffToggleGroup;

    @FXML
    private RadioButton easy;

    @FXML
    private RadioButton medium;

    @FXML
    private RadioButton hard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        easy.setUserData(Difficulty.EASY);
        medium.setUserData(Difficulty.MEDIUM);
        hard.setUserData(Difficulty.HARD);
    }

    public Difficulty getDifficulty() {
        Toggle selected = diffToggleGroup.getSelectedToggle();
        return (Difficulty) selected.getUserData();
    }

    @FXML
    protected void handleStartButtonAction(ActionEvent event) {
        SharpMemory.getInstance().setDifficulty(getDifficulty());
        SharpMemory.getInstance().showGame();
    }

    @FXML
    protected void handleQuitButtonAction(ActionEvent event) {
        Platform.exit();
    }
}
