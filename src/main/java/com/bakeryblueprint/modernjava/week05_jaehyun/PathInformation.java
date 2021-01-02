package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathInformation {

  public static void main(final String[] args) {

    final Path path = Paths.get("C:\\Windows\\System32\\drivers");

    System.out.println("toString : " + path.toString());
    System.out.println("getFileName : " + path.getFileName());
    System.out.println("getName : " + path.getName(0));
    System.out.println("getNameCount : " + path.getNameCount());
    System.out.println("subpath(0, 2) : " + path.subpath(0, 2));
    System.out.println("getParent : " + path.getParent());
    System.out.println("getRoot : " + path.getRoot());
  }

}
