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
  this(tokenBuf,1);
}

protected MyParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public MyParser(TokenStream lexer) {
  this(lexer,1);
}

public MyParser(ParserSharedInputState state) {
  super(state,1);
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
				ifStatment();
				break;
			}
			case LITERAL_enquanto:
			{
				whileStatment();
				break;
			}
			case ID:
			{
				assignmentStatement();
				break;
			}
			case LITERAL_read:
			case LITERAL_puts:
			{
				ioStatment();
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
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void ifStatment() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_se);
			match(AP);
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
			case LITERAL_enquanto:
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
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void whileStatment() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_enquanto);
			match(AP);
			expression();
			match(FP);
			match(AB);
			{
			_loop22:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					statment();
				}
				else {
					break _loop22;
				}
				
			} while (true);
			}
			match(FC);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void assignmentStatement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ID);
			match(EQUALS);
			{
			switch ( LA(1)) {
			case ID:
			case AP:
			case PLUS:
			case MINUS:
			{
				expression();
				break;
			}
			case NUM:
			{
				match(NUM);
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
			match(HT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void ioStatment() throws RecognitionException, TokenStreamException {
		
		
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
				expression();
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
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			addExpression();
			{
			_loop40:
			do {
				if ((_tokenSet_5.member(LA(1)))) {
					{
					switch ( LA(1)) {
					case EQUALS:
					{
						match(EQUALS);
						break;
					}
					case LT:
					{
						match(LT);
						break;
					}
					case LTE:
					{
						match(LTE);
						break;
					}
					case GT:
					{
						match(GT);
						break;
					}
					case GTE:
					{
						match(GTE);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					addExpression();
				}
				else {
					break _loop40;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void innerElement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				match(ID);
				break;
			}
			case AP:
			{
				match(AP);
				expression();
				match(FP);
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
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void signExpression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop28:
			do {
				if ((LA(1)==PLUS||LA(1)==MINUS)) {
					{
					switch ( LA(1)) {
					case PLUS:
					{
						match(PLUS);
						break;
					}
					case MINUS:
					{
						match(MINUS);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
				}
				else {
					break _loop28;
				}
				
			} while (true);
			}
			innerElement();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void multiplyExpression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			signExpression();
			{
			_loop32:
			do {
				if ((LA(1)==TIMES||LA(1)==DIV)) {
					{
					switch ( LA(1)) {
					case TIMES:
					{
						match(TIMES);
						break;
					}
					case DIV:
					{
						match(DIV);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					signExpression();
				}
				else {
					break _loop32;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void addExpression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			multiplyExpression();
			{
			_loop36:
			do {
				if ((LA(1)==PLUS||LA(1)==MINUS)) {
					{
					switch ( LA(1)) {
					case PLUS:
					{
						match(PLUS);
						break;
					}
					case MINUS:
					{
						match(MINUS);
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
					break _loop36;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_9);
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
		"NUM",
		"STRING",
		"\"se\"",
		"AP",
		"FP",
		"\"do contrario\"",
		"\"enquanto\"",
		"AB",
		"\"read\"",
		"\"puts\"",
		"PLUS",
		"MINUS",
		"TIMES",
		"DIV",
		"LT",
		"LTE",
		"GT",
		"GTE",
		"WS",
		"COMMENT"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 6848544L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 256L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 6850336L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 6848800L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 2013265984L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 135168L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 2139230272L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 2038566976L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 2013401152L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	
	}
