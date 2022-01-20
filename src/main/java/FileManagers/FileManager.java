package FileManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

class WriteOnFile{
    private FileWriter fwriter;
    private BufferedWriter writer;
    
    WriteOnFile(String file) throws IOException{
        this.fwriter = new FileWriter(file);
        this.writer = new BufferedWriter(fwriter);
    }
    
    void writeItAll(ArrayList<String> text) throws IOException{
        for(int i = 0; i < text.size(); i ++){
            this.writer.write(text.get(i));
            this.writer.newLine();
        }
        this.writer.flush();
    }
}

public abstract class FileManager {
    public ArrayList<String> textDoc = new ArrayList();
    private String document;
    private FileReader fReader;
    private BufferedReader reader;
    private WriteOnFile writer;
    
    FileManager(String doc) throws IOException{
        this.document = doc;
        this.fReader = new FileReader(doc);
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(doc),"ISO-8859-1"));
        String line;
        while((line = reader.readLine()) != null){
            line = line.replaceAll("Ã£", "ã");
            this.textDoc.add(line);
        }
        this.updateTextFile();
    }
    
    void updateTextFile() throws IOException{
        this.writer = new WriteOnFile(this.document);
        writer.writeItAll(this.textDoc);
    }
}
