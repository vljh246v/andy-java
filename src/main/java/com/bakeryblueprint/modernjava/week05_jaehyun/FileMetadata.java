package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;

public class FileMetadata {

  public static void main(final String[] args) {
    if (args.length > 1) {
      System.out.println("Usage : java FileMetadata <path>");
      return;
    }

    final Path file = Paths.get(args[0]);

    if (Files.exists(file)) {
      try {
        final BasicFileAttributes attr =
            Files.readAttributes(file, BasicFileAttributes.class);

        System.out.println("creationTime : " + attr.creationTime());
        System.out.println("lastAccessTime : " + attr.lastAccessTime());
        System.out.println("lastModifiedTime : " + attr.lastModifiedTime());

        System.out.println("isDirectory : " + attr.isDirectory());
        System.out.println("isOther : " + attr.isOther());
        System.out.println("isRegularFile : " + attr.isRegularFile());
        System.out.println("isSymbolicLink : " + attr.isSymbolicLink());
        System.out.println("size : " + attr.size());

        final DosFileAttributes dosAttr =
            Files.readAttributes(file, DosFileAttributes.class);
        System.out.println("isReadOnly : " + dosAttr.isReadOnly());
        System.out.println("isHidden : " + dosAttr.isHidden());
        System.out.println("isArchive : " + dosAttr.isArchive());
        System.out.println("isSystem. : " + dosAttr.isSystem());
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
  }

}
