// Generated from /home/escaleira/Desktop/Compiladores/pratica2/ex7/Calculator.g4 by ANTLR 4.7.1
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
		T__0=1, T__1=2, OPERATORS=3, INT=4, NEWLINE=5, WS=6;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_expr = 2, RULE_number = 3, RULE_op = 4, 
		RULE_close_par = 5;
	public static final String[] ruleNames = {
		"program", "stat", "expr", "number", "op", "close_par"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "OPERATORS", "INT", "NEWLINE", "WS"
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
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << INT) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(12);
				stat();
				}
				}
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(18);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(CalculatorParser.NEWLINE, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(24);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				expr(0);
				setState(21);
				match(NEWLINE);
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				match(NEWLINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Close_parContext close_par() {
			return getRuleContext(Close_parContext.class,0);
		}
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				{
				setState(27);
				number();
				}
				break;
			case T__0:
				{
				setState(28);
				match(T__0);
				setState(29);
				expr(0);
				setState(30);
				close_par();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(40);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(34);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(35);
					op();
					setState(36);
					expr(4);
					}
					} 
				}
				setState(42);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(CalculatorParser.INT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(INT);
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

	public static class OpContext extends ParserRuleContext {
		public TerminalNode OPERATORS() { return getToken(CalculatorParser.OPERATORS, 0); }
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_op);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(OPERATORS);
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

	public static class Close_parContext extends ParserRuleContext {
		public Close_parContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_close_par; }
	}

	public final Close_parContext close_par() throws RecognitionException {
		Close_parContext _localctx = new Close_parContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_close_par);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(T__1);
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
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\b\64\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\7\2\20\n\2\f\2\16\2\23\13\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\5\3\33\n\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4#\n\4\3\4"+
		"\3\4\3\4\3\4\7\4)\n\4\f\4\16\4,\13\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\2\3\6"+
		"\b\2\4\6\b\n\f\2\2\2\61\2\21\3\2\2\2\4\32\3\2\2\2\6\"\3\2\2\2\b-\3\2\2"+
		"\2\n/\3\2\2\2\f\61\3\2\2\2\16\20\5\4\3\2\17\16\3\2\2\2\20\23\3\2\2\2\21"+
		"\17\3\2\2\2\21\22\3\2\2\2\22\24\3\2\2\2\23\21\3\2\2\2\24\25\7\2\2\3\25"+
		"\3\3\2\2\2\26\27\5\6\4\2\27\30\7\7\2\2\30\33\3\2\2\2\31\33\7\7\2\2\32"+
		"\26\3\2\2\2\32\31\3\2\2\2\33\5\3\2\2\2\34\35\b\4\1\2\35#\5\b\5\2\36\37"+
		"\7\3\2\2\37 \5\6\4\2 !\5\f\7\2!#\3\2\2\2\"\34\3\2\2\2\"\36\3\2\2\2#*\3"+
		"\2\2\2$%\f\5\2\2%&\5\n\6\2&\'\5\6\4\6\')\3\2\2\2($\3\2\2\2),\3\2\2\2*"+
		"(\3\2\2\2*+\3\2\2\2+\7\3\2\2\2,*\3\2\2\2-.\7\6\2\2.\t\3\2\2\2/\60\7\5"+
		"\2\2\60\13\3\2\2\2\61\62\7\4\2\2\62\r\3\2\2\2\6\21\32\"*";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}