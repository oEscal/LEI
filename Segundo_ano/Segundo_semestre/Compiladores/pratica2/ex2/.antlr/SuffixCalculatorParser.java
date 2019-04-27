// Generated from /home/escaleira/Desktop/Compiladores/pratica2/ex2/SuffixCalculator.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SuffixCalculatorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Op=1, Number=2, NEWLINE=3, WS=4;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_expr = 2;
	public static final String[] ruleNames = {
		"program", "stat", "expr"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Op", "Number", "NEWLINE", "WS"
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
	public String getGrammarFileName() { return "SuffixCalculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SuffixCalculatorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SuffixCalculatorParser.EOF, 0); }
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
			setState(9);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Number || _la==NEWLINE) {
				{
				{
				setState(6);
				stat();
				}
				}
				setState(11);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(12);
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
		public TerminalNode NEWLINE() { return getToken(SuffixCalculatorParser.NEWLINE, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(19);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Number:
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				((StatContext)_localctx).expr = expr(0);

				   System.out.println("Result: " + ((StatContext)_localctx).expr.result);

				setState(16);
				match(NEWLINE);
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(18);
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
		public double result = 0;
		public ExprContext e1;
		public Token Number;
		public ExprContext e2;
		public Token Op;
		public TerminalNode Number() { return getToken(SuffixCalculatorParser.Number, 0); }
		public TerminalNode Op() { return getToken(SuffixCalculatorParser.Op, 0); }
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(22);
			((ExprContext)_localctx).Number = match(Number);

			   ((ExprContext)_localctx).result =  Double.parseDouble((((ExprContext)_localctx).Number!=null?((ExprContext)_localctx).Number.getText():null));

			}
			_ctx.stop = _input.LT(-1);
			setState(32);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					_localctx.e1 = _prevctx;
					_localctx.e1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(25);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(26);
					((ExprContext)_localctx).e2 = expr(0);
					setState(27);
					((ExprContext)_localctx).Op = match(Op);

					             switch((((ExprContext)_localctx).Op!=null?((ExprContext)_localctx).Op.getText():null)){ 
					                case "*" : ((ExprContext)_localctx).result =  ((ExprContext)_localctx).e1.result * ((ExprContext)_localctx).e2.result; break;
					                case "/" : ((ExprContext)_localctx).result =  ((ExprContext)_localctx).e1.result / ((ExprContext)_localctx).e2.result; break;
					                case "+" : ((ExprContext)_localctx).result =  ((ExprContext)_localctx).e1.result + ((ExprContext)_localctx).e2.result; break;
					                case "-" : ((ExprContext)_localctx).result =  ((ExprContext)_localctx).e1.result - ((ExprContext)_localctx).e2.result; break;
					                default: System.err.println("Calc error!");
					             }
					          
					}
					} 
				}
				setState(34);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\6&\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\7\2\n\n\2\f\2\16\2\r\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5"+
		"\3\26\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4!\n\4\f\4\16\4$\13\4"+
		"\3\4\2\3\6\5\2\4\6\2\2\2%\2\13\3\2\2\2\4\25\3\2\2\2\6\27\3\2\2\2\b\n\5"+
		"\4\3\2\t\b\3\2\2\2\n\r\3\2\2\2\13\t\3\2\2\2\13\f\3\2\2\2\f\16\3\2\2\2"+
		"\r\13\3\2\2\2\16\17\7\2\2\3\17\3\3\2\2\2\20\21\5\6\4\2\21\22\b\3\1\2\22"+
		"\23\7\5\2\2\23\26\3\2\2\2\24\26\7\5\2\2\25\20\3\2\2\2\25\24\3\2\2\2\26"+
		"\5\3\2\2\2\27\30\b\4\1\2\30\31\7\4\2\2\31\32\b\4\1\2\32\"\3\2\2\2\33\34"+
		"\f\4\2\2\34\35\5\6\4\2\35\36\7\3\2\2\36\37\b\4\1\2\37!\3\2\2\2 \33\3\2"+
		"\2\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#\7\3\2\2\2$\"\3\2\2\2\5\13\25\"";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}