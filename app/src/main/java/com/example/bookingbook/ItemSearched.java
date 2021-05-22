package com.example.bookingbook;

public class ItemSearched {

    String image;
    String title;
    String author;
    String year;
    //String translator;
    String publisher;

    public ItemSearched(String image, String title, String year, String author, String publisher) {
        this.image=image;
        this.title=title;
        this.year = year;
        this.author=author;
        this.publisher=publisher;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String image)    { this.title = title; }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getYear ()
    {
        return year;
    }

    public void setYear (String year)
    {
        this.year = year;
    }

    public String getPublisher ()
    {
        return publisher;
    }

    public void setPublisher (String publisher)
    {
        this.publisher = publisher;
    }

}
