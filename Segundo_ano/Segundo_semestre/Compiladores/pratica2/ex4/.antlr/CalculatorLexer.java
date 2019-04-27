// Generated from /home/escaleira/Desktop/Compiladores/pratica2/ex4/Calculator.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, INT=2, NAME=3, NEWLINE=4, WS=5;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "INT", "NAME", "NEWLINE", "WS"
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


	public CalculatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Calculator.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\7\'\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\3\6\3\23\n\3\r\3\16\3"+
		"\24\3\4\6\4\30\n\4\r\4\16\4\31\3\5\5\5\35\n\5\3\5\3\5\3\6\6\6\"\n\6\r"+
		"\6\16\6#\3\6\3\6\2\2\7\3\3\5\4\7\5\t\6\13\7\3\2\5\3\2\62;\4\2C\\c|\4\2"+
		"\13\13\"\"\2*\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\3\r\3\2\2\2\5\22\3\2\2\2\7\27\3\2\2\2\t\34\3\2\2\2\13!\3\2\2\2\r"+
		"\16\7\"\2\2\16\17\7/\2\2\17\20\7\"\2\2\20\4\3\2\2\2\21\23\t\2\2\2\22\21"+
		"\3\2\2\2\23\24\3\2\2\2\24\22\3\2\2\2\24\25\3\2\2\2\25\6\3\2\2\2\26\30"+
		"\t\3\2\2\27\26\3\2\2\2\30\31\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\b"+
		"\3\2\2\2\33\35\7\17\2\2\34\33\3\2\2\2\34\35\3\2\2\2\35\36\3\2\2\2\36\37"+
		"\7\f\2\2\37\n\3\2\2\2 \"\t\4\2\2! \3\2\2\2\"#\3\2\2\2#!\3\2\2\2#$\3\2"+
		"\2\2$%\3\2\2\2%&\b\6\2\2&\f\3\2\2\2\7\2\24\31\34#\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}