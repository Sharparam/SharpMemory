package com.sharparam.sharpmemory.helpers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

/**
 * Created by on 2014-03-27.
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

    public static Image getImage(String source) {
        if (!IMAGES.containsKey(source))
            IMAGES.put(source, BrickHelper.createImage(source));
        return IMAGES.get(source);
    }

    public static ImageView createImageView(Image image) {
        ImageView view = new ImageView(image);
        view.setFitWidth(IMAGE_SIZE);
        view.setFitHeight(IMAGE_SIZE);
        view.setSmooth(true);
        view.setPreserveRatio(true);
        view.setCache(true);
        return view;
    }

    public static ImageView createImageView(String source) {
        return createImageView(getImage(source));
    }

    private static Image createImage(String source) {
        return new Image(source, IMAGE_SIZE, IMAGE_SIZE, true, true, true);
    }
}
