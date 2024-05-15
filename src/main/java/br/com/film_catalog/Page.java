package br.com.film_catalog;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class Page {

    public Integer page;

    @JsonProperty("results")
    public List<Movie> results;

    @JsonProperty("total_pages")
    public Integer totalPages;
    @JsonProperty("total_results")
    public Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return "Page{\n" +
                "page=" + page +
                ",\nresults= [\n" + results.stream().map(String::valueOf).collect(Collectors.joining("\n"))+
                "]," +
                "\ntotalPages= " + totalPages +
                "\n,totalResults=" + totalResults +
                "\n}";
    }
}
