/* Copyright (c) 2026 Indian Mesh. All rights reserved. */
package com.indianmesh.app;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MeshApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        // Setup initial configuration and start mesh foreground service when appropriate
    }
}
