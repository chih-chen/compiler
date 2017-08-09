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
			((decCommand)command).changeMode(decCommand.TYPE_TEXT);
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
			case LITERAL_faca:
			{
				dowhileStatement();
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
				ioStatement();
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
	
	public final void ifStatement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_se);
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
			
			logicalExpr.append(LT(0).getText());
			
			match(RELATIONAL);
			
			logicalExpr.append(LT(0).getText());
			
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
			
			command = new ifCommand();
			logicalExpr.append(LT(0).getText());
			((ifCommand)command).setLogicalExpr(logicalExpr.toString());
			stack.push(command);
			logicalExpr.setLength(0);
			
			match(FP);
			match(AC);
			{
			_loop18:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					statment();
				}
				else {
					break _loop18;
				}
				
			} while (true);
			}
			match(FC);
			{
			switch ( LA(1)) {
			case LITERAL_senao:
			{
				match(LITERAL_senao);
				match(AC);
				
				ifCommand tmp = (ifCommand)stack.getTopElement();
				tmp.changeMode(ifCommand.ELSE_MODE);
				
				{
				_loop21:
				do {
					if ((_tokenSet_1.member(LA(1)))) {
						statment();
					}
					else {
						break _loop21;
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
			case LITERAL_faca:
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
			
			Command cmd = stack.pop();
			if (stack.isEmpty()){
			program.addCommand(cmd);
			} else {
			Command tmp = stack.getTopElement();
			tmp.addCommand(cmd);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
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
			
			logicalExpr.append(LT(0).getText());
			
			match(RELATIONAL);
			
			logicalExpr.append(LT(0).getText());
			
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
			
			command = new whileCommand();
			logicalExpr.append(LT(0).getText());
			((whileCommand)command).setLogicalExpr(logicalExpr.toString());
			stack.push(command);
			logicalExpr.setLength(0);
			
			match(FP);
			match(AC);
			{
			_loop26:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					statment();
				}
				else {
					break _loop26;
				}
				
			} while (true);
			}
			match(FC);
			
			Command cmd = stack.pop();
			if (stack.isEmpty()){
			program.addCommand(cmd);
			} else {
			Command tmp = stack.getTopElement();
			tmp.addCommand(cmd);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void dowhileStatement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_faca);
			
			command = new doWhileCommand();
			
			stack.push(command);
			
			
			match(AC);
			{
			_loop29:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					statment();
				}
				else {
					break _loop29;
				}
				
			} while (true);
			}
			match(FC);
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
			
			logicalExpr.append(LT(0).getText());
			
			match(RELATIONAL);
			
			logicalExpr.append(LT(0).getText());
			
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
			
			logicalExpr.append(LT(0).getText());
			doWhileCommand temp = (doWhileCommand)stack.getTopElement();
			temp.setLogicalExpr(logicalExpr.toString());
			logicalExpr.setLength(0);
			
			match(FP);
			match(HT);
			
			Command cmd = stack.pop();
			if (stack.isEmpty()){
			program.addCommand(cmd);
			} else {
			Command tmp = stack.getTopElement();
			tmp.addCommand(cmd);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void assignmentStatement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ID);
			
			element = LT(0).getText(); 
			command = new assignCommand();
			
			match(EQUALS);
			{
			switch ( LA(1)) {
			case ID:
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
			
			if(!LT(0).getText().contains("\"") && program.numberVarList.containsKey(element)) {
			((assignCommand)command).changeMode(assignCommand.TYPE_NUMBER);
			program.setNumberVarValue(element,result);
			//System.out.println("Resultado = " + result);
			((assignCommand)command).buildExpression(element, sb.toString());
			sb.setLength(0);
			} else if(LT(0).getText().contains("\"") && program.stringVarList.containsKey(element)) {
			((assignCommand)command).changeMode(assignCommand.TYPE_STRING);
			program.setStringVarValue(element,LT(0).getText());
			((assignCommand)command).buildString(element,LT(0).getText());
			} else {
			throw new RuntimeException ("<<<<< Usou sem declarar! >>>>>");
			}
			
			match(HT);
			
			if (stack.isEmpty()){
			program.addCommand(command);
			} else{
			Command tmp = stack.getTopElement();
			tmp.addCommand(command);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void ioStatement() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_read:
			{
				readCommand();
				break;
			}
			case LITERAL_puts:
			{
				putsCommand();
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
			multiplyExpression();
			
			result = multiResult;
			
			{
			_loop39:
			do {
				if ((LA(1)==PLUS||LA(1)==MINUS)) {
					{
					switch ( LA(1)) {
					case PLUS:
					{
						match(PLUS);
						
						operator = LT(0).getText();
						sb.append(LT(0).getText());
						
						break;
					}
					case MINUS:
					{
						match(MINUS);
						
						operator = LT(0).getText();
						sb.append(LT(0).getText());
						
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					multiplyExpression();
					
					if(operator.equals("+"))
					result+=multiResult;
					else
					result-=multiResult;
					
				}
				else {
					break _loop39;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void readCommand() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_read);
			
			command = new readCommand(); 
			
			match(AP);
			match(ID);
			
			element = LT(0).getText();
			if(program.stringVarList.containsKey(element)){
			((readCommand)command).setType(readCommand.TYPE_TEXT);
			} else if(program.numberVarList.containsKey(element)) {
			((readCommand)command).setType(readCommand.TYPE_NUMBER);
			} else {
			throw new RuntimeException ("<<<<< Variavel n declarado! >>>>>");
			}
			
			match(FP);
			match(HT);
			
			((readCommand)command).setId(element);
			if (stack.isEmpty()){
			program.addCommand(command);
			} else {
			Command tmp = stack.getTopElement();
			tmp.addCommand(command);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void putsCommand() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_puts);
			
			command = new putsCommand();
			
			match(AP);
			{
			switch ( LA(1)) {
			case ID:
			{
				match(ID);
				
				writeType = putsCommand.TYPE_ID;
				
				break;
			}
			case STRING:
			{
				match(STRING);
				
				writeType = putsCommand.TYPE_TEXT;
				
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			
			element = LT(0).getText();
			
			match(FP);
			match(HT);
			
			((putsCommand)command).setType(writeType);
			((putsCommand)command).setContent(element);
			if (stack.isEmpty()){
			program.addCommand(command);
			} else{
			Command tmp = stack.getTopElement();
			tmp.addCommand(command);
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void multiplyExpression() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			innerElement();
			
			multiResult = varValue;
			
			{
			_loop43:
			do {
				if ((LA(1)==TIMES||LA(1)==DIV)) {
					{
					switch ( LA(1)) {
					case TIMES:
					{
						match(TIMES);
						
						operator = LT(0).getText();
						sb.append(LT(0).getText());
						
						break;
					}
					case DIV:
					{
						match(DIV);
						
						operator = LT(0).getText();
						sb.append(LT(0).getText());
						
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					innerElement();
					
					if(operator.equals("*"))
					multiResult*=varValue;
					else
					multiResult/=varValue;
					
				}
				else {
					break _loop43;
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
			case NUM:
			{
				match(NUM);
				
				sb.append(LT(0).getText());
				varValue = Double.parseDouble(LT(0).getText());
				
				break;
			}
			case ID:
			{
				match(ID);
				
				sb.append(LT(0).getText());
				if(program.numberVarList.get(LT(0).getText())==null)
				throw new RuntimeException("<<<<< Usou sem atribuir! >>>>>");
				varValue = program.numberVarList.get(LT(0).getText());
				
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
		"NUM",
		"RELATIONAL",
		"FP",
		"\"senao\"",
		"\"enquanto\"",
		"\"faca\"",
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
		long[] data = { 15745056L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 256L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 15746848L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 15745312L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 4096L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 50335744L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 251662336L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	
	}
