package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NewFileCopy {

  public static void copyFile(final Path source, final Path target,
      final CopyOption[] copyOptions) {
    if (Files.isDirectory(source) || Files.notExists(source)) {
      System.out.println("소스 파일이 존재하지 않거나 디렉터리 입니다.");
      return;
    }

    try {
      if (Files.exists(target)) {
        System.out.println("대상 파일이 존재합니다.");
      }

      Files.copy(source, target, copyOptions);
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(final String[] args) {
    if (args.length > 2) {
      System.out.println("Usage : java newFileCopy <source file> <target file>");
      return;
    }
    final Path source = Paths.get(args[0]);
    final Path target = Paths.get(args[1]);

    final CopyOption[] options = {StandardCopyOption.REPLACE_EXISTING};

    NewFileCopy.copyFile(source, target, options);
  }

}
