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

@Module
@InstallIn(SingletonComponent.class)
public class RoutingModule {

    @Provides
    @Singleton
    public RoutingProtocol provideRoutingProtocol() {
        return new EpidemicRoutingProtocol();
    }

    @Provides
    @Singleton
    public SyncManager provideSyncManager() {
        return new SyncManager();
    }
}
