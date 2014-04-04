package com.sharparam.sharpmemory.models;

import com.sharparam.sharpmemory.State;
import com.sharparam.sharpmemory.tests.rules.JavaFXThreadingRule;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link com.sharparam.sharpmemory.models.BrickModel}
 *
 * Created on 2014-03-27.
 *
 * @author Sharparam
 */
@RunWith(JUnit4.class)
public class BrickModelTest /*extends JavaFXTester*/ {
    @Rule
    public JavaFXThreadingRule javaFXThreadingRule = new JavaFXThreadingRule();

    private BrickModel downBrick;
    private BrickModel upBrick;
    private BrickModel clearedBrick;
    private BrickModel invalidBrick;

    @Before
    public void setUp() {
        downBrick = new BrickModel(State.FACE_DOWN);
        upBrick = new BrickModel(State.FACE_UP);
        clearedBrick = new BrickModel(State.CLEARED);
        invalidBrick = new BrickModel(State.INVALID);
    }

    @Test
    public void testConstructor() throws Exception {
        BrickModel defaultBrick = new BrickModel();
        Assert.assertEquals("Expected default BrickModel constructor to set down state", State.FACE_DOWN, defaultBrick.getState());
        State expectedState = State.FACE_UP;
        BrickModel customBrick = new BrickModel(expectedState);
        Assert.assertEquals("Expected custom BrickModel to have supplied state", expectedState, customBrick.getState());
        Image expectedImage = new Image("/images/test.png");
        BrickModel sourceBrick = new BrickModel(expectedImage);
        Assert.assertEquals("Expected brick to use image from source", expectedImage, sourceBrick.getFaceUpImage());
    }

    @Test
    public void testGetFaceDownImage() throws Exception {
        BrickModel brick = new BrickModel();
        Assert.assertEquals("Expected face down image to be constant", BrickModel.getFaceDownImage(), brick.getFaceDownImage());
    }

    @Test
    public void testGetClearedImage() throws Exception {
        BrickModel brick = new BrickModel();
        Assert.assertEquals("Expected cleared image to be constant", BrickModel.getClearedImage(), brick.getClearedImage());
    }

    @Test
    public void testGetFaceUpImage() throws Exception {
        Image image = new Image("/images/test.png");
        BrickModel brick = new BrickModel(image);
        Assert.assertEquals("Expected face up image to be properly set", image, brick.getFaceUpImage());
    }

    @Test
    public void testGetState() throws Exception {
        Assert.assertEquals("Expected down state but got other", State.FACE_DOWN, downBrick.getState());
        Assert.assertEquals("Expected up state but got other", State.FACE_UP, upBrick.getState());
        Assert.assertEquals("Expected cleared state but got other", State.CLEARED, clearedBrick.getState());
        Assert.assertEquals("Expected invalid state but got other", State.INVALID, invalidBrick.getState());
    }

    @Test
    public void testSetState() throws Exception {
        State expectedState = State.FACE_UP;
        BrickModel brick = new BrickModel();
        State oldState = brick.getState();
        brick.setState(expectedState);
        Assert.assertNotSame("Expected state to change but didn't", oldState, brick.getState());
        Assert.assertEquals("Expected state to change to supplied state but didn't", expectedState, brick.getState());
    }

    @Test
    public void testGetImage() throws Exception {
        Image image = new Image("/images/test.png");
        BrickModel brick = new BrickModel(image);
        Assert.assertEquals("Expected face down image to be set as default", BrickModel.getFaceDownImage(), brick.getImage());
        brick.flip();
        Assert.assertEquals("Expected face up image to be set after flip", image, brick.getImage());
        brick.clear();
        Assert.assertEquals("Expected cleared image to be set after clear", BrickModel.getClearedImage(), brick.getImage());
        brick.faceUp();
        Assert.assertEquals("Expected face up image after faceUp call", image, brick.getImage());
        brick.faceDown();
        Assert.assertEquals("Expected face down image after faceDown call", BrickModel.getFaceDownImage(), brick.getImage());
    }

    @Test
    public void testGetImageView() throws Exception {
        Image image = new Image("/images/test.png");
        BrickModel brick = new BrickModel(image);
        brick.faceUp();
        ImageView brickImageView = brick.getImageView();
        Assert.assertEquals("Expected correct image on ImageView", image, brickImageView.getImage());
    }

    @Test
    public void testIsCleared() throws Exception {
        Assert.assertFalse("Expected downBrick to not be cleared", downBrick.isCleared());
        Assert.assertFalse("Expected upBrick to not be cleared", upBrick.isCleared());
        Assert.assertTrue("Expected clearedBrick to be cleared", clearedBrick.isCleared());
        Assert.assertFalse("Expected invalidBrick to not be cleared", invalidBrick.isCleared());
    }

    @Test
    public void testFaceUp() throws Exception {
        BrickModel brick = new BrickModel();
        brick.faceUp();
        Assert.assertEquals("Expected brick state to be face up after faceUp call", State.FACE_UP, brick.getState());
    }

    @Test
    public void testFaceDown() throws Exception {
        BrickModel brick = new BrickModel();
        brick.faceDown();
        Assert.assertEquals("Expected brick state to be face down after faceDown call", State.FACE_DOWN, brick.getState());
    }

    @Test
    public void testFlip() throws Exception {
        BrickModel brick = new BrickModel();
        Assert.assertEquals("Invalid default state for brick", State.FACE_DOWN, brick.getState());
        brick.flip();
        Assert.assertEquals("Expected brick state to be up after flip", State.FACE_UP, brick.getState());
        brick.flip();
        Assert.assertEquals("Expected brick state to be down after flip from up", State.FACE_DOWN, brick.getState());
        brick.setState(State.CLEARED);
        brick.flip();
        Assert.assertEquals("Expected brick state to stay if flipped from non-up/down state", State.CLEARED, brick.getState());
    }

    @Test
    public void testClear() throws Exception {
        BrickModel brick = new BrickModel();
        brick.clear();
        Assert.assertTrue("Expected BrickModel to clear when instructed to", brick.isCleared());
    }

    @Test
    public void testEquals() throws Exception {
        Image image = new Image("/images/test.png");
        BrickModel a = new BrickModel(image);
        BrickModel b = new BrickModel(image);
        Assert.assertEquals("Bricks should be equal when same image used for source", a, b);
        Image image2 = new Image("/images/test2.png");
        BrickModel c = new BrickModel(image2);
        Assert.assertNotSame("Bricks with different source image should not be equal", a, c);
    }

    @Test
    public void testUpdateImage() throws Exception {
        Image image = new Image("/images/test.png");
        BrickModel brick = new BrickModel(image);
        Image brickImage = brick.getImage();
        brick.flip();
        Assert.assertNotSame("Brick image should update after flip", brickImage, brick.getImage());
    }
}
