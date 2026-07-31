/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.converter;

import androidx.room.TypeConverter;
import com.indianmesh.domain.model.DeliveryStatus;

/**
 * TypeConverter for converting between String and DeliveryStatus enum.
 */
public class DeliveryStatusConverter {

    /**
     * Converts a String to a DeliveryStatus enum.
     * @param value The string value.
     * @return The corresponding DeliveryStatus enum, or null if the input is null.
     */
    @TypeConverter
    public static DeliveryStatus fromString(String value) {
        return value == null ? null : DeliveryStatus.valueOf(value);
    }

    /**
     * Converts a DeliveryStatus enum to a String.
     * @param status The DeliveryStatus enum.
     * @return The string representation, or null if the input is null.
     */
    @TypeConverter
    public static String statusToString(DeliveryStatus status) {
        return status == null ? null : status.name();
    }
}
