package com.bakeryblueprint.modernjava.week04_ssookie;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
Q.1 ExecutorService 또는 ScheduledExecutorService 를 사용해서 2초 쉬고 '본인 이름 + 쓰레드 이름' 을 찍는 작업을 5개 생성하고 실행하세요
(Thread.sleep 사용금지, 어떤 쓰레드 POOL 을 사용하던 상관 없음)
=> 5개의 thread sout 찍히면 됨?
 */
public class ExecutorExample {
    public static void main(String[] args) {
        // ScheduledExecutorService 객체 생성
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        // 0초 후 바로 실행, 2초 주기로 반복
        executorService.scheduleWithFixedDelay(getMyThreadName(), 0, 2, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(getMyThreadName(), 0, 2, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(getMyThreadName(), 0, 2, TimeUnit.SECONDS);
    }

    private static Runnable getMyThreadName() {
        return() -> System.out.println("ssookie : " + Thread.currentThread().getName());
    }
}
