package com.curtcox.arboretumseed;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.curtcox.arboretumseed.LeafValue.*;

public class GeneratorTest {

    @Test
    public void can_create_for_leaf_value() {
        Generator generator = Generator.of(LeafValue.builder(), b -> b.build());
        assertNotNull(generator);
    }

    @Test
    public void can_create_for_leaf_value_with_mutation() {
        Generator generator = Generator.of(LeafValue.builder(), b -> b.build())
                .with(LeafValueBuilder::cellType,"x","y");
        assertNotNull(generator);
    }

}
