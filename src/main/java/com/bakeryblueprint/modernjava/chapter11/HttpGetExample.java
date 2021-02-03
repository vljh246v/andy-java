package practical.chapter11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpGetExample {

    // HttpRequest -> HttpClient -> HttpResponse
    public static void main(String[] args) {
        //HttpRequest를 정의한다.
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://www.naver.com"))
                .GET()
                .build();

        //HttpClient 객체를 생성한다.
        HttpClient httpClient = HttpClient.newHttpClient();

        // 요청을 호출한다.
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Code : " + httpResponse.statusCode());
            System.out.println("Response Headeer : " + httpResponse.headers());
            System.out.println("Response Body : " + httpResponse.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
