// **************************** PARSER RULES *****************************
class MyParser extends Parser;

options {
  k = 2;                     // two characters of lookahead
}

{
    private Program program;
    private Command command;
    //math variables
    private double  result;
    private double  multiResult;
    private double  varValue;
    private String  operator;
    //relational operation string
    private StringBuilder logicalExpr;
    //assignment / puts
    private String element;
    //puts 
    private int writeType;
    // --
    private Stack   stack;
    //math expression string builder
    private StringBuilder sb;
   
    public void init(){
      program = new Program();
      stack   = new Stack();
      sb = new StringBuilder();
      logicalExpr = new StringBuilder();
    }
}

program: "program" ID { 
            program.className = LT(0).getText();
          } 
          EQUALS AC body FC {  
            System.out.println(program.writeJava());
          }
          ;

body: (declaration)* (statment)* ;

declaration: ("String" | "Number") ID { 
                command = new decCommand();
                if(LT(-1).getText().equals("String")){
                  ((decCommand)command).changeMode(decCommand.TYPE_TEXT);
                } else {
                  ((decCommand)command).changeMode(decCommand.TYPE_NUMBER);
                }
                ((decCommand)command).addVariable(LT(0).getText());
              }
              (COMMA ID {
                ((decCommand)command).addVariable(LT(0).getText());
              } )* 
              HT {
                program.addCommand(command);
              }
              ;  

//cada statement precisa ter pilha
statment: ifStatement | whileStatement | dowhileStatement | assignmentStatement  | ioStatement ;

assignmentStatement: ID { 
                      element = LT(0).getText(); 
                      command = new assignCommand();
                     } 
                     EQUALS (expression|STRING) {                         
                      if(!LT(0).getText().contains("\"") && program.numberVarList.containsKey(element)) {
                        ((assignCommand)command).changeMode(assignCommand.TYPE_NUMBER);
                        program.setNumberVarValue(element,result);
                        System.out.println("Resultado = " + result);
                        ((assignCommand)command).buildExpression(element, sb.toString());
                        sb.setLength(0);
                      } else if(LT(0).getText().contains("\"") && program.stringVarList.containsKey(element)) {
                        ((assignCommand)command).changeMode(assignCommand.TYPE_STRING);
                        program.setStringVarValue(element,LT(0).getText());
                        ((assignCommand)command).buildString(element,LT(0).getText());
                      } else {
                        throw new RuntimeException ("<<<<< Usou sem declarar! >>>>>");
                      }
                    }
                     HT {
                      if (stack.isEmpty()){
                        program.addCommand(command);
                    } else {
                      Command tmp = stack.getTopElement();
                      tmp.addCommand(command);
                    }
                  }
                  ;


ifStatement: "se" AP (ID | NUM) {
                logicalExpr.append(LT(0).getText());
              }
              RELATIONAL {
                logicalExpr.append(LT(0).getText());
              }
              (ID|NUM) {
                command = new ifCommand();
                logicalExpr.append(LT(0).getText());
                ((ifCommand)command).setLogicalExpr(logicalExpr.toString());
                stack.push(command);
                logicalExpr.setLength(0);
              } 
              FP AC (statment)* FC 
              ("senao" AC {
                ifCommand tmp = (ifCommand)stack.getTopElement();
                tmp.changeMode(ifCommand.ELSE_MODE);
              }
              (statment)* FC)? {
                Command cmd = stack.pop();
                if (stack.isEmpty()){
                  program.addCommand(cmd);
                } else {
                  Command tmp = stack.getTopElement();
                  tmp.addCommand(cmd);
                }
              }
              ;

whileStatement: "enquanto" AP (ID | NUM) {
                logicalExpr.append(LT(0).getText());
              }
              RELATIONAL {
                logicalExpr.append(LT(0).getText());
              }
              (ID|NUM) {
                command = new whileCommand();
                logicalExpr.append(LT(0).getText());
                ((whileCommand)command).setLogicalExpr(logicalExpr.toString());
                stack.push(command);
                logicalExpr.setLength(0);
              } 
              FP AC (statment)* FC {
                Command cmd = stack.pop();
                if (stack.isEmpty()){
                  program.addCommand(cmd);
                } else {
                  Command tmp = stack.getTopElement();
                  tmp.addCommand(cmd);
                }
              }
              ;
              
