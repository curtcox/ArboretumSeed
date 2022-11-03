package com.curtcox.arboretumseed;

import lombok.*;

import java.util.*;

@Data @Builder(toBuilder = true) @NoArgsConstructor @AllArgsConstructor
final class Root {

   private String name, type, color, texture;
   private double hardness;
   private List<Branch> branches = new ArrayList<>();

   Root copy() { return toBuilder().build(); }

}
