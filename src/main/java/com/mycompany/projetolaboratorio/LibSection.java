/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetolaboratorio;

import java.util.ArrayList;

/**
 *
 * @author robso
 */
public class LibSection{
    private final String type;
    private int diffBookAmn = 0;
    private int bookAmounts = 0;
    private final ArrayList<Book> books = new ArrayList();
    
    LibSection(String type){
        this.type = type;
    }
    
    void registerBook(Book book){
        this.books.add(book);
        this.diffBookAmn++;
        this.bookAmounts += book.getAmount();
    }
    
    public ArrayList<Book> getAllBooks(){
        return this.books;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getABookInfo(int index){
        Book b =  this.books.get(index);
        return b.getName() + " " + b.getAuthor() + " " + b.getAmount() + " " + b.getAmountFree() + " " + b.getRentBy();
    }
    
    public int getAmountDiff(){
        return this.diffBookAmn;
    }
    
    public int getBookAmout(){
        return this.bookAmounts;
    }
    
    public ArrayList<String> getAllBooksNames(){
        ArrayList<String> arr = new ArrayList();
        for(int i = 0; i < this.books.size(); i++){
            arr.add(this.books.get(i).getName());
        }
        return arr;
    }
    
    public ArrayList<String> getAllBooksId(){
        ArrayList<String> arr = new ArrayList();
        for(int i = 0; i < this.books.size(); i++){
            arr.add(this.books.get(i).getId());
        }
        return arr;
    }
}
