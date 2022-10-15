package com.curtcox.arboretumseed;

import lombok.Data;

import java.util.*;

@Data
public class Branch {
    private String name, value, description, brachiness, arity;
    private List<Leaf> leaves;
}
