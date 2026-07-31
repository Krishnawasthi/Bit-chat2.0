/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.config;

import org.junit.Test;
import static org.junit.Assert.*;

public class MeshConfigTest {

    @Test
    public void testDefaults() {
        MeshConfig config = new MeshConfig.Builder().build();
        assertEquals(7, config.maxTtl);
        assertEquals(7, config.maxHopCount);
        assertEquals(72 * 60 * 60 * 1000L, config.messageExpiryMillis);
        assertFalse(config.debugMode);
    }

    @Test
    public void testBuilderOverrides() {
        MeshConfig config = new MeshConfig.Builder()
                .setMaxTtl(5)
                .setDebugMode(true)
                .build();
        
        assertEquals(5, config.maxTtl);
        assertTrue(config.debugMode);
    }
}
