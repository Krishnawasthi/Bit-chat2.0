/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

/**
 * Represents the priority level of a message.
 */
public enum MessagePriority {
    /** Low priority. */
    LOW(0),
    /** Normal priority. */
    NORMAL(1),
    /** High priority. */
    HIGH(2),
    /** Emergency priority. */
    EMERGENCY(3);

    private final int value;

    /**
     * Constructs a MessagePriority.
     *
     * @param value The integer value of the priority.
     */
    MessagePriority(int value) {
        this.value = value;
    }

    /**
     * Gets the integer value of the priority.
     *
     * @return The priority value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the MessagePriority corresponding to the given value.
     *
     * @param value The priority value.
     * @return The corresponding MessagePriority, or NORMAL if not found.
     */
    public static MessagePriority fromValue(int value) {
        for (MessagePriority priority : values()) {
            if (priority.getValue() == value) {
                return priority;
            }
        }
        return NORMAL;
    }
}
