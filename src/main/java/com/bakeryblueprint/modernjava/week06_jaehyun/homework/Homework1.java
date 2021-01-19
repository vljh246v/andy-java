package com.bakeryblueprint.modernjava.week06_jaehyun.homework;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Homework1 {

  public static void main(final String[] args) {
    final Path dir = Paths.get("D:\\workspaces\\jaehyun\\andy-java");

    final DirectoryStream.Filter<Path> filter = Files::isWritable;

    try (final DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
      for (final Path file : stream) {
        System.out.println(file.getFileName());
      }
    } catch (final IOException | DirectoryIteratorException e) {
      e.printStackTrace();
    }
  }
}
