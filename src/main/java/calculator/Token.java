package calculator;

public class Token {

  private TokenType type;
  private String text;

  public Token(TokenType type, String text) {
    this.type = type;
    this.text = text;
  }

  public TokenType getType() {
    return type;
  }

  public String getText() {
    return text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Token token = (Token) o;

    return type == token.type && !(text != null ? !text.equals(token.text) : token.text != null);

  }

  @Override
  public int hashCode() {
    int result = type != null ? type.hashCode() : 0;
    result = 31 * result + (text != null ? text.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return type + " " + text;
  }
}