package com.sharparam.sharpmemory.controllers;

import com.sharparam.sharpmemory.SharpMemory;
import com.sharparam.sharpmemory.events.FieldEventListener;
import com.sharparam.sharpmemory.events.FieldEventType;
import com.sharparam.sharpmemory.models.BrickModel;
import com.sharparam.sharpmemory.models.FieldModel;
import javafx.application.Platform;
import javafx.event.EventHandler;
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
 * Controller for the game view.
 *
 * Created on 2014-03-27.
 *
 * @author Sharparam
 */
public class GameController implements Initializable {
    /**
     * Current amount of points for this game session.
     */
    private int points = 0;

    /**
     * Current amount of tries for this game session.
     */
    private int tries = 0;

    /**
     * Is the game active?
     */
    private boolean gameActive = false;

    /**
     * Time when the user started the game.
     */
    private long startTime;

    /**
     * Timer to update the elapsed time display.
     */
    private Timer updateTimer;

    /**
     * The brick field.
     */
    private FieldModel field;

    /**
     * The window that this controller is associated with.
     */
    @FXML
    private GridPane window;

    /**
     * Text object for displaying points.
     */
    @FXML
    private Text pointsText;

    /**
     * Text object for displaying amount of tries.
     */
    @FXML
    private Text triesText;

    /**
     * Text object for displaying elapsed time.
     */
    @FXML
    private Text timeText;

    /**
     * Text object for displaying "Game Over!"
     */
    @FXML
    private Text gameOverText;

    /**
     * Initializer method.
     * @param url FXML URL.
     * @param resourceBundle Resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameActive = true;

        // Initialize the start time.
        startTime = System.currentTimeMillis();

        // Create our field.
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

                        // Workaround to prevent application hang on shutdown.
                        if (updateTimer != null)
                            updateTimer.cancel();
                        updateTimer = null;
                        break;
                }

                // Update text objects.
                pointsText.setText("Points: " + points);
                triesText.setText("Tries: " + tries);
            }
        });

        // Add the ImageView for each brick.
        int count = field.getBrickCount();
        for (int i = 0; i < count; i++) {
            final BrickModel brick = field.getBrick(i);
            final ImageView imageView = brick.getImageView();

            // Set a mouse click listener to flip bricks via field,
            // which will run all the necessary checks.
            imageView.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                    if (field.isTryInProgress())
                        return;
                    field.flipBrick(brick);
                }
            });

            // Fancy formulas to distribute the bricks in rows of 5.
            int column = i % 5;
            int row = (int) Math.floor(i / 5) + 1;
            window.add(imageView, column, row);
        }

        // We need to explicitly cancel the game on window close or the application will stay open in
        // the background, stuck because of the shitty way Java timers are designed.
        // SERIOUSLY JAVA?
        // F**king language...
        window.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                cancelGame();
            }
        });

        // Initialize the time display by calling the update method.
        updateTime();
    }

    /**
     * Cancels the current game, disposing all game resources.
     */
    public void cancelGame() {
        gameActive = false;
        if (updateTimer != null)
            updateTimer.cancel();
        updateTimer = null;
    }

    /**
     * Handler method for the menu button.
     */
    @FXML
    protected void handleMenuButtonAction() {
        cancelGame();
        SharpMemory.getInstance().showMenu();
    }

    /**
     * Handler method for the quit button.
     */
    @FXML
    protected void handleQuitButtonAction() {
        cancelGame();
        Platform.exit();
    }

    /**
     * Formats the time.
     */
    private void formatTime() {
        formatTime(startTime);
    }

    /**
     * Formats the time based on the difference of current time - specified start time.
     * @param startTime Start time to measure from.
     */
    private void formatTime(long startTime) {
        long diff = (System.currentTimeMillis() - startTime) / 1000;
        long mins = (long) Math.floor((double) diff / 60.0);
        long secs = diff - mins * 60;
        timeText.setText(String.format("%02d:%02d", mins, secs));
    }

    /**
     * Updates the time display.
     */
    private void updateTime() {
        formatTime();

        // Once again we need this because of the stupid way they designed
        // the timer class in Java.
        if (updateTimer != null) {
            updateTimer.cancel();
            updateTimer = null;
        }

        if (!gameActive)
            return;

        updateTimer = new Timer();

        // Schedule the next update 1000ms (1sec) from now.
        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateTime();
            }
        }, 1000);
    }
}
