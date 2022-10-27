package com.curtcox.arboretumseed;

import java.util.*;

final class RootValueGenerator {
    static Iterable<List<LeafValue>> leaves(String... values) {
        LeafValue leaf = new LeafValue(null, null, null, null, null);
        return Generator.of(leaf, LeafValue.builder(),LeafValue.LeafValueBuilder::build)
                .with(LeafValue.LeafValueBuilder::cellType,values)
                .with(values(values),
                        LeafValue.LeafValueBuilder::text,
                        LeafValue.LeafValueBuilder::tag,
                        LeafValue.LeafValueBuilder::shape,
                        LeafValue.LeafValueBuilder::color
                )
                .lists();

    }

    static <T> Iterable<T> values(T... a) {
        return Arrays.asList(a);
    }

    static Iterable<List<BranchValue>> branches(String... values) {
        BranchValue branch = new BranchValue(null,null,null,null,null,null);
        return Generator.of(branch,BranchValue.builder(),b -> b.build())
                .with(values(values),
                        BranchValue.BranchValueBuilder::name,
                        BranchValue.BranchValueBuilder::value,
                        BranchValue.BranchValueBuilder::description,
                        BranchValue.BranchValueBuilder::brachiness,
                        BranchValue.BranchValueBuilder::arity
                )
                .with(BranchValue.BranchValueBuilder::leaves, leaves())
                .lists();
    }

    static Iterable<RootValue> roots(String... values) {
        RootValue root = new RootValue(null,null,null,null,null);
        return Generator.of(root,RootValue.builder(),b -> b.build())
                .with(values(values),
                        RootValue.RootValueBuilder::name,
                        RootValue.RootValueBuilder::type,
                        RootValue.RootValueBuilder::color,
                        RootValue.RootValueBuilder::texture
                )
                .with(RootValue.RootValueBuilder::branches, branches())
                .singles();
    }

}