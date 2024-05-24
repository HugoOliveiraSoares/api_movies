package br.com.film_catalog.entities;

public interface Content {
    String title();

    String imageUrl();

    float rating();

    int year();

    default String type(){
       return this.getClass().getSimpleName();
    }

}
