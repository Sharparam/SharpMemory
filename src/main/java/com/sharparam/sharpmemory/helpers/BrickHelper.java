package com.sharparam.sharpmemory.helpers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

/**
 * Contains various helper methods related to bricks.
 *
 * Created on 2014-03-27.
 *
 * @author Sharparam
 */
public final class BrickHelper {
    /**
     * (Default) image size to use when creating image objects.
     */
    public static final double IMAGE_SIZE = 64.0;

    /**
     * HashMap of image sources along with their image object.
     * This works as an image cache to reduce web requests and speed up the application.
     */
    private static final HashMap<String, Image> IMAGES = new HashMap<String, Image>();

    /**
     * Gets an image from the specified source.
     * Will get image from cache if it has been previously loaded
     * and forceNew param is false.
     * @param source Source to load image from.
     * @param forceNew Forces the image to be regenerated from source instead of fetched from cache.
     * @return Image object created from the specified source.
     */
    public static Image getImage(String source, boolean forceNew) {
        if (forceNew && IMAGES.containsKey(source))
            IMAGES.remove(source);
        if (!IMAGES.containsKey(source))
            IMAGES.put(source, BrickHelper.createImage(source));
        return IMAGES.get(source);
    }

    /**
     * Gets an image object from the specified source.
     * If the image has been previously loaded it will be fetched from cache.
     * @param source Source to load image from.
     * @return Image object created from the specified source.
     */
    public static Image getImage(String source) {
        return getImage(source, false);
    }

    /**
     * Creates an ImageView object displaying the specified Image.
     * @param image Image to display.
     * @return ImageView object.
     */
    public static ImageView createImageView(Image image) {
        ImageView view = new ImageView(image);
        view.setFitWidth(IMAGE_SIZE);
        view.setFitHeight(IMAGE_SIZE);
        view.setSmooth(true);
        view.setPreserveRatio(true);
        view.setCache(true);
        return view;
    }

    /**
     * Creates an ImageView with an image from the specified source.
     * @param source Source to load image from.
     * @return ImageView object.
     */
    public static ImageView createImageView(String source) {
        return createImageView(getImage(source));
    }

    /**
     * Creates an image from the specified source.
     * @param source Source to create image from.
     * @return Image object created from the specified source.
     */
    private static Image createImage(String source) {
        return new Image(source, IMAGE_SIZE, IMAGE_SIZE, true, true, true);
    }
}
