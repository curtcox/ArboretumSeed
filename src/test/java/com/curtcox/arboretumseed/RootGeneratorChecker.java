package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static com.curtcox.arboretumseed.RootGenerator.roots;
import static org.junit.Assert.fail;

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

}
