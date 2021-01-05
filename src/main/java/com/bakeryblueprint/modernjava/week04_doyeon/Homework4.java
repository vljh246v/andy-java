package com.bakeryblueprint.modernjava.week04_doyeon;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

public class Homework4 {

    public static void main(String[] args) {

        ScheduledExecutorService exeService = Executors.newScheduledThreadPool(5);
        exeService.schedule(()-> System.out.println("도연1 " + Thread.currentThread().getName()), 2, TimeUnit.SECONDS);
        exeService.schedule(()-> System.out.println("도연2 " + Thread.currentThread().getName()), 2, TimeUnit.SECONDS);
        exeService.schedule(()-> System.out.println("도연3 " + Thread.currentThread().getName()), 2, TimeUnit.SECONDS);
        exeService.schedule(()-> System.out.println("도연4 " + Thread.currentThread().getName()), 2, TimeUnit.SECONDS);
        exeService.schedule(()-> System.out.println("도연5 " + Thread.currentThread().getName()), 2, TimeUnit.SECONDS);

        exeService.shutdown();

    }
}