dowhileStatement: "faca" {
                  command = new doWhileCommand();
                  stack.push(command);
                } AC (statment)* FC 
                "enquanto" AP (ID | NUM) {
                  logicalExpr.append(LT(0).getText());
                }
                RELATIONAL {
                  logicalExpr.append(LT(0).getText());
                }
                (ID|NUM) {
                  logicalExpr.append(LT(0).getText());
                  doWhileCommand temp = (doWhileCommand)stack.getTopElement();
                  temp.setLogicalExpr(logicalExpr.toString());
                  logicalExpr.setLength(0);
                } 
                FP HT {
                  Command cmd = stack.pop();
                  if (stack.isEmpty()){
                    program.addCommand(cmd);
                  } else {
                    Command tmp = stack.getTopElement();
                    tmp.addCommand(cmd);
                  }
                }
              ;

ioStatement: readCommand | putsCommand ;
             
readCommand: "read" { 
                command = new readCommand(); 
              } AP ID {
                element = LT(0).getText();
                if(program.stringVarList.containsKey(element)){
                  ((readCommand)command).setType(readCommand.TYPE_TEXT);
                } else if(program.numberVarList.containsKey(element)) {
                  ((readCommand)command).setType(readCommand.TYPE_NUMBER);
                } else {
                  throw new RuntimeException ("<<<<< Variavel n declarado! >>>>>");
                }
              } 
              FP HT {
                ((readCommand)command).setId(element);
                if (stack.isEmpty()){
                  program.addCommand(command);
                } else {
                  Command tmp = stack.getTopElement();
                  tmp.addCommand(command);
                }
              }
              ;
              
putsCommand: "puts" {
                command = new putsCommand();
              } 
              AP (ID {
                writeType = putsCommand.TYPE_ID;
              }
              | STRING {
                writeType = putsCommand.TYPE_TEXT;
              }) {
                element = LT(0).getText();
              } 
              FP HT {
                ((putsCommand)command).setType(writeType);
                ((putsCommand)command).setContent(element);
                if (stack.isEmpty()){
                   program.addCommand(command);
                } else{
                  Command tmp = stack.getTopElement();
                  tmp.addCommand(command);
                }
              } 
              ;
              

            // valor eh pego no inner
expression: multiplyExpression {
              result = multiResult;
            }
            (( PLUS {
              operator = LT(0).getText();
              sb.append(LT(0).getText());
            } 
            | MINUS {
              operator = LT(0).getText();
              sb.append(LT(0).getText());
            } ) multiplyExpression {
              if(operator.equals("+"))
                result+=multiResult;
              else
                result-=multiResult;
            })* 
            ; 

                    //soh passou aqui, nada de mais, o valor eh pego no inner
multiplyExpression: innerElement {
                      multiResult = varValue;
                    }
                    (( TIMES {
                      operator = LT(0).getText();
                      sb.append(LT(0).getText());
                    } 
                    | DIV {
                      operator = LT(0).getText();
                      sb.append(LT(0).getText());
                    } ) 
                    innerElement {
                      if(operator.equals("*"))
                        multiResult*=varValue;
                      else
                        multiResult/=varValue;
                    })* 
                    ;

innerElement: NUM {
                sb.append(LT(0).getText());
                varValue = Double.parseDouble(LT(0).getText());
              } 
              | ID {
                sb.append(LT(0).getText());
                if(!program.numberVarList.containsKey(LT(0).getText()) )
                  throw new RuntimeException("<<<<< Usou sem atribuir! >>>>>");
                varValue = program.numberVarList.get(LT(0).getText());
              }   
              ;

// **************************** LEXER RULES *****************************

class MyLexer extends Lexer;

options {
   k              = 2;                     // two characters of lookahead
   testLiterals   = false;  
   caseSensitive  = true;
}

WS      : (' ' | '\n' | '\r' | '\t') 
          {newline();}
          {$setType(Token.SKIP);} ;
        
COMMENT : "//" (~('\n'|'\r'))* {$setType(Token.SKIP);} ;

//TYPE    : "String" | "Number" ;                              this causes nondeterminism

NUM     : ('0'..'9')+ ('.' ('0'..'9')+ )? ;
        
STRING  : '"' ('a'..'z' | 'A'..'Z' | ' ' | '?' | '!' |'(' | ')' | '0'..'9')* '"' ;

//PRINT : "puts" ;  READ : "read" ;                             this causes nondeterminism

//IF : "se" ;  ELSE : "do contrario" ;  WHILE   : "enquanto" ;  this causes nondeterminism

ID      options {testLiterals=true;}                            // hash table 
        : ('a'..'z' | 'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

AC     : '{' ;   FC    : '}' ;   AP    : '('  ;   FP  : ')' ;
HT     : '#' ;   COMMA : ',' ;
EQUALS : '=' ;   
RELATIONAL: '<' | "<=" | '>' | ">=" | "!=" | "|=";
PLUS   : '+' ;   MINUS : '-' ;   TIMES : '*'  ;   DIV : '/' ;