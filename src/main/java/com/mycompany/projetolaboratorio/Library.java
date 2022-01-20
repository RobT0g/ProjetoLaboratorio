package com.mycompany.projetolaboratorio;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;
import FileManagers.*;
/**
 *
 * @author robso
 */

public final class Library {
    private final ArrayList<LibSection> sections = new ArrayList();
    private final ArrayList<User> users = new ArrayList();
    private BookManager bookMan;
    private UserManager userMan;
    private int lastSecId = -1;
    private int loggedUser;
    
    Library(String doc1, String doc2) throws IOException{
        this.bookMan = new BookManager(doc1);
        this.userMan = new UserManager(doc2);
        for(int i = 0; i < this.bookMan.textDoc.size(); i++){
            if("-".equals(this.bookMan.textDoc.get(i).substring(0, 1))){
                this.sections.add(new LibSection(this.bookMan.textDoc.get(i).substring(1)));
                this.lastSecId++;
            } else if(!this.bookMan.textDoc.get(i).equals("")) {
                this.registerBook(this.getBookByLib(this.bookMan.textDoc.get(i)));
            }
        }
        ArrayList<String> booksRent;
        for(int i = 0; i < this.userMan.textDoc.size(); i++){
            if(!this.bookMan.textDoc.get(i).equals("")){
                booksRent = new ArrayList(Arrays.asList(this.userMan.textDoc.get(i).split("; ")[3].substring(1).split(", ")));
                this.users.add(this.getUserByLib(this.userMan.textDoc.get(i)));
                for(int j = 0; j < booksRent.size(); j++){
                    this.getBookById(booksRent.get(j)).setNewOwner(this.users.get(i).getName());
                }
            }
        }
    }
    
    private Book getBookByLib(String line){
        ArrayList<String> data = new ArrayList<>(Arrays.asList(line.substring(2).split("; ")));
        return new Book(data.get(0), data.get(1), data.get(2), Integer.parseInt(data.get(3)));
    }
    
    private User getUserByLib(String line){
        ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split("; ")));
        return new User(data.get(0), data.get(1), data.get(2),
            new ArrayList<>(Arrays.asList(data.get(3).substring(1).split(", "))));
    }
    
    private String formatName(String name){
        ArrayList<String> fstr = new ArrayList<>(Arrays.asList(name.split(" ")));
        String fname = "";
        for(int i = 0; i < fstr.size(); i++){
            if(!fstr.get(i).equals("")){
                fname = fstr.get(i);
                break;
            }
        }
        return fname.equals("") ? "" : fname.substring(0, 1).toUpperCase() + fname.substring(1).toLowerCase() ;
    }
    
    private String formatPassword(String pass){
        ArrayList<String> fstr = new ArrayList<>(Arrays.asList(pass.split(" ")));
        String fpass = "";
        for(int i = 0; i < fstr.size(); i++){
            if(!fstr.get(i).equals("")){
                fpass = fstr.get(i);
                break;
            }
        }
        return fpass;
    }
    
    public int login(String name, String password){
        String fname = this.formatName(name);
        String fpass = this.formatPassword(password);
        if(fname.equals("") || fpass.equals("")){
            return -1;
        }
        for(int i = 0; i < this.users.size(); i++){
            if(this.users.get(i).getName().equals(fname)){
                if(this.users.get(i).getPassword().equals(fpass)){
                    this.loggedUser = i;
                    return 1;
                } 
                return 2;
            }
        }
        return 0;
    }
    
    public int register(String name, String password) throws IOException{
        ArrayList<String> fname = new ArrayList<>(Arrays.asList(name.split(" ")));
        ArrayList<String> fpass = new ArrayList<>(Arrays.asList(password.split(" ")));
        if((fname.size() == 1 && fname.get(0).equals("")) || (fpass.size() == 1 && fpass.get(0).equals(""))){
            return 1;
        }
        String nome = this.formatName(name);
        int id = this.users.size();
        for(int i = 0; i < this.users.size(); i++){
            if(this.users.get(i).getName().equals(nome)){
                return 0;
            }
        }
        this.registerUser(new User(nome, password, ""+id, ""));
        return 2;
    }
    
    void registerBook(Book book){
        this.sections.get(this.lastSecId).registerBook(book);
    }
    
    void registerUser(User user) throws IOException{
        this.users.add(user);
        String booksOwn = "";
        ArrayList<String> bo = user.getBooksOwn();
        if(!bo.isEmpty()){
            if(!bo.get(0).equals("")){
                booksOwn = bo.get(0);
                for(int i = 1; i < bo.size(); i++){
                    booksOwn += ", " + bo.get(i);
                }
            }
        }
        this.userMan.addUser(user.getName(), user.getPassword(), user.getId(), booksOwn);
    }
    
    void showBooks(){
        for(int i = 0; i < this.sections.size(); i++){
            for(int j = 0; j < this.sections.get(i).getAmountDiff(); j++){
                System.out.println(this.sections.get(i).getABookInfo(j));
            }
        }
    }
    
    void showUsers(){
        for(int i = 0; i < this.users.size(); i++){
            System.out.println(this.users.get(i).getInfo());
        }
    }
    
    public String getLoggedUserName(){
        if(this.loggedUser == -1)
            return "";
        return this.users.get(this.loggedUser).getName();
    }
    
    public User getUserLogged(){
        if(this.loggedUser == -1)
            return new User("");
        return this.users.get(this.loggedUser);
    }
    
    public Book getBookById(String id){
        for(int i = 0; i<this.sections.size(); i++){
            for(int j = 0; j< this.sections.get(i).getAllBooks().size(); j++){
                if(this.sections.get(i).getAllBooks().get(j).getId() == null ? id == null : this.sections.get(i).getAllBooks().get(j).getId().equals(id))
                    return this.sections.get(i).getAllBooks().get(j);
            }
        }
        return new Book("");
    }
    
    public String getSectionBookIsAt(Book b){
        String s = "";
        ArrayList<Book> books;
        for(int i = 0; i < this.sections.size(); i++){
            books = this.sections.get(i).getAllBooks();
            for(int j = 0; j < books.size(); j++){
                if(b.getId().equals(books.get(j).getId())){
                    s = this.sections.get(i).getType();
                }
            }
        }
        return s;
    }
    
    public ArrayList<String> getSectionNames(){
        ArrayList<String> secnames = new ArrayList();
        for(int i = 0; i < this.sections.size(); i++){
            secnames.add(this.sections.get(i).getType());
        }
        return secnames;
    }
    
    public LibSection getSectionAt(int i){
        return this.sections.get(i);
    }
    
    public void rentABook(User user, Book book) throws IOException{
        user.addBookRent(book.getId());
        book.setNewOwner(user.getName());
        String booksRent = user.getBooksOwn().get(0);
        for(int i = 1; i < user.getBooksOwn().size(); i++){
            booksRent += ", " + user.getBooksOwn().get(i);
        }
        this.userMan.updateBookRents(booksRent, user.getId());
    }
    
    public void returnABook(User user, Book book) throws IOException{
        user.returnABook(book.getId());
        book.removeOwner(user.getName());
        String booksRent = user.getBooksOwn().isEmpty() ? "" : user.getBooksOwn().get(0);
        for(int i = 1; i < user.getBooksOwn().size(); i++){
                booksRent += ", " + user.getBooksOwn().get(i);
        }
        this.userMan.updateBookRents(booksRent, user.getId());
    }
}
