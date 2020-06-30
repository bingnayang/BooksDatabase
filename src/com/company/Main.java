package com.company;

import com.company.model.Author;
import com.company.model.Book;
import com.company.model.Datasource;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Cannot open datasource");
            return;
        }

        // Get Authors Name
        System.out.println("===List Authors in DataBase===");
        List<Author> authors = datasource.queryAuthors(Datasource.ORDER_BY_NONE);
        if(authors == null){
            System.out.println("No Author in database");
            return;
        }
        for(Author author : authors){
            System.out.println("Author Name: "+author.getAuthor_name());
        }

        // Get Book Title
        System.out.println("===List Book Title in DataBase===");
        List<Book> books = datasource.queryBooksTitle(Datasource.ORDER_BY_NONE);
        if(books == null){
            System.out.println("No book in database");
            return;
        }
        for(Book book: books){
            System.out.println("Book Title: "+book.getBook_name());
        }

        // Get Book Title
        System.out.println("===List Book Info in DataBase===");
        List<String> bookInfo = datasource.queryAllBooksInfo(Datasource.ORDER_BY_NONE);
        if(books == null){
            System.out.println("No book in database");
            return;
        }
        for(String book: bookInfo){
            System.out.println(book);
        }



        datasource.close();


    } // end: main
}
