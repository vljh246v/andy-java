package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteFileAndDirectory {

  public static void main(final String[] args) {
    if (args.length > 1) {
      System.out.println("Usage : java DeleteFileAndDirectory <path>");
      return;
    }

    final Path path = Paths.get(args[0]);

    try {
      Files.delete(path);
    } catch (final NoSuchFileException nsfe) {
      System.out.println(path + " : 파일 혹은 디렉터리가 없습니다.");
      nsfe.printStackTrace();
    } catch (final DirectoryNotEmptyException dnee) {
      System.out.println(path + " : 디렉터리가 비어 있지 앖습니다.");
      try {
        if (!Files.isSymbolicLink(path)) {
          DeleteFileAndDirectory.deleteNotEmptyDirectory(path);
        }
      } catch (final IOException ioe) {
        System.err.println(ioe);
      }
    } catch (final IOException ioe2) {
      System.err.println(ioe2);
    }
  }

  public static void deleteNotEmptyDirectory(final Path path) throws IOException {
    if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
      try (final DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
        for (final Path entry : entries) {
          DeleteFileAndDirectory.deleteNotEmptyDirectory(entry);
        }
      }
      Files.delete(path);
    }
  }
}
