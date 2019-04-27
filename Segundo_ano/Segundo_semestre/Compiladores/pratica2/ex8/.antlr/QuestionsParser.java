// Generated from /home/escaleira/Desktop/Compiladores/pratica2/ex8/Questions.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuestionsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, COMMENT=8, NUMBER=9, 
		WORD=10, WS=11;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_id = 2, RULE_question = 3, RULE_answer = 4;
	public static final String[] ruleNames = {
		"program", "stat", "id", "question", "answer"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "'\"'", "')'", "'{'", "'}'", "':'", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "COMMENT", "NUMBER", "WORD", 
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
	public String getGrammarFileName() { return "Questions.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QuestionsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(QuestionsParser.EOF, 0); }
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
			setState(13);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WORD) {
				{
				{
				setState(10);
				stat();
				}
				}
				setState(15);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(16);
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
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public List<AnswerContext> answer() {
			return getRuleContexts(AnswerContext.class);
		}
		public AnswerContext answer(int i) {
			return getRuleContext(AnswerContext.class,i);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			id();
			setState(19);
			match(T__0);
			setState(20);
			match(T__1);
			setState(21);
			question();
			setState(22);
			match(T__1);
			setState(23);
			match(T__2);
			setState(24);
			match(T__3);
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(25);
				answer();
				}
				}
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(31);
			match(T__4);
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

	public static class IdContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(QuestionsParser.WORD, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(WORD);
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

	public static class QuestionContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(QuestionsParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(QuestionsParser.WORD, i);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WORD) {
				{
				{
				setState(35);
				match(WORD);
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class AnswerContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(QuestionsParser.NUMBER, 0); }
		public List<TerminalNode> WORD() { return getTokens(QuestionsParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(QuestionsParser.WORD, i);
		}
		public AnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer; }
	}

	public final AnswerContext answer() throws RecognitionException {
		AnswerContext _localctx = new AnswerContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__1);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WORD) {
				{
				{
				setState(42);
				match(WORD);
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			match(T__1);
			setState(49);
			match(T__5);
			setState(50);
			match(NUMBER);
			setState(51);
			match(T__6);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\r8\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\7\2\16\n\2\f\2\16\2\21\13\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\35\n\3\f\3\16\3 \13\3\3\3\3\3\3\4\3\4"+
		"\3\5\7\5\'\n\5\f\5\16\5*\13\5\3\6\3\6\7\6.\n\6\f\6\16\6\61\13\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\2\2\7\2\4\6\b\n\2\2\2\66\2\17\3\2\2\2\4\24\3\2\2\2"+
		"\6#\3\2\2\2\b(\3\2\2\2\n+\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16\21\3\2\2"+
		"\2\17\r\3\2\2\2\17\20\3\2\2\2\20\22\3\2\2\2\21\17\3\2\2\2\22\23\7\2\2"+
		"\3\23\3\3\2\2\2\24\25\5\6\4\2\25\26\7\3\2\2\26\27\7\4\2\2\27\30\5\b\5"+
		"\2\30\31\7\4\2\2\31\32\7\5\2\2\32\36\7\6\2\2\33\35\5\n\6\2\34\33\3\2\2"+
		"\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37!\3\2\2\2 \36\3\2\2\2!\""+
		"\7\7\2\2\"\5\3\2\2\2#$\7\f\2\2$\7\3\2\2\2%\'\7\f\2\2&%\3\2\2\2\'*\3\2"+
		"\2\2(&\3\2\2\2()\3\2\2\2)\t\3\2\2\2*(\3\2\2\2+/\7\4\2\2,.\7\f\2\2-,\3"+
		"\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61/\3\2\2\2\62"+
		"\63\7\4\2\2\63\64\7\b\2\2\64\65\7\13\2\2\65\66\7\t\2\2\66\13\3\2\2\2\6"+
		"\17\36(/";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}