package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SmallFileIO {

  public static byte[] readAllFromFile(final Path filePath) throws IOException {
    final byte[] fileArray = Files.readAllBytes(filePath);
    return fileArray;
  }

  public static void writeAllToFile(final Path filePath, final byte[] fileArray)
      throws IOException {
    Files.write(filePath, fileArray, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
  }

  public static void main(final String[] args) {
    if (args.length > 1) {
      System.out.println("Usage : java SmallFileIO <path>");
      return;
    }

    final String filePath = args[0];

    try {
      final byte[] fileContents = SmallFileIO.readAllFromFile(Paths.get(filePath));
      System.out.println(fileContents.length);
      SmallFileIO.writeAllToFile(Paths.get(filePath), fileContents);
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
