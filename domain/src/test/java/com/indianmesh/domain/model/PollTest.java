/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class PollTest {

    @Test
    public void testTotalVotes() {
        PollOption opt1 = new PollOption("1", "p1", "Yes", 5);
        PollOption opt2 = new PollOption("2", "p1", "No", 3);
        
        Poll poll = new Poll.Builder()
                .options(Arrays.asList(opt1, opt2))
                .build();
                
        assertEquals(8, poll.totalVotes());
    }

    @Test
    public void testIsClosed() {
        long now = System.currentTimeMillis();
        Poll openPoll = new Poll.Builder().closesAt(now + 10000).build();
        assertFalse(openPoll.isClosed());
        
        Poll closedPoll = new Poll.Builder().closesAt(now - 10000).build();
        assertTrue(closedPoll.isClosed());
    }
}
