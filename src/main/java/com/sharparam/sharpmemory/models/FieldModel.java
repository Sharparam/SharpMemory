package com.sharparam.sharpmemory.models;

import com.sharparam.sharpmemory.Difficulty;
import com.sharparam.sharpmemory.State;
import com.sharparam.sharpmemory.events.FieldEventListener;
import com.sharparam.sharpmemory.events.FieldEventType;
import com.sharparam.sharpmemory.helpers.BrickHelper;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Created by on 2014-03-27.
 *
 * @author Sharparam
 */
public class FieldModel {
    private final ArrayList<FieldEventListener> fieldEventListeners = new ArrayList<FieldEventListener>();

    private BrickModel[] bricks;

    public FieldModel(BrickModel[] bricks) {
        this.bricks = bricks;
    }

    public FieldModel(ArrayList<BrickModel> bricks) {
        this.bricks = (BrickModel[]) bricks.toArray();
    }

    public FieldModel(int brickCount) {
        // brickCount is the number of UNIQUE bricks
        // this has to be doubled because each one has a dupe
        bricks = new BrickModel[brickCount * 2];
        randomizeBricks();
    }

    public void addEventListener(FieldEventListener listener) {
        fieldEventListeners.add(listener);
    }

    public void sendEvent(FieldEventType type) {
        for (FieldEventListener listener : fieldEventListeners)
            listener.handle(type);
    }

    public boolean hasBrick(int index) {
        return index >= 0 && index < bricks.length;
    }

    public BrickModel getBrick(int index) {
        return bricks[index];
    }

    public int getBrickCount() {
        return bricks.length;
    }

    public int getActiveBrickCount() {
        int count = 0;
        for (BrickModel brick : bricks)
            if (!brick.isCleared())
                count++;
        return count;
    }

    public State getBrickState(int index) {
        return hasBrick(index) ? getBrick(index).getState() : State.INVALID;
    }

    public boolean isMatch(BrickModel a, BrickModel b) {
        return a.getImage() == b.getImage();
    }

    /**
     * This removes the specified BrickModel and it's dupe version.
     * @param brick The BrickModel object to remove.
     */
    public void clearBrick(BrickModel brick) {
        for (BrickModel b : bricks)
            if (b.equals(brick))
                b.clear();
    }

    public void clearIfMatch(BrickModel a, BrickModel b) {
        sendEvent(FieldEventType.TRY);

        if (!isMatch(a, b)) {
            sendEvent(FieldEventType.FAIL);
            return;
        }

        clearBrick(a);
        clearBrick(b); // Just to be on the safe side, but this is probably redundant.
        sendEvent(FieldEventType.CLEAR);
    }

    public int getFacedUpCount() {
        int count = 0;
        for (BrickModel brick : bricks)
            if (brick.getState() == State.FACE_UP)
                count++;
        return count;
    }

    public int getClearedCount() {
        int count = 0;
        for (BrickModel brick : bricks)
            if (brick.isCleared())
                count++;
        return count;
    }

    public void flipBrick(BrickModel brick) {
        if (brick.isCleared() || brick.getState() == State.FACE_UP || brick.getState() == State.INVALID)
            return;
        brick.flip();
        checkBricks();
    }

    public void flipBrick(int index) {
        if (hasBrick(index))
            flipBrick(getBrick(index));
    }

    public void checkBricks() {
        if (getFacedUpCount() < 2)
            return;
        ArrayList<BrickModel> facedUp = new ArrayList<BrickModel>();
        for (BrickModel brick : bricks)
            if (brick.getState() == State.FACE_UP)
                facedUp.add(brick);
        if (facedUp.size() > 2) {
            resetBrickStates();
            return;
        }

        BrickModel a = facedUp.get(0);
        BrickModel b = facedUp.get(1);
        clearIfMatch(a, b);
        resetBrickStates();
        if (getClearedCount() == getBrickCount())
            sendEvent(FieldEventType.ALL_BRICKS_CLEARED);
    }

    public void resetBrickStates() {
        for (BrickModel brick : bricks)
            if (!brick.isCleared())
                brick.faceDown();
    }

    private void randomizeBricks() {
        randomizeBricks(Difficulty.EASY);
    }

    private void randomizeBricks(Difficulty diff) {
        Image[] images = new Image[] {
                BrickHelper.getImage("/images/easy/blue.png"),
                BrickHelper.getImage("/images/easy/green.png"),
                BrickHelper.getImage("/images/easy/pink.png"),
                BrickHelper.getImage("/images/easy/red.png"),
                BrickHelper.getImage("/images/easy/yellow.png")
        };

        for (int i = 0; i < bricks.length; i++)
            bricks[i] = new BrickModel(images[i < 5 ? i : i - 5]);
    }
}
