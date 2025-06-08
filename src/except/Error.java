package except;

public class Error {
    private int line, column;
    private String text;

    public Error(int line, int column, String text) {
        this.line = line;
        this.column = column;
        this.text = text;
    }

    public void print() {
        System.out.println(format());
    }

    public String format() {
        String aux = "Erro na linha " + line + " e coluna " + column + ": ";
        if (this.text == null)
            aux += "Erro desconhecido";
        else
            aux += this.text;

        return aux;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    } 
}
