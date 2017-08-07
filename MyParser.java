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
			match(EQUALS);
			match(AB);
			body();
			match(FC);
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
				if ((LA(1)==TYPE)) {
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
			varDecl();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void statment() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case IF:
			{
				ifStatment();
				break;
			}
			case WHILE:
			{
				whileStatment();
				break;
			}
			case ID:
			{
				assignmentStatement();
				break;
			}
			case READ:
			case PRINT:
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
	
	public final void varDecl() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(TYPE);
			idList();
			match(HT);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void idList() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ID);
			{
			_loop11:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					match(ID);
				}
				else {
					break _loop11;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void ifStatment() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IF);
			match(AP);
			expression();
			match(FP);
			match(AC);
			{
			_loop17:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					statment();
				}
				else {
					break _loop17;
				}
				
			} while (true);
			}
			match(FC);
			{
			switch ( LA(1)) {
			case ELSE:
			{
				match(ELSE);
				match(AC);
				{
				_loop20:
				do {
					if ((_tokenSet_1.member(LA(1)))) {
						statment();
					}
					else {
						break _loop20;
					}
					
				} while (true);
				}
				match(FC);
				break;
			}
			case ID:
			case FC:
			case IF:
			case WHILE:
			case READ:
			case PRINT:
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
			match(WHILE);
			match(AP);
			expression();
			match(FP);
			match(AB);
			{
			_loop23:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					statment();
				}
				else {
					break _loop23;
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
			varReference();
			match(EQUALS);
			expression();
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
			case READ:
			{
				match(READ);
				match(AP);
				varReference();
				match(FP);
				match(HT);
				break;
			}
			case PRINT:
			{
				match(PRINT);
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
	
	public final void varReference() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ID);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			addExpression();
			{
			_loop41:
			do {
				if ((_tokenSet_7.member(LA(1)))) {
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
					break _loop41;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void innerElement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				varReference();
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
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void signExpression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop29:
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
					break _loop29;
				}
				
			} while (true);
			}
			innerElement();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void multiplyExpression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			signExpression();
			{
			_loop33:
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
					break _loop33;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_9);
		}
	}
	
	public final void addExpression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			multiplyExpression();
			{
			_loop37:
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
					break _loop37;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_10);
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
		"AB",
		"FC",
		"TYPE",
		"HT",
		"COMMA",
		"IF",
		"AP",
		"FP",
		"AC",
		"ELSE",
		"WHILE",
		"READ",
		"PRINT",
		"PLUS",
		"MINUS",
		"TIMES",
		"DIV",
		"LT",
		"LTE",
		"GT",
		"GTE",
		"WS",
		"COMMENT",
		"NUM",
		"STRING"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 921632L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 256L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 922400L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 921888L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 1024L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 267404352L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 251658304L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 17408L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 254821440L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 251675712L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	
	}
