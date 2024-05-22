package br.com.film_catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TMDBMovieJsonParser {

    private final String json;

    public TMDBMovieJsonParser(String json) {
        this.json = json;
    }

    public List<Movie> parse() {

        Matcher matcher = Pattern
                .compile("\\[(.*)\\]")
                .matcher(this.json);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Match not found");
        }

        String[] movies = this.json.substring(matcher.start()).split("\\},\\{");

        return parseObject(movies);
    }

    public static List<Movie> parseObject(String[] movies) {
        List<Movie> list = new ArrayList<>();

        for (String m : movies) {
            String titleSub = m.substring(m.indexOf("title\":"));
            String ts = titleSub.split(",\"")[0];

            String posterSub = m.substring(m.indexOf("poster_path\":"));
            String pp = posterSub.split(",\"")[0];

            String popularitySub = m.substring(m.indexOf("vote_average\":"));
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
