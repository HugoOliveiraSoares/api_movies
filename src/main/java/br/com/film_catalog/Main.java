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

        Matcher matcher = Pattern
                .compile("\\[(.*)\\]")
                .matcher(body);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Match not found");
        }

        String[] movies = body.substring(matcher.start()).split("\\},\\{");

        List<String> titles = parseObject("original_title", movies);
        List<String> urlImages = parseObject("poster_path", movies);
        List<String> popularity = parseObject("popularity", movies);

        titles.forEach(System.out::println);
        urlImages.forEach(System.out::println);
        popularity.forEach(System.out::println);

    }

    public static List<String> parseObject(String field, String[] movies) {
        List<String> list = new ArrayList<>();

        for (String m : movies) {
            String substring = m.substring(m.indexOf(field+"\":"));
            String s = substring.split(",\"")[0];
            list.add(s.split("\":")[1].replaceAll("\"", ""));
        }

        return list;
    }

}
