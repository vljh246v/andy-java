package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.HashSet;
import java.util.Set;

public class FilePermission {

    public static void main(String[] args) throws IOException {



        Set<OpenOption> option = new HashSet<>();

        option.add(StandardOpenOption.APPEND);
        option.add(StandardOpenOption.CREATE);


        Set<PosixFilePermission> permissionSet =
                PosixFilePermissions.fromString("rwxr--r--");
        FileAttribute<Set<PosixFilePermission>> attr =
                PosixFilePermissions.asFileAttribute(permissionSet);

        Path sourcePath = Paths.get("/Users/demo.lim/Documents/workspace/demo/modern-java/src/main/java/com/bakeryblueprint/modernjava/week05_jaehyun/homework_result.txt");
        Path targetPath = Paths.get("/Users/demo.lim/Documents/workspace/demo/modern-java/src/main/java/com/bakeryblueprint/modernjava/week05_jaehyun/homework_result_info.txt");


//        1에서 생성한 파일의 이름과 상위 절대 경로 정보를 출력하는 txt 파일을 생성하세요.
//        파일 생성시 퍼미션을 지정하세요. 소유자에게는 읽기/쓰기/실행 권한을 모두 부여하고, 나머지에게는 읽기 권한만 부여합니다.



        Path fileName = sourcePath.getFileName();
        Path path = sourcePath.getParent().toAbsolutePath();

        String resultStr = "file name : " + fileName.toString() + "\nfile parent path : " + path.toString();
        byte data[] = resultStr.getBytes();
        ByteBuffer bb = ByteBuffer.wrap(data);

        try(SeekableByteChannel outputChannel =
                    Files.newByteChannel(targetPath, option, attr)){
            outputChannel.write(bb);
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
