package br.com.film_catalog.api_client;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class MarvelClient implements ApiClient {

    private final String apiKey;
    private final String ts;
    private final String hash;

    public MarvelClient(String apiKey, String privateKey) {
        this.apiKey = apiKey;
        this.ts = LocalDateTime.now().toString();
        this.hash = DigestUtils.md5Hex(this.ts + privateKey + this.apiKey);
    }

    @Override
    public String getBody() {

        String uri = String.format(
                "https://gateway.marvel.com:443/v1/public/comics?format=comic&formatType=comic&noVariants=true&orderBy=-focDate&apikey=%s&hash=%s&ts=%s",
                this.apiKey,
                this.hash,
                this.ts
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(uri))
                .header("accept", "application/json")
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
