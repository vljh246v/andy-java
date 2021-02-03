package practical.chapter11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpPostExample {

    public static void main(String[] args) {
        String json = "";

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://www.naver.com"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Code : " + httpResponse.statusCode());
            System.out.println("Response Headeer : " + httpResponse.headers());
            System.out.println("Response Body : " + httpResponse.body());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
