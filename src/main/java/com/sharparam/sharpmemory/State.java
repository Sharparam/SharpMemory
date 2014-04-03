package com.sharparam.sharpmemory;

/**
 * Brick states.
 *
 * Created on 2014-03-27.
 *
 * @author Sharparam
 */
public enum State {
    /**
     * Brick is faced down, hiding the match image.
     */
    FACE_DOWN,

    /**
     * Brick is faced up, showing the match image.
     */
    FACE_UP,

    /**
     * Brick is cleared.
     */
    CLEARED,

    /**
     * Brick is invalid.
     */
    INVALID
}
