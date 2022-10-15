package com.curtcox.arboretumseed;

import static org.junit.Assert.*;

import org.junit.Test;

public class MutatorTest {

    @Test
    public void can_create() {
        assertNotNull(new Mutator<>(""));
    }

    @Test
    public void mutator_with_no_mutations_returns_value_for_singles() {
        String value = "??";
        assertEquals(value,new Mutator<>(value).singles().iterator().next());
    }

    @Test
    public void mutator_with_no_mutations_returns_empty_list() {
        String value = "boo";
        assertTrue(new Mutator<>(value).lists().iterator().next().isEmpty());
    }

}
