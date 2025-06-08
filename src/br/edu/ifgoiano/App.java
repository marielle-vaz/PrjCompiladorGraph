package br.edu.ifgoiano;

import java.io.FileReader;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws Exception {
        String rootPath = Paths.get("").toAbsolutePath().toString();
        String inputFilePath = rootPath + "\\src\\br\\edu\\ifgoiano\\input.txt";
        FileReader in = new FileReader(inputFilePath);
        Yylex scanner = new Yylex(in); 
        Parser parser = new Parser(scanner); 
        try { 
            parser.parse(); 
        } catch (Exception e) { 
            System.out.print(""); 
        } 
        System.out.println("Sintaxe correta!"); 
        
    }
}
