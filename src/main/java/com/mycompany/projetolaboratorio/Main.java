package com.mycompany.projetolaboratorio;
import Screens.*;
import java.io.IOException;

/**
 *
 * @author robso
 */
public class Main {
    public static void main(String[] args) throws IOException{
        String dir = System.getProperty("user.dir");
        Library biblioteca = new Library(dir+("\\Lib.txt"), (dir+"\\Users.txt"));
        Initial.main(args, biblioteca);
    }
}
