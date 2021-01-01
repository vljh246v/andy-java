package com.bakeryblueprint.modernjava.week04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ForkJoinDirSize extends RecursiveTask<Long> {

  private final Path path;

  public ForkJoinDirSize(final Path path) {
    this.path = path;
  }

  public static void main(final String[] args) {
    final Path rootPath = Paths.get("../");

    final ForkJoinPool forkJoinPool = new ForkJoinPool();
    System.out.println(forkJoinPool.getParallelism());
    System.out.println(forkJoinPool.invoke(new ForkJoinDirSize(rootPath)));

  }

  @Override
  protected Long compute() {
    long fileSize = 0;
    if (Files.isDirectory(this.path)) {
      try {
        final List<Path> fileList = Files.list(this.path).collect(Collectors.toList());
        final List<ForkJoinDirSize> subTaskList = new ArrayList<>();

        for (final Path file : fileList) {
          final ForkJoinDirSize subTask = new ForkJoinDirSize(file);
          subTask.fork();
          subTaskList.add(subTask);
        }

        Long subSize = 0L;
        for (final ForkJoinDirSize subTask : subTaskList) {
          subSize += subTask.join();
        }
        return subSize;
      } catch (final IOException ioe) {
        System.out.println("Error : " + this.path);
      }
    } else {
      try {
        fileSize = Files.size(this.path);
      } catch (final IOException ioe) {
        System.out.println("Error : " + this.path);
      }
    }
    return fileSize;
  }
}
