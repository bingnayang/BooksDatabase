package com.company.model;

import javax.xml.transform.stream.StreamResult;
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

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;


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

    public List<Author> queryAuthors(int sortOrder){
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_AUTHORS);
        if(sortOrder != ORDER_BY_NONE){
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(COLUMN_AUTHOR_NAME);
            stringBuilder.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_BY_DESC){
                stringBuilder.append("DESC");
            }else {
                stringBuilder.append("ASC");
            }
        }

        // Use try-with-resource will automatic close both Statement and ResultSet
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuilder.toString())){

            List<Author> authors = new ArrayList<>();
            while(resultSet.next()){
                Author author = new Author();
                author.setAuthor_name(resultSet.getString(INDEX_AUTHOR_NAME));
                authors.add(author);
            }
            return authors;
        }catch (SQLException e){
            System.out.println("Query failed: "+e.getMessage());
            return null;
        }
    } // end: queryAuthors

    public List<Book> queryBooksTitle(int sortOrder){
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_BOOKS);
        if(sortOrder != ORDER_BY_NONE){
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(COLUMN_AUTHOR_NAME);
            stringBuilder.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_BY_DESC){
                stringBuilder.append("DESC");
            }else {
                stringBuilder.append("ASC");
            }
        }

        // Use try-with-resource will automatic close both Statement and ResultSet
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuilder.toString())){

            List<Book> books = new ArrayList<>();
            while(resultSet.next()){
                Book book = new Book();
                book.setBook_name(resultSet.getString(INDEX_AUTHOR_NAME));
                books.add(book);
            }
            return books;
        }catch (SQLException e){
            System.out.println("Query failed: "+e.getMessage());
            return null;
        }
    }// end: queryBooks

    public List<String> queryAllBooksInfo(int sortOrder){
//        SELECT book_name, author_name,book_ISBN_13,book_edition,book_language,publisher_name,book_publication_date
//        FROM books
//        INNER JOIN authors
//        ON books.book_author_ID = authors.author_ID
//        INNER JOIN publishers
//        ON books.book_publisher_ID = publishers.publisher_ID

        StringBuilder stringBuilder = new StringBuilder("SELECT "+
                COLUMN_BOOK_NAME+","+
                COLUMN_AUTHOR_NAME+","+
                COLUMN_BOOK_ISBN_13+","+
                COLUMN_BOOK_EDITION+","+
                COLUMN_BOOK_LANGUAGE+","+
                COLUMN_PUBLISHER_NAME+","+
                COLUMN_BOOK_PUBLICATION_DATE);
        stringBuilder.append(" FROM "+TABLE_BOOKS);
        stringBuilder.append(" INNER JOIN "+TABLE_AUTHORS);
        stringBuilder.append(" ON "+TABLE_BOOKS+"."+COLUMN_BOOK_AUTHOR_ID +"="+TABLE_AUTHORS+"."+COLUMN_AUTHOR_ID);
        stringBuilder.append(" INNER JOIN "+TABLE_PUBLISHER);
        stringBuilder.append(" ON "+TABLE_BOOKS+"."+COLUMN_BOOK_PUBLISHER_ID +"="+TABLE_PUBLISHER+"."+COLUMN_PUBLISHER_ID);

        System.out.println("SQL statement: "+stringBuilder.toString()+"\n");

        // Use try-with-resource will automatic close both Statement and ResultSet
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuilder.toString())){

            List<String> books = new ArrayList<>();
            while(resultSet.next()){
                books.add("Book Name: "+resultSet.getString(1)+
                        "\nAuthor: "+resultSet.getString(2)+
                        "\nISBN-13: "+resultSet.getString(3)+
                        "\nEdition: "+resultSet.getInt(4)+
                        "\nLanguage: "+resultSet.getString(5)+
                        "\nPublisher: "+resultSet.getString(6)+
                        "\nPublication Date: "+resultSet.getString(7)+"\n");
            }
            return books;
        }catch (SQLException e){
            System.out.println("Query failed: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }// end: queryAllBooksInfo

    public List<String> queryBooksTitleByAuthorName(String authorName){
//        SELECT book_name, author_name
//        FROM authors, books
//        WHERE authors.author_ID=books.book_author_ID
//        AND authors.author_name = 'authorName'
        StringBuilder stringBuilder = new StringBuilder("SELECT "+COLUMN_BOOK_NAME+","+COLUMN_AUTHOR_NAME+
                " FROM "+TABLE_AUTHORS+","+TABLE_BOOKS+
                " WHERE "+TABLE_AUTHORS+"."+COLUMN_AUTHOR_ID+"="+TABLE_BOOKS+"."+COLUMN_BOOK_AUTHOR_ID+
                " AND "+TABLE_AUTHORS+"."+COLUMN_AUTHOR_NAME+"='"+authorName+"'");

        System.out.println("SQL statement: "+stringBuilder.toString()+"\n");

        // Use try-with-resource will automatic close both Statement and ResultSet
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(stringBuilder.toString())){

            List<String> booksTitleByAuthor = new ArrayList<>();
            while(resultSet.next()){
                booksTitleByAuthor.add("Book Title: "+resultSet.getString("book_name")+
                        "\nAuthor: "+resultSet.getString("author_name")+"\n");
            }
            System.out.println("Size: "+booksTitleByAuthor.size());
            return booksTitleByAuthor;
        }catch (SQLException e){
            System.out.println("Query failed: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }// End: queryBooksByAuthorName

}
