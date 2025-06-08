package br.edu.ifgoiano.run;

import java.nio.file.Paths;

public class RunScanner {

    public static void main(String[] args) {
        String rootPath = Paths.get("").toAbsolutePath().toString();
        String subPath = "/src/br/edu/ifgoiano/";
        try {
            String flexFile[] = {rootPath + subPath + "lexical_analyzer.flex"};
            jflex.Main.main(flexFile);
            System.out.println("Yylex.java gerado!");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
