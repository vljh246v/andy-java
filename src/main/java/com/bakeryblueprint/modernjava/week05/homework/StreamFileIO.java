package com.bakeryblueprint.modernjava.week05.homework;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class BirthDay //클래스 BirthDay
{
    String name;
    String birthDay;

    public BirthDay(String name, String birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String toString() {
        return "Name : " + this.name + ", Birthday : " + this.birthDay;
    }

}

public class StreamFileIO {
    public static void main(String[] args) {

        ArrayList<BirthDay> birthDays = new ArrayList<BirthDay>();

        // 파일 경로
        Path sourcePath = Paths.get("/Users/ssookie/Documents/ssookie/andy-java/src/main/java/com/bakeryblueprint/modernjava/week05/homework/homework.txt");
        Path targetPath = Paths.get("/Users/ssookie/Documents/ssookie/andy-java/src/main/java/com/bakeryblueprint/modernjava/week05/homework/homework_answer.txt");

        // InputStream, OutputStream을 이용하여 파일 처리
        try (
                // 파일을 읽기 위한 InputStream 객체
                InputStream input = Files.newInputStream(sourcePath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));    // 성능 문제 해결을 위한 버퍼링 작업
                // 파일을 열거나 데이터를 저장하기 위한 OutputStream 객체
                OutputStream out = Files.newOutputStream(targetPath);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))
        ) {
            String line = null;
            String[] birthDay = null;

            // 파일에서 데이터를 한 줄씩 읽어들임.
            // BufferedReader reader 이용해 한줄을 읽어 와서 문자열 line에 대입하고 line이 널이 아니면 반복
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                birthDay = line.split("\t");

                // 비즈니스 로직 구현.
                birthDays.add(new BirthDay(birthDay[0], birthDay[1]));
            }

            // Stream을 통한 생년월일 정렬
            line = birthDays.stream()
                    .sorted((a, b) -> a.getBirthDay().compareTo(b.getBirthDay()))
                    .map(Object::toString)
                    .collect(Collectors.joining("\n"));

            // 데이터를 파일에 저장.
            writer.write(line, 0, line.length());
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }
}
