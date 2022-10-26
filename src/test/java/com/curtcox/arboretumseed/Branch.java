package com.curtcox.arboretumseed;

import lombok.*;

import java.util.*;

@Data @Builder(toBuilder = true) @NoArgsConstructor @AllArgsConstructor
final class Branch {

    private String name, value, description, brachiness, arity;
    private List<Leaf> leaves = new ArrayList<>();

    Branch copy() { return toBuilder().build(); }

}
