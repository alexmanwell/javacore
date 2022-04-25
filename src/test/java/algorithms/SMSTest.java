package algorithms;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SMSTest {

  @Test
  public void helloWorldSMSTest() {
    int lenghtSMS = 1;
    int latinLenghtSMS = 2;
    String textSMS = "Hello World!";

    SMS sms = new SMS(lenghtSMS, latinLenghtSMS, textSMS);

    int actuals = sms.send();
    int expected = 7;
    assertEquals(expected, actuals);
  }

  @Test
  public void firstSymbolExlamationPointSMSTest() {
    int lenghtSMS = 1;
    int latinLenghtSMS = 2;
    String textSMS = "!Hello World";

    SMS sms = new SMS(lenghtSMS, latinLenghtSMS, textSMS);

    int actuals = sms.send();
    int expected = 7;
    assertEquals(expected, actuals);
  }

  @Test
  public void multiSpaceSMSTest() {
    int lenghtSMS = 10;
    int latinLenghtSMS = 15;
    String textSMS = "                     "; // 21 space;

    SMS sms = new SMS(lenghtSMS, latinLenghtSMS, textSMS);

    int actuals = sms.send();
    int expected = 2;
    assertEquals(expected, actuals);
  }

  @Test
  public void exampleTaskSMSTest() {
    int lenghtSMS = 10;
    int latinLenghtSMS = 15;
    String textSMS = "On the 11-th of February, 2006 the contest \"Timus Top Coders: First Challenge\" is held!";

    SMS sms = new SMS(lenghtSMS, latinLenghtSMS, textSMS);

    int actuals = sms.send();
    int expected = 8;
    assertEquals(expected, actuals);
  }

  @Test
  public void russianTextSMSTest() {
    int lenghtSMS = 10;
    int latinLenghtSMS = 15;
    String textSMS = "Что-то погода сегодня не летная!";

    SMS sms = new SMS(lenghtSMS, latinLenghtSMS, textSMS);

    int actuals = sms.send();
    int expected = 4;
    assertEquals(expected, actuals);
  }
}