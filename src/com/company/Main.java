package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
	    try{
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/Bing/Documents/GitHub/BingNaYang.github.io/BooksDatabase/books.db");
//            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS books "+
                    "(book_ISBN_13 TEXT PRIMARY KEY, book_name TEXT, book_edition INTEGER, book_language TEXT, book_publication_date TEXT, author_ID INTEGER, publisher_ID INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS authors "
                    +"(author_ID INTEGER PRIMARY KEY, author_name TEXT)");
            statement.execute("CREATE TABLE IF NOT EXISTS publishers "
                    +"(publisher_ID INTEGER PRIMARY KEY, publisher_name TEXT )");

            /**
             * Insert data to database
             */
//            statement.execute("INSERT INTO books (book_ISBN_13,book_name,book_edition,book_language,book_publication_date,author_ID,publisher_ID)"+
//                    "VALUES('978-1259589317','Java: A Beginners Guide',7,'English','10/9/2017',1,1)");
//            statement.execute("INSERT INTO authors (author_ID,author_name)"+
//                    "VALUES(1,'Herbert Schildt')");
//            statement.execute("INSERT INTO publishers (publisher_ID,publisher_name)"+
//                    "VALUES(1,'McGraw-Hill Education')");
//
//            statement.execute("INSERT INTO books (book_ISBN_13,book_name,book_edition,book_language,book_publication_date,author_ID,publisher_ID)"+
//                    "VALUES('978-0672324536','Data Structures and Algorithms in Java',2,'English','11/16/2002',2,2)");
//            statement.execute("INSERT INTO authors (author_ID,author_name)"+
//                    "VALUES(2,'Robert Lafore')");
//            statement.execute("INSERT INTO publishers (publisher_ID,publisher_name)"+
//                    "VALUES(2,'Sams Publishing')");
//
//            statement.execute("INSERT INTO books (book_ISBN_13,book_name,book_edition,book_language,book_publication_date,author_ID,publisher_ID)"+
//                    "VALUES('978-1848000698','The Algorithm Design Manual',2,'English','4/27/2011',3,3)");
//            statement.execute("INSERT INTO authors (author_ID,author_name)"+
//                    "VALUES(3,'Steven S. Skiena')");
//            statement.execute("INSERT INTO publishers (publisher_ID,publisher_name)"+
//                    "VALUES(3,'Springer')");
            /**
             * Update database
             */
//            statement.execute("UPDATE books SET book_publication_date='04/27/2011' WHERE book_ISBN_13='978-1848000698'");
            /**
             * DELETE database
             */
//             statement.execute("DELETE FROM books WHERE book_ID=2");
            /**
             * Query
             */

            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            while(resultSet.next()){
                System.out.println("Book Title: "+resultSet.getString("book_name") +"\n"+
                        "ISBN-13: "+resultSet.getString("book_ISBN_13")+"\n"+
                        "Edition: "+resultSet.getInt("book_edition")+"\n"+
                        "Language: "+resultSet.getString("book_language")+"\n");
            }
            resultSet.close();


            statement.close();
            connection.close();
        }catch (SQLException e){
	        System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
