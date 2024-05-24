package br.com.film_catalog.api_client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TMDBApiClient implements ApiClient {

    private final String apiKey;

    public TMDBApiClient(String apiKey) {
       this.apiKey = apiKey;
    }

    public String getBody(){

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=&sort_by=popularity.desc"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + this.apiKey)
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("API Error: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();

    }

}
