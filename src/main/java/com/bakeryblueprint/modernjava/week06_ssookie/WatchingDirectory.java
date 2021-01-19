package com.bakeryblueprint.modernjava.week06_ssookie;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Q2. /andy-java/doc/week00 폴더 하위에 새로운 파일이 추가되는 것을 감지하고있다가 출력해주는 로직을 작성하시오.
 */
public class WatchingDirectory {
    public static void main(String[] args) throws IOException {
        // 1. 모니터링을 하는 WatchService 객체를 생성
        WatchService watchService = FileSystems.getDefault().newWatchService();

        // 2. 모니터링 대상 경로를 생성하고 WatchService에 등록한다.
        Path logsDir = Paths.get("/Users/ssookie/Documents/ssookie/andy-java/doc/week00");
        logsDir.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);

        // 4. 변경 감지 프로그램 작성
        while (true) {
            try {
                // 5. 키 값 조회
                WatchKey changeKey = watchService.take();
                List<WatchEvent<?>> watchEvents = changeKey.pollEvents();

                // 6. 키에 해당하는 변경 목록 조회
                for (WatchEvent<?> watchEvent : watchEvents) {
                    WatchEvent<Path> pathEvent = (WatchEvent<Path>) watchEvent;
                    Path path = pathEvent.context();
                    WatchEvent.Kind<Path> eventKind = pathEvent.kind();
                    System.out.println(eventKind + " for path: " + path);
                }

                // 7. 변경키 초기화
                changeKey.reset();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
