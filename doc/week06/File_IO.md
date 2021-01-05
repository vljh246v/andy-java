## 7.6 파일 생성, 쓰기, 읽기

### 7.6.1 파일 열기 옵션
- NIO에서는 파일을 읽고, 쓰기 위한 목적 등 다양한 목적을 위한 standardOpenOptions을 제공
- <img src = "https://user-images.githubusercontent.com/38370976/103625653-9e957280-4f7e-11eb-8f68-fc96978851ce.png" width="600px">
- <img src = "https://user-images.githubusercontent.com/38370976/103455813-a7463880-4d33-11eb-9b45-d4d10624abdd.png" width="600px">

### 7.6.2 Files 클래스 이용
- 파일의 크기가 작고 데이터를 쓰는 빈도가 빈번하지 않을 경우 사용
- 일반적인 경우 사용하면 힙메모리 부족.
- 별도의 스트림이나 채널을 생성하지 않고 Files 클래스에서 메서드만 호출하면 기능을 구현할 수 있기 때문에 편리함.

> - readAllByte(path) : 파일의 모든 데이터를 바이트로 읽어서 반환, 파일 전체 데이터를 한번에 처리할 때 사용
> - readAllLines(path, charset) : 파일의 모든 라인을 한번에 읽어서 리턴, 하나의 라인을 하나의 String 객체에 담고 List<String>을 리턴. 두번째 파라미터는 인코딩할 캐릭터셋 지정 
> - write(path, byte[], OpenOption...) : 전달받은 바이트 배열을 파일에 저장
> - write(path, Iterable<? extends CharSequence>, OpenOption) : List<String> 객체를 파라미터로 전달하면 String을 하나의 라인으로 조합해서 파일에 저장. 

```java
package insightbook.newjava.ch07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**    
 * InputStream/OutputStream, Reader/Write 없이 메서드 호출만으로 처리
 * 작은 파일을 읽어들일 때만 사용할 수 있는 방법이다.
 * 주의해서 사용해야 한다.
 */
public class SmallFileIO {
    // 파일의 전체 내용을 바이트 배열로 읽어드림. 
	public static byte[] readAllFromFile(Path filePath) throws IOException {
		byte[] fileArray = Files.readAllBytes(filePath);
		return fileArray;
	}
	
    //파일 전체 데이터를 한번에 저장.
	public static void writeAllToFile(Path filePath, byte[] fileArray) throws IOException {
		Files.write(filePath, fileArray, 
				StandardOpenOption.CREATE, StandardOpenOption.WRITE);
	}
	
	public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage : java SmallFileIO <path>");
            return;
        }

		String filePath = args[0];
		
		try {
			// 파일을 한번에 읽어 들인다.
			byte[] fileContents = readAllFromFile(Paths.get(filePath));

			// 데이터 처리하는 로직을 구현한다.
			// ...
			
			// 파일을 한번에 저장한다.
			writeAllToFile(Paths.get(filePath), fileContents);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
```

### 7.6.3 버퍼 입출력 이용
- 많은 데이터를 읽고 쓰는 것은 에러를 유발할 수 있어 권장하지 않으며, 조금씩 읽고쓰는 것은 잦은 I/O 발생으로 성능이 떨어진다 
- 속도를 높이기 위해 버퍼를 사용. 일정 크기의 데이터를 버퍼링하고, 버퍼링된 데이터를 한번에 읽고 쓰는 방식.
- 기존 IO File 객체를 이용하는 것과 NIO를 사용하는 것은 차이가 있음. 

> [IO File]
> -  여러 번의 new 메서드를 이용하여 객체를 생성, 순서에 맞게 I/O 객체를 종료 
```java
    BufferedReader bReader = null;
    BufferedWriter bWriter = null;

    try {
        // File 객체를 기반으로 FileReader를 생성하고 버퍼링할 BufferedReader를 생성한다.
        bReader = new BufferedReader(new FileReader(new File("c:\\data\\files.cvs")));
    
        // File 객체를 기반으로 FileWriter를 생성하고 버퍼링할 BufferedWriter를 생성한다.
        bWriter = new BuffredWriter(new FileWriter(new File("c:\\target\\newFiles.cvs")));
    } catch(IOExxception e) {
        System.err.format("IOExxception: %s%n", e);
    } finally {
        bReader.close();
        bWriter.close();
    }   
``` 

