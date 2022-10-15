package com.curtcox.arboretumseed;

import lombok.*;

import java.util.*;

@Data @Builder(toBuilder = true) @NoArgsConstructor @AllArgsConstructor
public class Root {

   private String name, type, color, texture;
   private List<Branch> branches;

   Root copy() { return toBuilder().build(); }

}
