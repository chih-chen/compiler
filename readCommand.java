import java.util.Scanner;

public class readCommand extends Command{
    private String id;
    
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    
    public void run(){
        System.out.println("Rodando comando READ");
        Scanner teclado = new Scanner(System.in);
        float valor = teclado.nextInt();
        Program.setNumberVarValue(id,valor);
    }
    
    public String writeJava(){
        return id+"=teclado.nextInt();\n";
    }
}