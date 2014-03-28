package com.sharparam.sharpmemory.controllers;

import com.sharparam.sharpmemory.SharpMemory;
import com.sharparam.sharpmemory.State;
import com.sharparam.sharpmemory.events.FieldEventListener;
import com.sharparam.sharpmemory.events.FieldEventType;
import com.sharparam.sharpmemory.models.BrickModel;
import com.sharparam.sharpmemory.models.FieldModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by on 2014-03-27.
 *
 * @author Sharparam
 */
public class GameController implements Initializable {
    private static final double IMAGE_SIZE = 64.0;
    private int points = 0;
    private int tries = 0;

    private FieldModel field;
    @FXML
    private GridPane window;

    @FXML
    private Text pointsText;

    @FXML
    private Text triesText;

    @FXML
    private Text timeText;

    @FXML
    private Text gameOverText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        field = new FieldModel(5);
        field.addEventListener(new FieldEventListener() {
            @Override
            public void handle(FieldEventType type) {
                switch (type) {
                    case TRY:
                        tries++;
                        break;
                    case CLEAR:
                        points++;
                        break;
                    case FAIL:
                        break;
                    case ALL_BRICKS_CLEARED:
                        gameOverText.setVisible(true);
                        break;
                }

                pointsText.setText("Points: " + points);
                triesText.setText("Tries: " + tries);
            }
        });
        for (int i = 0; i < field.getBrickCount(); i++) {
            final BrickModel brick = field.getBrick(i);
            final ImageView imageView = brick.getImageView();
            imageView.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                    if (field.isTryInProgress())
                        return;
                    field.flipBrick(brick);
                }
            });
            window.add(imageView, i < 5 ? i : i - 5, i < 5 ? 1 : 2);
        }
    }

    @FXML
    protected void handleMenuButtonAction(ActionEvent actionEvent) {
        SharpMemory.getInstance().showMenu();
    }

    @FXML
    protected void handleQuitButtonAction(ActionEvent event) {
        Platform.exit();
    }
}
