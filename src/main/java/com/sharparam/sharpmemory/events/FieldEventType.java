package com.sharparam.sharpmemory.events;

/**
 * Available field events.
 *
 * Created on 2014-03-27.
 *
 * @author Sharparam
 */
public enum FieldEventType {
    /**
     * Two bricks were flipped.
     * This event occurs on fails as well as clears.
     */
    TRY,

    /**
     * Two bricks were flipped that were matches
     * and have now been cleared.
     */
    CLEAR,

    /**
     * Two bricks were flipped that were not matches.
     */
    FAIL,

    /**
     * All bricks on the playing field have been cleared.
     */
    ALL_BRICKS_CLEARED
}
