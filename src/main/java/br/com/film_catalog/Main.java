package br.com.film_catalog;

import br.com.film_catalog.api_client.MarvelClient;
import br.com.film_catalog.api_client.TMDBApiClient;
import br.com.film_catalog.entities.ComicBook;
import br.com.film_catalog.entities.Content;
import br.com.film_catalog.entities.Movie;
import br.com.film_catalog.parser.MarvelComicBookJsonParser;
import br.com.film_catalog.parser.TMDBMovieJsonParser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String tmdbApiKey = "";
        String marvelPubApiKey = "";
        String marvelPrivApiKey = "";

        String tmdbJson = new TMDBApiClient(tmdbApiKey).getBody();
        List<Movie> movies = new TMDBMovieJsonParser(tmdbJson).parse();

        String marvelJson = new MarvelClient(marvelPubApiKey, marvelPrivApiKey).getBody();
        List<ComicBook> comicBooks = new MarvelComicBookJsonParser(marvelJson).parse();

        PrintWriter printWriter = new PrintWriter("index.html");

        HTMLGenerator htmlGenerator = new HTMLGenerator(printWriter);

        List<? extends Content> contents = Stream.of(movies, comicBooks)
                .flatMap(Collection::stream)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

//        Collections.sort(contents, Comparator.reverseOrder());
//        contents.sort(Comparator.comparing(Content::title));
//        contents.sort(Comparator.reverseOrder());

        htmlGenerator.generate(contents);

        printWriter.close();

    }

}
