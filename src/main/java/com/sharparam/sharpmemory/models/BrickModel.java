package com.sharparam.sharpmemory.models;

import com.sharparam.sharpmemory.State;
import com.sharparam.sharpmemory.helpers.BrickHelper;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Created by on 2014-03-27.
 *
 * @author Sharparam
 */
public class BrickModel {
    private static final Image FACE_DOWN_IMAGE = BrickHelper.getImage("http://placehold.it/64x64");

    private final Image faceUpImage;

    private final ImageView imageView;

    private State state;

    private Image image;

    public BrickModel(State state, Image image) {
        this.state = state;
        faceUpImage = image;
        this.image = image;
        imageView = BrickHelper.createImageView(FACE_DOWN_IMAGE);

        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                faceUp();
            }
        });
    }

    public BrickModel(State state, String imageSource) {
        this(state, BrickHelper.getImage(imageSource));
    }

    public BrickModel(State state) {
        this(state, (Image) null);
    }

    public BrickModel(Image image) {
        this(State.FACE_DOWN, image);
    }

    public BrickModel() {
        this(State.FACE_DOWN);
    }

    public BrickModel(String imageSource) {
        this(BrickHelper.getImage(imageSource));
    }

    public static Image getFaceDownImage() {
        return FACE_DOWN_IMAGE;
    }

    public Image getFaceUpImage() {
        return faceUpImage;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        updateImage();
    }

    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isCleared() {
        return state == State.CLEARED;
    }

    public void faceUp() {
        setState(State.FACE_UP);
    }

    public void faceDown() {
        setState(State.FACE_DOWN);
    }

    public void flip() {
        if (getState() == State.FACE_DOWN)
            setState(State.FACE_UP);
        else if (getState() == State.FACE_UP)
            setState(State.FACE_DOWN);
    }

    public void clear() {
        setState(State.CLEARED);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BrickModel))
            return false;
        BrickModel other = (BrickModel) obj;
        return getImage() == other.getImage();
    }

    private void updateImage() {
        image = getState() == State.FACE_UP ? getFaceUpImage() : getFaceDownImage();
        imageView.setImage(image);
    }
}
