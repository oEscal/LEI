// Generated from /home/escaleira/Desktop/Compiladores/pratica2/ex1/Hello.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HelloParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, ID=3, WS=4;
	public static final int
		RULE_greetings = 0, RULE_hello = 1, RULE_bye = 2;
	public static final String[] ruleNames = {
		"greetings", "hello", "bye"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'hello'", "'bye'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "ID", "WS"
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
	public String getGrammarFileName() { return "Hello.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HelloParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class GreetingsContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(HelloParser.EOF, 0); }
		public List<HelloContext> hello() {
			return getRuleContexts(HelloContext.class);
		}
		public HelloContext hello(int i) {
			return getRuleContext(HelloContext.class,i);
		}
		public List<ByeContext> bye() {
			return getRuleContexts(ByeContext.class);
		}
		public ByeContext bye(int i) {
			return getRuleContext(ByeContext.class,i);
		}
		public GreetingsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_greetings; }
	}

	public final GreetingsContext greetings() throws RecognitionException {
		GreetingsContext _localctx = new GreetingsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_greetings);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(8);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(6);
					hello();
					}
					break;
				case T__1:
					{
					setState(7);
					bye();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(10); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 || _la==T__1 );
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

	public static class HelloContext extends ParserRuleContext {
		public Token ID;
		public List<TerminalNode> ID() { return getTokens(HelloParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(HelloParser.ID, i);
		}
		public HelloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hello; }
	}

	public final HelloContext hello() throws RecognitionException {
		HelloContext _localctx = new HelloContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_hello);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(T__0);

			   System.out.print("Olá ");

			setState(18); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16);
				((HelloContext)_localctx).ID = match(ID);

				   System.out.print((((HelloContext)_localctx).ID!=null?((HelloContext)_localctx).ID.getText():null) + " ");

				}
				}
				setState(20); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );

			   System.out.println();

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

	public static class ByeContext extends ParserRuleContext {
		public Token ID;
		public List<TerminalNode> ID() { return getTokens(HelloParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(HelloParser.ID, i);
		}
		public ByeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bye; }
	}

	public final ByeContext bye() throws RecognitionException {
		ByeContext _localctx = new ByeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bye);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(T__1);

			   System.out.print("Bá xau ");

			setState(28); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				((ByeContext)_localctx).ID = match(ID);

				   System.out.print((((ByeContext)_localctx).ID!=null?((ByeContext)_localctx).ID.getText():null) + " ");

				}
				}
				setState(30); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );

			   System.out.println();

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\6%\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\6\2\13\n\2\r\2\16\2\f\3\2\3\2\3\3\3\3\3\3\3\3\6\3\25"+
		"\n\3\r\3\16\3\26\3\3\3\3\3\4\3\4\3\4\3\4\6\4\37\n\4\r\4\16\4 \3\4\3\4"+
		"\3\4\2\2\5\2\4\6\2\2\2%\2\n\3\2\2\2\4\20\3\2\2\2\6\32\3\2\2\2\b\13\5\4"+
		"\3\2\t\13\5\6\4\2\n\b\3\2\2\2\n\t\3\2\2\2\13\f\3\2\2\2\f\n\3\2\2\2\f\r"+
		"\3\2\2\2\r\16\3\2\2\2\16\17\7\2\2\3\17\3\3\2\2\2\20\21\7\3\2\2\21\24\b"+
		"\3\1\2\22\23\7\5\2\2\23\25\b\3\1\2\24\22\3\2\2\2\25\26\3\2\2\2\26\24\3"+
		"\2\2\2\26\27\3\2\2\2\27\30\3\2\2\2\30\31\b\3\1\2\31\5\3\2\2\2\32\33\7"+
		"\4\2\2\33\36\b\4\1\2\34\35\7\5\2\2\35\37\b\4\1\2\36\34\3\2\2\2\37 \3\2"+
		"\2\2 \36\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"#\b\4\1\2#\7\3\2\2\2\6\n\f\26 ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}