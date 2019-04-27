// Generated from /home/escaleira/Desktop/Compiladores/pratica2/ex9/Calculadora.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculadoraParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		COMMENT=10, WORD=11, VARRIABLE=12, NEWLINE=13, WS=14;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_operation = 2, RULE_var_define = 3, 
		RULE_set = 4, RULE_value = 5;
	public static final String[] ruleNames = {
		"program", "stat", "operation", "var_define", "set", "value"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'+'", "'\\'", "'&'", "'('", "')'", "'='", "'{'", "','", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "COMMENT", 
		"WORD", "VARRIABLE", "NEWLINE", "WS"
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
	public String getGrammarFileName() { return "Calculadora.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CalculadoraParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CalculadoraParser.EOF, 0); }
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__6) | (1L << VARRIABLE) | (1L << NEWLINE))) != 0)) {
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
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(CalculadoraParser.NEWLINE, 0); }
		public Var_defineContext var_define() {
			return getRuleContext(Var_defineContext.class,0);
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
			setState(27);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				operation();
				setState(21);
				match(NEWLINE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				var_define();
				setState(24);
				match(NEWLINE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(26);
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

	public static class OperationContext extends ParserRuleContext {
		public List<SetContext> set() {
			return getRuleContexts(SetContext.class);
		}
		public SetContext set(int i) {
			return getRuleContext(SetContext.class,i);
		}
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_operation);
		try {
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				set();
				setState(30);
				match(T__0);
				setState(31);
				set();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				set();
				setState(34);
				match(T__1);
				setState(35);
				set();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(37);
				set();
				setState(38);
				match(T__2);
				setState(39);
				set();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(41);
				match(T__3);
				setState(42);
				operation();
				setState(43);
				match(T__4);
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

	public static class Var_defineContext extends ParserRuleContext {
		public TerminalNode VARRIABLE() { return getToken(CalculadoraParser.VARRIABLE, 0); }
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public Var_defineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_define; }
	}

	public final Var_defineContext var_define() throws RecognitionException {
		Var_defineContext _localctx = new Var_defineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_var_define);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(VARRIABLE);
			setState(48);
			match(T__5);
			setState(49);
			set();
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

	public static class SetContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode VARRIABLE() { return getToken(CalculadoraParser.VARRIABLE, 0); }
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_set);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				match(T__6);
				setState(55);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(52);
					value();
					setState(53);
					match(T__7);
					}
					break;
				}
				setState(57);
				value();
				setState(58);
				match(T__8);
				}
				break;
			case VARRIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(VARRIABLE);
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

	public static class ValueContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(CalculadoraParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(CalculadoraParser.WORD, i);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(63);
				match(WORD);
				}
				}
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORD );
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\20G\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\7\2\20\n\2\f\2\16\2\23\13\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\36\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\60\n\4\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\5\6:\n\6\3\6\3\6\3\6\3\6\5\6@\n\6\3\7\6\7C\n\7\r\7\16\7D"+
		"\3\7\2\2\b\2\4\6\b\n\f\2\2\2I\2\21\3\2\2\2\4\35\3\2\2\2\6/\3\2\2\2\b\61"+
		"\3\2\2\2\n?\3\2\2\2\fB\3\2\2\2\16\20\5\4\3\2\17\16\3\2\2\2\20\23\3\2\2"+
		"\2\21\17\3\2\2\2\21\22\3\2\2\2\22\24\3\2\2\2\23\21\3\2\2\2\24\25\7\2\2"+
		"\3\25\3\3\2\2\2\26\27\5\6\4\2\27\30\7\17\2\2\30\36\3\2\2\2\31\32\5\b\5"+
		"\2\32\33\7\17\2\2\33\36\3\2\2\2\34\36\7\17\2\2\35\26\3\2\2\2\35\31\3\2"+
		"\2\2\35\34\3\2\2\2\36\5\3\2\2\2\37 \5\n\6\2 !\7\3\2\2!\"\5\n\6\2\"\60"+
		"\3\2\2\2#$\5\n\6\2$%\7\4\2\2%&\5\n\6\2&\60\3\2\2\2\'(\5\n\6\2()\7\5\2"+
		"\2)*\5\n\6\2*\60\3\2\2\2+,\7\6\2\2,-\5\6\4\2-.\7\7\2\2.\60\3\2\2\2/\37"+
		"\3\2\2\2/#\3\2\2\2/\'\3\2\2\2/+\3\2\2\2\60\7\3\2\2\2\61\62\7\16\2\2\62"+
		"\63\7\b\2\2\63\64\5\n\6\2\64\t\3\2\2\2\659\7\t\2\2\66\67\5\f\7\2\678\7"+
		"\n\2\28:\3\2\2\29\66\3\2\2\29:\3\2\2\2:;\3\2\2\2;<\5\f\7\2<=\7\13\2\2"+
		"=@\3\2\2\2>@\7\16\2\2?\65\3\2\2\2?>\3\2\2\2@\13\3\2\2\2AC\7\r\2\2BA\3"+
		"\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\r\3\2\2\2\b\21\35/9?D";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}