package practical.chapter8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class DateFormatError {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 하나의 DateFormat을 생성하고 공유
        //final DateFormat format = new SimpleDateFormat("yyyyMMdd");
        //Callable<Date> task = () -> format.parse("20101022");

        // 필요할 때마다 생성한다.
        Callable<Date> task = () -> new SimpleDateFormat("yyyyMMdd").parse("20101022");

        // 스레드 풀 5개 생성
        ExecutorService exec = Executors.newFixedThreadPool(5);
        List<Future<Date>> results = new ArrayList<Future<Date>>();

        // 5개의 스레드에서 동시 처리
        for(int i=0; i<100 ; i++) {
            results.add(exec.submit(task));
        }
        exec.shutdown();

        // 에러 발생
        for(Future<Date> result : results) {
            System.out.println(result.get());
        }
    }
}
