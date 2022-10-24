package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static com.curtcox.arboretumseed.RootGenerator.roots;
import static org.junit.Assert.*;

public class RootGeneratorTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    class EmptyCounter {

        int count;

        void note(List list) {
            if (list.isEmpty()) {
                count++;
                if (count > 1) {
                    fail("Too many empties");
                }
            }
        }

    }

    @Test
    public void roots_is_not_empty() {
        assertTrue(roots().iterator().hasNext());
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
        EmptyCounter empties = new EmptyCounter();
        for (List<Leaf> leaves : RootGenerator.leaves()) {
            empties.note(leaves);
            if (!leaves.isEmpty()) {
                return;
            }
        }
        fail();
    }

    @Test
    public void leaves_contains_list_with_a_specific_leaf() {
        String a = "a";
        String b = "b";
        EmptyCounter empties = new EmptyCounter();
        for (List<Leaf> leaves : RootGenerator.leaves(a,b)) {
            empties.note(leaves);
            for (Leaf leaf : leaves) {
                System.out.println("Leaf " + leaf);
                if (a.equals(leaf.getCellType()) &&
                    b.equals(leaf.getShape()) &&
                    a.equals(leaf.getColor()))
                {
                    return;
                }
            }
        }
        fail();
    }

    @Test
    public void branches_with_no_values_is_empty_then_one_element() {
        Iterator<List<Branch>> branches = RootGenerator.branches().iterator();
        assertTrue(branches.next().isEmpty());
        assertFalse(branches.next().isEmpty());
    }
}
