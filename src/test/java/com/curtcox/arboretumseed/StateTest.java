package com.curtcox.arboretumseed;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class StateTest {

    @Test
    public void can_create_empty() {
        assertNotNull(State.empty());
    }

    @Test
    public void empty_equals_empty() {
        assertEquals(State.empty(),State.empty());
    }

    @Test
    public void red_equals_red() {
        MutationChoices<Leaf,String> choices = MutationChoices.fromValues(Leaf::setColor, "red");
        Mutation red = choices.mutations().iterator().next();
        assertEquals(State.empty().apply(choices,red),State.empty().apply(choices,red));
    }

    @Test
    public void red_is_not_empty() {
        MutationChoices<Leaf,String> choices = MutationChoices.fromValues(Leaf::setColor, "red");
        Mutation red = choices.mutations().iterator().next();
        assertNotEquals(State.empty(),State.empty().apply(choices,red));
    }

    @Test
    public void state_can_only_appear_once_in_a_set_a() {
        MutationChoices<Leaf,String> choices = MutationChoices.fromValues(Leaf::setColor, "red");
        Mutation red = choices.mutations().iterator().next();
        Set<State> states = new HashSet<>();
        assertEquals(0,states.size());
        states.add(State.empty());
        assertEquals(1,states.size());
        states.add(State.empty().apply(choices,red));
        assertEquals(2,states.size());
        states.add(State.empty());
        assertEquals(2,states.size());
        states.add(State.empty().apply(choices,red));
        assertEquals(2,states.size());
    }

    @Test
    public void state_can_only_appear_once_in_a_set_b() {
        MutationChoices<Leaf,String> choices = MutationChoices.fromValues(Leaf::setColor, "red");
        Mutation red = choices.mutations().iterator().next();
        Set<State> states = new HashSet<>();
        State state = State.empty();
        assertEquals(0,states.size());
        states.add(state);
        assertEquals(1,states.size());
        state = state.apply(choices,red);
        states.add(state);
        assertEquals(2,states.size());
        state = state.apply(choices,red);
        states.add(state);
        assertEquals(2,states.size());
    }

    @Test
    public void state_is_contained_in_set_after_being_added() {
        MutationChoices<Leaf,String> choices = MutationChoices.fromValues(Leaf::setColor, "red");
        Mutation red = choices.mutations().iterator().next();
        Set<State> states = new HashSet<>();
        State state = State.empty();
        states.add(state);
        state = state.apply(choices,red);
        states.add(state);
        assertTrue(states.contains(State.empty()));
        assertTrue(states.contains(State.empty().apply(choices,red)));
    }

}
