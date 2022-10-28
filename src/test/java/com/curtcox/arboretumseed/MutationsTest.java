package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static com.curtcox.arboretumseed.Lists.*;
import static org.junit.Assert.*;

public class MutationsTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(3);

    @Test
    public void can_create() {
        assertNotNull(Mutations.of(null,null));
    }

    @Test
    public void count_is_1_when_there_are_no_mutations() {
        Mutations mutations = Mutations.of(new Object(),null);
        assertCount(1,mutations);
    }

    private void assertCount(long number, Mutations mutations) {
        assertEquals(number,(long) mutations.count());
    }

    @Test
    public void initial_value_is_only_value_when_there_are_no_mutations() {
        Object original = new Object();
        Mutations mutations = Mutations.of(original,null);
        Iterator iterator = mutations.values().iterator();
        assertTrue(iterator.hasNext());
        assertSame(original,iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void count_is_2_when_1_mutation() {
        Mutations<Branch> mutations = Mutations.of(new Branch(), (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,"foo"));
        assertCount(2,mutations);
    }

    @Test
    public void count_is_3_when_2_mutations() {
        Mutations<Branch> mutations = Mutations.of(new Branch(), (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,"foo","bar"));
        assertCount(3,mutations);
    }

    @Test
    public void mutator_with_1_mutation_returns_original_and_mutation_from_singles() {
        Branch branch = new Branch();
        String value = toString();
        Mutations<Branch> mutations = Mutations.of(branch, (Copier<Branch>) o -> o.copy());
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
        Mutations<Branch> mutations = Mutations.of(branch, (Copier<Branch>) o -> o.copy());
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
        Mutations<Branch> mutations = Mutations.of(new Branch(), (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,"One","Three"));
        mutations.add(MutationChoices.fromValues(Branch::setBrachiness,"Two"));
        assertCount(4,mutations);
    }

    @Test
    public void mutator_with_3_mutations_returns_original_and_3_mutations_from_singles() {
        Branch branch = new Branch();
        Mutations<Branch> mutations = Mutations.of(branch, (Copier<Branch>) o -> o.copy());
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
        Mutations<Branch> mutations = Mutations.of(branch, (Copier<Branch>) o -> o.copy());
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

    @Test
    public void count_of_branch_mutation_equals_actual_value() {
        Branch branch = new Branch();
        Mutations<Branch> mutations = Mutations.of(branch, (Copier<Branch>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Branch::setName,"a","b"));
        mutations.add(MutationChoices.fromValues(Branch::setBrachiness,"c","d"));
        assertValue(list(mutations.values()).size(),mutations.count());
    }

    private void assertValue(int size, Long count) {
        assertEquals(size,(long)count);
    }

    @Test
    public void count_of_leaf_mutations_equals_actual_value() {
        Leaf leaf = new Leaf();
        Mutations<Leaf> mutations = Mutations.of(leaf, (Copier<Leaf>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Leaf::setColor,"a","b"));
        mutations.add(MutationChoices.fromValues(Leaf::setShape,"c","d"));
        assertValue(list(mutations.values()).size(),mutations.count());
    }

    @Test
    public void leaf_mutations_contains_expected_values_from_2x2() {
        Leaf leaf = new Leaf();
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";

        Mutations<Leaf> mutations = Mutations.of(leaf, (Copier<Leaf>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Leaf::setColor,a,b));
        mutations.add(MutationChoices.fromValues(Leaf::setShape,c,d));
        assertContains(list(mutations.values()),leaf(a,c),leaf(a,d),leaf(b,c),leaf(b,d));
    }

    @Test
    public void leaf_mutations_contains_expected_values_from_3x3() {
        Leaf leaf = new Leaf();
        String a1 = "a1"; String a2 = "a2"; String a3 = "a3";
        String b1 = "b1"; String b2 = "b2"; String b3 = "b3";
        String c1 = "c1"; String c2 = "c2"; String c3 = "c3";

        Mutations<Leaf> mutations = Mutations.of(leaf, (Copier<Leaf>) o -> o.copy());
        mutations.add(MutationChoices.fromValues(Leaf::setColor,a1,a2,a3));
        mutations.add(MutationChoices.fromValues(Leaf::setShape,b1,b2,b3));
        mutations.add(MutationChoices.fromValues(Leaf::setText,c1,c2,c3));
        assertContains(list(mutations.values()),
                leaf(a1,b1,c1),
                leaf(a2,b2,c2),
                leaf(a1,b2,c3),
                leaf(a3,b3,c3));
    }

    private Leaf leaf(String color, String shape) {
        Leaf leaf = new Leaf();
        leaf.setColor(color);
        leaf.setShape(shape);
        return leaf;
    }

    private Leaf leaf(String color, String shape, String text) {
        Leaf leaf = new Leaf();
        leaf.setColor(color);
        leaf.setShape(shape);
        leaf.setText(text);
        return leaf;
    }

}
