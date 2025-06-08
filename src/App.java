import java.io.FileReader;
import java.nio.file.Paths;

import classes_graph.Graph;
import except.ListError;

public class App {
    public static void main(String[] args) throws Exception {
        String rootPath = Paths.get("").toAbsolutePath().toString();
        String inputFilePath = rootPath + "\\src\\input.txt";
        FileReader in = new FileReader(inputFilePath);
        ListError listError = new ListError();
        Yylex scanner = new Yylex(in, listError); 
        Parser parser = new Parser(scanner); 
        try { 
            parser.parse(); 

            if (listError.hasErrors()) {
                listError.logErrors();
            } else {
                System.out.println("Sintaxe correta!");
            
                if (parser.deveImprimir && parser.grafo != null) {
                    parser.grafo.saveAdjacencyMatrixToFile();
                } else {
                    System.out.println("Matriz não solicitada ou grafo não construído.");
                }
            }

        } catch (Exception e) { 
            System.err.println("Erro de sintaxe: " + e.getMessage()); 
        } 
    }
}
