package com.sharparam.sharpmemory.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SampleController {
    @FXML
    public Text testText;

    @FXML
    public void handleTestButtonAction(ActionEvent event) {
        testText.setText("Test text!");
    }
}
