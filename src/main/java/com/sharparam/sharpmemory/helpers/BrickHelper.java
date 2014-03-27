package com.sharparam.sharpmemory.helpers;

import javafx.scene.image.Image;

/**
 * Created by on 2014-03-27.
 *
 * @author Sharparam
 */
public final class BrickHelper {
    public static final double IMAGE_SIZE = 64.0;

    public static Image createImage(String source) {
        return new Image(source, IMAGE_SIZE, IMAGE_SIZE, true, true, true);
    }
}
