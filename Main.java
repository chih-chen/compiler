import java.io.*;

public class Main{
    public static void main(String args[]){
        try{
            MyLexer lexer   = new MyLexer(new FileInputStream(new File("input.prog")));
            MyParser parser = new MyParser(lexer);
            parser.init();
            parser.program();
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}