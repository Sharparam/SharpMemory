package com.sharparam.sharpmemory.controllers;

import com.sharparam.sharpmemory.Difficulty;
import com.sharparam.sharpmemory.SharpMemory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the menu view.
 *
 * Created on 2014-03-27.
 *
 * @author Sharparam
 */
public class MenuController implements Initializable {
    /**
     * Toggle group for difficulty radio buttons.
     */
    @FXML
    private ToggleGroup diffToggleGroup;

    /**
     * Radio button for the easy difficulty.
     */
    @FXML
    private RadioButton easy;

    /**
     * Radio button for the medium difficulty.
     */
    @FXML
    private RadioButton medium;

    /**
     * Radio button for the hard difficulty.
     */
    @FXML
    private RadioButton hard;

    /**
     * Initializer method.
     * @param url FXML URL.
     * @param resourceBundle Resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the difficulty as user data on the respective radio buttons so that
        // it can easily be retrieved in #getDifficulty()
        easy.setUserData(Difficulty.EASY);
        medium.setUserData(Difficulty.MEDIUM);
        hard.setUserData(Difficulty.HARD);
    }

    /**
     * Gets the selected difficulty.
     * @return Selected difficulty.
     */
    public Difficulty getDifficulty() {
        Toggle selected = diffToggleGroup.getSelectedToggle();
        return (Difficulty) selected.getUserData();
    }

    /**
     * Handler method for when the start button is pressed.
     */
    @FXML
    protected void handleStartButtonAction() {
        // This might be a dirty hack, but it achieves what we want.
        // We set the difficulty field on the main Application class through
        // a setter method before switching to the game view.
        SharpMemory.getInstance().setDifficulty(getDifficulty());
        SharpMemory.getInstance().showGame();
    }

    /**
     * Handler method for when the quit button is pressed.
     */
    @FXML
    protected void handleQuitButtonAction() {
        Platform.exit();
    }
}
