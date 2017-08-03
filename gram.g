class MyParser extends Parser;
options{
    k = 2;
}
{
   private java.util.HashMap<String, Variavel> symbolTable; 
   private java.util.HashMap<String, Variavel>[] arraySymbolos;
   private java.util.ArrayList<Variavel> varList;
   private int VAR_TYPE;
   private int tipoExpr;
   int contadorPilha;
   public void init(){
           arraySymbolos = new java.util.HashMap[100];
           symbolTable = new java.util.HashMap<String, Variavel>(); 
           arraySymbolos[0] = symbolTable;
           int contadorPilha = 0;
           
   }
}

prog    : "programa" declara corpo "fimprog" {System.out.println("CABO");}
        ;
          
declara :   ("declare"  { varList = new java.util.ArrayList<Variavel>(); }
             ID 
                  {
                     if (symbolTable.get(LT(0).getText()) == null){
                             Variavel v = new Variavel();
                             v.setId(LT(0).getText());
                             symbolTable.put(v.getId(), v);
                             varList.add(v);
                     }       
                  }
            ( VIRG ID 
            {
                 if (symbolTable.get(LT(0).getText()) == null){
                      Variavel v = new Variavel();
                      v.setId(LT(0).getText());
                      symbolTable.put(v.getId(), v);
                      varList.add(v);
                 }   
            
            }
             )*  
            DP 
            (    "inteiro" {VAR_TYPE = Variavel.INTEIRO;}
               | "real"    {VAR_TYPE = Variavel.REAL;}
               | "texto"   {VAR_TYPE = Variavel.STRING;}
            ) PF
            {
               for (Variavel v: varList) v.setTipo(VAR_TYPE);
               
               System.out.println("MOSTRANDO VARIAVEIS ----");
               for (Object v: symbolTable.values().toArray()){
                       System.out.println(v);
               }
            }
            
            
            )+
        ;
    
corpo   :   "inicio" (comando)+ "fim"
        ;
        
comando :   expr
        |   attr
        |   funcao
        ;
        
attr    : id:ID IGUAL expr
        {   if(symbolTable.get(id.getText()) == null)
                throw new RuntimeException("Tentou usar sem declarar: " + id.getText());
        int tipoId = symbolTable.get(id.getText()).getTipo();
        if(tipoId < tipoExpr)
                throw new RuntimeException("Tentou colocar tipo " + tipoExpr + " em " +tipoId);
        }
;
        
expr    {tipoExpr = 0;}:   termo exprl
        {System.out.println("O tipo da expr é: " + tipoExpr);}
        ;
        
exprl   :   (OP termo)*
        ;
    
termo   :   (ID
              {
               Variavel termo = symbolTable.get(LT(0).getText());
               int pilhaAtual = contadorPilha;
               while(termo == null && pilhaAtual > 0){
                   pilhaAtual -=1;
                   symbolTable = arraySymbolos[pilhaAtual];
                   termo = symbolTable.get(LT(0).getText());
               }
                  symbolTable = arraySymbolos[contadorPilha];
               
                  int tipoDoTermo = termo.getTipo();
               if(tipoDoTermo > tipoExpr)
                   tipoExpr = tipoDoTermo;}
            | NUMINT
              {if(Variavel.INTEIRO > tipoExpr)
                   tipoExpr = Variavel.INTEIRO;}
            | NUMPF
              {if(Variavel.REAL > tipoExpr)
                   tipoExpr = Variavel.REAL;}
            | STRING
              {if(Variavel.STRING > tipoExpr)
                   tipoExpr = Variavel.STRING;}
        );
        
funcao : ABRECHAVE "funcao" ID

        {
            java.util.HashMap<String,Variavel> novaTabela = new java.util.HashMap();
            contadorPilha+=1;
            arraySymbolos[contadorPilha] = novaTabela;
            symbolTable = novaTabela;
        }
         declara
         corpo
         FECHACHAVE
         {
             contadorPilha-=1;
             symbolTable = arraySymbolos[contadorPilha];
         }
         ;
         
         
        



class MyLexer extends Lexer;
options{
   caseSensitive = true;
   k = 2;
}

WS      : (' ' | '\n' | '\r' | '\t') {_ttype=Token.SKIP;}
        ;
        
ID      : ('a'..'z' | 'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
        ;
        
NUMINT  : ('0'..'9')+ 
        ;
        
OP      : '+' | '-'
        ;
NUMPF   : ('0'..'9')* ('.' ('0'..'9')+)
        ;
        
AP      : '('
        ;
        
FP      : ')'
        ;
        
PF      : '.'
        ;

VIRG    : ','
        ;
        
DP      : ':'
        ;
        
STRING  : ASPAS ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ASPAS
        ;

ASPAS   : '"'
        ;
        
IGUAL   : '='
        ;
        
ABRECHAVE : '{';

FECHACHAVE : '}';
