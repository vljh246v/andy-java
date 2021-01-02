package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertingPath {

  public static void main(final String[] args) throws IOException {
    if (args.length < 1) {
      System.out.println("usage: ConvertingPaht file");
      System.exit(-1);
    }

    final Path inputPath = Paths.get(args[0]);
    final Path fullPath = inputPath.toAbsolutePath();

    System.out.println("fullPath = " + fullPath);
  }

}
