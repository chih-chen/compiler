public class exprCommand extends Command {
  
  private StringBuilder str;
  
  public exprCommand(){
    str = new StringBuilder();
  }
  
  public void run(){
    
  }
  
  public String writeJava(){
    return str.toString();
  }
  
  
}