package com.bakeryblueprint.modernjava.week06_ssookie;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Q1. andy-java 폴더 하위에 쓸 수 있는 파일/디렉터리를 출력하시오. (람다표현식 사용) P292
 * 쓰기 권한이 있는 것 출력
 */
public class FileFilter {
    public static void main(String[] args) {
        Path dir = Paths.get("/Users/ssookie/Documents/ssookie/andy-java");

        // define Filter
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            public boolean accept(Path file) {
                return ( Files.isWritable(file));
            }
        };

        // Lambda Expression
        DirectoryStream.Filter<Path> filterLambda = (Path file) -> Files.isWritable(file);

        // DirectoryStream.Filter 기준으로 목록 조회
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filterLambda)){
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
