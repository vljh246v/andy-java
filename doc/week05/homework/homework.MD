# File 생성, 쓰기, 읽기
## 1. Stream I/O
* \t으로 구분된 이름과 생년월일 정보가 있는 homework.txt 파일을 읽어와 Stream을 이용하여, 생년월일 순서대로 이름과 생일을 출력하는 txt 파일을 생성하세요.
* InputStream, OutputStream 사용하기
* 예제 7.15 참고(page 272) 
```java
class BirthDay //클래스 BirthDay
{
    String name;
    String birthDay;

    public BirthDay(String name, String birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String toString() {
        return "Name : " + this.name + ", Birthday : " + this.birthDay;
    }

}

public class StreamFileIO {
    public static void main(String[] args) {

        ArrayList<BirthDay> birthDays = new ArrayList<BirthDay>();
        
        // 파일 경로
        Path sourcePath = 
        Path targetPath = 

        // InputStream, OutputStream을 이용하여 파일 처리
        try (
               /**
                * ... 
                */
        ) {
            String line = null;
            String[] birthDay = null;

            // 파일에서 데이터를 한 줄씩 읽어들임.
            // BufferedReader reader 이용해 한줄을 읽어 와서 문자열 line에 대입하고 line이 널이 아니면 반복
            /* ... */ {
                birthDay = line.split("\t");
                birthDays.add(new BirthDay(birthDay[0], birthDay[1]));
            }

            // Stream을 통한 생년월일 정렬
            /* ... */

            // 데이터를 파일에 저장.
            /* ... */
        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }
}
```

## 2. Channel I/O
* 1에서 생성한 파일의 이름과 절대 경로 정보를 출력하는 txt 파일을 생성하세요.
* 파일 생성시 퍼미션을 지정하세요. 소유자에게는 읽기/쓰기/실행 권한을 모두 부여하고, 나머지에게는 읽기 권한만 부여합니다.
* SeekableByteCannel 사용하기 
* 예제 7.17 참고(page 278)