package com.bakeryblueprint.modernjava.week06_jaehyun.homework;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class Homework2 {

  public static void main(final String[] args) throws IOException {
    // 1. 모니터링을 하는 WatchService 객체를 생성
    final WatchService watchService = FileSystems.getDefault().newWatchService();

    // 2. 모니터링 대상 경로를 생성하고 WatchService에 등록한다.
    final Path logsDir = Paths.get("D:\\workspaces\\jaehyun\\andy-java\\doc\\week00");
    logsDir.register(watchService,
        StandardWatchEventKinds.ENTRY_CREATE,
        StandardWatchEventKinds.ENTRY_MODIFY,
        StandardWatchEventKinds.ENTRY_DELETE);

    // 3. 변경 감지 프로그램 작성
    while (true) {
      try {
        // 4. 키 값 조회
        final WatchKey changeKey = watchService.take();
        final List<WatchEvent<?>> watchEvents = changeKey.pollEvents();

        // 5. 키에 해당하는 변경 목록 조회
        for (final WatchEvent<?> watchEvent : watchEvents) {
          final WatchEvent<Path> pathEvent = (WatchEvent<Path>) watchEvent;
          final Path path = pathEvent.context();

          final WatchEvent.Kind<Path> eventKind = pathEvent.kind();
          System.out.println(eventKind + " for path " + path);

          // 6. 변경키 초기화
          changeKey.reset();
        }
      } catch (final InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
