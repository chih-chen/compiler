import java.util.ArrayList;
import java.util.Scanner;

public class whileCommand extends Command {
  

  private ArrayList<Command> whileList;           //save commands inside the if curly braces
  private String             logicalExpr;      //save expression inside the if parenthesis
  
  public whileCommand(){
    this.whileList    = new ArrayList<Command>();
  }
  
  public void setLogicalExpr(String expr){
    this.logicalExpr = expr;
  }
    
  public String getLogicalExpr(){
    return this.logicalExpr;
  }
  
  public void addCommand(Command c){
    
    this.whileList.add(c);

  }

  public void run(){
    for (Command c: whileList){
      c.run();      //polymorphism
    }
  }
  
  public String writeJava(){
      StringBuilder str = new StringBuilder(); // not thread safe
      str.append("while (");
      str.append(this.getLogicalExpr());
      str.append("){\n");
      for (Command c: whileList){
          str.append(c.writeJava());
      }
      str.append("}\n");
      
      return str.toString();
  }

}