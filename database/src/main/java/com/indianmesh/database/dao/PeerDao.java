/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.PeerEntity;

import java.util.List;

/**
 * Data Access Object for the Peer entity.
 * Provides methods for performing CRUD operations on the peers table.
 */
@Dao
public interface PeerDao {

    /**
     * Inserts a new peer or replaces an existing one.
     *
     * @param peer The peer entity to insert.
     * @return The row ID of the newly inserted peer.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(PeerEntity peer);

    /**
     * Updates an existing peer.
     *
     * @param peer The peer entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(PeerEntity peer);

    /**
     * Deletes a peer.
     *
     * @param peer The peer entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(PeerEntity peer);

    /**
     * Retrieves a peer by its ID.
     *
     * @param peerId The ID of the peer.
     * @return The peer entity, or null if not found.
     */
    @Query("SELECT * FROM peers WHERE peer_id = :peerId LIMIT 1")
    PeerEntity getPeerById(String peerId);

    /**
     * Retrieves all peers.
     *
     * @return A list of all peer entities.
     */
    @Query("SELECT * FROM peers")
    List<PeerEntity> getAllPeers();
}
