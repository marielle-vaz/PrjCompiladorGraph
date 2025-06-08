import java_cup.runtime.Symbol;

%%

%cup
%unicode
%line
%column

%{
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
<<EOF>>         { return newSymbol(Sym.EOF); }