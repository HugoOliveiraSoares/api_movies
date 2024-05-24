package br.com.film_catalog.entities;

public record ComicBook(String title, String imageUrl, float rating, int year) implements Content{}