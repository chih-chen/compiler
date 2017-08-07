public class Stack{
  
  private Command itens[];
  private int     top;
  
  public Stack(){
      itens = new Command[10];
      top = -1;
  }
  
  public void push(Command cmd){
      itens[++top] = cmd;
  }
  public boolean isEmpty(){
      return top == -1;
  }
  public Command pop(){
      return itens[top--];
  }
  
  public Command getTopElement(){
      return itens[top];
  }
}