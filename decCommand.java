public class decCommand extends Command {
  
  public static final int TYPE_TEXT = 1;
  public static final int TYPE_NUMBER = 2;
  
  private int mode;
  
  public decCommand(){
    this.mode = 0;
  }
  
  public void changeMode(int mode){
    this.mode = mode;
  }
  
  public void addVariable(String value){
    if(this.mode == TYPE_NUMBER){
      Program.addNumberVariable(value);
    } else {
      Program.addStringVariable(value);
    }
  }
  
  public void run(){
    
  }
  
  public String writeJava(){
    
    StringBuilder str = new StringBuilder();
    if(this.mode == TYPE_STRING){
      for(String var: Program.stringVarList.keySet()){
        str.append("String "+var+";\n");
      }
    } else {
      for(String var: Program.numberVarList.keySet()){
        str.append("double "+var+";\n");
      }
    }
    return str.toString();
  }
  
}