package com.sharparam.sharpmemory.controllers;

import com.sharparam.sharpmemory.SharpMemory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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

    }
}
