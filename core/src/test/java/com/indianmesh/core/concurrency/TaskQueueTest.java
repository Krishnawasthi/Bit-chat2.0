/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.concurrency;

import org.junit.Test;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.*;

public class TaskQueueTest {

    @Test
    public void testEnqueueDequeueOrdering() throws InterruptedException {
        TaskQueue queue = new TaskQueue(10);
        
        AtomicInteger runOrder = new AtomicInteger(0);
        
        queue.enqueue(() -> runOrder.set(1), 1);
        queue.enqueue(() -> runOrder.set(2), 10);
        queue.enqueue(() -> runOrder.set(3), 5);
        
        queue.dequeue().run(); // priority 10
        assertEquals(2, runOrder.get());
        
        queue.dequeue().run(); // priority 5
        assertEquals(3, runOrder.get());
        
        queue.dequeue().run(); // priority 1
        assertEquals(1, runOrder.get());
    }

    @Test
    public void testCapacity() throws InterruptedException {
        TaskQueue queue = new TaskQueue(2);
        queue.enqueue(() -> {}, 1);
        queue.enqueue(() -> {}, 1);
        assertEquals(2, queue.size());
    }

    @Test
    public void testClear() throws InterruptedException {
        TaskQueue queue = new TaskQueue(5);
        queue.enqueue(() -> {}, 1);
        assertFalse(queue.isEmpty());
        queue.clear();
        assertTrue(queue.isEmpty());
    }
}
