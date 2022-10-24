package com.curtcox.arboretumseed;

import org.junit.Test;

import static java.util.Collections.*;
import static org.junit.Assert.assertNotNull;

public class MutatedValuesIteratorTest {

    @Test
    public void can_create_for_leaf() {
        assertNotNull(MutatedValuesIterator.of(new Leaf(),Leaf::copy,EMPTY_LIST));
    }
}
