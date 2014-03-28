package com.sharparam.sharpmemory.controllers;

import com.sharparam.sharpmemory.SharpMemory;
import com.sharparam.sharpmemory.events.FieldEventListener;
import com.sharparam.sharpmemory.events.FieldEventType;
import com.sharparam.sharpmemory.models.BrickModel;
import com.sharparam.sharpmemory.models.FieldModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by on 2014-03-27.
 *
 * @author Sharparam
 */
public class GameController implements Initializable {
    private static final double IMAGE_SIZE = 64.0;
    private int points = 0;
    private int tries = 0;

    private boolean gameActive = false;

    private long startTime;

    private Timer updateTimer;

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
        gameActive = true;
        startTime = System.currentTimeMillis();
        field = new FieldModel();
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
                        gameActive = false;
                        gameOverText.setVisible(true);
                        if (updateTimer != null)
                            updateTimer.cancel();
                        updateTimer = null;
                        break;
                }

                pointsText.setText("Points: " + points);
                triesText.setText("Tries: " + tries);
            }
        });
        int count = field.getBrickCount();
        for (int i = 0; i < count; i++) {
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
            int column = i % 5;
            int row = (int) Math.floor(i / 5) + 1;
            window.add(imageView, column, row);
        }

        window.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                cancelGame();
            }
        });

        updateTime();
    }

    public void cancelGame() {
        gameActive = false;
        if (updateTimer != null)
            updateTimer.cancel();
        updateTimer = null;
    }

    @FXML
    protected void handleMenuButtonAction(ActionEvent actionEvent) {
        cancelGame();
        SharpMemory.getInstance().showMenu();
    }

    @FXML
    protected void handleQuitButtonAction(ActionEvent event) {
        cancelGame();
        Platform.exit();
    }

    private void formatTime() {
        formatTime(startTime);
    }

    private void formatTime(long startTime) {
        long diff = (System.currentTimeMillis() - startTime) / 1000;
        long mins = (long) Math.floor((double) diff / 60.0);
        long secs = diff - mins * 60;
        timeText.setText(String.format("%02d:%02d", mins, secs));
    }

    private void updateTime() {
        formatTime();

        if (updateTimer != null) {
            updateTimer.cancel();
            updateTimer = null;
        }

        if (!gameActive)
            return;

        updateTimer = new Timer();
        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateTime();
            }
        }, 1000);
    }
}
