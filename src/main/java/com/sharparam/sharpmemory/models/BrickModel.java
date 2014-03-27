package com.sharparam.sharpmemory.models;

import com.sharparam.sharpmemory.State;
import com.sharparam.sharpmemory.helpers.BrickHelper;
import javafx.scene.image.Image;

/**
 * Created by on 2014-03-27.
 *
 * @author Sharparam
 */
public class BrickModel {
    private static final Image FACE_DOWN_IMAGE = BrickHelper.createImage("http://placehold.it/64x64");

    private State state;

    private Image image;

    public BrickModel() {
        this(State.FACE_DOWN);
    }

    public BrickModel(State state) {
        this.state = state;
    }

    public BrickModel(Image image) {
        this();
        this.image = image;
    }

    public BrickModel(String imageSource) {
        this(BrickHelper.createImage(imageSource));
    }

    public BrickModel(State state, Image image) {
        this(state);
        this.image = image;
    }

    public BrickModel(State state, String imageSource) {
        this(state, BrickHelper.createImage(imageSource));
    }

    public static Image getFaceDownImage() {
        return FACE_DOWN_IMAGE;
    }

    public State getState() {
        return state;
    }

    public Image getImage() {
        return image;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isCleared() {
        return state == State.CLEARED;
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
}
