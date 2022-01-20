package FileManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class UserManager extends FileManager{
    public UserManager(String doc) throws IOException{
        super(doc);
    }
    
    public void addUser(String name, String password, String id, String booksOwn) throws IOException{
        this.textDoc.add(name + "; " + password + "; " + id + "; [" + booksOwn);
        this.updateTextFile();
    }

    public void updateBookRents(String booksRent, String userId) throws IOException{
        ArrayList<String> br = new ArrayList<>(Arrays.asList(booksRent.split(",")));
        ArrayList<Integer> toDel = new ArrayList();
        for(int i = 0; i < br.size(); i++){
            if(br.get(i).equals("") || br.get(i).equals(" ")){
                toDel.add(i);
            }
        }
        for(int i = 0; i < toDel.size(); i++){
            br.remove(toDel.get(i));
        }
        String bOwn = br.get(0);
        for(int i = 1; i < br.size(); i++){
            bOwn += "," + br.get(i);
        }
        int lineAt = this.getLineUserIsAt(userId);
        ArrayList<String> oldLine = new ArrayList<>(Arrays.asList(this.textDoc.get(lineAt).split("; ")));
        oldLine.set(3, "[" + bOwn);
        String line = oldLine.get(0);
        for(int i = 1; i < oldLine.size(); i++){
            line += "; " + oldLine.get(i);
        }
        this.textDoc.set(lineAt, line);
        this.updateTextFile();
    }

    private int getLineUserIsAt(String userId){
        int userAt = -1;
        for(int i = 0; i < this.textDoc.size(); i++){
            if(this.textDoc.get(i).split("; ")[2].equals(userId)){
                userAt = i;
            }
        }
        return userAt;
    }
}
