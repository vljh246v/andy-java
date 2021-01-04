package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChecker {

  public static void main(final String[] args) {
    if (args.length < 1) {
      FileChecker.printUsage();
      return;
    }

    final Path path = Paths.get(args[0]);

    if (Files.exists(path) && !Files.notExists(path)) {
      System.out.println("Path 가 존재합니다.");
    } else {
      System.out.println("Path 가 존재하지 않거나 문제가 있습니다.");
    }

    if (Files.isDirectory(path)) {
      System.out.println("디렉터리");
    } else {
      System.out.println("파일");
    }

    if (Files.isWritable(path)) {
      System.out.println("쓰기 가능");
    }

    if (Files.isReadable(path)) {
      System.out.println("읽기 가능");
    }

    if (Files.isExecutable(path)) {
      System.out.println("실행 가능");
    }

    System.out.println("Regular Files : " + Files.isRegularFile(path));

  }

  public static void printUsage() {
    System.out.println("java FilesChecker <path information>");
    System.out.println("<path information> : file or directory path");
  }
}
