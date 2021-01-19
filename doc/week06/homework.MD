## Q1. andy-java 폴더 하위에 쓸 수 있는 파일/디렉터리를 출력하시오. (람다표현식 사용) P292

## Q2. /andy-java/doc/week00 폴더 하위에 새로운 파일이 추가되는 것을 감지하고있다가 출력해주는 로직을 작성하시오.

```java
public class WatchingDirectory {

    public static void main(String[] args) throws IOException {
        // 1. 모니터링을 하는 WatchService 객체를 생성

        // 2. 모니터링 대상 경로를 생성하고 WatchService에 등록한다.
        Path logsDir = Paths.get("/Users/doyeon/Documents/STUDY/andy-java/doc/week00");

        // 3. 변경 감지 프로그램 작성
        while (true) {
            try {
                // 4. 키 값 조회

                // 5. 키에 해당하는 변경 목록 조회

                // 6. 변경키 초기화
                changeKey.reset();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```