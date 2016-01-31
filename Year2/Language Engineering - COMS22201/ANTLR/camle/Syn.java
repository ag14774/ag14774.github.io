// $ANTLR 3.5.2 Syn.g 2015-12-18 00:39:54

    import java.util.HashSet;
    import java.util.Set;
    import java.util.Map;
    import java.util.HashMap;
    import java.util.Stack;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class Syn extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADD", "AND", "ASSIGN", "CLOSEPAREN", 
		"COMMENT", "DEF", "DIGIT", "DO", "ELSE", "EQ", "FALSE", "FOR", "ID", "IF", 
		"INTNUM", "LEQ", "LETTER", "LS", "MUL", "NOT", "OPENPAREN", "OR", "READ", 
		"REG", "RS", "SEMICOLON", "SKIP", "STRING", "SUB", "THEN", "TO", "TRUE", 
		"WHILE", "WRITE", "WRITELN", "WS"
	};
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public Syn(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public Syn(TokenStream input, RecognizerSharedState state) {
		super(input, state);
		this.state.ruleMemo = new HashMap[16+1];


	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return Syn.tokenNames; }
	@Override public String getGrammarFileName() { return "Syn.g"; }


	    boolean errorFound = false;
	    Stack<String> paraphrases = new Stack<String>();
	    private Set<String> symbolTable = new HashSet<String>();
	    private Set<String> temporaries = new HashSet<String>();

		private String cleanString(String s){
			String tmp;
			tmp = s.replaceAll("^'", "");
			s = tmp.replaceAll("'$", "");
			tmp = s.replaceAll("''", "'");
			return tmp;
		}
	    
	    public String getErrorMessage(RecognitionException e, String[] tokenNames){
	        String msg = super.getErrorMessage(e, tokenNames);
	        if(msg.contains("IDNOTFOUND"))
	            msg = "Undeclared identifier "+msg.split(" ")[1];
	        if(msg.contains("ALREADYDECLARED"))
	            msg = "Identifier " + msg.split(" ")[1] +" is already declared";
	        if(msg.contains("FORERROR")){
	            String id = msg.split(" ")[1];
	            msg = "Identifier "+id+" is already declared as a non-register variable";
	        }
	        if(paraphrases.size()>0) {
	            String paraphrase = (String)paraphrases.peek();
	            msg = msg+" "+paraphrase;
	        }
	        if(msg.contains("IDALT")){
	            String temp = msg.split(" ")[1];
	            String id = temp.split("->")[0];
	            String alt = temp.split("->")[1];
	            msg = "Undeclared identifier "+id+". Perhaps you meant "+alt+"?";
	        }
	        if(msg.contains("EOF")&&!msg.contains("<EOF>"))
	            return "missing \";\" between statements";
	        return msg;
	    }

	    public boolean getErrorFound(){
	        return errorFound;
	    }



	public static class program_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "program"
	// Syn.g:73:1: program : statements EOF !;
	public final Syn.program_return program() throws RecognitionException {
		Syn.program_return retval = new Syn.program_return();
		retval.start = input.LT(1);
		int program_StartIndex = input.index();

		Object root_0 = null;

		Token EOF2=null;
		ParserRuleReturnScope statements1 =null;

		Object EOF2_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

			// Syn.g:73:9: ( statements EOF !)
			// Syn.g:74:5: statements EOF !
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_statements_in_program68);
			statements1=statements();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, statements1.getTree());

			EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_program70); if (state.failed) return retval;
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 1, program_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "program"


	public static class statements_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statements"
	// Syn.g:77:1: statements : statement ( SEMICOLON ^ statement )* ;
	public final Syn.statements_return statements() throws RecognitionException {
		Syn.statements_return retval = new Syn.statements_return();
		retval.start = input.LT(1);
		int statements_StartIndex = input.index();

		Object root_0 = null;

		Token SEMICOLON4=null;
		ParserRuleReturnScope statement3 =null;
		ParserRuleReturnScope statement5 =null;

		Object SEMICOLON4_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

			// Syn.g:77:12: ( statement ( SEMICOLON ^ statement )* )
			// Syn.g:78:5: statement ( SEMICOLON ^ statement )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_statement_in_statements86);
			statement3=statement();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, statement3.getTree());

			// Syn.g:78:15: ( SEMICOLON ^ statement )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==SEMICOLON) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// Syn.g:78:17: SEMICOLON ^ statement
					{
					SEMICOLON4=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_statements90); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SEMICOLON4_tree = (Object)adaptor.create(SEMICOLON4);
					root_0 = (Object)adaptor.becomeRoot(SEMICOLON4_tree, root_0);
					}

					pushFollow(FOLLOW_statement_in_statements93);
					statement5=statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, statement5.getTree());

					}
					break;

				default :
					break loop1;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 2, statements_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "statements"


	public static class statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// Syn.g:81:1: statement : ( ( WRITE ^ OPENPAREN ! exp CLOSEPAREN !)=> ( WRITE ^ OPENPAREN ! exp CLOSEPAREN !) | WRITE ^ OPENPAREN ! ( string | boolexp ) CLOSEPAREN !| WRITELN | ( ID ASSIGN ^ exp )=> (var= ID ASSIGN ^ exp ) | ( REG ^var= ID ) ASSIGN ^ exp | DEF ^arr= ID LS ! INTNUM RS !| array ASSIGN ^ exp | READ ^ OPENPAREN !var= ID CLOSEPAREN !| SKIP | IF ^ boolexp THEN ! statement ELSE ! statement | WHILE ^ boolexp DO ! statement | FOR ^ ( forAssign ) TO ! ( exp ) DO ! ( statement ) | OPENPAREN ! statements CLOSEPAREN !);
	public final Syn.statement_return statement() throws RecognitionException {
		Syn.statement_return retval = new Syn.statement_return();
		retval.start = input.LT(1);
		int statement_StartIndex = input.index();

		Object root_0 = null;

		Token var=null;
		Token arr=null;
		Token WRITE6=null;
		Token OPENPAREN7=null;
		Token CLOSEPAREN9=null;
		Token WRITE10=null;
		Token OPENPAREN11=null;
		Token CLOSEPAREN14=null;
		Token WRITELN15=null;
		Token ASSIGN16=null;
		Token REG18=null;
		Token ASSIGN19=null;
		Token DEF21=null;
		Token LS22=null;
		Token INTNUM23=null;
		Token RS24=null;
		Token ASSIGN26=null;
		Token READ28=null;
		Token OPENPAREN29=null;
		Token CLOSEPAREN30=null;
		Token SKIP31=null;
		Token IF32=null;
		Token THEN34=null;
		Token ELSE36=null;
		Token WHILE38=null;
		Token DO40=null;
		Token FOR42=null;
		Token TO44=null;
		Token DO46=null;
		Token OPENPAREN48=null;
		Token CLOSEPAREN50=null;
		ParserRuleReturnScope exp8 =null;
		ParserRuleReturnScope string12 =null;
		ParserRuleReturnScope boolexp13 =null;
		ParserRuleReturnScope exp17 =null;
		ParserRuleReturnScope exp20 =null;
		ParserRuleReturnScope array25 =null;
		ParserRuleReturnScope exp27 =null;
		ParserRuleReturnScope boolexp33 =null;
		ParserRuleReturnScope statement35 =null;
		ParserRuleReturnScope statement37 =null;
		ParserRuleReturnScope boolexp39 =null;
		ParserRuleReturnScope statement41 =null;
		ParserRuleReturnScope forAssign43 =null;
		ParserRuleReturnScope exp45 =null;
		ParserRuleReturnScope statement47 =null;
		ParserRuleReturnScope statements49 =null;

		Object var_tree=null;
		Object arr_tree=null;
		Object WRITE6_tree=null;
		Object OPENPAREN7_tree=null;
		Object CLOSEPAREN9_tree=null;
		Object WRITE10_tree=null;
		Object OPENPAREN11_tree=null;
		Object CLOSEPAREN14_tree=null;
		Object WRITELN15_tree=null;
		Object ASSIGN16_tree=null;
		Object REG18_tree=null;
		Object ASSIGN19_tree=null;
		Object DEF21_tree=null;
		Object LS22_tree=null;
		Object INTNUM23_tree=null;
		Object RS24_tree=null;
		Object ASSIGN26_tree=null;
		Object READ28_tree=null;
		Object OPENPAREN29_tree=null;
		Object CLOSEPAREN30_tree=null;
		Object SKIP31_tree=null;
		Object IF32_tree=null;
		Object THEN34_tree=null;
		Object ELSE36_tree=null;
		Object WHILE38_tree=null;
		Object DO40_tree=null;
		Object FOR42_tree=null;
		Object TO44_tree=null;
		Object DO46_tree=null;
		Object OPENPAREN48_tree=null;
		Object CLOSEPAREN50_tree=null;

		 paraphrases.push("in statement"); 
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

			// Syn.g:84:3: ( ( WRITE ^ OPENPAREN ! exp CLOSEPAREN !)=> ( WRITE ^ OPENPAREN ! exp CLOSEPAREN !) | WRITE ^ OPENPAREN ! ( string | boolexp ) CLOSEPAREN !| WRITELN | ( ID ASSIGN ^ exp )=> (var= ID ASSIGN ^ exp ) | ( REG ^var= ID ) ASSIGN ^ exp | DEF ^arr= ID LS ! INTNUM RS !| array ASSIGN ^ exp | READ ^ OPENPAREN !var= ID CLOSEPAREN !| SKIP | IF ^ boolexp THEN ! statement ELSE ! statement | WHILE ^ boolexp DO ! statement | FOR ^ ( forAssign ) TO ! ( exp ) DO ! ( statement ) | OPENPAREN ! statements CLOSEPAREN !)
			int alt3=13;
			switch ( input.LA(1) ) {
			case WRITE:
				{
				int LA3_1 = input.LA(2);
				if ( (synpred1_Syn()) ) {
					alt3=1;
				}
				else if ( (true) ) {
					alt3=2;
				}

				}
				break;
			case WRITELN:
				{
				alt3=3;
				}
				break;
			case ID:
				{
				int LA3_3 = input.LA(2);
				if ( (synpred2_Syn()) ) {
					alt3=4;
				}
				else if ( (true) ) {
					alt3=7;
				}

				}
				break;
			case REG:
				{
				alt3=5;
				}
				break;
			case DEF:
				{
				alt3=6;
				}
				break;
			case READ:
				{
				alt3=8;
				}
				break;
			case SKIP:
				{
				alt3=9;
				}
				break;
			case IF:
				{
				alt3=10;
				}
				break;
			case WHILE:
				{
				alt3=11;
				}
				break;
			case FOR:
				{
				alt3=12;
				}
				break;
			case OPENPAREN:
				{
				alt3=13;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}
			switch (alt3) {
				case 1 :
					// Syn.g:84:6: ( WRITE ^ OPENPAREN ! exp CLOSEPAREN !)=> ( WRITE ^ OPENPAREN ! exp CLOSEPAREN !)
					{
					root_0 = (Object)adaptor.nil();


					// Syn.g:84:45: ( WRITE ^ OPENPAREN ! exp CLOSEPAREN !)
					// Syn.g:84:46: WRITE ^ OPENPAREN ! exp CLOSEPAREN !
					{
					WRITE6=(Token)match(input,WRITE,FOLLOW_WRITE_in_statement137); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					WRITE6_tree = (Object)adaptor.create(WRITE6);
					root_0 = (Object)adaptor.becomeRoot(WRITE6_tree, root_0);
					}

					OPENPAREN7=(Token)match(input,OPENPAREN,FOLLOW_OPENPAREN_in_statement140); if (state.failed) return retval;
					pushFollow(FOLLOW_exp_in_statement143);
					exp8=exp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, exp8.getTree());

					CLOSEPAREN9=(Token)match(input,CLOSEPAREN,FOLLOW_CLOSEPAREN_in_statement145); if (state.failed) return retval;
					}

					}
					break;
				case 2 :
					// Syn.g:85:5: WRITE ^ OPENPAREN ! ( string | boolexp ) CLOSEPAREN !
					{
					root_0 = (Object)adaptor.nil();


					WRITE10=(Token)match(input,WRITE,FOLLOW_WRITE_in_statement153); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					WRITE10_tree = (Object)adaptor.create(WRITE10);
					root_0 = (Object)adaptor.becomeRoot(WRITE10_tree, root_0);
					}

					OPENPAREN11=(Token)match(input,OPENPAREN,FOLLOW_OPENPAREN_in_statement156); if (state.failed) return retval;
					// Syn.g:85:23: ( string | boolexp )
					int alt2=2;
					int LA2_0 = input.LA(1);
					if ( (LA2_0==STRING) ) {
						alt2=1;
					}
					else if ( (LA2_0==FALSE||LA2_0==ID||LA2_0==INTNUM||(LA2_0 >= NOT && LA2_0 <= OPENPAREN)||LA2_0==TRUE) ) {
						alt2=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 2, 0, input);
						throw nvae;
					}

					switch (alt2) {
						case 1 :
							// Syn.g:85:25: string
							{
							pushFollow(FOLLOW_string_in_statement161);
							string12=string();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, string12.getTree());

							}
							break;
						case 2 :
							// Syn.g:85:34: boolexp
							{
							pushFollow(FOLLOW_boolexp_in_statement165);
							boolexp13=boolexp();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, boolexp13.getTree());

							}
							break;

					}

					CLOSEPAREN14=(Token)match(input,CLOSEPAREN,FOLLOW_CLOSEPAREN_in_statement169); if (state.failed) return retval;
					}
					break;
				case 3 :
					// Syn.g:86:5: WRITELN
					{
					root_0 = (Object)adaptor.nil();


					WRITELN15=(Token)match(input,WRITELN,FOLLOW_WRITELN_in_statement176); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					WRITELN15_tree = (Object)adaptor.create(WRITELN15);
					adaptor.addChild(root_0, WRITELN15_tree);
					}

					}
					break;
				case 4 :
					// Syn.g:87:5: ( ID ASSIGN ^ exp )=> (var= ID ASSIGN ^ exp )
					{
					root_0 = (Object)adaptor.nil();


					// Syn.g:87:25: (var= ID ASSIGN ^ exp )
					// Syn.g:87:26: var= ID ASSIGN ^ exp
					{
					var=(Token)match(input,ID,FOLLOW_ID_in_statement196); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);
					}

					ASSIGN16=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_statement198); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASSIGN16_tree = (Object)adaptor.create(ASSIGN16);
					root_0 = (Object)adaptor.becomeRoot(ASSIGN16_tree, root_0);
					}

					pushFollow(FOLLOW_exp_in_statement201);
					exp17=exp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, exp17.getTree());

					}

					if ( state.backtracking==0 ) {symbolTable.add(var.getText());}
					}
					break;
				case 5 :
					// Syn.g:88:5: ( REG ^var= ID ) ASSIGN ^ exp
					{
					root_0 = (Object)adaptor.nil();


					// Syn.g:88:5: ( REG ^var= ID )
					// Syn.g:88:6: REG ^var= ID
					{
					REG18=(Token)match(input,REG,FOLLOW_REG_in_statement211); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					REG18_tree = (Object)adaptor.create(REG18);
					root_0 = (Object)adaptor.becomeRoot(REG18_tree, root_0);
					}

					var=(Token)match(input,ID,FOLLOW_ID_in_statement216); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);
					}

					}

					ASSIGN19=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_statement219); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASSIGN19_tree = (Object)adaptor.create(ASSIGN19);
					root_0 = (Object)adaptor.becomeRoot(ASSIGN19_tree, root_0);
					}

					pushFollow(FOLLOW_exp_in_statement222);
					exp20=exp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, exp20.getTree());

					if ( state.backtracking==0 ) {
					    if(symbolTable.contains(var.getText())){
					        throw new FailedPredicateException(input,var.getText(),"ALREADYDECLARED");
					    }
					    else {
					        symbolTable.add(var.getText());
					        temporaries.add(var.getText());
					    }
					    }
					}
					break;
				case 6 :
					// Syn.g:97:5: DEF ^arr= ID LS ! INTNUM RS !
					{
					root_0 = (Object)adaptor.nil();


					DEF21=(Token)match(input,DEF,FOLLOW_DEF_in_statement230); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DEF21_tree = (Object)adaptor.create(DEF21);
					root_0 = (Object)adaptor.becomeRoot(DEF21_tree, root_0);
					}

					arr=(Token)match(input,ID,FOLLOW_ID_in_statement235); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					arr_tree = (Object)adaptor.create(arr);
					adaptor.addChild(root_0, arr_tree);
					}

					LS22=(Token)match(input,LS,FOLLOW_LS_in_statement237); if (state.failed) return retval;
					INTNUM23=(Token)match(input,INTNUM,FOLLOW_INTNUM_in_statement240); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INTNUM23_tree = (Object)adaptor.create(INTNUM23);
					adaptor.addChild(root_0, INTNUM23_tree);
					}

					RS24=(Token)match(input,RS,FOLLOW_RS_in_statement242); if (state.failed) return retval;
					if ( state.backtracking==0 ) {symbolTable.add(arr.getText());}
					}
					break;
				case 7 :
					// Syn.g:98:5: array ASSIGN ^ exp
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_array_in_statement251);
					array25=array();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, array25.getTree());

					ASSIGN26=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_statement253); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASSIGN26_tree = (Object)adaptor.create(ASSIGN26);
					root_0 = (Object)adaptor.becomeRoot(ASSIGN26_tree, root_0);
					}

					pushFollow(FOLLOW_exp_in_statement256);
					exp27=exp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, exp27.getTree());

					}
					break;
				case 8 :
					// Syn.g:99:5: READ ^ OPENPAREN !var= ID CLOSEPAREN !
					{
					root_0 = (Object)adaptor.nil();


					READ28=(Token)match(input,READ,FOLLOW_READ_in_statement262); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					READ28_tree = (Object)adaptor.create(READ28);
					root_0 = (Object)adaptor.becomeRoot(READ28_tree, root_0);
					}

					OPENPAREN29=(Token)match(input,OPENPAREN,FOLLOW_OPENPAREN_in_statement265); if (state.failed) return retval;
					var=(Token)match(input,ID,FOLLOW_ID_in_statement270); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);
					}

					CLOSEPAREN30=(Token)match(input,CLOSEPAREN,FOLLOW_CLOSEPAREN_in_statement272); if (state.failed) return retval;
					if ( state.backtracking==0 ) {symbolTable.add(var.getText());}
					}
					break;
				case 9 :
					// Syn.g:100:5: SKIP
					{
					root_0 = (Object)adaptor.nil();


					SKIP31=(Token)match(input,SKIP,FOLLOW_SKIP_in_statement281); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SKIP31_tree = (Object)adaptor.create(SKIP31);
					adaptor.addChild(root_0, SKIP31_tree);
					}

					}
					break;
				case 10 :
					// Syn.g:101:5: IF ^ boolexp THEN ! statement ELSE ! statement
					{
					root_0 = (Object)adaptor.nil();


					IF32=(Token)match(input,IF,FOLLOW_IF_in_statement287); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IF32_tree = (Object)adaptor.create(IF32);
					root_0 = (Object)adaptor.becomeRoot(IF32_tree, root_0);
					}

					pushFollow(FOLLOW_boolexp_in_statement290);
					boolexp33=boolexp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, boolexp33.getTree());

					THEN34=(Token)match(input,THEN,FOLLOW_THEN_in_statement292); if (state.failed) return retval;
					pushFollow(FOLLOW_statement_in_statement295);
					statement35=statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, statement35.getTree());

					ELSE36=(Token)match(input,ELSE,FOLLOW_ELSE_in_statement297); if (state.failed) return retval;
					pushFollow(FOLLOW_statement_in_statement300);
					statement37=statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, statement37.getTree());

					}
					break;
				case 11 :
					// Syn.g:102:5: WHILE ^ boolexp DO ! statement
					{
					root_0 = (Object)adaptor.nil();


					WHILE38=(Token)match(input,WHILE,FOLLOW_WHILE_in_statement306); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					WHILE38_tree = (Object)adaptor.create(WHILE38);
					root_0 = (Object)adaptor.becomeRoot(WHILE38_tree, root_0);
					}

					pushFollow(FOLLOW_boolexp_in_statement309);
					boolexp39=boolexp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, boolexp39.getTree());

					DO40=(Token)match(input,DO,FOLLOW_DO_in_statement311); if (state.failed) return retval;
					pushFollow(FOLLOW_statement_in_statement314);
					statement41=statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, statement41.getTree());

					}
					break;
				case 12 :
					// Syn.g:103:5: FOR ^ ( forAssign ) TO ! ( exp ) DO ! ( statement )
					{
					root_0 = (Object)adaptor.nil();


					FOR42=(Token)match(input,FOR,FOLLOW_FOR_in_statement320); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FOR42_tree = (Object)adaptor.create(FOR42);
					root_0 = (Object)adaptor.becomeRoot(FOR42_tree, root_0);
					}

					// Syn.g:103:10: ( forAssign )
					// Syn.g:103:11: forAssign
					{
					pushFollow(FOLLOW_forAssign_in_statement324);
					forAssign43=forAssign();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, forAssign43.getTree());

					}

					TO44=(Token)match(input,TO,FOLLOW_TO_in_statement327); if (state.failed) return retval;
					// Syn.g:103:26: ( exp )
					// Syn.g:103:27: exp
					{
					pushFollow(FOLLOW_exp_in_statement331);
					exp45=exp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, exp45.getTree());

					}

					DO46=(Token)match(input,DO,FOLLOW_DO_in_statement334); if (state.failed) return retval;
					// Syn.g:103:36: ( statement )
					// Syn.g:103:37: statement
					{
					pushFollow(FOLLOW_statement_in_statement338);
					statement47=statement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, statement47.getTree());

					}

					}
					break;
				case 13 :
					// Syn.g:104:5: OPENPAREN ! statements CLOSEPAREN !
					{
					root_0 = (Object)adaptor.nil();


					OPENPAREN48=(Token)match(input,OPENPAREN,FOLLOW_OPENPAREN_in_statement345); if (state.failed) return retval;
					pushFollow(FOLLOW_statements_in_statement348);
					statements49=statements();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, statements49.getTree());

					CLOSEPAREN50=(Token)match(input,CLOSEPAREN,FOLLOW_CLOSEPAREN_in_statement350); if (state.failed) return retval;
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) { paraphrases.pop(); }
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 3, statement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "statement"


	public static class forAssign_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "forAssign"
	// Syn.g:107:10: fragment forAssign : (var= ID ASSIGN exp ) -> ^( ASSIGN ^( REG ID ) exp ) ;
	public final Syn.forAssign_return forAssign() throws RecognitionException {
		Syn.forAssign_return retval = new Syn.forAssign_return();
		retval.start = input.LT(1);
		int forAssign_StartIndex = input.index();

		Object root_0 = null;

		Token var=null;
		Token ASSIGN51=null;
		ParserRuleReturnScope exp52 =null;

		Object var_tree=null;
		Object ASSIGN51_tree=null;
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
		RewriteRuleSubtreeStream stream_exp=new RewriteRuleSubtreeStream(adaptor,"rule exp");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

			// Syn.g:107:20: ( (var= ID ASSIGN exp ) -> ^( ASSIGN ^( REG ID ) exp ) )
			// Syn.g:108:5: (var= ID ASSIGN exp )
			{
			// Syn.g:108:5: (var= ID ASSIGN exp )
			// Syn.g:108:6: var= ID ASSIGN exp
			{
			var=(Token)match(input,ID,FOLLOW_ID_in_forAssign371); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ID.add(var);

			ASSIGN51=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_forAssign373); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN51);

			pushFollow(FOLLOW_exp_in_forAssign375);
			exp52=exp();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_exp.add(exp52.getTree());
			}

			if ( state.backtracking==0 ) {
			    if(symbolTable.contains(var.getText())&&!temporaries.contains(var.getText())){
			        throw new FailedPredicateException(input,var.getText(),"FORERROR");
			    }
			    else if(!symbolTable.contains(var.getText())){
			        symbolTable.add(var.getText());
			        temporaries.add(var.getText());
			    }
			    }
			// AST REWRITE
			// elements: ASSIGN, ID, exp
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 116:7: -> ^( ASSIGN ^( REG ID ) exp )
			{
				// Syn.g:116:10: ^( ASSIGN ^( REG ID ) exp )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_ASSIGN.nextNode(), root_1);
				// Syn.g:116:19: ^( REG ID )
				{
				Object root_2 = (Object)adaptor.nil();
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(REG, "REG"), root_2);
				adaptor.addChild(root_2, stream_ID.nextNode());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_exp.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 4, forAssign_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "forAssign"


	public static class array_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "array"
	// Syn.g:119:10: fragment array : (arr= ID ^ LS ! exp RS !) ;
	public final Syn.array_return array() throws RecognitionException {
		Syn.array_return retval = new Syn.array_return();
		retval.start = input.LT(1);
		int array_StartIndex = input.index();

		Object root_0 = null;

		Token arr=null;
		Token LS53=null;
		Token RS55=null;
		ParserRuleReturnScope exp54 =null;

		Object arr_tree=null;
		Object LS53_tree=null;
		Object RS55_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

			// Syn.g:119:16: ( (arr= ID ^ LS ! exp RS !) )
			// Syn.g:120:5: (arr= ID ^ LS ! exp RS !)
			{
			root_0 = (Object)adaptor.nil();


			// Syn.g:120:5: (arr= ID ^ LS ! exp RS !)
			// Syn.g:120:6: arr= ID ^ LS ! exp RS !
			{
			arr=(Token)match(input,ID,FOLLOW_ID_in_array412); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			arr_tree = (Object)adaptor.create(arr);
			root_0 = (Object)adaptor.becomeRoot(arr_tree, root_0);
			}

			LS53=(Token)match(input,LS,FOLLOW_LS_in_array415); if (state.failed) return retval;
			pushFollow(FOLLOW_exp_in_array418);
			exp54=exp();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, exp54.getTree());

			RS55=(Token)match(input,RS,FOLLOW_RS_in_array420); if (state.failed) return retval;
			}

			if ( state.backtracking==0 ) {if(!symbolTable.contains(arr.getText())){
			        int min = 99999;
			        String alt = "";
			        for(String B : symbolTable){
			            int dist = EditDistance.calculate(arr.getText(),B);
			            if(dist<=min){
			                min = dist;
			                alt = B;
			            }
			        }
			        if(alt.equals(""))
			            throw new FailedPredicateException(input,arr.getText(),"IDNOTFOUND");
			        else
			            throw new FailedPredicateException(input,arr.getText()+"->"+alt,"IDALT");
			     }
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 5, array_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "array"


	public static class boolexp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "boolexp"
	// Syn.g:139:1: boolexp : boolterm ( ( AND | OR ) ^ boolterm )* ;
	public final Syn.boolexp_return boolexp() throws RecognitionException {
		Syn.boolexp_return retval = new Syn.boolexp_return();
		retval.start = input.LT(1);
		int boolexp_StartIndex = input.index();

		Object root_0 = null;

		Token set57=null;
		ParserRuleReturnScope boolterm56 =null;
		ParserRuleReturnScope boolterm58 =null;

		Object set57_tree=null;

		 paraphrases.push("in boolean expression"); 
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

			// Syn.g:142:3: ( boolterm ( ( AND | OR ) ^ boolterm )* )
			// Syn.g:142:6: boolterm ( ( AND | OR ) ^ boolterm )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_boolterm_in_boolexp453);
			boolterm56=boolterm();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, boolterm56.getTree());

			// Syn.g:142:15: ( ( AND | OR ) ^ boolterm )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==AND||LA4_0==OR) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// Syn.g:142:16: ( AND | OR ) ^ boolterm
					{
					set57=input.LT(1);
					set57=input.LT(1);
					if ( input.LA(1)==AND||input.LA(1)==OR ) {
						input.consume();
						if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set57), root_0);
						state.errorRecovery=false;
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_boolterm_in_boolexp463);
					boolterm58=boolterm();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, boolterm58.getTree());

					}
					break;

				default :
					break loop4;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) { paraphrases.pop(); }
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 6, boolexp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "boolexp"


	public static class boolterm_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "boolterm"
	// Syn.g:145:1: boolterm : ( NOT ^)? bool ;
	public final Syn.boolterm_return boolterm() throws RecognitionException {
		Syn.boolterm_return retval = new Syn.boolterm_return();
		retval.start = input.LT(1);
		int boolterm_StartIndex = input.index();

		Object root_0 = null;

		Token NOT59=null;
		ParserRuleReturnScope bool60 =null;

		Object NOT59_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

			// Syn.g:145:10: ( ( NOT ^)? bool )
			// Syn.g:146:5: ( NOT ^)? bool
			{
			root_0 = (Object)adaptor.nil();


			// Syn.g:146:5: ( NOT ^)?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==NOT) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// Syn.g:146:6: NOT ^
					{
					NOT59=(Token)match(input,NOT,FOLLOW_NOT_in_boolterm481); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NOT59_tree = (Object)adaptor.create(NOT59);
					root_0 = (Object)adaptor.becomeRoot(NOT59_tree, root_0);
					}

					}
					break;

			}

			pushFollow(FOLLOW_bool_in_boolterm486);
			bool60=bool();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, bool60.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 7, boolterm_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "boolterm"


	public static class bool_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "bool"
	// Syn.g:149:1: bool : ( TRUE | FALSE | ( exp bop ^ exp )=> ( exp bop ^ exp ) | OPENPAREN ! boolexp CLOSEPAREN !);
	public final Syn.bool_return bool() throws RecognitionException {
		Syn.bool_return retval = new Syn.bool_return();
		retval.start = input.LT(1);
		int bool_StartIndex = input.index();

		Object root_0 = null;

		Token TRUE61=null;
		Token FALSE62=null;
		Token OPENPAREN66=null;
		Token CLOSEPAREN68=null;
		ParserRuleReturnScope exp63 =null;
		ParserRuleReturnScope bop64 =null;
		ParserRuleReturnScope exp65 =null;
		ParserRuleReturnScope boolexp67 =null;

		Object TRUE61_tree=null;
		Object FALSE62_tree=null;
		Object OPENPAREN66_tree=null;
		Object CLOSEPAREN68_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

			// Syn.g:149:6: ( TRUE | FALSE | ( exp bop ^ exp )=> ( exp bop ^ exp ) | OPENPAREN ! boolexp CLOSEPAREN !)
			int alt6=4;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==TRUE) ) {
				alt6=1;
			}
			else if ( (LA6_0==FALSE) ) {
				alt6=2;
			}
			else if ( (LA6_0==ID) && (synpred3_Syn())) {
				alt6=3;
			}
			else if ( (LA6_0==INTNUM) && (synpred3_Syn())) {
				alt6=3;
			}
			else if ( (LA6_0==OPENPAREN) ) {
				int LA6_5 = input.LA(2);
				if ( (synpred3_Syn()) ) {
					alt6=3;
				}
				else if ( (true) ) {
					alt6=4;
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// Syn.g:150:5: TRUE
					{
					root_0 = (Object)adaptor.nil();


					TRUE61=(Token)match(input,TRUE,FOLLOW_TRUE_in_bool501); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TRUE61_tree = (Object)adaptor.create(TRUE61);
					adaptor.addChild(root_0, TRUE61_tree);
					}

					}
					break;
				case 2 :
					// Syn.g:150:12: FALSE
					{
					root_0 = (Object)adaptor.nil();


					FALSE62=(Token)match(input,FALSE,FOLLOW_FALSE_in_bool505); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FALSE62_tree = (Object)adaptor.create(FALSE62);
					adaptor.addChild(root_0, FALSE62_tree);
					}

					}
					break;
				case 3 :
					// Syn.g:151:5: ( exp bop ^ exp )=> ( exp bop ^ exp )
					{
					root_0 = (Object)adaptor.nil();


					// Syn.g:151:21: ( exp bop ^ exp )
					// Syn.g:151:22: exp bop ^ exp
					{
					pushFollow(FOLLOW_exp_in_bool521);
					exp63=exp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, exp63.getTree());

					pushFollow(FOLLOW_bop_in_bool523);
					bop64=bop();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(bop64.getTree(), root_0);
					pushFollow(FOLLOW_exp_in_bool526);
					exp65=exp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, exp65.getTree());

					}

					}
					break;
				case 4 :
					// Syn.g:152:5: OPENPAREN ! boolexp CLOSEPAREN !
					{
					root_0 = (Object)adaptor.nil();


					OPENPAREN66=(Token)match(input,OPENPAREN,FOLLOW_OPENPAREN_in_bool533); if (state.failed) return retval;
					pushFollow(FOLLOW_boolexp_in_bool536);
					boolexp67=boolexp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, boolexp67.getTree());

					CLOSEPAREN68=(Token)match(input,CLOSEPAREN,FOLLOW_CLOSEPAREN_in_bool538); if (state.failed) return retval;
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 8, bool_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "bool"


	public static class bop_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "bop"
	// Syn.g:155:10: fragment bop : ( EQ | LEQ );
	public final Syn.bop_return bop() throws RecognitionException {
		Syn.bop_return retval = new Syn.bop_return();
		retval.start = input.LT(1);
		int bop_StartIndex = input.index();

		Object root_0 = null;

		Token set69=null;

		Object set69_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

			// Syn.g:155:14: ( EQ | LEQ )
			// Syn.g:
			{
			root_0 = (Object)adaptor.nil();


			set69=input.LT(1);
			if ( input.LA(1)==EQ||input.LA(1)==LEQ ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set69));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 9, bop_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "bop"


	public static class exp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "exp"
	// Syn.g:159:1: exp : term ( ( ADD | SUB ) ^ term )* ;
	public final Syn.exp_return exp() throws RecognitionException {
		Syn.exp_return retval = new Syn.exp_return();
		retval.start = input.LT(1);
		int exp_StartIndex = input.index();

		Object root_0 = null;

		Token set71=null;
		ParserRuleReturnScope term70 =null;
		ParserRuleReturnScope term72 =null;

		Object set71_tree=null;

		 paraphrases.push("in expression"); 
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

			// Syn.g:162:3: ( term ( ( ADD | SUB ) ^ term )* )
			// Syn.g:162:6: term ( ( ADD | SUB ) ^ term )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_term_in_exp585);
			term70=term();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, term70.getTree());

			// Syn.g:162:11: ( ( ADD | SUB ) ^ term )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==ADD||LA7_0==SUB) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// Syn.g:162:13: ( ADD | SUB ) ^ term
					{
					set71=input.LT(1);
					set71=input.LT(1);
					if ( input.LA(1)==ADD||input.LA(1)==SUB ) {
						input.consume();
						if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot((Object)adaptor.create(set71), root_0);
						state.errorRecovery=false;
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_term_in_exp600);
					term72=term();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, term72.getTree());

					}
					break;

				default :
					break loop7;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) { paraphrases.pop(); }
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 10, exp_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "exp"


	public static class term_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "term"
	// Syn.g:165:1: term : factor ( MUL ^ factor )* ;
	public final Syn.term_return term() throws RecognitionException {
		Syn.term_return retval = new Syn.term_return();
		retval.start = input.LT(1);
		int term_StartIndex = input.index();

		Object root_0 = null;

		Token MUL74=null;
		ParserRuleReturnScope factor73 =null;
		ParserRuleReturnScope factor75 =null;

		Object MUL74_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

			// Syn.g:165:6: ( factor ( MUL ^ factor )* )
			// Syn.g:166:5: factor ( MUL ^ factor )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_factor_in_term618);
			factor73=factor();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, factor73.getTree());

			// Syn.g:166:12: ( MUL ^ factor )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==MUL) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// Syn.g:166:14: MUL ^ factor
					{
					MUL74=(Token)match(input,MUL,FOLLOW_MUL_in_term622); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					MUL74_tree = (Object)adaptor.create(MUL74);
					root_0 = (Object)adaptor.becomeRoot(MUL74_tree, root_0);
					}

					pushFollow(FOLLOW_factor_in_term625);
					factor75=factor();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, factor75.getTree());

					}
					break;

				default :
					break loop8;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 11, term_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "term"


	public static class factor_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "factor"
	// Syn.g:169:1: factor : (var= ID | INTNUM | array | OPENPAREN ! exp CLOSEPAREN !);
	public final Syn.factor_return factor() throws RecognitionException {
		Syn.factor_return retval = new Syn.factor_return();
		retval.start = input.LT(1);
		int factor_StartIndex = input.index();

		Object root_0 = null;

		Token var=null;
		Token INTNUM76=null;
		Token OPENPAREN78=null;
		Token CLOSEPAREN80=null;
		ParserRuleReturnScope array77 =null;
		ParserRuleReturnScope exp79 =null;

		Object var_tree=null;
		Object INTNUM76_tree=null;
		Object OPENPAREN78_tree=null;
		Object CLOSEPAREN80_tree=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

			// Syn.g:169:8: (var= ID | INTNUM | array | OPENPAREN ! exp CLOSEPAREN !)
			int alt9=4;
			switch ( input.LA(1) ) {
			case ID:
				{
				int LA9_1 = input.LA(2);
				if ( (LA9_1==LS) ) {
					alt9=3;
				}
				else if ( (LA9_1==EOF||(LA9_1 >= ADD && LA9_1 <= AND)||LA9_1==CLOSEPAREN||(LA9_1 >= DO && LA9_1 <= EQ)||LA9_1==LEQ||LA9_1==MUL||LA9_1==OR||(LA9_1 >= RS && LA9_1 <= SEMICOLON)||(LA9_1 >= SUB && LA9_1 <= TO)) ) {
					alt9=1;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 9, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case INTNUM:
				{
				alt9=2;
				}
				break;
			case OPENPAREN:
				{
				alt9=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// Syn.g:170:5: var= ID
					{
					root_0 = (Object)adaptor.nil();


					var=(Token)match(input,ID,FOLLOW_ID_in_factor645); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					var_tree = (Object)adaptor.create(var);
					adaptor.addChild(root_0, var_tree);
					}

					if ( state.backtracking==0 ) {if(!symbolTable.contains(var.getText())){
					        int min = 99999;
					        String alt = "";
					        for(String B : symbolTable){
					            int dist = EditDistance.calculate(var.getText(),B);
					            if(dist<=min){
					                min = dist;
					                alt = B;
					            }
					        }
					        if(alt.equals(""))
					            throw new FailedPredicateException(input,var.getText(),"IDNOTFOUND");
					        else
					            throw new FailedPredicateException(input,var.getText()+"->"+alt,"IDALT");
					     }
					    }
					}
					break;
				case 2 :
					// Syn.g:187:5: INTNUM
					{
					root_0 = (Object)adaptor.nil();


					INTNUM76=(Token)match(input,INTNUM,FOLLOW_INTNUM_in_factor657); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INTNUM76_tree = (Object)adaptor.create(INTNUM76);
					adaptor.addChild(root_0, INTNUM76_tree);
					}

					}
					break;
				case 3 :
					// Syn.g:188:5: array
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_array_in_factor663);
					array77=array();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, array77.getTree());

					}
					break;
				case 4 :
					// Syn.g:189:5: OPENPAREN ! exp CLOSEPAREN !
					{
					root_0 = (Object)adaptor.nil();


					OPENPAREN78=(Token)match(input,OPENPAREN,FOLLOW_OPENPAREN_in_factor669); if (state.failed) return retval;
					pushFollow(FOLLOW_exp_in_factor672);
					exp79=exp();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, exp79.getTree());

					CLOSEPAREN80=(Token)match(input,CLOSEPAREN,FOLLOW_CLOSEPAREN_in_factor674); if (state.failed) return retval;
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 12, factor_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "factor"


	protected static class string_scope {
		String tmp;
	}
	protected Stack<string_scope> string_stack = new Stack<string_scope>();

	public static class string_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "string"
	// Syn.g:192:1: string : s= STRING -> STRING[$string::tmp] ;
	public final Syn.string_return string() throws RecognitionException {
		string_stack.push(new string_scope());
		Syn.string_return retval = new Syn.string_return();
		retval.start = input.LT(1);
		int string_StartIndex = input.index();

		Object root_0 = null;

		Token s=null;

		Object s_tree=null;
		RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

			// Syn.g:194:3: (s= STRING -> STRING[$string::tmp] )
			// Syn.g:194:5: s= STRING
			{
			s=(Token)match(input,STRING,FOLLOW_STRING_in_string694); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_STRING.add(s);

			if ( state.backtracking==0 ) { string_stack.peek().tmp = cleanString((s!=null?s.getText():null)); }
			// AST REWRITE
			// elements: STRING
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 194:54: -> STRING[$string::tmp]
			{
				adaptor.addChild(root_0, (Object)adaptor.create(STRING, string_stack.peek().tmp));
			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		    catch (RecognitionException e){
		        errorFound = true;
		        reportError(e);
		        recover(input,e);
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 13, string_StartIndex); }

			string_stack.pop();
		}
		return retval;
	}
	// $ANTLR end "string"

	// $ANTLR start synpred1_Syn
	public final void synpred1_Syn_fragment() throws RecognitionException {
		// Syn.g:84:6: ( WRITE ^ OPENPAREN ! exp CLOSEPAREN !)
		// Syn.g:84:7: WRITE ^ OPENPAREN ! exp CLOSEPAREN !
		{
		match(input,WRITE,FOLLOW_WRITE_in_synpred1_Syn122); if (state.failed) return;

		match(input,OPENPAREN,FOLLOW_OPENPAREN_in_synpred1_Syn125); if (state.failed) return;

		pushFollow(FOLLOW_exp_in_synpred1_Syn128);
		exp();
		state._fsp--;
		if (state.failed) return;

		match(input,CLOSEPAREN,FOLLOW_CLOSEPAREN_in_synpred1_Syn130); if (state.failed) return;

		}

	}
	// $ANTLR end synpred1_Syn

	// $ANTLR start synpred2_Syn
	public final void synpred2_Syn_fragment() throws RecognitionException {
		// Syn.g:87:5: ( ID ASSIGN ^ exp )
		// Syn.g:87:6: ID ASSIGN ^ exp
		{
		match(input,ID,FOLLOW_ID_in_synpred2_Syn183); if (state.failed) return;

		match(input,ASSIGN,FOLLOW_ASSIGN_in_synpred2_Syn185); if (state.failed) return;

		pushFollow(FOLLOW_exp_in_synpred2_Syn188);
		exp();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred2_Syn

	// $ANTLR start synpred3_Syn
	public final void synpred3_Syn_fragment() throws RecognitionException {
		// Syn.g:151:5: ( exp bop ^ exp )
		// Syn.g:151:6: exp bop ^ exp
		{
		pushFollow(FOLLOW_exp_in_synpred3_Syn512);
		exp();
		state._fsp--;
		if (state.failed) return;

		pushFollow(FOLLOW_bop_in_synpred3_Syn514);
		bop();
		state._fsp--;
		if (state.failed) return;

		pushFollow(FOLLOW_exp_in_synpred3_Syn517);
		exp();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred3_Syn

	// Delegated rules

	public final boolean synpred1_Syn() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_Syn_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred2_Syn() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred2_Syn_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred3_Syn() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred3_Syn_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}



	public static final BitSet FOLLOW_statements_in_program68 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_program70 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_statements86 = new BitSet(new long[]{0x0000000020000002L});
	public static final BitSet FOLLOW_SEMICOLON_in_statements90 = new BitSet(new long[]{0x000000704D038200L});
	public static final BitSet FOLLOW_statement_in_statements93 = new BitSet(new long[]{0x0000000020000002L});
	public static final BitSet FOLLOW_WRITE_in_statement137 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_OPENPAREN_in_statement140 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_statement143 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSEPAREN_in_statement145 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WRITE_in_statement153 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_OPENPAREN_in_statement156 = new BitSet(new long[]{0x0000000881854000L});
	public static final BitSet FOLLOW_string_in_statement161 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_boolexp_in_statement165 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSEPAREN_in_statement169 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WRITELN_in_statement176 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_statement196 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ASSIGN_in_statement198 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_statement201 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REG_in_statement211 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_ID_in_statement216 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ASSIGN_in_statement219 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_statement222 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DEF_in_statement230 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_ID_in_statement235 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_LS_in_statement237 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_INTNUM_in_statement240 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_RS_in_statement242 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_in_statement251 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ASSIGN_in_statement253 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_statement256 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_READ_in_statement262 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_OPENPAREN_in_statement265 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_ID_in_statement270 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSEPAREN_in_statement272 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SKIP_in_statement281 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_statement287 = new BitSet(new long[]{0x0000000801854000L});
	public static final BitSet FOLLOW_boolexp_in_statement290 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_THEN_in_statement292 = new BitSet(new long[]{0x000000704D038200L});
	public static final BitSet FOLLOW_statement_in_statement295 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_ELSE_in_statement297 = new BitSet(new long[]{0x000000704D038200L});
	public static final BitSet FOLLOW_statement_in_statement300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHILE_in_statement306 = new BitSet(new long[]{0x0000000801854000L});
	public static final BitSet FOLLOW_boolexp_in_statement309 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_DO_in_statement311 = new BitSet(new long[]{0x000000704D038200L});
	public static final BitSet FOLLOW_statement_in_statement314 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FOR_in_statement320 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_forAssign_in_statement324 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_TO_in_statement327 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_statement331 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_DO_in_statement334 = new BitSet(new long[]{0x000000704D038200L});
	public static final BitSet FOLLOW_statement_in_statement338 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPAREN_in_statement345 = new BitSet(new long[]{0x000000704D038200L});
	public static final BitSet FOLLOW_statements_in_statement348 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSEPAREN_in_statement350 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_forAssign371 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ASSIGN_in_forAssign373 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_forAssign375 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_array412 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_LS_in_array415 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_array418 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_RS_in_array420 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_boolterm_in_boolexp453 = new BitSet(new long[]{0x0000000002000022L});
	public static final BitSet FOLLOW_set_in_boolexp456 = new BitSet(new long[]{0x0000000801854000L});
	public static final BitSet FOLLOW_boolterm_in_boolexp463 = new BitSet(new long[]{0x0000000002000022L});
	public static final BitSet FOLLOW_NOT_in_boolterm481 = new BitSet(new long[]{0x0000000801054000L});
	public static final BitSet FOLLOW_bool_in_boolterm486 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_bool501 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_bool505 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_exp_in_bool521 = new BitSet(new long[]{0x0000000000082000L});
	public static final BitSet FOLLOW_bop_in_bool523 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_bool526 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPAREN_in_bool533 = new BitSet(new long[]{0x0000000801854000L});
	public static final BitSet FOLLOW_boolexp_in_bool536 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSEPAREN_in_bool538 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_term_in_exp585 = new BitSet(new long[]{0x0000000100000012L});
	public static final BitSet FOLLOW_set_in_exp589 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_term_in_exp600 = new BitSet(new long[]{0x0000000100000012L});
	public static final BitSet FOLLOW_factor_in_term618 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_MUL_in_term622 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_factor_in_term625 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_ID_in_factor645 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTNUM_in_factor657 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_in_factor663 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENPAREN_in_factor669 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_factor672 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSEPAREN_in_factor674 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_string694 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WRITE_in_synpred1_Syn122 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_OPENPAREN_in_synpred1_Syn125 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_synpred1_Syn128 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_CLOSEPAREN_in_synpred1_Syn130 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_synpred2_Syn183 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ASSIGN_in_synpred2_Syn185 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_synpred2_Syn188 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_exp_in_synpred3_Syn512 = new BitSet(new long[]{0x0000000000082000L});
	public static final BitSet FOLLOW_bop_in_synpred3_Syn514 = new BitSet(new long[]{0x0000000001050000L});
	public static final BitSet FOLLOW_exp_in_synpred3_Syn517 = new BitSet(new long[]{0x0000000000000002L});
}
