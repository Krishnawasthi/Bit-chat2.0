/* Copyright (c) 2026 Indian Mesh. All rights reserved. */
package com.indianmesh.app.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;
import com.indianmesh.database.MeshDatabase;
import com.indianmesh.database.DatabaseProvider;
import com.indianmesh.database.dao.UserDao;
import com.indianmesh.database.dao.MessageDao;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public MeshDatabase provideMeshDatabase(@ApplicationContext Context context) {
        // Use an empty or mock passphrase for initialization here; real implementation would fetch securely
        return DatabaseProvider.getInstance(context, new char[0]);
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(MeshDatabase db) {
        return db.userDao();
    }

    @Provides
    @Singleton
    public MessageDao provideMessageDao(MeshDatabase db) {
        return db.messageDao();
    }
}
