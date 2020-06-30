package com.company.model;

public class Book {
    private String book_ISBN_13;
    private String book_name;
    private String book_edition;
    private String book_publication_date;
    private int book_author_ID;
    private int book_publisher_ID;


    public String getBook_ISBN_13() {
        return book_ISBN_13;
    }

    public void setBook_ISBN_13(String book_ISBN_13) {
        this.book_ISBN_13 = book_ISBN_13;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_edition() {
        return book_edition;
    }

    public void setBook_edition(String book_edition) {
        this.book_edition = book_edition;
    }

    public String getBook_publication_date() {
        return book_publication_date;
    }

    public void setBook_publication_date(String book_publication_date) {
        this.book_publication_date = book_publication_date;
    }

    public int getBook_author_ID() {
        return book_author_ID;
    }

    public void setBook_author_ID(int book_author_ID) {
        this.book_author_ID = book_author_ID;
    }

    public int getBook_publisher_ID() {
        return book_publisher_ID;
    }

    public void setBook_publisher_ID(int book_publisher_ID) {
        this.book_publisher_ID = book_publisher_ID;
    }
}
