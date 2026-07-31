/* Copyright (c) 2026 Indian Mesh. All rights reserved. */
package com.indianmesh.app.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;
import com.indianmesh.routing.protocol.RoutingProtocol;
import com.indianmesh.routing.protocol.EpidemicRoutingProtocol;
import com.indianmesh.routing.sync.SyncManager;

import androidx.annotation.NonNull;
import com.indianmesh.routing.protocol.RoutingTableManager;
import com.indianmesh.routing.sync.BloomFilterSync;

@Module
@InstallIn(SingletonComponent.class)
public class RoutingModule {

    @Provides
    @Singleton
    public RoutingProtocol provideRoutingProtocol() {
        RoutingTableManager rtm = new RoutingTableManager();
        EpidemicRoutingProtocol.DestinationExtractor extractor = new EpidemicRoutingProtocol.DestinationExtractor() {
            @NonNull
            @Override
            public String extract(@NonNull byte[] payload) {
                return "dummy-dest";
            }
        };
        EpidemicRoutingProtocol.MessageForwarder forwarder = new EpidemicRoutingProtocol.MessageForwarder() {
            @Override
            public void forward(@NonNull byte[] payload, @NonNull String peerId) {}
            @Override
            public void broadcast(@NonNull byte[] payload) {}
            @Override
            public void deliverToApplication(@NonNull byte[] payload) {}
        };
        return new EpidemicRoutingProtocol("local-peer-id", rtm, extractor, forwarder);
    }

    @Provides
    @Singleton
    public SyncManager provideSyncManager() {
        return new SyncManager(new BloomFilterSync());
    }
}
