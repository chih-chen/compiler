public class putsCommand extends Command{
  
  public static final int TYPE_TEXT = 1;
  public static final int TYPE_ID  = 2;
  
  private String content;
  private int    type;
  
  public void setContent(String content){
    this.content = content;
  }
  
  public String getContent(){
    return this.content;
  }
  
  public void setType(int type){
    this.type = type;
  }
  
  public int getType(){
    return this.type;
  }
  
  
  public void run(){
    if (this.getType() == TYPE_ID)
      System.out.println(Program.getStringVarValue(content));       
    else 
      System.out.println(this.getContent());
  }
  
  public String writeJava(){
      return "System.out.println("+this.getContent()+");\n";
  }
  
}