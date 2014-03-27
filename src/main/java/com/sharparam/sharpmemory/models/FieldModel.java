package com.sharparam.sharpmemory.models;

import com.sharparam.sharpmemory.State;

import java.util.ArrayList;

/**
 * Created by on 2014-03-27.
 *
 * @author Sharparam
 */
public class FieldModel {
    private BrickModel[] bricks;

    public FieldModel(BrickModel[] bricks) {
        this.bricks = bricks;
    }

    public FieldModel(ArrayList<BrickModel> bricks) {
        this.bricks = (BrickModel[]) bricks.toArray();
    }

    public FieldModel(int brickCount) {
        bricks = new BrickModel[brickCount];
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
}
