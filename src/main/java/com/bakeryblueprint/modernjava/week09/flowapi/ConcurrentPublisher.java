package com.bakeryblueprint.modernjava.week09.flowapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 병렬 처리 가능한 Publisher를 구현한 예제 - ExecutorService 이용
 */
public class ConcurrentPublisher<T> implements Flow.Publisher<String> {
    // ExecutorService 객체 생성
    // Subscription을 구현한 클래스에서 생성하면 스레드 풀을 관리하지 못하고 오직 하나의 Subscriber에만 대응하기 때문에
    // 반드시 Publisher 에서 생성한 후 전달해야 함
    private final ExecutorService executor = ForkJoinPool.commonPool();

    @Override
    public synchronized void subscribe(Subscriber<? super String> subscriber) {
        MySubscription subscription = new MySubscription(subscriber, executor);
        subscriber.onSubscribe(subscription);
    }

    /**
     * request 메서드에서 Subscriber 에 직접 메시지 전달하지 않고
     * ExecutorService 이용하여 비동기로 호출
     */
    public static void main(String[] args) throws Exception {
        ConcurrentPublisher<String> publisher = new ConcurrentPublisher<>();
        publisher.subscribe(new FirstSubscriber<>("Subscriber 1", 10));
        publisher.subscribe(new FirstSubscriber<>("Subscriber 2", 10));

        TimeUnit.SECONDS.sleep(10);
    }

    class MySubscription implements Flow.Subscription {
        // ExecutorService 이용하여 병렬 처리
        private ExecutorService executor;
        private Subscriber<? super String> subscriber;
        private Future<?> future;

        public MySubscription(Subscriber<? super String> subscriber, ExecutorService executor) {
            this.subscriber = subscriber;
            this.executor = executor;   // Publisher로부터 Executor Service 전달 받음
        }

        public Future<?> getFuture() {
            return future;
        }

        @Override
        public void request(long n) {   // 비동기 호출
            future = executor.submit(() -> publishItems(n));
        }

        @Override
        public void cancel() {
            if(future != null) future.cancel(false);
            System.out.println("Canceled");
        }

        private void publishItems(long n) {
            // n번 반복해서 메시지를 전송한다.
            for (var i = 0; i < n; i++) {
                subscriber.onNext("Hello Subscriber!! " + n);
            }
        }
    }
}
