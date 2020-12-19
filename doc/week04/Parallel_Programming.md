# Week 04 "Parallel Programming"

## 1. "분산", "병행", "병렬" 의 차이

* 분산 : 원격 서버 혹은 물리적인 장비에 작업을 분산시켜서 처리한다는 개념
* 병행, 병렬 - 이번 장에서는 그냥 합쳐서 "병렬" 이라는 용어로 통일
    * 공통점 : 하나의 물리적인 서버에서 동작한다는 공통점
    * 차이점
        1. 병행 : 하나의 CPU 코어에서 소프트웨어 적인 기법으로 동시에 여러 작업을 교차하면서 실행 (싱글 코어)
        2. 병렬 : 여러개의 코어에서 작업을 배분해서 동시에 작업을 실행하는 것 (멀티 코어)

## 2. 컨터런트 API

### 2.1. 특징

1. 데이터 동기화와 정합을 확보를 위해 Lock 객체를 제공
2. 대량 데이터 병렬 처리에 적합한 Executors 클래스 제공
3. 정합성 유지를 위한 컨터런트 컬렉션 클래스 제공
4. synchronized 키워드 사용을 최소화, 메모리 정합성 에러를 방지하는 기능 제공
5. 멀티 스레드 환경에서 효율적인 난수 생성 기능 제공

### 2.2. 컨커런트 API 패키지  [참고](https://www.uml-diagrams.org/java-7-concurrent-uml-class-diagram-example.html)

* 실행자 (Executors)
    * 작업을 실행하는 역할을 함
    * 구현체에 따라 "새로운 스레드 생성 / 기존 생성 스레드 재활용", "순차 / 병렬"
    * 스캐줄링 기능
* 큐 (Queues)
    * 멀티 스레드 환경에서 안정성을 보장
    * 사용하는 유형에 따라 다른 구현체를 제공
* 타이밍 (Timing)
    * 시간에 대한 정확성을 높이기 위해 사용
* 동기화 (Synchronizers)
    * Semaphore 클래스를 통해 쉽게 구현 가능
* 컨커런트 컬렉션
    * 컨커런트 API 환경에서 데이터를 다루기 위한 컬렉션 인터페이스 / 클래스

### 2.3. Executors 클래스

* 스레드 관리와 비즈니스 구현을 분리 가능
* Executors, ExecutorService, ScheduledExecutorService 인터페이스가 있음

#### 2.3.1. Executors 인터페이스

* Thread 와 Runnable 인터페이스를 이용한 구현

```java
Thread myThread=new Thread(new Runnable(){
  @Override
  public void run(){
    System.out.println(Thread.currentThread());
  }
});

myThread.start();
```

* Executor 인터페이스를 이용한 구현

```java
Executor e=new Executor(){
  @Override
  public void execute(Runnable task){
    // 1. runnable 인터페이스를 직접 실행
    task.run();

    // 2. Thread를 생성해서 실행
    new Thread(task).start();
  }
};
e.execute(()->System.out.println(Thread.currentThread()));
```

#### 2.3.2. ExecutorService 인터페이스

* 스레드 생성 / 관리 를 위한 메소드 추가

```java
// ExecutorService 객체 생성
final ExecutorService executorService = Executors.newSingleThreadExecutor();

// Thread 실행
executorService.execute(new MyTask("TODO 1"));
executorService.execute(new MyTask("TODO 2"));
executorService.execute(new MyTask("TODO 3"));

// ExecutorService 종료
executorService.shutdown();

/* ... */
public class MyTask implements Runnable {
  // ...
  TimeUnit.SECONDS.sleep(1);
  // ...
}
/* ... */
```
* ExecutorService : 많이 사용되는 핵심 인터페이스
* Executors : 구현체를 직접 제공
* TimeUnit : 시간 관련 클래스
* 실제 ExecutorService 구현 하기가 까다로움, 그래서 유틸성으로 Executors 제공
```java
Executors.newSingleThreadExecutor();
Executors.newFixedThreadPool(10);
Executors.newCachedThreadPool();
Executors.newWorkStealingPool();
Executors.unconfigurableExecutorService(Executors.newSingleThreadExecutor());
```
* newSingleThreadExecutor : 오직 하나의 스레드로 처리
* newFixedThreadPool : 생성할 스레드풀 크기 지정
* newCachedThreadPool : 멀티 스레드 / 기존 스레드 재사용
* newWorkStealingPool : 사용 가능한 모든 프로세스를 사용하도록 설정
* unconfigurableExecutorService : ExecutorService 가 가지는 인터페이스로 실행 제한시 사용
> 어떤 스레드 풀을 이용해서 병렬 처리할 것인가를 잘 생각해야함
#### 2.3.3. ScheduledExecutorService 인터페이스
* Task 스캐줄링 기능 추가
* 주기적 실행, 예약 기능에 주로 사용
```java
Executors.newScheduledThreadPool(10);
Executors.newSingleThreadScheduledExecutor();
Executors.unconfigurableScheduledExecutorService(Executors.newSingleThreadScheduledExecutor());


/* ... */
final ScheduledExecutorService exeService = Executors
        .newSingleThreadScheduledExecutor();

exeService.schedule(() -> System.out.println("TODO 1"), 5, TimeUnit.SECONDS);
exeService.schedule(() -> System.out.println("TODO 2"), 10, TimeUnit.SECONDS);
exeService.schedule(() -> System.out.println("TODO 3"), 15, TimeUnit.SECONDS);

exeService.shutdown();
/* ... */
```
