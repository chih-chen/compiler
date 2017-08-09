import java.util.Scanner;

public class readCommand extends Command{
  
  public static final int TYPE_TEXT   = 1;
  public static final int TYPE_NUMBER = 2;
  private String id;
  private int type;
    
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    
    public void setType(int type){
      this.type = type;
    }
    
    public int getType(){
      return this.type;
    }
    
    public void run(){
        
    }
    
    public String writeJava(){
      if(this.getType()==readCommand.TYPE_NUMBER)
        return id+"=scan.nextDouble();\n";
      else
        return id+"=scan.nextLine();\n";
    }
}