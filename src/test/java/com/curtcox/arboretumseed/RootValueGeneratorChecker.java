package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static com.curtcox.arboretumseed.RootValueGenerator.*;
import static com.curtcox.arboretumseed.TestUtil.*;
import static org.junit.Assert.*;

public class RootValueGeneratorChecker {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);

    @Test
    public void roots_contains_enough_roots() {
        assertGreaterThan(3,Lists.list(roots()).size());
    }

    @Test
    public void branches_contains_enough_branches() {
        assertGreaterThan(3,Lists.list(branches()).size());
    }

    @Test
    public void leaves_contains_enough_leaves() {
        assertEquals(2,Lists.list(leaves()).size());
    }

    @Test
    public void roots_contains_root_with_no_branches() {
        for (RootValue root : roots()) {
            if (root.getBranches().isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void roots_contains_root_with_branches() {
        for (RootValue root : roots()) {
            if (!root.getBranches().isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void roots_contains_branch_with_no_leaves() {
        for (RootValue root : roots()) {
            for (BranchValue branch : root.getBranches()) {
                System.out.println("branch = " + branch);
                if (branch.getLeaves().isEmpty()) {
                    return;
                }
            }
        }
        fail();
    }

    @Test
    public void roots_contains_branch_with_leaves() {
        for (RootValue root : roots()) {
            for (BranchValue branch : root.getBranches()) {
                if (!branch.getLeaves().isEmpty()) {
                    return;
                }
            }
        }
        fail();
    }

    @Test
    public void branches_contains_branch_with_leaves() {
        for (List<BranchValue> branches : branches()) {
            for (BranchValue branch : branches) {
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
        for (List<LeafValue> leaves : leaves()) {
            if (leaves.isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void there_is_at_least_one_non_empty_leaf_list() {
        for (List<LeafValue> leaves : leaves()) {
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
