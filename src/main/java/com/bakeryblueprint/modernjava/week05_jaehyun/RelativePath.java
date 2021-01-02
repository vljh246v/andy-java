package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativePath {

  public static void main(final String[] args) {
    final Path path = Paths.get("./com/bakeryblueprint/modernjava/week05_jaehyun/test.txt");

    System.out.println("toString : " + path.toString());
    System.out.println("getFileName : " + path.getFileName());
    System.out.println("getName : " + path.getName(0));
    System.out.println("getNameCount : " + path.getNameCount());
    System.out.println("subpath(0, 2) : " + path.subpath(0, 2));
    System.out.println("getParent : " + path.getParent());
    System.out.println("getRoot : " + path.getRoot());
    System.out.println("getAbsolutePath : " + path.toAbsolutePath());
  }

}
