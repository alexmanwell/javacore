package calculator;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
public class LexerTest {

  private Lexer lexer;

  @Before
  public void setUp() {
    lexer = new Lexer();
  }

  @Test
  public void testTokenizeInteger() {
    List<Token> tokens = lexer.tokenize("23");
    assertEquals(1, tokens.size());
    assertEquals(new Token(TokenType.INTEGER, "23"), tokens.get(0));
  }

  @Test
  public void testTokenizeDouble() {
    List<Token> tokens = lexer.tokenize("2.3");
    assertEquals(1, tokens.size());
    assertEquals(new Token(TokenType.DOUBLE, "2.3"), tokens.get(0));
  }

  @Test
  public void testTokenizeFuncSIN() {
    List<Token> tokens = lexer.tokenize("sin");
    assertEquals(1, tokens.size());
    assertEquals(new Token(TokenType.IDENTIFIER, "sin"), tokens.get(0));
  }

  @Test
  public void testTokenizeFuncSIN1() {
    List<Token> tokens = lexer.tokenize("sin(1)");
    assertEquals(4, tokens.size());
    assertEquals(new Token(TokenType.IDENTIFIER, "sin"), tokens.get(0));
    assertEquals(new Token(TokenType.OPEN_PARENTHESIS, ""), tokens.get(1));
    assertEquals(new Token(TokenType.INTEGER, "1"), tokens.get(2));
    assertEquals(new Token(TokenType.CLOSE_PARENTHESIS, ""), tokens.get(3));
  }

  @Test
  public void testTokenizeFuncSIN1PLUS2() {
    List<Token> tokens = lexer.tokenize("sin(1 + 2)");
    assertEquals(6, tokens.size());
    assertEquals(new Token(TokenType.IDENTIFIER, "sin"), tokens.get(0));
    assertEquals(new Token(TokenType.OPEN_PARENTHESIS, ""), tokens.get(1));
    assertEquals(new Token(TokenType.INTEGER, "1"), tokens.get(2));
    assertEquals(new Token(TokenType.PLUS, ""), tokens.get(3));
    assertEquals(new Token(TokenType.INTEGER, "2"), tokens.get(4));
    assertEquals(new Token(TokenType.CLOSE_PARENTHESIS, ""), tokens.get(5));
  }

  @Test
  public void testTokenizeFuncCOS() {
    List<Token> tokens = lexer.tokenize("cos");
    assertEquals(1, tokens.size());
    assertEquals(new Token(TokenType.IDENTIFIER, "cos"), tokens.get(0));
  }

  @Test
  public void testTokenizeFuncTAN() {
    List<Token> tokens = lexer.tokenize("tan");
    assertEquals(1, tokens.size());
    assertEquals(new Token(TokenType.IDENTIFIER, "tan"), tokens.get(0));
  }

  @Test
  public void testTokenizeVariableAssignNumber() {
    List<Token> tokens = lexer.tokenize("x = 1");
    assertEquals(3, tokens.size());
    assertEquals(new Token(TokenType.IDENTIFIER, "x"), tokens.get(0));
    assertEquals(new Token(TokenType.ASSIGN, ""), tokens.get(1));
    assertEquals(new Token(TokenType.INTEGER, "1"), tokens.get(2));
  }

  @Test
  public void testTokenizeFuncDecl_X_Y() {
    List<Token> tokens = lexer.tokenize("f(x, y)");
    assertEquals(6, tokens.size());
    assertEquals(new Token(TokenType.IDENTIFIER, "f"), tokens.get(0));
    assertEquals(new Token(TokenType.OPEN_PARENTHESIS, ""), tokens.get(1));
    assertEquals(new Token(TokenType.IDENTIFIER, "x"), tokens.get(2));
    assertEquals(new Token(TokenType.COMMA, ""), tokens.get(3));
    assertEquals(new Token(TokenType.IDENTIFIER, "y"), tokens.get(4));
    assertEquals(new Token(TokenType.CLOSE_PARENTHESIS, ""), tokens.get(5));
  }
}