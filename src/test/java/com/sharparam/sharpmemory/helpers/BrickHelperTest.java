package com.sharparam.sharpmemory.helpers;

import com.sharparam.sharpmemory.tests.rules.JavaFXThreadingRule;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link com.sharparam.sharpmemory.helpers.BrickHelper}.
 *
 * Created on 2014-04-04.
 *
 * @author Sharparam
 */
@RunWith(JUnit4.class)
public class BrickHelperTest {
    @Rule
    public JavaFXThreadingRule javaFXThreadingRule = new JavaFXThreadingRule();

    private static final String TEST_IMAGE_SOURCE = "http://placehold.it/1x1";

    @Test
    public void testGetImage() throws Exception {
        Image firstImage = BrickHelper.getImage(TEST_IMAGE_SOURCE);
        Image secondImage = BrickHelper.getImage(TEST_IMAGE_SOURCE);
        Assert.assertEquals("BrickHelper should return cached image on second request", firstImage, secondImage);
        Image forced = BrickHelper.getImage(TEST_IMAGE_SOURCE, true);
        Assert.assertNotSame("BrickHelper should return new image when forced=true", firstImage, forced);
    }

    @Test
    public void testCreateImageView() throws Exception {
        Image image = BrickHelper.getImage(TEST_IMAGE_SOURCE);
        Image forced = BrickHelper.getImage(TEST_IMAGE_SOURCE, true);

        ImageView view = BrickHelper.createImageView(image);
        Assert.assertEquals("ImageView requested with image object should have that image", image, view.getImage());

        ImageView forcedView = BrickHelper.createImageView(TEST_IMAGE_SOURCE);
        Assert.assertEquals("ImageView requested with source should have latest cached image", forced, forcedView.getImage());

        Assert.assertNotSame("ImageViews with different Image objects should not be equal", view, forcedView);
    }
}
