// $ANTLR 2.7.6 (2005-12-22): "gram.g" -> "MyParser.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class MyParser extends antlr.LLkParser       implements MyParserTokenTypes
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

protected MyParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public MyParser(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected MyParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public MyParser(TokenStream lexer) {
  this(lexer,2);
}

public MyParser(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
}

	public final void prog() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_programa);
			declara();
			corpo();
			match(LITERAL_fimprog);
			System.out.println("CABO");
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void declara() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt7=0;
			_loop7:
			do {
				if ((LA(1)==LITERAL_declare)) {
					match(LITERAL_declare);
					varList = new java.util.ArrayList<Variavel>();
					match(ID);
					
					if (symbolTable.get(LT(0).getText()) == null){
					Variavel v = new Variavel();
					v.setId(LT(0).getText());
					symbolTable.put(v.getId(), v);
					varList.add(v);
					}       
					
					{
					_loop5:
					do {
						if ((LA(1)==VIRG)) {
							match(VIRG);
							match(ID);
							
							if (symbolTable.get(LT(0).getText()) == null){
							Variavel v = new Variavel();
							v.setId(LT(0).getText());
							symbolTable.put(v.getId(), v);
							varList.add(v);
							}   
							
							
						}
						else {
							break _loop5;
						}
						
					} while (true);
					}
					match(DP);
					{
					switch ( LA(1)) {
					case LITERAL_inteiro:
					{
						match(LITERAL_inteiro);
						VAR_TYPE = Variavel.INTEIRO;
						break;
					}
					case LITERAL_real:
					{
						match(LITERAL_real);
						VAR_TYPE = Variavel.REAL;
						break;
					}
					case LITERAL_texto:
					{
						match(LITERAL_texto);
						VAR_TYPE = Variavel.STRING;
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(PF);
					
					for (Variavel v: varList) v.setTipo(VAR_TYPE);
					
					System.out.println("MOSTRANDO VARIAVEIS ----");
					for (Object v: symbolTable.values().toArray()){
					System.out.println(v);
					}
					
				}
				else {
					if ( _cnt7>=1 ) { break _loop7; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt7++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void corpo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_inicio);
			{
			int _cnt10=0;
			_loop10:
			do {
				if ((_tokenSet_2.member(LA(1)))) {
					comando();
				}
				else {
					if ( _cnt10>=1 ) { break _loop10; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt10++;
			} while (true);
			}
			match(LITERAL_fim);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void comando() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			if ((_tokenSet_4.member(LA(1))) && (_tokenSet_5.member(LA(2)))) {
				expr();
			}
			else if ((LA(1)==ID) && (LA(2)==IGUAL)) {
				attr();
			}
			else if ((LA(1)==ABRECHAVE)) {
				funcao();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		tipoExpr = 0;
		
		try {      // for error handling
			termo();
			exprl();
			System.out.println("O tipo da expr é: " + tipoExpr);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void attr() throws RecognitionException, TokenStreamException {
		
		Token  id = null;
		
		try {      // for error handling
			id = LT(1);
			match(ID);
			match(IGUAL);
			expr();
			if(symbolTable.get(id.getText()) == null)
			throw new RuntimeException("Tentou usar sem declarar: " + id.getText());
			int tipoId = symbolTable.get(id.getText()).getTipo();
			if(tipoId < tipoExpr)
			throw new RuntimeException("Tentou colocar tipo " + tipoExpr + " em " +tipoId);
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void funcao() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ABRECHAVE);
			match(LITERAL_funcao);
			match(ID);
			
			java.util.HashMap<String,Variavel> novaTabela = new java.util.HashMap();
			contadorPilha+=1;
			arraySymbolos[contadorPilha] = novaTabela;
			symbolTable = novaTabela;
			
			declara();
			corpo();
			match(FECHACHAVE);
			
			contadorPilha-=1;
			symbolTable = arraySymbolos[contadorPilha];
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void termo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case ID:
			{
				match(ID);
				
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
				tipoExpr = tipoDoTermo;
				break;
			}
			case NUMINT:
			{
				match(NUMINT);
				if(Variavel.INTEIRO > tipoExpr)
				tipoExpr = Variavel.INTEIRO;
				break;
			}
			case NUMPF:
			{
				match(NUMPF);
				if(Variavel.REAL > tipoExpr)
				tipoExpr = Variavel.REAL;
				break;
			}
			case STRING:
			{
				match(STRING);
				if(Variavel.STRING > tipoExpr)
				tipoExpr = Variavel.STRING;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void exprl() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop16:
			do {
				if ((LA(1)==OP)) {
					match(OP);
					termo();
				}
				else {
					break _loop16;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"programa\"",
		"\"fimprog\"",
		"\"declare\"",
		"ID",
		"VIRG",
		"DP",
		"\"inteiro\"",
		"\"real\"",
		"\"texto\"",
		"PF",
		"\"inicio\"",
		"\"fim\"",
		"IGUAL",
		"OP",
		"NUMINT",
		"NUMPF",
		"STRING",
		"ABRECHAVE",
		"\"funcao\"",
		"FECHACHAVE",
		"WS",
		"AP",
		"FP",
		"ASPAS"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 16384L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 3932288L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 8388640L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 1835136L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 4096128L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 3965056L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	
	}
