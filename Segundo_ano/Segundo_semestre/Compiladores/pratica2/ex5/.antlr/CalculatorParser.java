// Generated from /home/escaleira/Desktop/Compiladores/pratica2/ex5/Calculator.g4 by ANTLR 4.7.1

   import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculatorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, ID=8, INT=9, NEWLINE=10, 
		WS=11;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_expr = 2, RULE_assignment = 3;
	public static final String[] ruleNames = {
		"program", "stat", "expr", "assignment"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'*'", "'/'", "'+'", "'-'", "'('", "')'", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "ID", "INT", "NEWLINE", 
		"WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Calculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	   HashMap<String, Integer> variables = new HashMap<>();

	public CalculatorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CalculatorParser.EOF, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << ID) | (1L << INT) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(8);
				stat();
				}
				}
				setState(13);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(14);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public ExprContext expr;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(CalculatorParser.NEWLINE, 0); }
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(22);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				((StatContext)_localctx).expr = expr(0);

				   System.out.println("Result: " + ((StatContext)_localctx).expr.result);

				setState(18);
				match(NEWLINE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(20);
				assignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(21);
				match(NEWLINE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public int result = 0;
		public ExprContext e1;
		public Token INT;
		public Token ID;
		public ExprContext expr;
		public Token op;
		public ExprContext e2;
		public TerminalNode INT() { return getToken(CalculatorParser.INT, 0); }
		public TerminalNode ID() { return getToken(CalculatorParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				{
				setState(25);
				((ExprContext)_localctx).INT = match(INT);

				         ((ExprContext)_localctx).result =  Integer.parseInt((((ExprContext)_localctx).INT!=null?((ExprContext)_localctx).INT.getText():null));
				      
				}
				break;
			case ID:
				{
				setState(27);
				((ExprContext)_localctx).ID = match(ID);

				         ((ExprContext)_localctx).result =  variables.get((((ExprContext)_localctx).ID!=null?((ExprContext)_localctx).ID.getText():null));
				      
				}
				break;
			case T__4:
				{
				setState(29);
				match(T__4);
				setState(30);
				((ExprContext)_localctx).expr = expr(0);
				setState(31);
				match(T__5);

				         ((ExprContext)_localctx).result =  ((ExprContext)_localctx).expr.result; 
				      
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(48);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(46);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(36);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(37);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__0 || _la==T__1) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(38);
						((ExprContext)_localctx).e2 = ((ExprContext)_localctx).expr = expr(6);

						                   switch((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null)){ 
						                      case "*": ((ExprContext)_localctx).result =  ((ExprContext)_localctx).e1.result * ((ExprContext)_localctx).e2.result; break;
						                      case "/": ((ExprContext)_localctx).result =  ((ExprContext)_localctx).e1.result / ((ExprContext)_localctx).e2.result; break;
						                      default:  System.err.println("Calc error!");
						                   }
						                
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.e1 = _prevctx;
						_localctx.e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(41);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(42);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__2 || _la==T__3) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(43);
						((ExprContext)_localctx).e2 = ((ExprContext)_localctx).expr = expr(5);

						                   switch((((ExprContext)_localctx).op!=null?((ExprContext)_localctx).op.getText():null)){ 
						                      case "+": ((ExprContext)_localctx).result =  ((ExprContext)_localctx).e1.result + ((ExprContext)_localctx).e2.result; break;
						                      case "-": ((ExprContext)_localctx).result =  ((ExprContext)_localctx).e1.result - ((ExprContext)_localctx).e2.result; break;
						                      default: System.err.println("Calc error!");
						                   }
						                
						}
						break;
					}
					} 
				}
				setState(50);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public Token ID;
		public ExprContext expr;
		public TerminalNode ID() { return getToken(CalculatorParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(CalculatorParser.NEWLINE, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			((AssignmentContext)_localctx).ID = match(ID);
			setState(52);
			match(T__6);
			setState(53);
			((AssignmentContext)_localctx).expr = expr(0);
			setState(54);
			match(NEWLINE);

			   String var_name = (((AssignmentContext)_localctx).ID!=null?((AssignmentContext)_localctx).ID.getText():null);

			   variables.put(var_name, ((AssignmentContext)_localctx).expr.result);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\r<\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\7\2\f\n\2\f\2\16\2\17\13\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3\31\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4%\n"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4\64\13\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\2\3\6\6\2\4\6\b\2\4\3\2\3\4\3\2\5\6\2>\2"+
		"\r\3\2\2\2\4\30\3\2\2\2\6$\3\2\2\2\b\65\3\2\2\2\n\f\5\4\3\2\13\n\3\2\2"+
		"\2\f\17\3\2\2\2\r\13\3\2\2\2\r\16\3\2\2\2\16\20\3\2\2\2\17\r\3\2\2\2\20"+
		"\21\7\2\2\3\21\3\3\2\2\2\22\23\5\6\4\2\23\24\b\3\1\2\24\25\7\f\2\2\25"+
		"\31\3\2\2\2\26\31\5\b\5\2\27\31\7\f\2\2\30\22\3\2\2\2\30\26\3\2\2\2\30"+
		"\27\3\2\2\2\31\5\3\2\2\2\32\33\b\4\1\2\33\34\7\13\2\2\34%\b\4\1\2\35\36"+
		"\7\n\2\2\36%\b\4\1\2\37 \7\7\2\2 !\5\6\4\2!\"\7\b\2\2\"#\b\4\1\2#%\3\2"+
		"\2\2$\32\3\2\2\2$\35\3\2\2\2$\37\3\2\2\2%\62\3\2\2\2&\'\f\7\2\2\'(\t\2"+
		"\2\2()\5\6\4\b)*\b\4\1\2*\61\3\2\2\2+,\f\6\2\2,-\t\3\2\2-.\5\6\4\7./\b"+
		"\4\1\2/\61\3\2\2\2\60&\3\2\2\2\60+\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2"+
		"\62\63\3\2\2\2\63\7\3\2\2\2\64\62\3\2\2\2\65\66\7\n\2\2\66\67\7\t\2\2"+
		"\678\5\6\4\289\7\f\2\29:\b\5\1\2:\t\3\2\2\2\7\r\30$\60\62";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}