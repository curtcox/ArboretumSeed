package com.curtcox.arboretumseed;

import lombok.*;

@Data @Builder(toBuilder = true) @NoArgsConstructor @AllArgsConstructor
public class Leaf {

    private String cellType, text, tag, shape, color;

    Leaf copy() { return toBuilder().build(); }
}
