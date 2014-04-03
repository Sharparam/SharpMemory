package com.sharparam.sharpmemory.models;

import com.sharparam.sharpmemory.State;
import com.sharparam.sharpmemory.helpers.BrickHelper;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Model for a memory brick.
 *
 * Created on 2014-03-27.
 *
 * @author Sharparam
 */
public class BrickModel {
    /**
     * Image used when brick is faced down.
     */
    private static final Image FACE_DOWN_IMAGE = BrickHelper.getImage("http://placehold.it/64x64");

    /**
     * Image used when brick is cleared.
     */
    private static final Image CLEARED_IMAGE = BrickHelper.getImage("/images/transparent.png");

    /**
     * Image used when brick is faced up.
     */
    private final Image faceUpImage;

    /**
     * ImageView object used to render the image in JavaFX.
     */
    private final ImageView imageView;

    /**
     * Current brick state.
     */
    private State state;

    /**
     * Current image being displayed.
     */
    private Image image;

    /**
     * Initializes a new instance of this BrickModel with the specified state and image.
     * @param state State to initialize with.
     * @param image Image to iniitalize with.
     */
    public BrickModel(State state, Image image) {
        this.state = state;
        faceUpImage = image;
        this.image = image;
        imageView = BrickHelper.createImageView(FACE_DOWN_IMAGE);
    }

    /**
     * Initializes a new instance of this BrickModel with the specified state and image source.
     * @param state State to initialize with.
     * @param imageSource Source to load image from.
     */
    public BrickModel(State state, String imageSource) {
        this(state, BrickHelper.getImage(imageSource));
    }

    /**
     * Initializes a new instance of this BrickModel with the specified state,
     * image initialized to null.
     * @param state State to initialize with.
     */
    public BrickModel(State state) {
        this(state, (Image) null);
    }

    /**
     * Initializes a new instance of this BrickModel with the specified image,
     * state initialized to FACE_DOWN.
     * @param image Image to iniitalize with.
     */
    public BrickModel(Image image) {
        this(State.FACE_DOWN, image);
    }

    /**
     * Initializes a new instance of this BrickModel with default values.
     * State is initialized to FACE_DOWN and image to null.
     */
    public BrickModel() {
        this(State.FACE_DOWN);
    }

    /**
     * Initializes a new instance of this BrickModel with image from the specified source,
     * state initialized to FACE_DOWN.
     * @param imageSource Source to load image from.
     */
    public BrickModel(String imageSource) {
        this(BrickHelper.getImage(imageSource));
    }

    /**
     * Gets the image used when brick is faced down.
     * @return Image object for face down state.
     */
    public static Image getFaceDownImage() {
        return FACE_DOWN_IMAGE;
    }

    /**
     * Gets the image used when brick is faced up.
     * @return Image object for face up state.
     */
    public Image getFaceUpImage() {
        return faceUpImage;
    }

    /**
     * Gets the current state of this brick.
     * @return Brick state.
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state of this brick.
     * @param state State to set to.
     */
    public void setState(State state) {
        this.state = state;
        updateImage();
    }

    /**
     * Gets the current image of this brick.
     * @return Image object.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Gets the image view associated with this brick.
     * @return ImageView object.
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Gets whether this brick has been cleared.
     * @return True if cleared, false otherwise.
     */
    public boolean isCleared() {
        return state == State.CLEARED;
    }

    /**
     * Faces this brick up.
     */
    public void faceUp() {
        setState(State.FACE_UP);
    }

    /**
     * Faces this brick down.
     */
    public void faceDown() {
        setState(State.FACE_DOWN);
    }

    /**
     * Faces this brick up if it's down, or down if it's up.
     * If brick is not currently faced down or up, this method does nothing.
     */
    public void flip() {
        if (getState() == State.FACE_DOWN)
            setState(State.FACE_UP);
        else if (getState() == State.FACE_UP)
            setState(State.FACE_DOWN);
    }

    /**
     * Clears this brick.
     */
    public void clear() {
        setState(State.CLEARED);
        updateImage();
    }

    /**
     * Checks if this BrickModel is equal to another object.
     * @param obj Object to test against.
     * @return True if both objects are BrickModel and share the same image.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BrickModel))
            return false;
        BrickModel other = (BrickModel) obj;
        return getImage() == other.getImage();
    }

    /**
     * Updates the current image and associated ImageView on this brick based on state.
     */
    private void updateImage() {
        if (isCleared())
            image = CLEARED_IMAGE;
        else if (getState() == State.FACE_UP)
            image = faceUpImage;
        else
            image = FACE_DOWN_IMAGE;

        imageView.setImage(image);
    }
}
