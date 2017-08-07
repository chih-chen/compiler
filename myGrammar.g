// **************************** PARSER RULES *****************************
class MyParser extends Parser;

program: "program" ID EQUALS AC body FC ;

body: (declaration)* (statment)* ;

declaration: TYPE ID (COMMA ID)* HT ;  

statment: ifStatment | whileStatment | assignmentStatement | ioStatment | (expression HT);

assignmentStatement: ID EQUALS (expression|NUM|STRING) HT ;

ifStatment: IF AP expression FP AC (statment)* FC 
            (ELSE AC (statment)* FC)? ;

whileStatment: WHILE AP expression FP AB (statment)* FC ;

ioStatment: READ AP ID FP HT |
            PRINT AP expression FP HT ;

innerElement: ID | AP expression FP ;

signExpression: ((PLUS|MINUS))* innerElement ;

multiplyExpression: signExpression ((TIMES|DIV) signExpression)* ;

expression: multiplyExpression ((PLUS|MINUS) multiplyExpression)* ;

// **************************** LEXER RULES *****************************

class MyLexer extends Lexer;

options {
   k              = 2;                     // two characters of lookahead
   testLiterals   = false;  
   caseSensitive  = true;   
   charVocabulary = '\0'..'\377';
}

WS      : (' ' | '\n' | '\r' | '\t') 
          {newline();}
          {$setType(Token.SKIP);} ;
        
COMMENT : "//" (~('\n'|'\r'))* {$setType(Token.SKIP);} ;

TYPE    : "String" | "Number" ;

ID      options {testLiterals=true;} // hash table 
        : ('a'..'z' | 'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

NUM     : ('0'..'9')+ ('.' ('0'..'9')+ )? ;
        
STRING  : '"' ('a'..'z' | 'A'..'Z' | ' ' | '0'..'9')* '"' ;

PRINT : "puts" ;  READ : "read" ;

IF : "se" ;  ELSE : "do contrario" ;  WHILE   : "enquanto" ;

AC     : '{' ;   FC    : '}' ;   AP    : '('  ;   FP  : ')' ;
HT     : '#' ;   COMMA : ',' ;
EQUALS : '=' ;   LT    : '<' ;   LTE   : "<=" ;   GT  : '>' ;   GTE : ">=" ;  
PLUS   : '+' ;   MINUS : '-' ;   TIMES : '*'  ;   DIV : '/' ;