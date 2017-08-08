import java.util.HashMap;

public class assignCommand extends Command {
  
  public String expression;
  public String key;
  public String stringValue;
  
  public static final int TYPE_NUMBER = 1;
  public static final int TYPE_STRING = 2;
  
  private int mode;

  public assignCommand(){
    mode = 0;
  }

  public void run(){
  }
  
  public void changeMode(int mode){
    this.mode = mode;
  }
  
  public void buildExpression(String k, String ex){
    this.expression = ex;
    this.key = k;
  }
  
  public void buildString(String k, String str){
    this.key = k;
    this.stringValue = str;
  }
  
  public String writeJava(){
    return (mode==1)?String.format("%s = %s;\n",key,expression):String.format("%s = %s;\n",key,stringValue);
  }
  
}