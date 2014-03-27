package com.sharparam.sharpmemory.models;

import com.sharparam.sharpmemory.State;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by on 2014-03-27.
 *
 * @author Sharparam
 */
public class BrickModelTest {
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
    public void testIsCleared() throws Exception {
        Assert.assertFalse("Expected downBrick to not be cleared", downBrick.isCleared());
        Assert.assertFalse("Expected upBrick to not be cleared", upBrick.isCleared());
        Assert.assertTrue("Expected clearedBrick to be cleared", clearedBrick.isCleared());
        Assert.assertFalse("Expected invalidBrick to not be cleared", invalidBrick.isCleared());
    }

    @Test
    public void testClear() throws Exception {
        BrickModel brick = new BrickModel();
        brick.clear();
        Assert.assertTrue("Expected BrickModel to clear when instructed to", brick.isCleared());
    }
}
