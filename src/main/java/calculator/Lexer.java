package calculator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lexer implements Iterator<Token> {

  private static final String OPERATOR_CHARS = "=^+-*/()&|,";
  private static final String POINT_CHARS = ".";
  private static final TokenType[] OPERATOR_TOKENS = {
          TokenType.ASSIGN,
          TokenType.POWER,
          TokenType.PLUS,
          TokenType.MINUS,
          TokenType.STAR,
          TokenType.SLASH,
          TokenType.OPEN_PARENTHESIS,
          TokenType.CLOSE_PARENTHESIS,
          TokenType.BITWISE_AND,
          TokenType.BITWISE_OR,
          TokenType.COMMA,
  };

  private String input;

  private final List<Token> tokens;
  private int index;

  public Lexer() {
    tokens = new ArrayList<>();
  }

  public List<Token> tokenize(String input) {
    this.input = input;
    while ( hasNextSymbol()) {
      final char current = currentSymbol();
      if (Character.isDigit(current)) {
        tokenizeNumber();
      } else if (Character.isLetter(current)) {
        tokenizeVariable();
      } else if ( OPERATOR_CHARS.indexOf(current) != -1 ) {
        tokenizeOperator();
      } else {
        nextSymbol();
      }
    }
    return tokens;
  }

  private void tokenizeOperator() {
    final int position = OPERATOR_CHARS.indexOf(currentSymbol());
    addToken(OPERATOR_TOKENS[position]);
    nextSymbol();
  }

  public void tokenizeNumber() {
    final StringBuilder buffer = new StringBuilder();
    boolean isDouble = false;
    char current = currentSymbol();
    while (Character.isDigit(current)) {
      buffer.append(current);
      current = nextSymbol();
    }
    if (isPoint(current)) {
      buffer.append(current);
      current = nextSymbol();
      while (Character.isDigit(current)) {
        buffer.append(current);
        current = nextSymbol();
      }
      isDouble = true;
    }
    if (isDouble) {
      addToken(TokenType.DOUBLE, buffer.toString());
    } else {
      addToken(TokenType.INTEGER, buffer.toString());
    }
  }

  private boolean isPoint(char current) {

    return (POINT_CHARS.indexOf(current) >= 0
            && current == POINT_CHARS.charAt(0));
  }

  private void tokenizeVariable() {
    final StringBuilder buffer = new StringBuilder();
    char current = currentSymbol();
    while (Character.isLetter(current)) {
      buffer.append(current);
      current = nextSymbol();
    }

    addToken(TokenType.IDENTIFIER,  buffer.toString());
  }

  private boolean hasNextSymbol() {
    return (index <= input.length() - 1 );
  }

  private char nextSymbol() {
    index++;
    return (hasNextSymbol()) ? input.charAt(index) : 0;
  }

  private char currentSymbol() {
    return input.charAt(index);
  }

  private void addToken(TokenType type) {
    addToken(type, "");
  }

  public void addToken(TokenType type, String text) {
    tokens.add(new Token(type, text));
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Token next() {
    return null;
  }
}