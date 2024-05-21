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

        List<Movie> movies1 = parseObject(movies);

        movies1.forEach(System.out::println);

    }

    public static List<Movie> parseObject(String[] movies) {
        List<Movie> list = new ArrayList<>();

        for (String m : movies) {
            String titleSub = m.substring(m.indexOf("original_title\":"));
            String ts = titleSub.split(",\"")[0];

            String posterSub = m.substring(m.indexOf("poster_path\":"));
            String pp = posterSub.split(",\"")[0];

            String popularitySub = m.substring(m.indexOf("popularity\":"));
            String p = popularitySub.split(",\"")[0];

            String releaseDateSub = m.substring(m.indexOf("release_date\":"));
            String rd = releaseDateSub.split(",\"")[0];

            list.add(
                    new Movie(
                            ts.split("\":")[1].replaceAll("\"", ""),
                            pp.split("\":")[1].replaceAll("\"", ""),
                            Float.parseFloat(p.split("\":")[1].replaceAll("\"", "")),
                            Integer.parseInt(rd.split("\":")[1].replaceAll("\"", "").split("-")[0])
                    ));
        }

        return list;
    }

}
