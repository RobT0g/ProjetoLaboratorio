package com.mycompany.projetolaboratorio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author robso
 */
public class Book {
    private final String name;
    private final String author;
    private final String id;
    private final int amount;
    private int amountFree;
    private ArrayList<String> rentBy = new ArrayList();
    
    Book(String name, String author, String id, int amount){
        this.name = name;
        this.author = author;
        this.id = id;
        this.amount = amount;
        this.amountFree = this.amount;
    }
    
    public void setNewOwner(String name){
        this.rentBy.add(name);
        this.amountFree = this.amount - this.rentBy.size();
    }
    
    public void removeOwner(String name){
        if(this.rentBy.contains(name)){
            this.rentBy.remove(this.rentBy.indexOf(name));
        }
        this.amountFree = this.amount - this.rentBy.size();
    }
    
    public Book(String empty){
        this.name = "";
        this.author = "";
        this.id = "";
        this.amount = 0;
        this.amountFree = 0;
        this.rentBy = new ArrayList();
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getAuthor(){
        return this.author;
    }
    
    public String getId(){
        return this.id;
    }
    
    public int getAmount(){
        return this.amount;
    }
    
    public int getAmountFree(){
        return this.amountFree;
    }
    
    public ArrayList<String> getRentBy(){
        return this.rentBy;
    }
}
