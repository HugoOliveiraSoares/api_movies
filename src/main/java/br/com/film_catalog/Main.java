package br.com.film_catalog;

import br.com.film_catalog.api_client.MarvelClient;
import br.com.film_catalog.api_client.TMDBApiClient;
import br.com.film_catalog.entities.ComicBook;
import br.com.film_catalog.entities.Movie;
import br.com.film_catalog.parser.MarvelComicBookJsonParser;
import br.com.film_catalog.parser.TMDBMovieJsonParser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

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
        htmlGenerator.generate(movies);
        htmlGenerator.generate(comicBooks);

        printWriter.close();

    }

}
