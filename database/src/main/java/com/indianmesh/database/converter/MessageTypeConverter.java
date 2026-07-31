/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.converter;

import androidx.room.TypeConverter;
import com.indianmesh.domain.model.MessageType;

/**
 * TypeConverter for converting between String and MessageType enum.
 */
public class MessageTypeConverter {

    /**
     * Converts a String to a MessageType enum.
     * @param value The string value.
     * @return The corresponding MessageType enum, or null if the input is null.
     */
    @TypeConverter
    public static MessageType fromString(String value) {
        return value == null ? null : MessageType.valueOf(value);
    }

    /**
     * Converts a MessageType enum to a String.
     * @param type The MessageType enum.
     * @return The string representation, or null if the input is null.
     */
    @TypeConverter
    public static String typeToString(MessageType type) {
        return type == null ? null : type.name();
    }
}
