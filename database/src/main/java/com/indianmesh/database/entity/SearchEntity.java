/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a recent search query.
 */
@Entity(tableName = "searches")
public class SearchEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "search_id")
    public String searchId;

    @ColumnInfo(name = "query")
    public String query;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    public SearchEntity(@NonNull String searchId, String query, long timestamp) {
        this.searchId = searchId;
        this.query = query;
        this.timestamp = timestamp;
    }
}
