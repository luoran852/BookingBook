package com.example.bookingbook;

public class ItemSearched {

    int image;
    String title;
    String author;
    String year;
    String translator;
    String publisher;

    public ItemSearched(int image, String title, String year, String author, String translator, String publisher) {
        this.image=image;
        this.title=title;
        this.year = year;
        this.author=author;
        this.translator=translator;
        this.publisher=publisher;
    }

}
