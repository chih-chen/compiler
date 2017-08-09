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
    // --
    private String  element;
    private Stack   stack;
    private StringBuilder sb;
   
    public void init(){
      program = new Program();
      stack   = new Stack();
      sb = new StringBuilder();
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
                                          ((decCommand)command).changeMode(decCommand.TYPE_STRING);
                                        } else {
                                          ((decCommand)command).changeMode(decCommand.TYPE_NUMBER);
                                        }
                                        ((decCommand)command).addVariable(LT(0).getText());
                                      }
              (COMMA ID {
                          ((decCommand)command).addVariable(LT(0).getText());
                        }
              )* HT {
                      program.addCommand(command);
                    }
            ;  

statment: ifStatement | whileStatement | assignmentStatement  | ioStatement | expression ;

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
                          program.addCommand(command);
                     };

ifStatement: "se" AP expression RELATIONAL expression FP AC (statment)* FC 
            ("do contrario" AC (statment)* FC)? ;

whileStatement: "enquanto" AP (ID|NUM) RELATIONAL (ID|NUM) FP AB (statment)* FC ;

ioStatement: "read" AP ID FP HT |
             "puts" AP (expression|STRING) FP HT ;

            //expression just do math
            //soh passou aqui, nada de mais, o valor eh pego no inner
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
                    } ) innerElement {
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
                if(program.numberVarList.get(LT(0).getText())==null)
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
   //charVocabulary = '\0'..'\377';
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