package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OldFileCopy {

  public static void copyFileUseFileReaderAndWriter(final File sourceFile,
      final File targetFile) {
    if (sourceFile.exists() && sourceFile.isFile()) {
      try (final BufferedReader br = new BufferedReader(new FileReader(sourceFile));
          final BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
        System.out.println("## copyFileUseFileReaderAndWriter 파일 복사 시작 ##");

        final char[] buffer = new char[1024];
        int readCount = 0;

        while ((readCount = br.read(buffer)) > 0) {
          bw.write(buffer, 0, readCount);
        }

        System.out.println("## copyFileUseFileReaderAndWriter 파일 복사 완료 ##");

      } catch (final FileNotFoundException e) {
        e.printStackTrace();
      } catch (final IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("파일을 복사할 수 없습니다.");
    }
  }


  public static void copyFileUseFileStream(final File sourceFile,
      final File targetFile) {
    if (sourceFile.exists() && sourceFile.isFile()) {
      try (final BufferedInputStream in = new BufferedInputStream(
          new FileInputStream(sourceFile));
          final BufferedOutputStream out = new BufferedOutputStream(
              new FileOutputStream(targetFile))) {
        System.out.println("## copyFileUseFileStream 파일 복사 시작 ##");

        final byte[] buffer = new byte[1024];
        int readCount = 0;

        while ((readCount = in.read(buffer)) > 0) {
          out.write(buffer, 0, readCount);
        }

        System.out.println("## copyFileUseFileStream 파일 복사 완료 ##");

      } catch (final IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("파일을 복사할 수 없습니다.");
    }
  }

  public static void main(final String[] args) {

    if (args.length > 2) {
      System.out.println("Usage : java OldFileCopy <source file> <target file>");
      return;
    }
    final File sourceFile = new File(args[0]);
    final File targetFile = new File(args[1]);

    OldFileCopy.copyFileUseFileReaderAndWriter(sourceFile, targetFile);
//    OldFileCopy.copyFileUseFileStream(sourceFile, targetFile);
  }
}
