// Generated from /home/escaleira/Desktop/Compiladores/pratica2/ex9/Calculadora.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculadoraLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		COMMENT=10, WORD=11, VARRIABLE=12, NEWLINE=13, WS=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"COMMENT", "WORD", "VARRIABLE", "NEWLINE", "WS"
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


	public CalculadoraLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Calculadora.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20Q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\7\13\66"+
		"\n\13\f\13\16\139\13\13\3\13\3\13\3\13\3\13\3\f\5\f@\n\f\3\f\6\fC\n\f"+
		"\r\f\16\fD\3\r\6\rH\n\r\r\r\16\rI\3\16\3\16\3\17\3\17\3\17\3\17\3\67\2"+
		"\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\3\2\7\4\2--\61\61\3\2\62;\3\2C\\\4\2\f\f\17\17\4\2\13\13\"\"\2T\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\5!\3\2\2\2\7#\3\2\2"+
		"\2\t%\3\2\2\2\13\'\3\2\2\2\r)\3\2\2\2\17+\3\2\2\2\21-\3\2\2\2\23/\3\2"+
		"\2\2\25\61\3\2\2\2\27?\3\2\2\2\31G\3\2\2\2\33K\3\2\2\2\35M\3\2\2\2\37"+
		" \7-\2\2 \4\3\2\2\2!\"\7^\2\2\"\6\3\2\2\2#$\7(\2\2$\b\3\2\2\2%&\7*\2\2"+
		"&\n\3\2\2\2\'(\7+\2\2(\f\3\2\2\2)*\7?\2\2*\16\3\2\2\2+,\7}\2\2,\20\3\2"+
		"\2\2-.\7.\2\2.\22\3\2\2\2/\60\7\177\2\2\60\24\3\2\2\2\61\62\7/\2\2\62"+
		"\63\7/\2\2\63\67\3\2\2\2\64\66\13\2\2\2\65\64\3\2\2\2\669\3\2\2\2\678"+
		"\3\2\2\2\67\65\3\2\2\28:\3\2\2\29\67\3\2\2\2:;\7\f\2\2;<\3\2\2\2<=\b\13"+
		"\2\2=\26\3\2\2\2>@\t\2\2\2?>\3\2\2\2?@\3\2\2\2@B\3\2\2\2AC\t\3\2\2BA\3"+
		"\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\30\3\2\2\2FH\t\4\2\2GF\3\2\2\2H"+
		"I\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\32\3\2\2\2KL\t\5\2\2L\34\3\2\2\2MN\t\6"+
		"\2\2NO\3\2\2\2OP\b\17\2\2P\36\3\2\2\2\7\2\67?DI\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}