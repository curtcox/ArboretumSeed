package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static com.curtcox.arboretumseed.Lists.assertContains;
import static java.util.Collections.*;
import static org.junit.Assert.*;

public class MutatedValuesIteratorTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Test
    public void can_create_for_leaf() {
        assertNotNull(MutatedValuesIterator.of(new Leaf(),Leaf::copy,EMPTY_LIST));
    }

    @Test
    public void iterator_only_contains_original_when_no_choices() {
        Leaf leaf = new Leaf();
        MutatedValuesIterator iterator = MutatedValuesIterator.of(leaf, Leaf::copy, EMPTY_LIST);
        assertTrue(iterator.hasNext());
        assertEquals(leaf,iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_contains_mutated_value_when_1_choice() {
        Leaf leaf = new Leaf();
        String red = "red";
        List<Leaf> leaves = Lists.list(
                MutatedValuesIterator.of(leaf, Leaf::copy,
                        singletonList(MutationChoices.fromValues(Leaf::setColor,red))));
        assertEquals(2,leaves.size());
        assertContains(leaves,leaf);
        leaf.setColor(red);
        assertContains(leaves,leaf);
    }

}
