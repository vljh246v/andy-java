package com.bakeryblueprint.modernjava.week06_doyeon;

import java.io.IOException;
import java.nio.file.*;

public class StreamFilter {
    public static void main(String[] args) {

        Path dirPath = Paths.get("/Users/doyeon/Documents/STUDY/andy-java");

        try (DirectoryStream<Path> stream
                     = Files.newDirectoryStream(dirPath, (Path file) -> Files.isWritable(file))) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }

    }
}