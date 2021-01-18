package com.bakeryblueprint.modernjava.week05.homework;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

public class ChannelFileIO {
    public static void main(String[] args) {

        // 파일 경로
        Path sourcePath = Paths.get("/Users/ssookie/Documents/ssookie/andy-java/src/main/java/com/bakeryblueprint/modernjava/week05/homework/homework_answer.txt");
        Path targetPath = Paths.get("/Users/ssookie/Documents/ssookie/andy-java/src/main/java/com/bakeryblueprint/modernjava/week05/homework/homework_path.txt");

        // 파일을 생성할 때 사용할 옵션을 지정한다.
        // 없으면 생성하고 있으면 파일의 제일 뒷부분에 데이터를 추가하는 어펜드 모드를 사용
        Set<OpenOption> options = new HashSet<OpenOption>();
        options.add(StandardOpenOption.APPEND);
        options.add(StandardOpenOption.CREATE);

        // 파일 생성시 퍼미션 지정.
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr--r--");
        FileAttribute<Set<PosixFilePermission>> attr =
                PosixFilePermissions.asFileAttribute(perms);

        // 저장할 데이터를 작성
        Path normalizePath = sourcePath.toAbsolutePath().normalize();
        String result = "파일이름: " + normalizePath.getFileName() + "\n절대 경로: " + normalizePath;
        byte data[] = result.getBytes();
        ByteBuffer bb = ByteBuffer.wrap(data);

        // SeekableBytechannel을 이용
        try (SeekableByteChannel sbc =
                     Files.newByteChannel(targetPath, options, attr)) {
            sbc.write(bb);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
