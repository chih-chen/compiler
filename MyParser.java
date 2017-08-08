// $ANTLR 2.7.6 (2005-12-22): "myGrammar.g" -> "MyParser.java"$

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

   private Program  program;
   private Command  command;
   private int      writeType;
   private String   element;
   private Stack    stack;

   public void init(){
       program = new Program();
       stack   = new Stack();
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

	public final void program() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_program);
			match(ID);
			
			program.className = LT(0).getText();
			
			match(EQUALS);
			match(AC);
			body();
			match(FC);
			
			System.out.println(program.writeJava());
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void body() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop4:
			do {
				if ((LA(1)==LITERAL_String||LA(1)==LITERAL_Number)) {
					declaration();
				}
				else {
					break _loop4;
				}
				
			} while (true);
			}
			{
			_loop6:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					statment();
				}
				else {
					break _loop6;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void declaration() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_String:
			{
				match(LITERAL_String);
				break;
			}
			case LITERAL_Number:
			{
				match(LITERAL_Number);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(ID);
			
			command = new decCommand();
			if(LT(-1).getText().equals("String")){
			((decCommand)command).changeMode(decCommand.TYPE_STRING);
			} else {
			((decCommand)command).changeMode(decCommand.TYPE_NUMBER);
			}
			((decCommand)command).addVariable(LT(0).getText());
			
			{
			_loop10:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					match(ID);
					
					((decCommand)command).addVariable(LT(0).getText());
					
				}
				else {
					break _loop10;
				}
				
			} while (true);
			}
			match(HT);
			
			program.addCommand(command);
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void statment() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_se:
			{
				ifStatement();
				break;
			}
			case LITERAL_enquanto:
			{
				whileStatement();
				break;
			}
			case LITERAL_read:
			case LITERAL_puts:
			{
				ioStatement();
				break;
			}
			default:
				if ((LA(1)==ID) && (LA(2)==EQUALS)) {
					assignmentStatement();
				}
				else if ((LA(1)==ID||LA(1)==AP||LA(1)==NUM) && (_tokenSet_4.member(LA(2)))) {
					expression();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void ifStatement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_se);
			match(AP);
			expression();
			match(RELATIONAL);
			expression();
			match(FP);
			match(AC);
			{
			_loop16:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					statment();
				}
				else {
					break _loop16;
				}
				
			} while (true);
			}
			match(FC);
			{
			switch ( LA(1)) {
			case 18:
			{
				match(18);
				match(AC);
				{
				_loop19:
				do {
					if ((_tokenSet_1.member(LA(1)))) {
						statment();
					}
					else {
						break _loop19;
					}
					
				} while (true);
				}
				match(FC);
				break;
			}
			case ID:
			case FC:
			case LITERAL_se:
			case AP:
			case LITERAL_enquanto:
			case NUM:
			case LITERAL_read:
			case LITERAL_puts:
			{
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
	
	public final void whileStatement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_enquanto);
			match(AP);
			{
			switch ( LA(1)) {
			case ID:
			{
				match(ID);
				break;
			}
			case NUM:
			{
				match(NUM);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RELATIONAL);
			{
			switch ( LA(1)) {
			case ID:
			{
				match(ID);
				break;
			}
			case NUM:
			{
				match(NUM);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(FP);
			match(AB);
			{
			_loop24:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					statment();
				}
				else {
					break _loop24;
				}
				
			} while (true);
			}
			match(FC);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void assignmentStatement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ID);
			
			element = LT(0).getText(); 
			
			match(EQUALS);
			{
			switch ( LA(1)) {
			case ID:
			case AP:
			case NUM:
			{
				expression();
				break;
			}
			case STRING:
			{
				match(STRING);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			System.out.println();
			match(HT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void ioStatement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_read:
			{
				match(LITERAL_read);
				match(AP);
				match(ID);
				match(FP);
				match(HT);
				break;
			}
			case LITERAL_puts:
			{
				match(LITERAL_puts);
				match(AP);
				{
				switch ( LA(1)) {
				case ID:
				case AP:
				case NUM:
				{
					expression();
					break;
				}
				case STRING:
				{
					match(STRING);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(FP);
				match(HT);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			multiplyExpression();
			{
			_loop30:
			do {
				if ((LA(1)==PLUS||LA(1)==MINUS)) {
					{
					switch ( LA(1)) {
					case PLUS:
					{
						match(PLUS);
						System.out.print(LT(0).getText());
						break;
					}
					case MINUS:
					{
						match(MINUS);
						System.out.print(LT(0).getText());
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					multiplyExpression();
				}
				else {
					break _loop30;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void multiplyExpression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			innerElement();
			{
			_loop34:
			do {
				if ((LA(1)==TIMES||LA(1)==DIV)) {
					{
					switch ( LA(1)) {
					case TIMES:
					{
						match(TIMES);
						System.out.print(LT(0).getText());
						break;
					}
					case DIV:
					{
						match(DIV);
						System.out.print(LT(0).getText());
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					innerElement();
				}
				else {
					break _loop34;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void innerElement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case NUM:
			{
				match(NUM);
				System.out.print(LT(0).getText());
				break;
			}
			case ID:
			{
				match(ID);
				System.out.print(LT(0).getText());
				break;
			}
			case AP:
			{
				match(AP);
				System.out.print(LT(0).getText());
				expression();
				match(FP);
				System.out.print(LT(0).getText());
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"program\"",
		"ID",
		"EQUALS",
		"AC",
		"FC",
		"\"String\"",
		"\"Number\"",
		"COMMA",
		"HT",
		"STRING",
		"\"se\"",
		"AP",
		"RELATIONAL",
		"FP",
		"\"do contrario\"",
		"\"enquanto\"",
		"NUM",
		"AB",
		"\"read\"",
		"\"puts\"",
		"PLUS",
		"MINUS",
		"TIMES",
		"DIV",
		"WS",
		"COMMENT"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 14204960L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 256L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 14206752L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 265863456L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 14205216L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 14405920L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 64737568L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 266064160L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	
	}
