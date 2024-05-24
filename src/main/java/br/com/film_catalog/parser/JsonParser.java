package br.com.film_catalog.parser;

import br.com.film_catalog.entities.Content;

import java.util.List;

public interface JsonParser {
     List<? extends Content> parse();

}
