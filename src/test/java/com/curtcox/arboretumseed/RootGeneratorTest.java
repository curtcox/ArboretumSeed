package com.curtcox.arboretumseed;

import org.junit.Test;

import java.util.*;

import static com.curtcox.arboretumseed.RootGenerator.roots;
import static org.junit.Assert.*;

public class RootGeneratorTest {

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
    public void leaves_contains_empty_list() {
        for (List<Leaf> leaves : RootGenerator.leaves()) {
            if (leaves.isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void leaves_contains_list_with_items() {
        for (List<Leaf> leaves : RootGenerator.leaves()) {
            if (!leaves.isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void leaves_contains_list_with_a() {
        String a = "a";
        String b = "b";
        for (List<Leaf> leaves : RootGenerator.leaves(a,b)) {
            for (Leaf leaf : leaves) {
                if (a.equals(leaf.getColor()) && b.equals(leaf.getShape()) && a.equals(leaf.getCellType())) {
                    return;
                }
            }
        }
        fail();
    }

}
