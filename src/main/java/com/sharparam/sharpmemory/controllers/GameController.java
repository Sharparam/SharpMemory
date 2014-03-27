package com.sharparam.sharpmemory.controllers;

import com.sharparam.sharpmemory.SharpMemory;
import com.sharparam.sharpmemory.State;
import com.sharparam.sharpmemory.models.BrickModel;
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

    private ImageView[] imageViews;
    private BrickModel[] bricks;

    @FXML
    private GridPane window;

    @FXML
    private Text pointsText;

    @FXML
    private Text timeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bricks = new BrickModel[10];
        imageViews = new ImageView[bricks.length];
        for (int i = 0; i < bricks.length; i++) {
            bricks[i] = new BrickModel("http://placekitten.com/64/64");
            imageViews[i] = createImageView(BrickModel.getFaceDownImage());
            imageViews[i].setId("image" + i);
            final BrickModel brick = bricks[i];
            final ImageView imageView = imageViews[i];
            imageViews[i].setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                    points++;
                    pointsText.setText("Points: " + points);
                    brick.flip();
                    if (brick.getState() == State.FACE_DOWN)
                        imageView.setImage(BrickModel.getFaceDownImage());
                    else
                        imageView.setImage(brick.getImage());
                }
            });
            window.add(imageViews[i], i < 5 ? i : i - 5, i < 5 ? 1 : 2);
        }
    }

    private Image createImage(String source) {
        return new Image(source, IMAGE_SIZE, IMAGE_SIZE, true, true, true);
    }

    private ImageView createImageView(Image image) {
        ImageView view = new ImageView(image);
        view.setFitWidth(IMAGE_SIZE);
        view.setFitHeight(IMAGE_SIZE);
        view.setSmooth(true);
        view.setPreserveRatio(true);
        view.setCache(true);
        return view;
    }

    private ImageView createImageView(String source) {
        return createImageView(createImage(source));
    }

    @FXML
    protected void handleQuitButtonAction(ActionEvent event) {
        SharpMemory.getInstance().showMenu();
    }
}
