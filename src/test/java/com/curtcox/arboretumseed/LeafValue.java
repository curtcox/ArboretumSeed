package com.curtcox.arboretumseed;

import lombok.*;
import lombok.Builder;

@Value @Builder
final class LeafValue {
    private String cellType, text, tag, shape, color;
}
