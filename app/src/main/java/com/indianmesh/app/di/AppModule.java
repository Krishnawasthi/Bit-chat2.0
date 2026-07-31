/* Copyright (c) 2026 Indian Mesh. All rights reserved. */
package com.indianmesh.app.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;
import com.indianmesh.core.config.MeshConfig;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public Context provideApplicationContext(@ApplicationContext Context context) {
        return context;
    }

    @Provides
    @Singleton
    public MeshConfig provideMeshConfig() {
        return new MeshConfig.Builder().build();
    }
}
