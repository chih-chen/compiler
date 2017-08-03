class MeuLexico extends Lexer;

options
{
   caseSensitive = true;   
   k = 2;
}

OP   
 options{
    paraphrase = "an operator";
 }
      :  ('+' | '-' | '*' | '/' | '>' | ">=" | '<' | "<=" )
         
      ;
 
ID    
   options{
       paraphrase = "an identifier";
   }
      :  ('a'..'z') ('a'..'z' | '0'..'9')*
         
      ;
      
NUM  
   options{
      paraphrase = "a number";
   }
      :  ('0'..'9')+
         
      ;
      
BLANK : (' ' | '\t' | '\n' | '\r') { _ttype = Token.SKIP; }
      ;
      
PF    : '.'
      ;
      
VIRG  : ','
      ;
      
EQ    : '='
      ;
      
      