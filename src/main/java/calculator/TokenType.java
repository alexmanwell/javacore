package calculator;

public enum TokenType {

  INTEGER,
  DOUBLE,

  POWER,
  PLUS,
  MINUS,
  STAR,
  SLASH,
  OPEN_PARENTHESIS,
  CLOSE_PARENTHESIS,
  BITWISE_AND,
  BITWISE_OR,
  ASSIGN,

  IDENTIFIER,
  COMMA,

  END_OF_FILE;
}