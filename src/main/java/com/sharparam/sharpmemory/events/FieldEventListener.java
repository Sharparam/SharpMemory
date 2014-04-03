package com.sharparam.sharpmemory.events;

/**
 * Event interface for FieldModel events.
 *
 * Created on 2014-03-27.
 *
 * @author Sharparam
 */
public interface FieldEventListener {
    /**
     * Handles an event from FieldModel.
     * @param args The type of event.
     */
    void handle(FieldEventType args);
}
