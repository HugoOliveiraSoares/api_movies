package br.com.film_catalog;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String apiKey = "";

        String body = new TMDBApiClient(apiKey).getBody();

        List<Movie> movies = new TMDBMovieJsonParser(body).parse();

        PrintWriter printWriter = new PrintWriter("index.html");

        HTMLGenerator htmlGenerator = new HTMLGenerator(printWriter);
        htmlGenerator.generate(movies);

        printWriter.close();

    }

}
