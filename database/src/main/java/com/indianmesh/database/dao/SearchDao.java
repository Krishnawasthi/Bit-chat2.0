/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.SearchEntity;

import java.util.List;

/**
 * Data Access Object for the Search entity.
 * Provides methods for performing CRUD operations on the searches table.
 */
@Dao
public interface SearchDao {

    /**
     * Inserts a new search record or replaces an existing one.
     *
     * @param search The search entity to insert.
     * @return The row ID of the newly inserted search.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(SearchEntity search);

    /**
     * Updates an existing search record.
     *
     * @param search The search entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(SearchEntity search);

    /**
     * Deletes a search record.
     *
     * @param search The search entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(SearchEntity search);

    /**
     * Retrieves a search record by its ID.
     *
     * @param searchId The ID of the search record.
     * @return The search entity, or null if not found.
     */
    @Query("SELECT * FROM searches WHERE search_id = :searchId LIMIT 1")
    SearchEntity getSearchById(String searchId);

    /**
     * Retrieves all search records ordered by timestamp descending.
     *
     * @return A list of all search entities.
     */
    @Query("SELECT * FROM searches ORDER BY timestamp DESC")
    List<SearchEntity> getAllSearches();

    /**
     * Clears all search history.
     */
    @Query("DELETE FROM searches")
    void clearSearchHistory();
}
