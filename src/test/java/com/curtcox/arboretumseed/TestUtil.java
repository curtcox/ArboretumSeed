package com.curtcox.arboretumseed;

import static org.junit.Assert.*;

final class TestUtil {

    static void assertGreaterThan(int target, int value) {
        String message = value + " should be greater than " + target;
        assertTrue(message,value>target);
    }
}
