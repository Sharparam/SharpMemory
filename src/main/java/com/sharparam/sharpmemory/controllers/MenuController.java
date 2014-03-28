package com.sharparam.sharpmemory.controllers;

import com.sharparam.sharpmemory.SharpMemory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created on 2014-03-27.
 *
 * @author Sharparam
 */
public class MenuController {
    @FXML
    protected void handleStartButtonAction(ActionEvent event) {
        SharpMemory.getInstance().showGame();
    }

    @FXML
    protected void handleQuitButtonAction(ActionEvent event) {
        Platform.exit();
    }
}