> [NIO Files class 이용]
> - 파일 NIO의 Files를 이용하면 Reader와 Writer를 생성하기 위해 개발자가 직접 코딩할 필요 없이 Files 클래스에 위임 -> 코드량 감소, 가독성 높아짐.
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BufferedFileIO {
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Usage : java BufferedFileIO <source file> <target file>");
			return;
		}
		
		Path sourcePath = Paths.get(args[0]);
		Path targetPath = Paths.get(args[1]);
		
		Charset charset = Charset.forName("EUC-KR");
		
		try (BufferedReader reader = Files.newBufferedReader(sourcePath, charset);
				BufferedWriter writer = Files.newBufferedWriter(targetPath, charset)) {
		    String line = null;

		    // 파일에서 데이터를 한줄씩 읽는다.
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
				// 특정한 비즈니스 로직을 구현한다.
		        // ...
		        
		        // 데이터를 파일에 한줄식 저장한다.
			    writer.write(line, 0, line.length());
		    }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
- newBufferedReader(Path path) : path 경로에 있는 파일을 열어서 읽기 위한 BuffererdReader 객체 리턴
- newBufferedWriter(Path path, OpenOption... options) : path 경로에 있는 파일을 열거나 새로 생성해서 데이터를 저장하기 위한 BufferedWriter 객체를 리턴.

### 7.6.4 스트림 I/O
- Input/Output Stream은 파일 뿐만 아니라 네트워크 등 다양한 곳에서 응용할 수 있으며, 서블릿과 JSP로 프로그래밍할 때의 HTTP 통신 역시 WAS 내부에서 Input/Output Stream으로 구성.
- Stream은 바이트 단위로 입출력을 함. (Reader/Writer는 문자단위)
- 파일 NIO 에서도 입출력 스트림 제공. 
- Stream 계열을 사용할 때는 버퍼링되어 있지 않아서 직접 사용하면 성능이 떨어지기때문에 반드시 버퍼링 작업을 병행해야함.
```java
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StreamFileIO {
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Usage : java StreamFileIO <source> <target>");
			return;
		}
		
		Path sourcePath = Paths.get(args[0]);
		Path targetPath = Paths.get(args[1]);
		
		// InputStream, OutputStream을 이용해서 파일 처리를 한다.
		try (InputStream input = Files.newInputStream(sourcePath);
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));   //BufferedInputStream binput = new BufferedInputStream(input);
				OutputStream out = Files.newOutputStream(targetPath);
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {  //BufferedOutputStream bout = new BufferedOutputStream(out);
		    String line = null;

		    // 파일에서 데이터를 읽어들인다. 한줄씩 읽어들이는 예제이다.
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		        
				// 특정한 비즈니스 로직을 구현한다.
		        // ...
		        
		        // 데이터를 파일에 저장한다.
			    writer.write(line, 0, line.length());
		    }
		}
		catch (IOException e) {
		    System.err.format("IOException: %s%n", e);
		}
	}
	
	// byte 배열로 처리하는 방식이다.
	public void temp(String source, String target) {
		Path sourcePath = Paths.get(source);
		Path targetPath = Paths.get(target);
		
		try (InputStream input = Files.newInputStream(sourcePath);
				BufferedInputStream binput = new BufferedInputStream(input);
				OutputStream out = Files.newOutputStream(targetPath);
				BufferedOutputStream bout = new BufferedOutputStream(out)) {

			int index = 0;
			byte[] buffer = new byte[1024];
					
		    // 파일에서 데이터를 읽어들인다.
			// 한줄씩 읽지 않고 일정 크기의 버퍼 만큼 읽어들인다.
		    while ((index = binput.read(buffer)) != -1) {
			    bout.write(buffer, 0, index);
		    }
		}
		catch (IOException e) {
		    System.err.format("IOException: %s%n", e);
		}
	}
}
```
- newInputStream(Path path, OpenOption... options) : Path 경로에 있는 파일을 열어서 읽기 위한 InputStream 객체를 리턴한다. 
- newOutputStream(Path path, OpenOption... options) : Path 경로에 있는 파일을 열거나 새로 생성해서 데이터를 저장하기 위한 OutputStream 객체를 리턴한다. 

