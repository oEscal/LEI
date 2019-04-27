// Generated from /home/escaleira/Desktop/Compiladores/pratica2/ex4/Calculator.g4 by ANTLR 4.7.1
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
		T__0=1, INT=2, NAME=3, NEWLINE=4, WS=5;
	public static final int
		RULE_program = 0, RULE_stat = 1;
	public static final String[] ruleNames = {
		"program", "stat"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "' - '"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "INT", "NAME", "NEWLINE", "WS"
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
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INT || _la==NEWLINE) {
				{
				{
				setState(4);
				stat();
				}
				}
				setState(9);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(10);
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
		public Token number;
		public Token name;
		public TerminalNode NEWLINE() { return getToken(CalculatorParser.NEWLINE, 0); }
		public TerminalNode INT() { return getToken(CalculatorParser.INT, 0); }
		public TerminalNode NAME() { return getToken(CalculatorParser.NAME, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(17);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(12);
				((StatContext)_localctx).number = match(INT);
				setState(13);
				match(T__0);
				setState(14);
				((StatContext)_localctx).name = match(NAME);
				setState(15);
				match(NEWLINE);
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(16);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\7\26\4\2\t\2\4\3"+
		"\t\3\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3\24"+
		"\n\3\3\3\2\2\4\2\4\2\2\2\25\2\t\3\2\2\2\4\23\3\2\2\2\6\b\5\4\3\2\7\6\3"+
		"\2\2\2\b\13\3\2\2\2\t\7\3\2\2\2\t\n\3\2\2\2\n\f\3\2\2\2\13\t\3\2\2\2\f"+
		"\r\7\2\2\3\r\3\3\2\2\2\16\17\7\4\2\2\17\20\7\3\2\2\20\21\7\5\2\2\21\24"+
		"\7\6\2\2\22\24\7\6\2\2\23\16\3\2\2\2\23\22\3\2\2\2\24\5\3\2\2\2\4\t\23";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}