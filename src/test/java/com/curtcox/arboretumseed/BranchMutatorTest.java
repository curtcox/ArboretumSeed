package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static com.curtcox.arboretumseed.Lists.assertContains;
import static com.curtcox.arboretumseed.Lists.list;
import static java.util.Collections.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BranchMutatorTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Test
    public void can_create() {
        assertNotNull(Mutator.of(new Branch(),Branch::copy));
    }

    @Test
    public void singles_returns_original() {
        Branch branch = new Branch();
        assertEquals(singletonList(branch),list(Mutator.of(branch,Branch::copy).singles()));
    }

    @Test
    public void singles_contains_1_specified_string_mutation() {
        Mutator<Branch> mutator = Mutator.of(new Branch(),Branch::copy)
                .with(Branch::setName,"Fred");
        assertContains(
                list(mutator.singles()),
                new Branch("Fred",null,null,null,0,new ArrayList<>())
        );
    }

    @Test
    public void singles_contains_1_specified_int_mutation() {
        Mutator<Branch> mutator = Mutator.of(new Branch(),Branch::copy)
                .with(Branch::setArity,42);
        assertContains(
                list(mutator.singles()),
                new Branch(null,null,null,null,42,new ArrayList<>())
        );
    }

}
