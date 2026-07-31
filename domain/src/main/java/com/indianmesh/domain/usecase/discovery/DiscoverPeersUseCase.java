/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.discovery;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.model.Peer;
import com.indianmesh.domain.repository.PeerRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case for discovering nearby peers.
 */
public class DiscoverPeersUseCase {

    private final PeerRepository peerRepository;

    /**
     * Constructs a DiscoverPeersUseCase.
     *
     * @param peerRepository the peer repository
     */
    public DiscoverPeersUseCase(PeerRepository peerRepository) {
        this.peerRepository = peerRepository;
    }

    /**
     * Executes the use case to discover nearby peers.
     *
     * @return a CompletableFuture containing the result with a list of nearby peers or an exception
     */
    public CompletableFuture<Result<List<Peer>, Exception>> execute() {
        return peerRepository.getNearbyPeers()
                .thenApply(Result::success)
                .exceptionally(ex -> Result.failure(new Exception(ex)));
    }
}
