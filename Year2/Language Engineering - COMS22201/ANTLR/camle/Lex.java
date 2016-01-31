// $ANTLR 3.5.2 Lex.g 2015-12-18 00:39:53

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class Lex extends Lexer {
	public static final int EOF=-1;
	public static final int ADD=4;
	public static final int AND=5;
	public static final int ASSIGN=6;
	public static final int CLOSEPAREN=7;
	public static final int COMMENT=8;
	public static final int DEF=9;
	public static final int DIGIT=10;
	public static final int DO=11;
	public static final int ELSE=12;
	public static final int EQ=13;
	public static final int FALSE=14;
	public static final int FOR=15;
	public static final int ID=16;
	public static final int IF=17;
	public static final int INTNUM=18;
	public static final int LEQ=19;
	public static final int LETTER=20;
	public static final int LS=21;
	public static final int MUL=22;
	public static final int NOT=23;
	public static final int OPENPAREN=24;
	public static final int OR=25;
	public static final int READ=26;
	public static final int REG=27;
	public static final int RS=28;
	public static final int SEMICOLON=29;
	public static final int SKIP=30;
	public static final int STRING=31;
	public static final int SUB=32;
	public static final int THEN=33;
	public static final int TO=34;
	public static final int TRUE=35;
	public static final int WHILE=36;
	public static final int WRITE=37;
	public static final int WRITELN=38;
	public static final int WS=39;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public Lex() {} 
	public Lex(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public Lex(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "Lex.g"; }

	// $ANTLR start "WRITE"
	public final void mWRITE() throws RecognitionException {
		try {
			int _type = WRITE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:8:12: ( 'write' )
			// Lex.g:8:14: 'write'
			{
			match("write"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WRITE"

	// $ANTLR start "WRITELN"
	public final void mWRITELN() throws RecognitionException {
		try {
			int _type = WRITELN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:9:12: ( 'writeln' )
			// Lex.g:9:14: 'writeln'
			{
			match("writeln"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WRITELN"

	// $ANTLR start "DO"
	public final void mDO() throws RecognitionException {
		try {
			int _type = DO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:10:12: ( 'do' )
			// Lex.g:10:14: 'do'
			{
			match("do"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DO"

	// $ANTLR start "ELSE"
	public final void mELSE() throws RecognitionException {
		try {
			int _type = ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:11:12: ( 'else' )
			// Lex.g:11:14: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSE"

	// $ANTLR start "FALSE"
	public final void mFALSE() throws RecognitionException {
		try {
			int _type = FALSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:12:12: ( 'false' )
			// Lex.g:12:14: 'false'
			{
			match("false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FALSE"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:13:12: ( 'if' )
			// Lex.g:13:14: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "READ"
	public final void mREAD() throws RecognitionException {
		try {
			int _type = READ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:14:12: ( 'read' )
			// Lex.g:14:14: 'read'
			{
			match("read"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "READ"

	// $ANTLR start "SKIP"
	public final void mSKIP() throws RecognitionException {
		try {
			int _type = SKIP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:15:12: ( 'skip' )
			// Lex.g:15:14: 'skip'
			{
			match("skip"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SKIP"

	// $ANTLR start "THEN"
	public final void mTHEN() throws RecognitionException {
		try {
			int _type = THEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:16:12: ( 'then' )
			// Lex.g:16:14: 'then'
			{
			match("then"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "THEN"

	// $ANTLR start "TRUE"
	public final void mTRUE() throws RecognitionException {
		try {
			int _type = TRUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:17:12: ( 'true' )
			// Lex.g:17:14: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUE"

	// $ANTLR start "WHILE"
	public final void mWHILE() throws RecognitionException {
		try {
			int _type = WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:18:12: ( 'while' )
			// Lex.g:18:14: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHILE"

	// $ANTLR start "DEF"
	public final void mDEF() throws RecognitionException {
		try {
			int _type = DEF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:19:12: ( 'def' )
			// Lex.g:19:14: 'def'
			{
			match("def"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DEF"

	// $ANTLR start "REG"
	public final void mREG() throws RecognitionException {
		try {
			int _type = REG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:20:12: ( 'register' )
			// Lex.g:20:14: 'register'
			{
			match("register"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REG"

	// $ANTLR start "FOR"
	public final void mFOR() throws RecognitionException {
		try {
			int _type = FOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:21:12: ( 'for' )
			// Lex.g:21:14: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FOR"

	// $ANTLR start "TO"
	public final void mTO() throws RecognitionException {
		try {
			int _type = TO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:22:12: ( 'to' )
			// Lex.g:22:14: 'to'
			{
			match("to"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TO"

	// $ANTLR start "SEMICOLON"
	public final void mSEMICOLON() throws RecognitionException {
		try {
			int _type = SEMICOLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:27:14: ( ';' )
			// Lex.g:27:16: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMICOLON"

	// $ANTLR start "OPENPAREN"
	public final void mOPENPAREN() throws RecognitionException {
		try {
			int _type = OPENPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:28:14: ( '(' )
			// Lex.g:28:16: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OPENPAREN"

	// $ANTLR start "CLOSEPAREN"
	public final void mCLOSEPAREN() throws RecognitionException {
		try {
			int _type = CLOSEPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:29:14: ( ')' )
			// Lex.g:29:16: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLOSEPAREN"

	// $ANTLR start "ASSIGN"
	public final void mASSIGN() throws RecognitionException {
		try {
			int _type = ASSIGN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:30:14: ( ':=' )
			// Lex.g:30:16: ':='
			{
			match(":="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASSIGN"

	// $ANTLR start "ADD"
	public final void mADD() throws RecognitionException {
		try {
			int _type = ADD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:31:14: ( '+' )
			// Lex.g:31:16: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ADD"

	// $ANTLR start "SUB"
	public final void mSUB() throws RecognitionException {
		try {
			int _type = SUB;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:32:14: ( '-' )
			// Lex.g:32:16: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUB"

	// $ANTLR start "MUL"
	public final void mMUL() throws RecognitionException {
		try {
			int _type = MUL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:33:14: ( '*' )
			// Lex.g:33:16: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MUL"

	// $ANTLR start "LEQ"
	public final void mLEQ() throws RecognitionException {
		try {
			int _type = LEQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:34:14: ( '<=' )
			// Lex.g:34:16: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEQ"

	// $ANTLR start "EQ"
	public final void mEQ() throws RecognitionException {
		try {
			int _type = EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:35:14: ( '=' )
			// Lex.g:35:16: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ"

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:36:14: ( '!' )
			// Lex.g:36:16: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT"

	// $ANTLR start "AND"
	public final void mAND() throws RecognitionException {
		try {
			int _type = AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:37:14: ( '&' )
			// Lex.g:37:16: '&'
			{
			match('&'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AND"

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:38:14: ( '|' )
			// Lex.g:38:16: '|'
			{
			match('|'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OR"

	// $ANTLR start "LS"
	public final void mLS() throws RecognitionException {
		try {
			int _type = LS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:39:14: ( '[' )
			// Lex.g:39:16: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LS"

	// $ANTLR start "RS"
	public final void mRS() throws RecognitionException {
		try {
			int _type = RS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:40:14: ( ']' )
			// Lex.g:40:16: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RS"

	// $ANTLR start "INTNUM"
	public final void mINTNUM() throws RecognitionException {
		try {
			int _type = INTNUM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:43:14: ( ( DIGIT )+ )
			// Lex.g:43:16: ( DIGIT )+
			{
			// Lex.g:43:16: ( DIGIT )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// Lex.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTNUM"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			CommonToken ch=null;
			List<Object> list_ch=null;
			// Lex.g:45:14: (ch+= LETTER (ch+= LETTER |ch+= DIGIT )* {...}?)
			// Lex.g:45:16: ch+= LETTER (ch+= LETTER |ch+= DIGIT )* {...}?
			{
			int chStart483 = getCharIndex();
			int chStartLine483 = getLine();
			int chStartCharPos483 = getCharPositionInLine();
			mLETTER(); 
			ch = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, chStart483, getCharIndex()-1);
			ch.setLine(chStartLine483);
			ch.setCharPositionInLine(chStartCharPos483);

			if (list_ch==null) list_ch=new ArrayList<Object>();
			list_ch.add(ch);
			// Lex.g:45:27: (ch+= LETTER |ch+= DIGIT )*
			loop2:
			while (true) {
				int alt2=3;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= 'A' && LA2_0 <= 'Z')||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
					alt2=1;
				}
				else if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=2;
				}

				switch (alt2) {
				case 1 :
					// Lex.g:45:28: ch+= LETTER
					{
					int chStart488 = getCharIndex();
					int chStartLine488 = getLine();
					int chStartCharPos488 = getCharPositionInLine();
					mLETTER(); 
					ch = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, chStart488, getCharIndex()-1);
					ch.setLine(chStartLine488);
					ch.setCharPositionInLine(chStartCharPos488);

					if (list_ch==null) list_ch=new ArrayList<Object>();
					list_ch.add(ch);
					}
					break;
				case 2 :
					// Lex.g:45:41: ch+= DIGIT
					{
					int chStart494 = getCharIndex();
					int chStartLine494 = getLine();
					int chStartCharPos494 = getCharPositionInLine();
					mDIGIT(); 
					ch = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, chStart494, getCharIndex()-1);
					ch.setLine(chStartLine494);
					ch.setCharPositionInLine(chStartCharPos494);

					if (list_ch==null) list_ch=new ArrayList<Object>();
					list_ch.add(ch);
					}
					break;

				default :
					break loop2;
				}
			}

			if ( !((list_ch.size()<=8)) ) {
				throw new FailedPredicateException(input, "ID", "$ch.size()<=8");
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "LETTER"
	public final void mLETTER() throws RecognitionException {
		try {
			// Lex.g:47:16: ( 'a' .. 'z' | 'A' .. 'Z' )
			// Lex.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LETTER"

	// $ANTLR start "DIGIT"
	public final void mDIGIT() throws RecognitionException {
		try {
			// Lex.g:48:16: ( '0' .. '9' )
			// Lex.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIGIT"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:50:14: ( '\\'' ( '\\'' '\\'' |~ '\\'' )* '\\'' )
			// Lex.g:50:16: '\\'' ( '\\'' '\\'' |~ '\\'' )* '\\''
			{
			match('\''); 
			// Lex.g:50:21: ( '\\'' '\\'' |~ '\\'' )*
			loop3:
			while (true) {
				int alt3=3;
				int LA3_0 = input.LA(1);
				if ( (LA3_0=='\'') ) {
					int LA3_1 = input.LA(2);
					if ( (LA3_1=='\'') ) {
						alt3=1;
					}

				}
				else if ( ((LA3_0 >= '\u0000' && LA3_0 <= '&')||(LA3_0 >= '(' && LA3_0 <= '\uFFFF')) ) {
					alt3=2;
				}

				switch (alt3) {
				case 1 :
					// Lex.g:50:22: '\\'' '\\''
					{
					match('\''); 
					match('\''); 
					}
					break;
				case 2 :
					// Lex.g:50:34: ~ '\\''
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop3;
				}
			}

			match('\''); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:52:14: ( '{' (~ '}' )* '}' )
			// Lex.g:52:16: '{' (~ '}' )* '}'
			{
			match('{'); 
			// Lex.g:52:20: (~ '}' )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '\u0000' && LA4_0 <= '|')||(LA4_0 >= '~' && LA4_0 <= '\uFFFF')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// Lex.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '|')||(input.LA(1) >= '~' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop4;
				}
			}

			match('}'); 
			skip();
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Lex.g:54:14: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
			// Lex.g:54:16: ( ' ' | '\\t' | '\\r' | '\\n' )+
			{
			// Lex.g:54:16: ( ' ' | '\\t' | '\\r' | '\\n' )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '\t' && LA5_0 <= '\n')||LA5_0=='\r'||LA5_0==' ') ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// Lex.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
			}

			skip();
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// Lex.g:1:8: ( WRITE | WRITELN | DO | ELSE | FALSE | IF | READ | SKIP | THEN | TRUE | WHILE | DEF | REG | FOR | TO | SEMICOLON | OPENPAREN | CLOSEPAREN | ASSIGN | ADD | SUB | MUL | LEQ | EQ | NOT | AND | OR | LS | RS | INTNUM | ID | STRING | COMMENT | WS )
		int alt6=34;
		alt6 = dfa6.predict(input);
		switch (alt6) {
			case 1 :
				// Lex.g:1:10: WRITE
				{
				mWRITE(); 

				}
				break;
			case 2 :
				// Lex.g:1:16: WRITELN
				{
				mWRITELN(); 

				}
				break;
			case 3 :
				// Lex.g:1:24: DO
				{
				mDO(); 

				}
				break;
			case 4 :
				// Lex.g:1:27: ELSE
				{
				mELSE(); 

				}
				break;
			case 5 :
				// Lex.g:1:32: FALSE
				{
				mFALSE(); 

				}
				break;
			case 6 :
				// Lex.g:1:38: IF
				{
				mIF(); 

				}
				break;
			case 7 :
				// Lex.g:1:41: READ
				{
				mREAD(); 

				}
				break;
			case 8 :
				// Lex.g:1:46: SKIP
				{
				mSKIP(); 

				}
				break;
			case 9 :
				// Lex.g:1:51: THEN
				{
				mTHEN(); 

				}
				break;
			case 10 :
				// Lex.g:1:56: TRUE
				{
				mTRUE(); 

				}
				break;
			case 11 :
				// Lex.g:1:61: WHILE
				{
				mWHILE(); 

				}
				break;
			case 12 :
				// Lex.g:1:67: DEF
				{
				mDEF(); 

				}
				break;
			case 13 :
				// Lex.g:1:71: REG
				{
				mREG(); 

				}
				break;
			case 14 :
				// Lex.g:1:75: FOR
				{
				mFOR(); 

				}
				break;
			case 15 :
				// Lex.g:1:79: TO
				{
				mTO(); 

				}
				break;
			case 16 :
				// Lex.g:1:82: SEMICOLON
				{
				mSEMICOLON(); 

				}
				break;
			case 17 :
				// Lex.g:1:92: OPENPAREN
				{
				mOPENPAREN(); 

				}
				break;
			case 18 :
				// Lex.g:1:102: CLOSEPAREN
				{
				mCLOSEPAREN(); 

				}
				break;
			case 19 :
				// Lex.g:1:113: ASSIGN
				{
				mASSIGN(); 

				}
				break;
			case 20 :
				// Lex.g:1:120: ADD
				{
				mADD(); 

				}
				break;
			case 21 :
				// Lex.g:1:124: SUB
				{
				mSUB(); 

				}
				break;
			case 22 :
				// Lex.g:1:128: MUL
				{
				mMUL(); 

				}
				break;
			case 23 :
				// Lex.g:1:132: LEQ
				{
				mLEQ(); 

				}
				break;
			case 24 :
				// Lex.g:1:136: EQ
				{
				mEQ(); 

				}
				break;
			case 25 :
				// Lex.g:1:139: NOT
				{
				mNOT(); 

				}
				break;
			case 26 :
				// Lex.g:1:143: AND
				{
				mAND(); 

				}
				break;
			case 27 :
				// Lex.g:1:147: OR
				{
				mOR(); 

				}
				break;
			case 28 :
				// Lex.g:1:150: LS
				{
				mLS(); 

				}
				break;
			case 29 :
				// Lex.g:1:153: RS
				{
				mRS(); 

				}
				break;
			case 30 :
				// Lex.g:1:156: INTNUM
				{
				mINTNUM(); 

				}
				break;
			case 31 :
				// Lex.g:1:163: ID
				{
				mID(); 

				}
				break;
			case 32 :
				// Lex.g:1:166: STRING
				{
				mSTRING(); 

				}
				break;
			case 33 :
				// Lex.g:1:173: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 34 :
				// Lex.g:1:181: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA6 dfa6 = new DFA6(this);
	static final String DFA6_eotS =
		"\1\uffff\10\30\23\uffff\2\30\1\53\4\30\1\60\4\30\1\66\2\30\1\uffff\1\71"+
		"\2\30\1\74\1\uffff\5\30\1\uffff\2\30\1\uffff\1\104\1\30\1\uffff\1\106"+
		"\1\30\1\110\1\111\1\112\1\114\1\115\1\uffff\1\116\1\uffff\1\30\3\uffff"+
		"\1\30\3\uffff\1\30\1\122\1\30\1\uffff\1\124\1\uffff";
	static final String DFA6_eofS =
		"\125\uffff";
	static final String DFA6_minS =
		"\1\11\1\150\1\145\1\154\1\141\1\146\1\145\1\153\1\150\23\uffff\2\151\1"+
		"\60\1\146\1\163\1\154\1\162\1\60\1\141\1\151\1\145\1\165\1\60\1\164\1"+
		"\154\1\uffff\1\60\1\145\1\163\1\60\1\uffff\1\144\1\151\1\160\1\156\1\145"+
		"\1\uffff\2\145\1\uffff\1\60\1\145\1\uffff\1\60\1\163\5\60\1\uffff\1\60"+
		"\1\uffff\1\164\3\uffff\1\156\3\uffff\1\145\1\60\1\162\1\uffff\1\60\1\uffff";
	static final String DFA6_maxS =
		"\1\174\1\162\1\157\1\154\1\157\1\146\1\145\1\153\1\162\23\uffff\2\151"+
		"\1\172\1\146\1\163\1\154\1\162\1\172\1\147\1\151\1\145\1\165\1\172\1\164"+
		"\1\154\1\uffff\1\172\1\145\1\163\1\172\1\uffff\1\144\1\151\1\160\1\156"+
		"\1\145\1\uffff\2\145\1\uffff\1\172\1\145\1\uffff\1\172\1\163\5\172\1\uffff"+
		"\1\172\1\uffff\1\164\3\uffff\1\156\3\uffff\1\145\1\172\1\162\1\uffff\1"+
		"\172\1\uffff";
	static final String DFA6_acceptS =
		"\11\uffff\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33"+
		"\1\34\1\35\1\36\1\37\1\40\1\41\1\42\17\uffff\1\3\4\uffff\1\6\5\uffff\1"+
		"\17\2\uffff\1\14\2\uffff\1\16\7\uffff\1\4\1\uffff\1\7\1\uffff\1\10\1\11"+
		"\1\12\1\uffff\1\1\1\13\1\5\3\uffff\1\2\1\uffff\1\15";
	static final String DFA6_specialS =
		"\125\uffff}>";
	static final String[] DFA6_transitionS = {
			"\2\33\2\uffff\1\33\22\uffff\1\33\1\22\4\uffff\1\23\1\31\1\12\1\13\1\17"+
			"\1\15\1\uffff\1\16\2\uffff\12\27\1\14\1\11\1\20\1\21\3\uffff\32\30\1"+
			"\25\1\uffff\1\26\3\uffff\3\30\1\2\1\3\1\4\2\30\1\5\10\30\1\6\1\7\1\10"+
			"\2\30\1\1\3\30\1\32\1\24",
			"\1\35\11\uffff\1\34",
			"\1\37\11\uffff\1\36",
			"\1\40",
			"\1\41\15\uffff\1\42",
			"\1\43",
			"\1\44",
			"\1\45",
			"\1\46\6\uffff\1\50\2\uffff\1\47",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\51",
			"\1\52",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"\1\54",
			"\1\55",
			"\1\56",
			"\1\57",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"\1\61\5\uffff\1\62",
			"\1\63",
			"\1\64",
			"\1\65",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"\1\67",
			"\1\70",
			"",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"\1\72",
			"\1\73",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"",
			"\1\75",
			"\1\76",
			"\1\77",
			"\1\100",
			"\1\101",
			"",
			"\1\102",
			"\1\103",
			"",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"\1\105",
			"",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"\1\107",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"\12\30\7\uffff\32\30\6\uffff\13\30\1\113\16\30",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"",
			"\1\117",
			"",
			"",
			"",
			"\1\120",
			"",
			"",
			"",
			"\1\121",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			"\1\123",
			"",
			"\12\30\7\uffff\32\30\6\uffff\32\30",
			""
	};

	static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
	static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
	static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
	static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
	static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
	static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
	static final short[][] DFA6_transition;

	static {
		int numStates = DFA6_transitionS.length;
		DFA6_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
		}
	}

	protected class DFA6 extends DFA {

		public DFA6(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 6;
			this.eot = DFA6_eot;
			this.eof = DFA6_eof;
			this.min = DFA6_min;
			this.max = DFA6_max;
			this.accept = DFA6_accept;
			this.special = DFA6_special;
			this.transition = DFA6_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( WRITE | WRITELN | DO | ELSE | FALSE | IF | READ | SKIP | THEN | TRUE | WHILE | DEF | REG | FOR | TO | SEMICOLON | OPENPAREN | CLOSEPAREN | ASSIGN | ADD | SUB | MUL | LEQ | EQ | NOT | AND | OR | LS | RS | INTNUM | ID | STRING | COMMENT | WS );";
		}
	}

}
