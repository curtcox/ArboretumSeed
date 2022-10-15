package com.curtcox.arboretumseed;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class MutatorTest {

    @Test
    public void can_create() {
        assertNotNull(new Mutator<>("",null));
    }

    @Test
    public void mutator_with_no_mutations_returns_equivalent_value_for_singles() {
        String value = "??";
        assertEquals(value,new Mutator<>(value,null).singles().iterator().next());
    }

    @Test
    public void mutator_does_not_return_same_object_every_time() {
        Set set = new HashSet();
        Mutator<Set> mutator = Mutator.of(set,(x)->new HashSet(x))
                .with(Set::add,new Object());
        Iterator<Set> sets = mutator.singles().iterator();
        assertNotSame(sets.next(),sets.next());
    }

    @Test
    public void mutator_with_1_mutation_returns_original_and_mutation() {
        Set set = new HashSet<>();
        Object value = new Object();
        Mutator<Set> mutator = Mutator.of(set,(x)->new HashSet(x))
                .with(Set::add,value);
        Iterator<Set> sets = mutator.singles().iterator();
        assertEquals(set,sets.next());
        set.add(value);
        assertEquals(set,sets.next());
    }

    @Test
    public void mutator_with_no_mutations_returns_empty_list() {
        String value = "boo";
        assertTrue(new Mutator<>(value,null).lists().iterator().next().isEmpty());
    }

}
