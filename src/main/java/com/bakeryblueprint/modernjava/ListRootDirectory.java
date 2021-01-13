package com.bakeryblueprint.modernjava;

import java.nio.file.FileSystems;
        import java.nio.file.Path;

public class ListRootDirectory {
    public static void main(String[] args) {
        Iterable<Path> rootDirs = FileSystems.getDefault().getRootDirectories();

        for (Path rootPath : rootDirs) {
            System.out.println(rootPath);
        }
    }
}