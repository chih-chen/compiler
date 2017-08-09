import java.util.ArrayList;
import java.util.Scanner;

public class doWhileCommand extends Command {
  

  private ArrayList<Command> doWhileList;           //save commands inside the do curly braces
  private String             logicalExpr;      //save expression inside the do parenthesis
  
  public doWhileCommand(){
    this.doWhileList    = new ArrayList<Command>();
  }
  
  public void setLogicalExpr(String expr){
    this.logicalExpr = expr;
  }
    
  public String getLogicalExpr(){
    return this.logicalExpr;
  }
  
  public void addCommand(Command c){
    
    this.doWhileList.add(c);

  }

  public void run(){
    for (Command c: doWhileList){
      c.run();      //polymorphism
    }
  }
  
  public String writeJava(){
      StringBuilder str = new StringBuilder(); // not thread safe
      str.append("do {\n");
      for (Command c: doWhileList){
          str.append(c.writeJava());
      }
      str.append("}\n");
       str.append("while (");
      str.append(this.getLogicalExpr());
      str.append(");\n");
      return str.toString();
  }

}