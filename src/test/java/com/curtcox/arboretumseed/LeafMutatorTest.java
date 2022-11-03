package com.curtcox.arboretumseed;

import org.junit.Test;

import java.util.*;

import static com.curtcox.arboretumseed.Lists.*;
import static java.util.Collections.*;
import static org.junit.Assert.*;

public class LeafMutatorTest {

    @Test
    public void can_create() {
        assertNotNull(Mutator.of(new Leaf(),Leaf::copy));
    }

    @Test
    public void singles_returns_original() {
        Leaf leaf = new Leaf();
        assertEquals(singletonList(leaf),list(Mutator.of(leaf,Leaf::copy).singles()));
    }

    @Test
    public void singles_contains_1_specified_string_mutation() {
        Mutator<Leaf> mutator = Mutator.of(new Leaf(),Leaf::copy)
                .with(Leaf::setText,"foo");
        assertContains(
                list(mutator.singles()),
                new Leaf(null,"foo",null,null,null,false)
        );
    }

    @Test
    public void singles_contains_1_specified_boolean_mutation() {
        Mutator<Leaf> mutator = Mutator.of(new Leaf(),Leaf::copy)
                .with(Leaf::setPointy,true);
        assertContains(
                list(mutator.singles()),
                new Leaf(null,null,null,null,null,true)
        );
    }

    @Test
    public void singles_contains_1_specified_string_mutation_across_several_fields() {
        Mutator<Leaf> mutator = Mutator.of(new Leaf(),Leaf::copy)
                .with(Arrays.asList("x"),
                        Leaf::setColor,Leaf::setText,Leaf::setShape,Leaf::setCellType,Leaf::setTag
                );
        assertContains(
                list(mutator.singles()),
                new Leaf("x","x","x","x","x",false)
        );
    }

    @Test
    public void singles_contains_2_specified_string_mutation_across_several_fields() {
        Mutator<Leaf> mutator = Mutator.of(new Leaf(),Leaf::copy)
                .with(Arrays.asList("x","y"),
                        Leaf::setColor,Leaf::setText,Leaf::setShape,Leaf::setCellType,Leaf::setTag
                );
        assertContains(
                list(mutator.singles()),
                new Leaf("x","x","x","x","x",false),
                new Leaf("x","y","x","y","x",false),
                new Leaf("y","y","y","y","y",false)
        );
    }

    @Test
    public void singles_contains_2_specified_mutations() {
        Mutator<Leaf> mutator = Mutator.of(new Leaf(),Leaf::copy)
                .with(Leaf::setShape,"maple","oak");
        assertContains(
                list(mutator.singles()),
                new Leaf(null,null,null,"maple",null,false),
                new Leaf(null,null,null,"oak",null,false)
        );
    }

    @Test
    public void singles_contains_3_specified_mutations() {
        Mutator<Leaf> mutator = Mutator.of(new Leaf(),Leaf::copy)
                .with(Leaf::setText,"T1","T2","T3");
        assertContains(
                list(mutator.singles()),
                new Leaf(null,"T1",null,null,null,false),
                new Leaf(null,"T2",null,null,null,false),
                new Leaf(null,"T3",null,null,null,false)
        );
    }

}
