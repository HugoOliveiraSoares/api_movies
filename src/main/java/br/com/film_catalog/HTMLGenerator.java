package br.com.film_catalog;

import br.com.film_catalog.entities.Content;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    private final PrintWriter writer;

    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    public void generate(List<? extends Content> contents) {

        String head = """
                <head>
                  <meta charset="utf-8" />
                  <title>Movies 7DaysOfCode</title>
                  <meta
                    name="viewport"
                    content="width=device-width, initial-scale=1, shrink-to-fit=no"
                  />
                  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
                  
                </head>
                
                <style>
                    body {
                        background-color: #6c757d
                    }
                    
                    .type {
                        color: gray
                    }
                </style>
                
                """;

        String body = """
                <body>
                <div class="container mt-5">
                    <div class="row">
                 """;

        String divTemplate = """
                        <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
                            <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
                                <img class="card-img-top" src="%s" alt="%s">
                                <div class="card-body">
                                    <p class="card-text fs-6 type">%s</p>
                                    <h5 class="card-title">%s </h5>
                                    <p class="card-text mt-2">Nota: %s - Ano: %s</p>
                                </div>
                            </div>
                        </div>
                """;


        writer.println(head + body);

        contents.forEach(content -> writer.println(String.format(
                divTemplate,
                content.imageUrl(),
                content.title(),
                content.type(),
                content.title(),
                content.rating(),
                content.year()
        )));

        writer.println("</div>\n</div>\n</body>");

    }

}
