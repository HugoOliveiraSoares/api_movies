package br.com.film_catalog.entities;

public interface Content extends Comparable<Object> {
    String title();

    String imageUrl();

    float rating();

    int year();

    default String type(){
       return this.getClass().getSimpleName();
    }

    @Override
    default int compareTo(Object o){
        return this.compareTo((Content) o);
    }

    default int compareTo(Content content){
        return Integer.compare(this.year(), content.year());
    }
}
