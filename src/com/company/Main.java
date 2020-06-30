package com.company;

import com.company.model.Author;
import com.company.model.Book;
import com.company.model.Datasource;
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
        System.out.println("===List Books Title in DataBase===");
        List<Book> books = datasource.queryBooksTitle(Datasource.ORDER_BY_NONE);
        if(books == null){
            System.out.println("No book in database");
            return;
        }
        for(Book book: books){
            System.out.println("Book Title: "+book.getBook_name());
        }

        // Get All Book Info
        System.out.println("===List All Books Info in DataBase===");
        List<String> bookInfo = datasource.queryAllBooksInfo(Datasource.ORDER_BY_NONE);
        if(books == null){
            System.out.println("No book in database");
            return;
        }
        for(String book: bookInfo){
            System.out.println(book);
        }

        // Get Books Title by Author
        System.out.println("===List Books Title By Author===");
        List<String> booksByAuthor = datasource.queryBooksTitleByAuthorName("Robert Lafore");
        if(booksByAuthor == null){
            System.out.println("No such data");
            return;
        }
        for(String bookByAuthor: booksByAuthor){
            System.out.println(bookByAuthor);
        }

        datasource.close();


    } // end: main
}
