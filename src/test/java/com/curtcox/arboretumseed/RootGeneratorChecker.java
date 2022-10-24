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
            System.out.println("Root " + root);
            for (Branch branch : root.getBranches()) {
                if (!branch.getLeaves().isEmpty()) {
                    return;
                }
            }
        }
        fail();
    }

    @Test
    public void branches_contains_branch_with_leaves() {
        for (List<Branch> branches : branches()) {
            for (Branch branch : branches) {
                System.out.println("Branch " + branch);
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
            System.out.println("leaves " + leaves);
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
