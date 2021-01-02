package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesChecker {

  public static void main(final String[] args) {

    final Path path = Paths.get(URI.create(
        "file:///D:/workspaces/jaehyun/andy-java/src/main/java/com/bakeryblueprint/modernjava/week05_jaehyun/test.txt"));

    System.out.println("exists : " + Files.exists(path));
    System.out.println("exists : " + Files.notExists(path));
  }

}
