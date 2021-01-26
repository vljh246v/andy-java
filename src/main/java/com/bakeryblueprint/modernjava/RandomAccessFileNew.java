package com.bakeryblueprint.modernjava;

import java.io.IOException;
        import java.nio.ByteBuffer;
        import java.nio.channels.FileChannel;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.nio.file.StandardOpenOption;

public class RandomAccessFileNew {
    public static void main(String[] args) {

        Path file = Paths.get("/Users/doyeon/Documents/STUDY/andy-java/src/main/java/com/bakeryblueprint/modernjava/test");

        String s = "Java New Features\n";
        byte data[] = s.getBytes();

        ByteBuffer out = ByteBuffer.wrap(data);
        ByteBuffer copy = ByteBuffer.allocate(1024);

        // 데이터를 조회할 파일을 오픈하기 위해 FileChannel을 생성하였다.
        // FileChannel은 SeekableByteChannel 인터페이스를 상속받음.
        try (FileChannel fileChannel
                     = (FileChannel.open(file, StandardOpenOption.READ, StandardOpenOption.WRITE))) {

            // 파일에서 1024바이트를 읽어들인다.
            int index;
            do {
                index = fileChannel.read(copy);
            }
            while (index != -1 && copy.hasRemaining());

            // 1024번째 위치에서 0번째 위치로 이동한 후 데이터를 쓴다.
            fileChannel.position(0);
            while (out.hasRemaining()) {
                fileChannel.write(out);
            }
            out.rewind(); // ByteBuffer를 초기화 한다.

            // 파일의 제일 뒤로 이동한 후 다시 데이터를 쓴다.
            long length = fileChannel.size();
            fileChannel.position(length-1);
            copy.flip();
            while (copy.hasRemaining()) {
                fileChannel.write(copy);
            }

            while (out.hasRemaining()) {
                fileChannel.write(out);
            }
        }
        catch (IOException e) {
            System.out.println("I/O Exception: " + e);
        }
    }
}