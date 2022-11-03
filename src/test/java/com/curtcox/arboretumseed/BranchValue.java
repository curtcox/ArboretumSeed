package com.curtcox.arboretumseed;

import lombok.*;

import java.util.*;

@Value @Builder
final class BranchValue {

    private String name, value, description, brachiness;
    private int arity;
    @Builder.Default private List<LeafValue> leaves = new ArrayList<>();

}
