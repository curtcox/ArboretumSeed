package com.curtcox.arboretumseed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.meanbean.test.BeanVerifier;

public class RootTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Test
    public void verify_bean() {
        BeanVerifier.forClass(Root.class).verify();
    }


//    @Property
//    void equals_is_consistent_with_hashcode() {
//        EqualityContractChecker.consistent(roots("a","b"));
//    }
//
//    @Property
//    void equals_is_consistent_with_hashcode(@ForAll @UseType Root a, @ForAll @UseType Root b) {
//        EqualityContractChecker.equalsAndHashCodeConsistent(a,b);
//    }
}