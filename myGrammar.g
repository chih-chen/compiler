// **************************** PARSER RULES *****************************
class MyParser extends Parser;

{
   private Program  program;
   private Command  command;
   private int      writeType;
   private String   element;
   private Stack    stack;

   public void init(){
       program = new Program();
       stack   = new Stack();
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

statment: ifStatment | whileStatment | assignmentStatement | ioStatment ;

assignmentStatement: ID EQUALS (expression|NUM|STRING) HT ;

ifStatment: "se" AP expression FP AC (statment)* FC 
            ("do contrario" AC (statment)* FC)? ;

whileStatment: "enquanto" AP expression FP AB (statment)* FC ;

ioStatment: "read" AP ID FP HT |
            "puts" AP expression FP HT ;

innerElement: ID | AP expression FP ;

signExpression: ((PLUS|MINUS))* innerElement ;

multiplyExpression: signExpression ((TIMES|DIV) signExpression)* ;

addExpression: multiplyExpression ((PLUS|MINUS) multiplyExpression)* ;

expression: addExpression ((EQUALS|LT|LTE|GT|GTE) addExpression)* ;

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
        
STRING  : '"' ('a'..'z' | 'A'..'Z' | ' ' | '?' | '!' | '#' |'(' | ')' | '0'..'9')* '"' ;

//PRINT : "puts" ;  READ : "read" ;                             this causes nondeterminism

//IF : "se" ;  ELSE : "do contrario" ;  WHILE   : "enquanto" ;  this causes nondeterminism

ID      options {testLiterals=true;}                            // hash table 
        : ('a'..'z' | 'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

AC     : '{' ;   FC    : '}' ;   AP    : '('  ;   FP  : ')' ;
HT     : '#' ;   COMMA : ',' ;
EQUALS : '=' ;   LT    : '<' ;   LTE   : "<=" ;   GT  : '>' ;   GTE : ">=" ;  
PLUS   : '+' ;   MINUS : '-' ;   TIMES : '*'  ;   DIV : '/' ;