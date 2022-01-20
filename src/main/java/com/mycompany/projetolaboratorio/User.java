package com.mycompany.projetolaboratorio;

import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author robso
 */
public class User {
    private String name;
    private String password;
    private String id;
    private ArrayList<String> booksOwn = new ArrayList();
    
    User(String empty){
        this.name = "";
        this.password = "";
        this.id = "";
    }
    
    User(String name, String password, String id){
        this.name = name;
        this.password = password;
        this.id = id;
    }
    
    User(String name, String password, String id, ArrayList<String> books){
        this(name, password, id);
        if(!books.isEmpty())
            if((!(books.size() == 1)) || (!(books.get(0).equals(""))))
                this.booksOwn = books;
    }
    
    User(String name, String password, String id, String books){
        this(name, password, id);
        this.booksOwn = new ArrayList<>(Arrays.asList(books.split(", ")));/*
        int emptyId = -1;
        for(int i = 0; i < this.booksOwn.size(); i++){
            if(this.booksOwn.get(i).equals("")){
                emptyId = i;
            }
        }
        if(emptyId != -1)
            this.booksOwn.remove(emptyId);*/
    }
    
    public void rentABook(Book book){
        this.booksOwn.add(book.getId());
    }
    
    public void returnABook(Book book){
        if(this.booksOwn.contains(book.getId())){
            this.booksOwn.remove(book.getId());
        }
    }
    
    public String getInfo(){
        return this.name + " --- " + this.id + " --- " + this.booksOwn;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getId(){
        return this.id;
    }
    
    public ArrayList<String> getBooksOwn(){
        return this.booksOwn;
    }
    
    public void addBookRent(String bookId){
        this.booksOwn.add(bookId);
    }
    
    public void returnABook(String bookId){
        if(this.booksOwn.contains(bookId)){
            this.booksOwn.remove(this.booksOwn.indexOf(bookId));
        }
    }
}
