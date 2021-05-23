package com.example.bookingbook;

public class Book {
    String image;

    String author;

    String price;

    String isbn;

    String link;

    String discount;

    String publisher;

    String description;

    String title;

    String pubdate;


    public Book() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Book(String image, String author, String price, String isbn, String link, String discount,
                String publisher, String description, String title, String pubdate) {
        this.image = image;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.link = link;
        this.discount = discount;
        this.publisher = publisher;
        this.description = description;
        this.title = title;
        this.pubdate = pubdate;

    }
    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getIsbn ()
    {
        return isbn;
    }

    public void setIsbn (String isbn)
    {
        this.isbn = isbn;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getDiscount ()
    {
        return discount;
    }

    public void setDiscount (String discount)
    {
        this.discount = discount;
    }

    public String getPublisher ()
    {
        return publisher;
    }

    public void setPublisher (String publisher)
    {
        this.publisher = publisher;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getPubdate ()
    {
        return pubdate;
    }

    public void setPubdate (String pubdate)
    {
        this.pubdate = pubdate;
    }





    @Override
    public String toString() {
        return "Book{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", price='" + price + '\'' +
                ", link'" + link + '\'' +
                ", discount='" + discount + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
