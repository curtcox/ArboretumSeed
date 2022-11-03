package com.curtcox.arboretumseed;

import lombok.*;

import java.util.*;

@Value @Builder
class RootValue {

   private String name, type, color, texture;
   private double hardness;
   private List<BranchValue> branches;

}
