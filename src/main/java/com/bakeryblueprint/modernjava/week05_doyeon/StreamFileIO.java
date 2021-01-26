package com.bakeryblueprint.modernjava.week05_doyeon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class StreamFileIO {
    public static void main(String[] args) {

        ArrayList<BirthDay> birthDays = new ArrayList<BirthDay>();

        // 파일 경로
        Path sourcePath = Paths.get("/Users/doyeon/Documents/STUDY/andy-java/doc/week05/homework/homework.txt");
        Path targetPath = Paths.get("/Users/doyeon/Documents/STUDY/andy-java/doc/week05/homework/homework2.txt");

        // InputStream, OutputStream을 이용하여 파일 처리
        try (InputStream input = Files.newInputStream(sourcePath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream out = Files.newOutputStream(targetPath);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            String line = null;
            String[] birthDay = null;

            // 파일에서 데이터를 한 줄씩 읽어들임.
            // BufferedReader reader 이용해 한줄을 읽어 와서 문자열 line에 대입하고 line이 널이 아니면 반복
            while ((line = reader.readLine()) != null) {
                birthDay = line.split("\t");
                birthDays.add(new BirthDay(birthDay[0], birthDay[1]));
            }

            // Stream을 통한 생년월일 정렬
            line = birthDays.stream().sorted((a, b) -> a.getBirthDay().compareTo(b.getBirthDay()))
                    .map(Object::toString).collect(Collectors.joining("\n"));

            // 데이터를 파일에 저장.
            writer.write(line, 0, line.length());
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }
}
