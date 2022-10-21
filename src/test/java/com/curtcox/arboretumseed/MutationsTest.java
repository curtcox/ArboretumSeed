package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static org.junit.Assert.*;

public class MutationsTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Test
    public void can_create() {
        assertNotNull(new Mutations(null,null));
    }

    @Test
    public void count_is_1_when_there_are_no_mutations() {
        Mutations mutations = new Mutations(new Object(),null);
        assertCount(1,mutations);
    }

    private void assertCount(long number, Mutations mutations) {
        assertEquals(number,(long) mutations.count());
    }

    @Test
    public void initial_value_is_only_value_when_there_are_no_mutations() {
        Object original = new Object();
        Mutations mutations = new Mutations(original,null);
        Iterator iterator = mutations.values().iterator();
        assertTrue(iterator.hasNext());
        assertSame(original,iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void count_is_2_when_1_mutation() {
        Mutations<Branch> mutations = new Mutations(new Branch(), (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,"foo"));
        assertCount(2,mutations);
    }

    @Test
    public void count_is_3_when_2_mutations() {
        Mutations<Branch> mutations = new Mutations(new Branch(), (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,"foo","bar"));
        assertCount(3,mutations);
    }

    @Test
    public void mutator_with_1_mutation_returns_original_and_mutation_from_singles() {
        Branch branch = new Branch();
        String value = toString();
        Mutations<Branch> mutations = new Mutations(branch, (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,value));
        Iterator<Branch> branches = mutations.values().iterator();
        assertEquals(branch,branches.next());
        branch.setName(value);
        assertEquals(branch,branches.next());
        assertFalse(branches.hasNext());
    }

    @Test
    public void mutator_with_2_mutations_returns_original_and_2_mutations_from_singles() {
        Branch branch = new Branch();
        String name = "Fred";
        String branchiness = "Very";
        Mutations<Branch> mutations = new Mutations(branch, (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,name));
        mutations.add(MutationChoices.fromValues(Branch::setBrachiness,branchiness));
        Iterator<Branch> branches = mutations.values().iterator();
        assertEquals(branch,branches.next());
        branch.setName(name);
        assertEquals(branch,branches.next());
        branch.setBrachiness(branchiness);
        assertEquals(branch,branches.next());
        assertFalse(branches.hasNext());
    }

    @Test
    public void count_is_4_when_3_mutations() {
        Mutations<Branch> mutations = new Mutations(new Branch(), (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,"One","Three"));
        mutations.add(MutationChoices.fromValues(Branch::setBrachiness,"Two"));
        assertCount(4,mutations);
    }

    @Test
    public void mutator_with_3_mutations_returns_original_and_3_mutations_from_singles() {
        Branch branch = new Branch();
        Mutations<Branch> mutations = new Mutations(branch, (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,"One","Three"));
        mutations.add(MutationChoices.fromValues(Branch::setBrachiness,"Two"));
        Iterator<Branch> branches = mutations.values().iterator();
        assertEquals(branch,branches.next());
        branch.setName("One");
        assertEquals(branch,branches.next());
        branch.setBrachiness("Two");
        assertEquals(branch,branches.next());
        branch.setName("Three");
        assertEquals(branch,branches.next());
        assertFalse(branches.hasNext());
    }

    @Test
    public void mutator_with_4_mutations_returns_original_and_4_mutations_from_singles() {
        Branch branch = new Branch();
        Mutations<Branch> mutations = new Mutations(branch, (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,"A1","A2"));
        mutations.add(MutationChoices.fromValues(Branch::setBrachiness,"B1","B2"));
        Iterator<Branch> branches = mutations.values().iterator();
        assertEquals(branch,branches.next());
        branch.setName("A1");
        assertEquals(branch,branches.next());
        branch.setBrachiness("B1");
        assertEquals(branch,branches.next());
        branch.setName("A2");
        assertEquals(branch,branches.next());
        branch.setBrachiness("B2");
        assertEquals(branch,branches.next());
        assertFalse(branches.hasNext());
    }

}
