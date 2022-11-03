package com.curtcox.arboretumseed;

import java.util.*;

final class RootGenerator {
    static Iterable<List<Leaf>> leaves(String... values) {
        return Mutator.of(new Leaf(),Leaf::copy)
                .with(values(values),
                        Leaf::setCellType,
                        Leaf::setText,
                        Leaf::setTag,
                        Leaf::setShape,
                        Leaf::setColor
                )
                .lists();
    }

    static <T> Iterable<T> values(T... a) {
        return Arrays.asList(a);
    }

    static Iterable<List<Branch>> branches(String... values) {
        return Mutator.of(new Branch(),Branch::copy)
                .with(values(values),
                        Branch::setName,
                        Branch::setValue,
                        Branch::setDescription,
                        Branch::setBrachiness
                )
                .with(Branch::setArity,-1,0,1)
                .with(Branch::setLeaves, leaves())
                .lists();
    }

    static Iterable<Root> roots(String... values) {
        return Mutator.of(new Root(),Root::copy)
                .with(values(values),
                        Root::setName,
                        Root::setType,
                        Root::setColor,
                        Root::setTexture
                )
                .with(Root::setBranches, branches())
                .singles();
    }

}