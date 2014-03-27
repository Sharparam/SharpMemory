package com.sharparam.sharpmemory.controllers;

import com.sharparam.sharpmemory.SharpMemory;
import com.sharparam.sharpmemory.State;
import com.sharparam.sharpmemory.models.BrickModel;
import com.sharparam.sharpmemory.models.FieldModel;
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

/**
 * Created by on 2014-03-27.
 *
 * @author Sharparam
 */
public class GameController implements Initializable {
    private static final double IMAGE_SIZE = 64.0;

    private int points = 0;

    private FieldModel field;

    private BrickModel[] bricks;

    @FXML
    private GridPane window;

    @FXML
    private Text pointsText;

    @FXML
    private Text timeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        field = new FieldModel(5);
        bricks = new BrickModel[10];
        for (int i = 0; i < bricks.length; i++) {
            bricks[i] = new BrickModel("http://placekitten.com/64/64");
            window.add(bricks[i].getImageView(), i < 5 ? i : i - 5, i < 5 ? 1 : 2);
        }
    }

    @FXML
    protected void handleQuitButtonAction(ActionEvent event) {
        SharpMemory.getInstance().showMenu();
    }
}
