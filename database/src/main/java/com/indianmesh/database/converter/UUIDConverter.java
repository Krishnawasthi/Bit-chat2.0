/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.converter;

import androidx.room.TypeConverter;
import java.util.UUID;

/**
 * TypeConverter for converting between String and java.util.UUID.
 */
public class UUIDConverter {

    /**
     * Converts a String to a UUID object.
     * @param value The UUID string.
     * @return The corresponding UUID object, or null if the input is null.
     */
    @TypeConverter
    public static UUID fromString(String value) {
        return value == null ? null : UUID.fromString(value);
    }

    /**
     * Converts a UUID object to a String.
     * @param uuid The UUID object.
     * @return The string representation, or null if the input is null.
     */
    @TypeConverter
    public static String uuidToString(UUID uuid) {
        return uuid == null ? null : uuid.toString();
    }
}
