package com.curtcox.arboretumseed;

import org.junit.Test;

import static org.junit.Assert.*;

public class MutationTest {

    @Test
    public void can_create() {
        assertNotNull(new Mutation<>((type, value) -> {}, ""));
    }

    @Test
    public void equals() {

        Setter setter = (type, value) -> {};
        assertEquals(
            new Mutation<>(setter, ""),
            new Mutation<>(setter, "")
        );
    }


    @Test
    public void not_equals() {
        Setter setter = (type, value) -> {};
        assertNotEquals(
                new Mutation<>(setter, "a"),
                new Mutation<>(setter, "b")
        );
    }

}
