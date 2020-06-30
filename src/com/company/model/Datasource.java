package com.company.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME = "books.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/Bing/Documents/GitHub/BingNaYang.github.io/BooksDatabase/"+ DB_NAME;
    // Constant
    public static final String TABLE_BOOKS = "books";
    public static final String COLUMN_BOOK_ISBN_13 = "book_ISBN_13";
    public static final String COLUMN_BOOK_NAME = "book_name";
    public static final String COLUMN_BOOK_EDITION = "book_edition";
    public static final String COLUMN_BOOK_LANGUAGE = "book_language";
    public static final String COLUMN_BOOK_PUBLICATION_DATE = "book_publication_date";
    public static final String COLUMN_BOOK_AUTHOR_ID = "book_author_ID";
    public static final String COLUMN_BOOK_PUBLISHER_ID = "book_publisher_ID";
    public static final int INDEX_BOOK_ISBN_13 = 1;
    public static final int INDEX_BOOK_NAME = 2;
    public static final int INDEX_BOOK_EDITION = 3;
    public static final int INDEX_BOOK_LANGUAGE = 4;
    public static final int INDEX_BOOK_PUBLICATION_DATE = 5;
    public static final int INDEX_BOOK_AUTHOR_ID = 6;
    public static final int INDEX_BOOK_PUBLISHER_ID = 7;

    public static final String TABLE_AUTHORS = "authors";
    public static final String COLUMN_AUTHOR_ID = "author_ID";
    public static final String COLUMN_AUTHOR_NAME = "author_name";
    public static final int INDEX_AUTHOR_ID = 1;
    public static final int INDEX_AUTHOR_NAME = 2;

    public static final String TABLE_PUBLISHER = "publishers";
    public static final String COLUMN_PUBLISHER_ID = "publisher_ID";
    public static final String COLUMN_PUBLISHER_NAME = "publisher_NAME";
    public static final int INDEX_PUBLISHER_ID = 1;
    public static final int INDEX_PUBLISHER_NAME = 2;

    private Connection connection;
    public boolean open(){
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database: "+e.getMessage());
            return false;
        }
    }
    public void close(){
        try{
            if(connection != null){
                connection.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection: "+e.getMessage());
        }
    }

    public List<Author> queryAuthors(){
        // Use try-with-resource will automacally close both statement and resultset
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+TABLE_AUTHORS)){

            List<Author> authors = new ArrayList<>();
            while(resultSet.next()){
                Author author = new Author();
                author.setAuthor_ID(resultSet.getInt(INDEX_AUTHOR_ID));
                author.setAuthor_name(resultSet.getString(INDEX_AUTHOR_NAME));
                authors.add(author);
            }
            return authors;
        }catch (SQLException e){
            System.out.println("Query failed: "+e.getMessage());
            return null;
        }
    } // end: queryAuthors
    
}
