package com.curtcox.arboretumseed;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void can_create_for_leaf_value() {
        LeafValue original = new LeafValue(null,null,null,null,null);
        LeafValue.LeafValueBuilder builder = LeafValue.builder();
        Generator generator = Generator.of(original, builder, b -> b.build());
        assertNotNull(generator);
    }

    @Test
    public void can_create_for_leaf_value_with_mutation() {
        LeafValue original = new LeafValue(null,null,null,null,null);
        LeafValue.LeafValueBuilder builder = LeafValue.builder();
        Generator generator = Generator.of(original,builder,b -> b.build())
                .with(LeafValue.LeafValueBuilder::cellType,"x","y");
        assertNotNull(generator);
    }

}
