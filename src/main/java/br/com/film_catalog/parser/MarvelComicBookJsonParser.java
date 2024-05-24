package br.com.film_catalog.parser;

import br.com.film_catalog.entities.ComicBook;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarvelComicBookJsonParser implements JsonParser {

    private final String json;

    public MarvelComicBookJsonParser(String json) {
        this.json = json;
    }

    public List<ComicBook> parse() {

        Matcher matcher = Pattern
                .compile("\\[(.*)\\]")
                .matcher(this.json);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Match not found");
        }

        String[] books = this.json.substring(matcher.start()).split("\\},\\{\"id");

        return parseObject(books);
    }

    public List<ComicBook> parseObject(String[] books) {
        List<ComicBook> list = new ArrayList<>();

        for (String b : books) {
            String titleSub = b.substring(b.indexOf("title\":"));
            String ts = titleSub.split(",\"")[0];

            String posterSub = b.substring(b.indexOf("\"thumbnail\":{\"path\":"));
            String pp = posterSub.split(",\"")[0];

            String releaseDateSub = b.substring(b.indexOf("dates\":"));
            String rd = releaseDateSub.split("],\"")[0];
            String dateString = rd.substring(rd.indexOf("\"date\"")).split("\\},\\{")[0].split("\":\"")[1];
            int date = Integer.parseInt(dateString.split("-")[0]);

            list.add(
                    new ComicBook(
                            ts.split("\":")[1].replaceAll("\"", ""),
                            pp.split("path\":")[1].replaceAll("\"", "")+".jpg",
                            0,
                            date
                    ));
        }

        return list;
    }

}
