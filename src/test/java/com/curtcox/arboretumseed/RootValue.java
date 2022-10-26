package com.curtcox.arboretumseed;

import lombok.*;

import java.util.*;

@Value @Builder
class RootValue {

   private String name, type, color, texture;
   private List<BranchValue> branches;

}
