package br.com.film_catalog;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        String apiKey = "";

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=&sort_by=popularity.desc"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        System.out.println(body);

        String[] split = body.split(".,\"");

        List<String> titles = new ArrayList<>();
        List<String> urlImages = new ArrayList<>();
        List<String> popularity= new ArrayList<>();


        for(String s : split){
            if (s.contains("original_title")){
                titles.add(s.split("\":\"")[1]);
            }
            if (s.contains("poster_path")){
                urlImages.add(s.split("\":\"")[1]);
            }
            if (s.contains("popularity")){
                popularity.add(s.split("\":")[1]);
            }
        }

        titles.forEach(System.out::println);
        urlImages.forEach(System.out::println);
        popularity.forEach(System.out::println);

    }

}
