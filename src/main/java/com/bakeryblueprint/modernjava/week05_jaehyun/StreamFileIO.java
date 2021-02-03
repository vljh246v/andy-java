package com.bakeryblueprint.modernjava.week05_jaehyun;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFileIO {
    public static void main(String[] args) {

        ArrayList<BirthDay> birthDays = new ArrayList<BirthDay>();
        
        // 파일 경로
        Path sourcePath = Paths.get("/Users/demo.lim/Documents/workspace/demo/modern-java/src/main/java/com/bakeryblueprint/modernjava/week05_jaehyun/homework.txt");
        Path targetPath = Paths.get("/Users/demo.lim/Documents/workspace/demo/modern-java/src/main/java/com/bakeryblueprint/modernjava/week05_jaehyun/homework_result.txt");

        // InputStream, OutputStream을 이용하여 파일 처리
        try (InputStream input = Files.newInputStream(sourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = Files.newOutputStream(targetPath);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output))) {


            String line = null;
            String[] birthDay = null;

            // 파일에서 데이터를 한 줄씩 읽어들임.
            // BufferedReader reader 이용해 한줄을 읽어 와서 문자열 line에 대입하고 line이 널이 아니면 반복

            while((line = reader.readLine()) != null){
                birthDay = line.split("\t");
                birthDays.add(new BirthDay(birthDay[0], birthDay[1]));
            }

            // Stream을 통한 생년월일 정렬
            /* ... */
            List<BirthDay> collect = birthDays.stream()
                    .sorted(Comparator.comparing(it -> Integer.parseInt(it.getBirthDay()), Integer::compare))
                    .collect(Collectors.toList());

            // 데이터를 파일에 저장.
            /* ... */
            for (BirthDay s : collect) {
                writer.write(s.getName() + "\t" + s.getBirthDay() + "\n");
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}