/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResultTest {

    @Test
    public void testSuccess() {
        Result<String, Exception> result = Result.success("OK");
        assertTrue(result.isSuccess());
        assertFalse(result.isFailure());
        assertEquals("OK", result.getValue());
    }

    @Test
    public void testFailure() {
        Exception e = new RuntimeException("Error");
        Result<String, Exception> result = Result.failure(e);
        assertTrue(result.isFailure());
        assertFalse(result.isSuccess());
        assertEquals(e, result.getError());
    }

    @Test
    public void testMap() {
        Result<Integer, Exception> success = Result.success(5);
        Result<String, Exception> mapped = success.map(Object::toString);
        assertTrue(mapped.isSuccess());
        assertEquals("5", mapped.getValue());
    }

    @Test
    public void testFlatMap() {
        Result<Integer, Exception> success = Result.success(5);
        Result<String, Exception> flatMapped = success.flatMap(v -> Result.success(v.toString()));
        assertTrue(flatMapped.isSuccess());
        assertEquals("5", flatMapped.getValue());
    }

    @Test
    public void testRecover() {
        Result<String, Exception> fail = Result.failure(new Exception());
        Result<String, Exception> recovered = fail.recover(e -> "Recovered");
        assertTrue(recovered.isSuccess());
        assertEquals("Recovered", recovered.getValue());
    }

    @Test
    public void testOrElse() {
        Result<String, Exception> fail = Result.failure(new Exception());
        assertEquals("Fallback", fail.orElse("Fallback"));
        
        Result<String, Exception> success = Result.success("OK");
        assertEquals("OK", success.orElse("Fallback"));
    }

    @Test(expected = Exception.class)
    public void testOrElseThrowOnFailure() throws Exception {
        Result<String, Exception> fail = Result.failure(new Exception());
        fail.orElseThrow();
    }

    @Test
    public void testOrElseThrowOnSuccess() throws Exception {
        Result<String, Exception> success = Result.success("OK");
        assertEquals("OK", success.orElseThrow());
    }
}
