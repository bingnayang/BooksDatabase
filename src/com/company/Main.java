package com.company;

import com.company.model.Author;
import com.company.model.Datasource;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Cannot open datasource");
            return;
        }

        List<Author> authors = datasource.queryAuthors();
        if(authors == null){
            System.out.println("No Author in database");
            return;
        }
        for(Author author : authors){
            System.out.println("ID: "+author.getAuthor_ID()+", Author Name: "+author.getAuthor_name());
        }

        datasource.close();
    } // end: main
}
