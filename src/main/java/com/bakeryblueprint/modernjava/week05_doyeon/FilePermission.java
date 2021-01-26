package com.bakeryblueprint.modernjava.week05_doyeon;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

public class FilePermission {
    public static void main(String[] args) {
        Path sourcePath = Paths.get("/Users/doyeon/Documents/STUDY/andy-java/doc/week05/homework/homework2.txt");
        Path targetPath = Paths.get("/Users/doyeon/Documents/STUDY/andy-java/doc/week05/homework/homework3.txt");

        Set<OpenOption> options = new HashSet<OpenOption>();
        options.add(StandardOpenOption.APPEND);
        options.add(StandardOpenOption.CREATE);

        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr--r--");
        FileAttribute<Set<PosixFilePermission>> attr =
                PosixFilePermissions.asFileAttribute(perms);

        String result = "절대 경로: " + sourcePath.toAbsolutePath().normalize();
        byte data[] = result.getBytes();
        ByteBuffer bb = ByteBuffer.wrap(data);

        try (SeekableByteChannel sbc = Files.newByteChannel(targetPath, options, attr)) {
            sbc.write(bb);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
