package com.company;

import com.company.model.Author;
import com.company.model.Book;
import com.company.model.Datasource;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        Scanner scanner = new Scanner(System.in);
        if (!datasource.open()) {
            System.out.println("Cannot open datasource");
            return;
        }
        int option;
        do {
            startMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    displayAllAuthorsName(datasource);
                    break;
                case 2:
                    displayAllBookTitle(datasource);
                    break;
                case 3:
                    displayAllBookInfo(datasource);
                    break;
                case 4:
                    searchBooksByAuthor(datasource);
                    break;
                case 5:
                    searchBookByISBN_13(datasource);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Not an option");
                    break;
            }
        } while (option != 5);
        datasource.close();
    } // end: main

    public static void startMenu() {
        System.out.println("========Book Database========");
        System.out.println("1) List All Authors in Database");
        System.out.println("2) List All Books Title in DataBase");
        System.out.println("3) List All Books Info in DataBase");
        System.out.println("4) Search Books Title By Author");
        System.out.println("5) Search Book and Author By ISBN_13");
        System.out.println("6) Exit");
        System.out.print("Enter Your Option: ");
        System.out.println();
    }

    public static void displayAllAuthorsName(Datasource datasource) {
        // Get Authors Name
        System.out.println("===List Authors in DataBase===");
        List<Author> authors = datasource.queryAuthors(Datasource.ORDER_BY_NONE);
        if (authors == null) {
            System.out.println("No Author in database");
            return;
        }
        for (Author author : authors) {
            System.out.println("Author Name: " + author.getAuthor_name());
        }
    }

    public static void displayAllBookTitle(Datasource datasource) {
        // Get Book Title
        System.out.println("===List Books Title in DataBase===");
        List<Book> books = datasource.queryBooksTitle(Datasource.ORDER_BY_NONE);
        if (books == null) {
            System.out.println("No book in database");
            return;
        }
        for (Book book : books) {
            System.out.println("Book Title: " + book.getBook_name());
        }
    }

    public static void displayAllBookInfo(Datasource datasource) {
        // Get All Book Info
        System.out.println("===List All Books Info in DataBase===");
        List<String> bookInfo = datasource.queryAllBooksInfo(Datasource.ORDER_BY_NONE);
        if (bookInfo == null) {
            System.out.println("No book in database");
            return;
        }
        for (String book : bookInfo) {
            System.out.println(book);
        }
    }

    public static void searchBooksByAuthor(Datasource datasource) {
        // Get Books Title by Author
        Scanner scanner = new Scanner(System.in);
        System.out.println("===List Books Title By Author===");
        System.out.println("Enter Author Name: ");
        String authorName = scanner.nextLine();

        List<String> booksByAuthor = datasource.queryBooksTitleByAuthorName(authorName);
        if (booksByAuthor == null) {
            System.out.println("No such data");
            return;
        }
        for (String bookByAuthor : booksByAuthor) {
            System.out.println(bookByAuthor);
        }
    }
    public static void searchBookByISBN_13(Datasource datasource){
        List<String> booksByAuthor = datasource.queryBookAuthorByISBN("978-1259589317");
        if (booksByAuthor == null) {
            System.out.println("No such data");
            return;
        }
        for (String bookByAuthor : booksByAuthor) {
            System.out.println(bookByAuthor);
        }
    }
}
