package com.curtcox.arboretumseed;

import lombok.*;

import java.util.*;

@Value @Builder
final class BranchValue {

    private String name, value, description, brachiness, arity;
    private List<LeafValue> leaves;

}
