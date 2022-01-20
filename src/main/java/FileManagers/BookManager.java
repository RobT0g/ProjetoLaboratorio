package FileManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public final class BookManager extends FileManager{
            
    public BookManager(String doc) throws IOException{
        super(doc);
    }

    private int getLineBookIsAt(String bookId){
        String line;
        String id;
        int lineAt = -1;
        for(int i = 0; i < this.textDoc.size(); i++){
            line = this.textDoc.get(i);
            if(!line.substring(0, 1).equals("-")){
                id = line.split("; ")[2];
                if(id.equals(bookId)){
                    lineAt = i;
                }
            }
        }
        return lineAt;
    }
}