### 7.6.5 채널과 바이트버퍼
- 파일 NIO의 Files 클래스의 newByteChannel 메서드 : 채널 I/O 객체를 리턴
- newByteChannel 메서드를 호출할 때는 반드시 OpenOption 클래스를 이용해서 파일을 열어야하며, 최소한 READ 옵션을 적용해야 파일에 저장되어 있는 데이터를 읽어들일 수 있음.
- newByteChannel 메서드를 이용하면 더 빠른 파일 입출력을 위한 객체를 생성할 수 있으며, 별도의 버퍼링을 위해 객체를 선언하는 등의 작업이 불필요하여 소스코드가 간편해짐. 

```java
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelFileIO {
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Usage : java ChannelFileIO <source> <target>");
		}
		
		Path sourcePath = Paths.get(args[0]);
		Path targetPath = Paths.get(args[1]);

		// SeekableBytechannel을 이용하였다.
		try (SeekableByteChannel inputChannel = 
				Files.newByteChannel(sourcePath, StandardOpenOption.READ);  
				SeekableByteChannel outChannel = 
						Files.newByteChannel(targetPath, StandardOpenOption.CREATE_NEW);) {
			ByteBuffer buf = ByteBuffer.allocate(1024);

		    // 파일에서 데이터를 읽어들인다. 버퍼의 크기만큼 읽어서 저장한다.
		    while (inputChannel.read(buf) != 0) {
		    	// 데이터를 파일에 저장한다.
		    	outChannel.write(buf);
		    	
		    	// ...
		    	
		    	// ByteBuffer 값을 초기화 한다.
		    	buf.rewind();
		    }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
- newByteChannel(Path path, OpenOptio... options) : 입력 파라미터에 따라 메서드가 오버로드 되어있음, 이 메서드를 이용하면 주어진 경로를 기반으로 파일을 읽거나 쓸 수 있는 SeekableByteChannel 객체 리턴.
하나의 메서드로 읽기와 쓰기용 둘다 생성 가능
- SeekableByteChannel :  현재 읽고 있는 위치를 내부적으로 관리하며 그 위치 정보를 변경해서 수정할 수 있음. ReadableByteChannel, WritableByteChannel의 하위 인터페이스로 Readable한 명세와 Writable한 명세를 하나의 인터페이스에 모두
포함하고 있기 때문에 하나의 객체로 파일을 읽고 쓰는 작업이 모두 가능함. 
- <img src = "https://user-images.githubusercontent.com/38370976/103632408-9857c400-4f87-11eb-9482-cd4d3450de4e.png" width="600px">

### 7.6.6 일반 파일과 임시 파일 생성
- 파일의 내용 중 일부를 필터링 하거나 정렬하거나 여러개의 파일을 합치거나 할때 임시파일 사용.
```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TemporaryFile {
	public static void main(String[] args) {
		try {
			Path tempDirectory = Files.createTempDirectory("java9");
			System.out.format("임시 디렉토리 : %s%n", tempDirectory);
			
		    Path tempFile = Files.createTempFile(tempDirectory, 
		    		null, ".tempdata");
		    System.out.format("임시 파일 : %s%n", tempFile);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
//임시 디렉토리 : /var/folders/my/1wx1ydk53wdc_r_32f4tbnvm0000gn/T/java918660693808460039
//임시 파일 : /var/folders/my/1wx1ydk53wdc_r_32f4tbnvm0000gn/T/java918660693808460039/5432434590937192091.tempdata
```
- createTempDirectory(Path dir, String prefix, FileAttribute<?>... attrs) : 운영체제의 임시 디렉터리 하위에 디렉터리를 생성
- createTempFile(Path dir, String prefix, String suffix, FileAttribute<?>... attrs) : 운영체제의 임시 디렉터리에 임시 파일을 생성. 

### 7.7 랜덤 액세스 파일
- 파일을 열어서 파일의 특정한 영역에 있는 데이터를 읽거나 쓸 수 있음.
- 임의의 파일 데이터에 접근할 수 있으며, 비순차적으로 데이터 처리가 가능
- 읽는 작업과 쓰는 작업도 임의로 접근해서 처리 가능
- SeekableBytechannel 인터페이스의 position메서드가 위치를 이동시키는 기능을 함. 
```java
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class RandomAccessFileNew {
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage : java RandomAccessFileNew <file>");
			return;
		}
		
		Path file = Paths.get(args[0]);
		
		String s = "Java New Features\n";
		byte data[] = s.getBytes();

		ByteBuffer out = ByteBuffer.wrap(data);
		ByteBuffer copy = ByteBuffer.allocate(1024);

		// 데이터를 조회할 파일을 오픈하기 위해 FileChannel을 생성하였다.
        // FileChannel은 SeekableByteChannel 인터페이스를 상속받음.
		try (FileChannel fileChannel
				= (FileChannel.open(file, StandardOpenOption.READ, StandardOpenOption.WRITE))) {
			
			// 파일에서 1024바이트를 읽어들인다.
			int index;
			do {
				index = fileChannel.read(copy);
			} 
			while (index != -1 && copy.hasRemaining());

			// 1024번째 위치에서 0번째 위치로 이동한 후 데이터를 쓴다.
			fileChannel.position(0);
			while (out.hasRemaining()) {
				fileChannel.write(out);
			}
			out.rewind(); // ByteBuffer를 초기화 한다.

			// 파일의 제일 뒤로 이동한 후 다시 데이터를 쓴다.
			long length = fileChannel.size();
			fileChannel.position(length-1);
			copy.flip();
			while (copy.hasRemaining()) {
				fileChannel.write(copy);
			}
			
			while (out.hasRemaining()) {
				fileChannel.write(out);
			}
		} 
		catch (IOException e) {
			System.out.println("I/O Exception: " + e);
		}
	}
}
```

## 7.8 디렉터리 처리

### 7.8.1 디렉터리 생성 
- 디렉터리 생성, 복사, 이동, 삭제할때도 파일과 동일하게 Files 클래스를 이용.
- 디렉터리는 데이터를 저장하는 것이 아니라 파일을 그룹으로 묶고 트리구조로 분류하는 것이 목적이기 때문에 createDirectory 메서드 호출로 충분함.
```java
    Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
    FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
    Files.createDirectory(file, attr);
```
- createDirectory : 특정한 디렉터리 하위에 하나의 디렉터리를 생성. path로 전달할 파라미터에 있는 디렉터리의 상위 디렉터리가 실제로 파일 시스템에 존재해야함. 없으면 NoSuchFileException 예외 발생 
- createDirectories : 특정한 디렉터리 하위에 여러 단계의 디렉터리를 생성. 생성하고자 하는 디렉터리의 상위 디렉터리 존재 여부와 관계없이 디렉터리 생성 작업을 진행함. 디렉터리의 속성을 지정할 수 없음.

> 디렉터리 생성 절차 
1. 생성할 디렉터리의 경로 정보인 Path 객체를 생성
2. path 객체의 경로 정보 중 상위 디렉터리 정보를 조회하여 해당 상위 디엑터리가 존재하는지 확인
3. 상위 디렉터리가 존재하지 않는다면 createDirectories 메서드로 생성
4. Path 객체 정보를 기반으로 createDirectory 메서드를 이용해서 디렉터리 생성

### 7.8.2 디렉터리 목록 조회 
- Files 클래스의 newDirectorySystem 메서드 제공 
```java
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ListDirectory {
	public static void main(String[] args) {
		Path dir = Paths.get("c:/windows");

		// DirectoryStream을 이용한 디렉터리 목록 조회 
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		        System.out.println(file.getFileName());
		    }
		} 
		catch (IOException | DirectoryIteratorException e) {
		    e.printStackTrace();
		}
	}
}
```
- Iterable 인터페이스의 하위 인터페이스이고, 제네릭으로 Path 정보를 사용하도록 하고있어 반복문을 이용해 결과값을 얻을 수 있음
- Collection 인터페이스를 상속받지 않아서 스트림 API를 이용하여 람다 표현식을 적용할 수 없음.

### 7.8.3 목록 필터링
- 대용량의 소프트웨어를 개발하고 해당 소프트웨어가 많은 파일을 처리해야한다면 파일에 대한 핉터링 기능이 반드시 필요.
- java 6 까지는 FilenameFilter 인터페이스를 사용하였지만 NIO에서는 newDirectoryStream 메서드를 이용하여 필터 가능. 
```java
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewFileFilter {
	public static void main(String[] args) {
		Path dir = Paths.get("C:/Windows");
		
		// DirectoryStream을 이용해서 조회한다.
		// Glob 문자열 패턴을 이용해서 필터링 한다.
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{exe}")) {
		    for (Path file: stream) {
		        System.out.println(file.getFileName());
		    }
		} 
		catch (IOException | DirectoryIteratorException e) {
		    e.printStackTrace();
		}
	}
}
```
- newDirectoryStream(Path dir, String glob) : 주어진 경로에 포함된 파일이나 디렉터리 목록을 컬렉션 형태의 DirectoryStream 객체로 만들어 리턴한다. 두번째 글로빙 파라미터를 통해 목록을 필터링 할 수 있다.
- newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) : 주어진 경로에 포함된 파일이나 디렉터리 목록을 DirectoryStream 객체로 전달하되 DirectoryStream.Filter에 포함되어 있는 정보를 기반으로 필터링.

- <img src = "https://user-images.githubusercontent.com/38370976/103540488-1f039700-4edd-11eb-9945-8cf0d02264fd.png" width="600px">

### 7.8.4 루트 디렉터리 
```java
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ListRootDirectory {
	public static void main(String[] args) {
		Iterable<Path> rootDirs = FileSystems.getDefault().getRootDirectories();

		for (Path rootPath : rootDirs) {
		    System.out.println(rootPath);
		}
	}
}
// 윈도우 기준 
// C:\
// D:\
```

## 7.9  파일 트리 
- 파일과 디렉터리 처리를 배우면서 복사나 이동에 대해 배웠지만, 이는 디렉터리 내에 아무런 데이터도 존재하지 않는 다는 것을 전제로 함.
- 디렉터리가 파일이나 디렉터리가 하나도 존재하지 없는 경우는 거의 없기 때문에 파일 트리를 이용해서 처리할 수 밖에 없음.

### 7.9.1 walkFileTree 메서드 
- 파일 NIO 에서는 디렉터리의 트리 구조를 지원하기 위해서 FileVisitor 인터페이스 제공 
- SimpleFileVisitor 클래스를 이용해 처리 
<img src = "https://user-images.githubusercontent.com/38370976/103626957-465f7000-4f80-11eb-8ebd-17ec6e5b5e21.png" width="600px">

> /maintree/subtree1/subtree2 디렉터리 구조에서 FileVisitor의 메서드 호출 순서
> preVisitDirectory는 디렉터리에 접근하기 전에 호출되며 CONTINUE 값을 리턴받으면 디렉터리에 있는 파일과 서브디렉터리를 처리, 처리가 완료되면 최종적으로 postVisitDirectory 메서드 호
1. maintree를 위한 preVisitDirectory 호출
2. subtree1을 위한 preVisitDirectory 호출
3. subtree2을 위한 preVisitDirectory 호출 
4. subtree2을 위한 postVisitDirectory 호출
5. subtree1을 위한 postVisitDirectory 호출
6. maintree를 위한 postVisitDirectory 호출

```java
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class PrintAllFiles {

    public static void main(String[] args) {
        Path dirPath = Paths.get("/Users/doyeon/Documents/STUDY/andy-java");
        try {
            Files.walkFileTree(dirPath, new FileVisitor<Path>() {
                // 디렉토리에 진입하기 전에 호출된다.
                // 항상 FileVisitResult.CONTINUE 리턴. 모든 디렉터레에 대해서 계속 진행하겠다는 의미 
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                        throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                // 디렉토리에 진입한 후에 호출된다.
                // 디렉터리명 출력 후 FileVisitResult.CONTINUE 리턴
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                        throws IOException {
                    System.out.format("디렉토리 : %s%n", dir);
                    return FileVisitResult.CONTINUE;
                }

                // 파일에 접근하면서 호출된다.
                // 파일 유형, 크기 출력 후 FileVisitResult.CONTINUE 리턴.
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attr)
                        throws IOException {
                    if (attr.isSymbolicLink()) {
                        System.out.format("심볼릭 링크 : %s ", file);
                    }
                    else if (attr.isRegularFile()) {
                        System.out.format("일반 파일 : %s ", file);
                    }
                    else {
                        System.out.format("기타 파일 : %s ", file);
                    }

                    System.out.println("(" + attr.size() + "바이트)");
                    return FileVisitResult.CONTINUE;
                }
    
                // 파일 접근 실패(권한 없음, 작업 중 다른프로세스에 의해 삭제된 경우) 에러출력 후 무조건 진행. 
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.err.println(exc);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
```

> Files 클래스에 있는 walk 관련 메서드 298p 참고 
1. walk(Path start, FileVisitOption... options) : 파일 트리에 대한 처리를 할 수 있는 스트림 객체 리턴.
2. walk(Path start, int maxDepth, FileVisitOption... options) : 위의 메서드와 동일하며 maxDepth 파라미터로 처리할 파일 트리의 깊이를 제한 
3. walkFileTree(Path start, FileVisitor<? super Path> visitor): 특정 경로를 기준으로 파일 트리 작업을 호출하며, 트리에 있는 파일 혹은 디렉터리에 대한 처리 여부를 정의한 FileVisitor 인터페이스를 이용하여 처리 
4. walkFileTree(Path start, SEt<FileVisitOption> optins, int maxDepth, FileVisitor<? super Path> visitor): 위와 동일하며, 방문할 파일트리에 대한 옵션과 리의 깊이를 지정할 수 있음.

> FileVisitResult 속성
><img src = "https://user-images.githubusercontent.com/38370976/103619519-4312b700-4f75-11eb-859e-71e81f4ba817.png" width="600px">

## 7.10 디렉터리 변경 감지
- 기존에는 많은 라이브러리나 프레임워크에서 파일의 변화나 디렉터리의 변경을 감지해주는 기능을 제공했으나 기능구현이 까다롭고 어렵다 
- 파일 NIO 에서는 변경을 감지하기 위해 파일 Watch API를 제공. 모니터링 하고자 하는 자원의 스레드를 생성하고 스레드 풀에 등록해서 관리하며, 변화가 생겼을 때 이벤트를 발생시켜 알려줌.

```java
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class WatchingDirectory {

	public static void main(String[] args) throws IOException {
		// 1. 모니터링을 하는 WatchService 객체를 생성
		WatchService watchService = FileSystems.getDefault().newWatchService();

		// 2. 모니터링 대상 경로를 생성하고 WatchService에 등록한다.
        // Watch API에는 Wathable 인터페이스를 구현한 클래스만 등록가능, Path는 Watchable 인터페이스 구현
		Path logsDir = Paths.get("C:/temp/logs");
		logsDir.register(watchService, 
                            StandardWatchEventKinds.ENTRY_CREATE, 
                            StandardWatchEventKinds.ENTRY_MODIFY,
		                    StandardWatchEventKinds.ENTRY_DELETE);      //StandardWatchEventKids 클래스는 어떤 변경을 모니터링하고 이벤트를 발생시킬 것인지 결정.

		// 2. 모니터링 대상 경로를 생성하고 WatchService에 추가로 등록한다.
		Path propertiesDir = Paths.get("C:/temp/properties");   
		propertiesDir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

		// 4. 변경 감지 프로그램 작성
		while (true) {
			try {
				// 5. 키 값 조회
				WatchKey changeKey = watchService.take();
				List<WatchEvent<?>> watchEvents = changeKey.pollEvents();   // 변경된 내용이 담겨서 리턴됨. 

				// 6. 키에 해당하는 변경 목록 조회
                // 변경된 경로 정보인 Path 객체가 담겨있음. 
				for (WatchEvent<?> watchEvent : watchEvents) {
					WatchEvent<Path> pathEvent = (WatchEvent<Path>) watchEvent;
					Path path = pathEvent.context();
					WatchEvent.Kind<Path> eventKind = pathEvent.kind();
					System.out.println(eventKind + " for path: " + path);
				}
				
				// 7. 변경키 초기화
				changeKey.reset();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
```

> StandardWatchEventKids 클래스 제공 항목
><img src = "https://user-images.githubusercontent.com/38370976/103619519-4312b700-4f75-11eb-859e-71e81f4ba817.png" width="600px">

> 사용 예 
- 애플리케이션 설정 파일 모니터링 : 설정파일을 변경하면 실시간으로 반영. ENTRY_MODIFY 이벤트를 사용하여 감지
- 파일 에디터 : 열린 파일의 변경 여부를 실시간으로 감지하는 기능. ENTRY_MODIFY 이벤트 이용.
- 파일 큐 : 특정 디렉터리에 새로운 파일이 생성되면 이를 모니터링 하고있다가 처리하는 방식. ENTRY_CREATE 이벤트 
 
