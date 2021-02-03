package com.bakeryblueprint.modernjava.week09.flowapi;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstSubscriber<T> implements Flow.Subscriber<String> {
    private AtomicInteger maxNumber;
    private String subscriberName;
    private Subscription subscription;

    public FirstSubscriber(String subscriberName, int maxRequest) {
        this.subscriberName = subscriberName;
        this.maxNumber = new AtomicInteger(maxRequest);
    }

    /**
     * 1. 최초 Publisher에 등록되었을 때 호출되는 메소드
     * Subscription 은 Publisher와 Subscriber 사이에서 메시지 중계
     *
     * @param subscription - Subscription 객체는 주로 Publisher 가 외부에서 생성하고 호출
     */
    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1); // Publisher 에게 메시지를 요청, 몇 번 호출할지 지정
    }

    /**
     * 2. Publisher로 부터 메시지를 수신했을 때 호출되는 메소드
     * 메시지 수신 이후의 작업 정의 (수신한 메서드를 자신의 비즈니스 요건에 맞게 내용 구현)
     *
     * @param item
     */
    @Override
    public void onNext(String item) {
        System.out.println(subscriberName + ", 수신 항목 : " + item);

        // 최대 호출 값을 하나 줄인다.
        maxNumber.decrementAndGet();
        // -1이 되기 전까지 반복해서 Publisher에게 데이터를 요청한다.(별다른 조건 없으면 무한으로 메시지 요청)
        if(maxNumber.get() > -1) {
            // 1초 대기 후 요청
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscription.request(1);
        }
        else {
            subscription.cancel();
        }
    }

    /**
     * 4. 에러가 발생했을 때 호출되는 메소드
     * cancel 메서드를 호출할 필요는 없음 (이미 Publihser에 복구할 수 없는 에러가 발생하여 연결이 끊어졌으므로)
     *
     * @param throwable
     */
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 3. 종료되었을 때 호출되는 메소드
     * 종료를 위한 자원의 해제 - cancel()
     */
    @Override
    public void onComplete() {
        System.out.println(subscriberName + ", 완료");
        subscription.cancel();  // Subscription 종료되어 메시지 흐름과 관련된 모든 객체의 관계가 끊어짐
    }
}