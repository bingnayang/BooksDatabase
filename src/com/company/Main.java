package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
	    try{
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/Bing/Documents/GitHub/BingNaYang.github.io/BooksDatabase/books.db");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS books "+
                    "(book_ID INTEGER PRIMARY KEY, book_name, book_ISBN_10 INTEGER, book_ISBN_13 INTEGER, book_edition INTEGER, book_language TEXT, book_publication_date TEXT, author_ID INTEGER, publisher_ID INTEGER, category_ID INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS authors "
                    +"(author_ID INTEGER PRIMARY KEY, author_name TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS publishers "
                    +"(publisher_ID INTEGER PRIMARY KEY, publisher_name TEXT )");
            statement.execute("CREATE TABLE IF NOT EXISTS categories "
                    +"(category_ID INTEGER PRIMARY KEY, category_type TEXT )");




            
            statement.close();
            connection.close();
        }catch (SQLException e){
	        System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
