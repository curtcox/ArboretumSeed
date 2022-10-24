package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static java.util.Collections.*;
import static org.junit.Assert.*;
import static com.curtcox.arboretumseed.Lists.*;

public class MutationChoicesTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Test
    public void can_create_from_values() {
        assertNotNull(MutationChoices.fromValues(Leaf::setColor,"red","green"));
    }

    @Test
    public void can_create_from_producer() {
        assertNotNull(MutationChoices.fromProducer(Leaf::setColor,EMPTY_LIST));
    }

    @Test
    public void list_from_1_mutation_returns_expected_size() {
        MutationChoices choices = MutationChoices.fromValues(Leaf::setShape,"spiral");
        List<Mutation<Leaf,String>> list = list(choices.mutations());
        assertEquals(1,list.size());
    }

    @Test
    public void list_from_1_mutation_value_will_mutate() {
        MutationChoices choices = MutationChoices.fromValues(Leaf::setColor,"red");
        List<Mutation<Leaf,String>> list = list(choices.mutations());

        Leaf leaf = new Leaf();
        leaf.setColor("green");
        assertEquals("green",leaf.getColor());
        list.get(0).mutate(leaf);
        assertEquals("red",leaf.getColor());
    }

    @Test
    public void list_from_1_mutation_produced_will_mutate() {
        MutationChoices choices = MutationChoices.fromProducer(Leaf::setColor,singletonList("red"));
        List<Mutation<Leaf,String>> list = list(choices.mutations());

        Leaf leaf = new Leaf();
        leaf.setColor("green");
        assertEquals("green",leaf.getColor());
        list.get(0).mutate(leaf);
        assertEquals("red",leaf.getColor());
    }

}
