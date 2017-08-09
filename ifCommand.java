import java.util.ArrayList;
import java.util.Scanner;

public class ifCommand extends Command {
  
  public static final int IF_MODE   = 1;
  public static final int ELSE_MODE = 2;
    
  private ArrayList<Command> ifList;           //save commands inside the if curly braces
  private ArrayList<Command> elseList;         //save commands inside the else curly braces
  private String             logicalExpr;      //save expression inside the if parenthesis
  private int                mode;             //if or else mode
  
  public ifCommand(){
    this.ifList    = new ArrayList<Command>();
    this.elseList  = new ArrayList<Command>();
    this.mode      = IF_MODE;
  }
  
  public void setLogicalExpr(String expr){
    this.logicalExpr = expr;
  }
    
  public String getLogicalExpr(){
    return this.logicalExpr;
  }
  
  public void changeMode(int mode){
    this.mode = mode;
  }
  
  public int getMode(){
    return this.mode;
  }

  public void addCommand(Command c){
    if (this.getMode() == IF_MODE)
      this.ifList.add(c);
    else
      this.elseList.add(c);
  }

  public void run(){
    for (Command c: ifList){
      c.run();      //polymorphism
    }
    if(!elseList.isEmpty()){
      for(Command c: elseList){
        c.run();
      }
    }
  }
  
  public String writeJava(){
      StringBuilder str = new StringBuilder(); // not thread safe
      str.append("if (");
      str.append(this.getLogicalExpr());
      str.append("){\n");
      for (Command c: ifList){
          str.append(c.writeJava());
      }
      str.append("}\n");
      if (!elseList.isEmpty()){
          str.append("else{\n");
          for (Command c: elseList){
              str.append(c.writeJava());
          }
          str.append("}\n");
      }
      return str.toString();
  }

}