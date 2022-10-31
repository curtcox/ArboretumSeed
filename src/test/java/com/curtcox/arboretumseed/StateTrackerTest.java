package com.curtcox.arboretumseed;

import org.junit.Test;

import java.util.*;

import static java.util.Collections.*;
import static org.junit.Assert.*;

public class StateTrackerTest {

    @Test
    public void can_create() {
        assertNotNull(StateTracker.of(EMPTY_LIST));
    }

    @Test
    public void next_is_null_when_no_choices() {
        assertNull(StateTracker.of(EMPTY_LIST).next());
    }

    @Test
    public void next_returns_1_valid_mutation_for_1x1_field_value() {
        MutationChoices<Leaf,String> choices = MutationChoices.fromValues(Leaf::setColor, "red");
        StateTracker tracker = StateTracker.of(singletonList(choices));
        Mutation mutation = tracker.next();
        assertNotNull(mutation);
        Leaf leaf = new Leaf();
        mutation.mutate(leaf);
        assertEquals("red",leaf.getColor());
        assertNull(tracker.next());
    }

    @Test
    public void next_returns_2_valid_mutations_for_1x2_field_value() {
        MutationChoices<Leaf,String> choices = MutationChoices.fromValues(Leaf::setColor, "blue","green");
        StateTracker tracker = StateTracker.of(singletonList(choices));
        Leaf leaf = new Leaf();
        tracker.next().mutate(leaf);
        assertEquals("blue",leaf.getColor());
        tracker.next().mutate(leaf);
        assertEquals("green",leaf.getColor());
        assertNull(tracker.next());
    }

    @Test
    public void next_returns_2_valid_mutations_for_2x1_field_value() {
        StateTracker tracker = StateTracker.of(Arrays.asList(
                MutationChoices.fromValues(Leaf::setColor, "tan"),
                MutationChoices.fromValues(Leaf::setShape, "maple")
        ));
        Leaf leaf = new Leaf();
        tracker.next().mutate(leaf);
        assertEquals("tan",leaf.getColor());
        tracker.next().mutate(leaf);
        assertEquals("maple",leaf.getShape());
        assertNull(tracker.next());
    }

}
