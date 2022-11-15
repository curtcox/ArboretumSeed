package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static com.curtcox.arboretumseed.Lists.*;
import static java.util.Collections.*;
import static org.junit.Assert.*;

public class LeafMutatorTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

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
    public void singles_contains_2_specified_boolean_mutations() {
        Mutator<Leaf> mutator = Mutator.of(new Leaf(),Leaf::copy)
                .with(Leaf::setPointy,true,false);
        assertContains(
                list(mutator.singles()),
                new Leaf(null,null,null,null,null,false),
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

    @Test
    public void singles_contains_combined_2x2x2_mutations() {
        Mutator<Leaf> mutator = Mutator.of(new Leaf(),Leaf::copy)
                .with(Leaf::setShape,"maple","oak")
                .with(Leaf::setText,"T1","T2")
                .with(Leaf::setPointy,true,false);
        assertContains(
                list(mutator.singles()),
                new Leaf(null,"T1",null,"maple",null,false),
                new Leaf(null,"T1",null,"oak",null,false),
                new Leaf(null,"T2",null,"maple",null,false),
                new Leaf(null,"T2",null,"oak",null,false),
                new Leaf(null,"T1",null,"maple",null,true),
                new Leaf(null,"T1",null,"oak",null,true),
                new Leaf(null,"T2",null,"maple",null,true),
                new Leaf(null,"T2",null,"oak",null,true)
        );
    }

    @Test
    public void singles_contains_combined_2x3x2_mutations() {
        Mutator<Leaf> mutator = Mutator.of(new Leaf(),Leaf::copy)
                .with(Leaf::setShape,"maple","oak")
                .with(Leaf::setText,"T1","T2","T3")
                .with(Leaf::setPointy,true,false);
        assertContains(
                list(mutator.singles()),
                new Leaf(null,"T1",null,"maple",null,false),
                new Leaf(null,"T1",null,"oak",null,false),
                new Leaf(null,"T2",null,"maple",null,false),
                new Leaf(null,"T2",null,"oak",null,false),
                new Leaf(null,"T3",null,"maple",null,false),
                new Leaf(null,"T3",null,"oak",null,false),
                new Leaf(null,"T1",null,"maple",null,true),
                new Leaf(null,"T1",null,"oak",null,true),
                new Leaf(null,"T2",null,"maple",null,true),
                new Leaf(null,"T2",null,"oak",null,true),
                new Leaf(null,"T3",null,"maple",null,true),
                new Leaf(null,"T3",null,"oak",null,true)
        );
    }

    @Test
    public void singles_contains_combined_3x3x2_mutations() {
        Mutator<Leaf> mutator = Mutator.of(new Leaf(),Leaf::copy)
                .with(Leaf::setShape,"maple","oak","pine")
                .with(Leaf::setText,"T1","T2","T3")
                .with(Leaf::setPointy,true,false);
        assertContains(
                list(mutator.singles()),
                new Leaf(null,"T1",null,"maple",null,false),
                new Leaf(null,"T1",null,"oak",null,false),
                new Leaf(null,"T2",null,"maple",null,false),
                new Leaf(null,"T2",null,"oak",null,false),
                new Leaf(null,"T3",null,"maple",null,false),
                new Leaf(null,"T3",null,"oak",null,false),
                new Leaf(null,"T1",null,"maple",null,true),
                new Leaf(null,"T1",null,"oak",null,true),
                new Leaf(null,"T2",null,"maple",null,true),
                new Leaf(null,"T2",null,"oak",null,true),
                new Leaf(null,"T3",null,"maple",null,true),
                new Leaf(null,"T3",null,"oak",null,true),
                new Leaf(null,"T1",null,"pine",null,false),
                new Leaf(null,"T2",null,"pine",null,false),
                new Leaf(null,"T3",null,"pine",null,false),
                new Leaf(null,"T1",null,"pine",null,true),
                new Leaf(null,"T2",null,"pine",null,true),
                new Leaf(null,"T3",null,"pine",null,true)
        );
    }

}
