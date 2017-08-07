import java.util.ArrayList;
import java.util.HashMap;

public class Program{
  
  public static HashMap<String,String> stringVarList;
  public static HashMap<String,Float>  numberVarList;
  public static ArrayList<Command> commands;
  public static String className;
  
  public Program(){
    numberVarList = new HashMap<String,Float>();
    stringVarList = new HashMap<String,String>();
    commands = new ArrayList<Command>();
  }
  
  public static void addStringVariable(String var){
    stringVarList.put(var,null);
  }
  
  public static void setStringVarValue(String var, String value){
    if (stringVarList.containsKey(var)){
        stringVarList.put(var, value);
    }
  }
  
  public static String getStringVarValue(String var){
    return stringVarList.get(var);
  }
  
  public static void addNumberVariable(String var){
    numberVarList.put(var,null);
  }
  
  public static void setNumberVarValue(String var, Float value){
    if (numberVarList.containsKey(var)){
        numberVarList.put(var, value);
    }
  }
  
  public static Float getNumberVarValue(String var){
    return numberVarList.get(var);
  }
  
  public static void addCommand(Command cmd){
    commands.add(cmd);
  }
  
  public void run(){
    for (Command c: commands){
      c.run();
    }
  }

  public String writeJava(){
      StringBuilder str = new StringBuilder();
      str.append("public class "+this.className+"{\n");
      str.append("public static void main(String args[]){\n");
      str.append("java.util.Scanner teclado = new java.util.Scanner(System.in);\n");

      for (Command c: commands){
          str.append(c.writeJava());    
      }
      str.append("}\n");
      str.append("}\n");
      return str.toString();
      
  }
    
}