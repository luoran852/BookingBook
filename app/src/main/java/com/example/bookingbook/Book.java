package com.example.bookingbook;

public class Book {
    public String title;
    public String author;
    public String publisher;
    public String ISBN;
    public String imageUrl;

    public Book() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Book(String title, String author, String publisher, String ISBN, String imageUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher(){
        return publisher;
    }

    public String getISBN(){
        return ISBN;
    }

    public String getImageUrl(){
        return imageUrl;
    }


    public void setTitle(String userName) {
        this.title = title;
    }

    public void setAuthor(String email) {
        this.author = author;
    }

    public void setPublisher(String password) {
        this.publisher = publisher;
    }

    public void setISBN(String mobile) {
        this.ISBN = ISBN;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
