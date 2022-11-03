package com.curtcox.arboretumseed;

import java.util.*;
import static com.curtcox.arboretumseed.LeafValue.*;
import static com.curtcox.arboretumseed.BranchValue.*;
import static com.curtcox.arboretumseed.RootValue.*;

final class RootValueGenerator {
    static Iterable<List<LeafValue>> leaves(String... values) {
        return Generator.of(LeafValue.builder(),LeafValue.LeafValueBuilder::build)
                .with(LeafValue.LeafValueBuilder::cellType,values)
                .with(values(values),
                        LeafValueBuilder::text,
                        LeafValueBuilder::tag,
                        LeafValueBuilder::shape,
                        LeafValueBuilder::color
                )
                .lists();

    }

    static <T> Iterable<T> values(T... a) {
        return Arrays.asList(a);
    }

    static Iterable<List<BranchValue>> branches(String... values) {
        return Generator.of(BranchValue.builder(),b -> b.build())
                .with(values(values),
                        BranchValueBuilder::name,
                        BranchValueBuilder::value,
                        BranchValueBuilder::description,
                        BranchValueBuilder::brachiness
                )
                .with(BranchValueBuilder::arity,-1,0,1)
                .with(BranchValueBuilder::leaves, leaves())
                .lists();
    }

    static Iterable<RootValue> roots(String... values) {
        return Generator.of(RootValue.builder(),b -> b.build())
                .with(values(values),
                        RootValueBuilder::name,
                        RootValueBuilder::type,
                        RootValueBuilder::color,
                        RootValueBuilder::texture
                )
                .with(RootValue.RootValueBuilder::branches, branches())
                .singles();
    }

}