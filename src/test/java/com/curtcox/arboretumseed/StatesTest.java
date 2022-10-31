package com.curtcox.arboretumseed;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatesTest {

    States states = new States();

    @Test
    public void can_create() {
        assertNotNull(new States());
    }

    @Test
    public void red_is_unused_choice_once() {
        MutationChoices<Leaf,String> choices = MutationChoices.fromValues(Leaf::setColor, "red");
        Mutation choice = states.findUnusedChoice(choices);
        Mutation red = choices.mutations().iterator().next();
        assertEquals(red,choice);
        assertNull(states.findUnusedChoice(choices));
    }

}
