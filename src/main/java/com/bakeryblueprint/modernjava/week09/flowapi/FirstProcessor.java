package com.bakeryblueprint.modernjava.week09.flowapi;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Processor는 Subscriber와 Publisher의 기능을 모두 가진다.
 */
public class FirstProcessor<T, R> extends SubmissionPublisher<R> implements Flow.Processor<T, R> {
    private Function<T, R> function;
    private Flow.Subscription subscription;

    public FirstProcessor(Function<T, R> function) {
        this.function = function;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        submit(function.apply(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }

    public static void main(String[] args) throws Exception {
        ConcurrentPublisher<String> publisher = new ConcurrentPublisher<>();
        FirstProcessor<String, String> processor = new FirstProcessor(name -> name + "값을 변환");

        FirstSubscriber<String> subscriber1 = new FirstSubscriber<>("Subscriber 1", 10);
        FirstSubscriber<String> subscriber2 = new FirstSubscriber<>("Subscriber 2", 10);

        /**
         * ConcurrentPublihser 입장에서의 구독자는 FirstProcessor, FirstProcessor 입장에서의 구독자는 FirstScriber
         */
        // Publisher에 Processor 등록
        publisher.subscribe(processor);
        // Processor에 Subscriber 등록
        processor.subscribe(subscriber1);
        processor.subscribe(subscriber2);

        TimeUnit.SECONDS.sleep(10);
    }
}