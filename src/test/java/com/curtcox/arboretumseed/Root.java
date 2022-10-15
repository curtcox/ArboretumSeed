package com.curtcox.arboretumseed;

import lombok.Data;

import java.util.*;

@Data
public class Root {
   private String name, type, color, texture;
   private List<Branch> branches;
}
