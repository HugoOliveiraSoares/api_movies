package br.com.film_catalog;

//TIP O uso de um record é valido neste cenário, pois a Classe Movie pode ser usado como DTO
public record Movie(String title, String imageUrl, float rating, int year){}