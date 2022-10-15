package com.curtcox.arboretumseed;

import net.jqwik.api.*;
import net.jqwik.api.constraints.UseType;
import org.junit.Test;
import org.meanbean.test.BeanVerifier;

import static com.curtcox.arboretumseed.RootGenerator.roots;

public class RootTest {

    @Test
    public void verify_bean() {
        BeanVerifier.forClass(Root.class)
                .withSettings(settings -> settings.setDefaultIterations(1000))
                .withSettings(settings -> settings.addEqualsInsignificantProperty(Root::getTexture))
                .verify();
    }


    @Property
    void equals_is_consistent_with_hashcode() {
        EqualityContractChecker.consistent(roots("a","b"));
    }

    @Property
    void equals_is_consistent_with_hashcode(@ForAll @UseType Root a, @ForAll @UseType Root b) {
        EqualityContractChecker.equalsAndHashCodeConsistent(a,b);
    }
}