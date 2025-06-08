import java_cup.runtime.Symbol;
import except.ListError;

%%

%cup
%unicode
%line
%column

%{
    private ListError listError;

    public Yylex(java.io.FileReader in, ListError listError) {
        this(in);
        this.listError = listError;
    }

    public ListError getListError() {
        return listError;
    }

    public void defineError(int line, int column, String text) {
        this.listError.defineError(line, column, text);
    }

    public void defineError(int linha, int coluna) {
        this.listError.defineError(linha, coluna);
    } 

    public void defineError(String texto) {
        this.listError.defineError(texto);
    }

    public Symbol createSymbol(int token, Object value) {
        return new Symbol(token, yyline, yycolumn, value);
    }

    public Symbol createSymbol(int token) {
        return this.createSymbol(token, null);
    }

    public Symbol newSymbol(int token, Object value) { 
        return new Symbol(token, yyline, yycolumn, value); 
    } 
 
    public Symbol newSymbol(int token) { 
        return this.newSymbol(token, null); 
    }
%}

espaco = [ \n\r\t\f]+
letra = [a-zA-Z_]
numero = [0-9]
id = {letra}({letra}|{numero})*

%%

"GRAPH"         { return newSymbol(Sym.GRAPH); }
"vertex"        { return newSymbol(Sym.vertex); }
"edge"          { return newSymbol(Sym.edge); }
"directed"      { return newSymbol(Sym.directed); }
"undirected"    { return newSymbol(Sym.undirected); }
"print"         { return newSymbol(Sym.print); }
"adjacency"     { return newSymbol(Sym.adjacency); }
":"             { return newSymbol(Sym.colon); }
"->"            { return newSymbol(Sym.arrow); }
"--"            { return newSymbol(Sym.line); }
{id}            { return newSymbol(Sym.ID, yytext()); }
{espaco}        { /* Ignora espaços, quebras de linha e tabulações */ }
.               { this.defineError(yyline, yycolumn, "Sintaxe inválida! Código desconhecido: " + yytext());
                  return newSymbol(Sym.EOF);} 
<<EOF>>         { return newSymbol(Sym.EOF); }