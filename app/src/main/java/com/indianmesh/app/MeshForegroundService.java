/* Copyright (c) 2026 Indian Mesh. All rights reserved. */
package com.indianmesh.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import com.indianmesh.network.MeshNetworkManager;

@AndroidEntryPoint
public class MeshForegroundService extends Service {

    @Inject
    MeshNetworkManager meshNetworkManager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Build and display foreground notification
        meshNetworkManager.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        meshNetworkManager.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
