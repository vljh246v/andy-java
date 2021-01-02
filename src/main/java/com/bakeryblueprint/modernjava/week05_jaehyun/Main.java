package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

  public static void main(final String[] args) {
    Main.createPath();
  }

  public static void createPath() {
    // 문자열을 이용한 Path 객체 생성
    final Path p1 = Paths
        .get("../../main/java/com/bakeryblueprint/modernjava/week05_jaehyun/test.txt");

    // URI 클래스를 이용한 Path 객체 생성
    final Path p2 = Paths.get(URI.create(
        "file:///D:/workspaces/jaehyun/andy-java/src/main/java/com/bakeryblueprint/modernjava/week05_jaehyun/test.txt"));

    // FileSystems  클래스를 이용한 Path 객체 생성
    final Path p3 = FileSystems.getDefault()
        .getPath("../../main/java/com/bakeryblueprint/modernjava/week05_jaehyun/test.txt");

  }

}
