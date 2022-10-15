package com.curtcox.arboretumseed;

import static org.junit.Assert.assertTrue;

public class EqualityContractChecker {

    public static void consistent(Iterable<?> list) {
        for (Object a : list) {
            for (Object b: list) {
                String message = a + " and " + b + " are inconsistent. " +
                        a.equals(b) + " " + b.equals(a) + " " + a.hashCode() + " " + b.hashCode();
                assertTrue(message,equalsAndHashCodeConsistent(a,b));
            }
        }
    }

    public static boolean equalsAndHashCodeConsistent(Object a, Object b) {
        if (a.equals(b)) return b!=null && b.equals(a) && a.hashCode() == b.hashCode();
        if (b.equals(a)) return a!=null && a.equals(b) && a.hashCode() == b.hashCode();
        return true;
    }

}
