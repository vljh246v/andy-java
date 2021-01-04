package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BufferedFileIO {

  public static void main(final String[] args) {
    if (args.length > 2) {
      System.out.println("Usage : java BufferedFileIO <source> <target>");
      return;
    }

    final Path sourcePath = Paths.get(args[0]);
    final Path targetPath = Paths.get(args[1]);

    final Charset charset = Charset.forName("ISO-8859-1");

    try (final BufferedReader reader = Files.newBufferedReader(sourcePath, charset);
        final BufferedWriter writer = Files.newBufferedWriter(targetPath, charset)) {

      String line = null;

      while ((line = reader.readLine()) != null) {
        System.out.println(line.length());

        writer.write(line, 0, line.length());
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
