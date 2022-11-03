package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static com.curtcox.arboretumseed.RootGenerator.*;
import static org.junit.Assert.*;

public class RootGeneratorChecker {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Test
    public void leaves_contains_enough_leaves_when_no_leaf_mutations() {
        assertEquals(2,Lists.list(leaves()).size());
    }

    @Test
    public void leaves_contains_enough_leaves_when_1_leaf_mutation_value() {
        assertEquals(7,Lists.list(leaves("foo")).size());
    }

    @Test
    public void branches_contains_enough_branches_when_no_mutation_values() {
        assertEquals(9,Lists.list(branches()).size());
    }

    @Test
    public void branches_contains_enough_branches_when_1_mutation_value() {
        assertEquals(13,Lists.list(branches("bar")).size());
    }

    @Test
    public void leaves_contains_enough_leaves_when_2_leaf_mutation_value2() {
        assertEquals(36,Lists.list(leaves("foo","bar")).size());
    }

    @Test
    public void branches_contains_enough_branches_when_2_mutation_value() {
        assertEquals(87,Lists.list(branches("bar","baz")).size());
    }

    @Test
    public void leaves_contains_enough_leaves_when_3_leaf_mutation_value() {
        assertEquals(224,Lists.list(leaves("foo","bar","bin")).size());
    }

    @Test
    public void branches_contains_enough_branches_when_3_mutation_value() {
        assertEquals(292,Lists.list(branches("bar","baz","bin")).size());
    }

    @Test
    public void roots_contains_enough_roots_when_2_mutation_values() {
        assertEquals(106,Lists.list(roots("a","b")).size());
    }

    @Test
    public void roots_contains_enough_roots_when_3_mutation_values() {
        assertEquals(493,Lists.list(roots("a","b","c")).size());
    }

    @Test
    public void roots_contains_enough_roots_when_4_mutation_values() {
        assertEquals(1591,Lists.list(roots("a","b","c","d")).size());
    }

    @Test
    public void roots_contains_enough_roots_when_no_mutation_values() {
        assertEquals(8,Lists.list(roots()).size());
    }

    @Test
    public void roots_contains_enough_roots_when_1_mutation_value() {
        assertEquals(12,Lists.list(roots("a")).size());
    }

    @Test
    public void roots_contains_root_with_no_branches() {
        for (Root root : roots()) {
            if (root.getBranches().isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void roots_contains_root_with_branches() {
        for (Root root : roots()) {
            if (!root.getBranches().isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void roots_contains_branch_with_no_leaves() {
        for (Root root : roots()) {
            for (Branch branch : root.getBranches()) {
                if (branch.getLeaves().isEmpty()) {
                    return;
                }
            }
        }
        fail();
    }

    @Test
    public void roots_contains_branch_with_leaves() {
        for (Root root : roots()) {
            for (Branch branch : root.getBranches()) {
                if (!branch.getLeaves().isEmpty()) {
                    return;
                }
            }
        }
        fail();
    }

    @Test
    public void branches_contains_list_with_no_branch() {
        for (List<Branch> branches : branches()) {
            if (branches.isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void branches_contains_list_with_branch() {
        for (List<Branch> branches : branches()) {
            if (!branches.isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void branches_contains_branch_with_leaves() {
        for (List<Branch> branches : branches()) {
            for (Branch branch : branches) {
                if (!branch.getLeaves().isEmpty()) {
                    return;
                }
            }
        }
        fail();
    }

    @Test
    public void there_is_at_least_one_leaf_even_with_no_mutations() {
        assertTrue(leaves().iterator().hasNext());
    }

    @Test
    public void there_is_at_least_one_empty_leaf_list() {
        for (List<Leaf> leaves : leaves()) {
            if (leaves.isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void there_is_at_least_one_non_empty_leaf_list() {
        for (List<Leaf> leaves : leaves()) {
            if (!leaves.isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void there_is_at_least_one_branch_even_with_no_mutations() {
        assertTrue(branches().iterator().hasNext());
    }

}
