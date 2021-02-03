package com.bakeryblueprint.modernjava.week09.flowapi;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;

/**
 * Publisher 개발 예제
 */
public class FirstPublisher<T> implements Flow.Publisher<String> {

    /**
     * Subscriber 객체를 Publisher에 등록, Publisher에서 관리하고 있는 Subscription 객체를 Subscriber 에 전달
     * → Subscription을 기준으로, Publisher과 Subscriber의 관계를 정의하고 프로그램이 종료할 때까지 연결을 유지
     * → 이후의 모든 작업은 Subscription 객체를 통해 구현
     * @param subscriber
     */
    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        subscriber.onSubscribe(new MySubscription(subscriber));
    }

    public static void main(String[] args) throws Exception {
        FirstPublisher<String> publisher = new FirstPublisher<>();
        publisher.subscribe(new FirstSubscriber<>("Subscriber 1", 10));
        publisher.subscribe(new FirstSubscriber<>("Subscriber 2", 10));
    }

    /**
     * Subscription 구현
     * Subscriber 에서 호출한 request, cancel 메서드는 모두 Subscription 을 구현한 클래스에 정의함
     */
    class MySubscription implements Flow.Subscription {
        private Subscriber<? super String> subscriber;

        public MySubscription(Subscriber<? super String> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            publishItems(n);
        }

        @Override
        public void cancel() {
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