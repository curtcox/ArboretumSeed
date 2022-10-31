package com.curtcox.arboretumseed;

import static java.util.Collections.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

public class MutatorTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);
    @Test
    public void can_create() {
        assertNotNull(new Mutator<>("",Mutations.of("",null)));
    }

    @Test
    public void mutator_with_no_mutations_returns_equivalent_value_for_singles() {
        String value = "??";
        assertEquals(value,new Mutator<>(value,Mutations.of(value,null)).singles().iterator().next());
    }

    @Test
    public void mutator_does_not_return_same_object_every_time_for_singles() {
        Set set = new HashSet();
        Mutator<Set> mutator = Mutator.of(set,(x)->new HashSet(x))
                .with(Set::add,new Object());
        Iterator<Set> sets = mutator.singles().iterator();
        assertNotSame(sets.next(),sets.next());
    }

    @Test
    public void mutator_does_not_return_same_object_every_time_for_lists() {
        Set set = new HashSet();
        Mutator<Set> mutator = Mutator.of(set,(x)->new HashSet(x))
                .with(Set::add,new Object());
        Iterator<List<Set>> lists = mutator.lists().iterator();
        assertNotSame(lists.next(),lists.next());
    }

    @Test
    public void mutator_with_1_mutation_returns_original_and_mutation_from_singles() {
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
    public void mutator_with_1_mutation_returns_original_and_mutation_in_lists() {
        Set set = new HashSet<>();
        Object value = new Object();
        Mutator<Set> mutator = Mutator.of(set,(x)->new HashSet(x))
                .with(Set::add,value);
        Iterator<List<Set>> lists = mutator.lists().iterator();
        assertEquals(EMPTY_LIST,lists.next());
        assertEquals(singletonList(set),lists.next());
        assertEquals(singletonList(singleton(value)),lists.next());
        assertFalse(lists.hasNext());
    }

    @Test
    public void mutator_with_no_mutations_returns_empty_list() {
        String value = "boo";
        assertTrue(new Mutator<String>(value,Mutations.of(value,null)).lists().iterator().next().isEmpty());
    }

}
