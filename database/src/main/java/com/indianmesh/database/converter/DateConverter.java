/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.converter;

import androidx.room.TypeConverter;
import java.util.Date;

/**
 * TypeConverter for converting between Long and java.util.Date.
 */
public class DateConverter {

    /**
     * Converts a Long timestamp to a Date object.
     * @param value The timestamp in milliseconds.
     * @return The corresponding Date object, or null if the input is null.
     */
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    /**
     * Converts a Date object to a Long timestamp.
     * @param date The Date object.
     * @return The timestamp in milliseconds, or null if the input is null.
     */
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
