package algorithms;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// http://acm.timus.ru/problem.aspx?space=1&num=1427

public class SMS {

    private int lenghtSMS;
    private int latinLenghtSMS;
    private String textSMS;


    public SMS(int lenghtSMS, int latinLenghtSMS, String textSMS) {
        this.lenghtSMS = lenghtSMS;
        this.latinLenghtSMS = latinLenghtSMS;
        this.textSMS = textSMS;
    }

    public int send() {
        int countSendSMS = 0;
        int countSymbols = 0;
        boolean isLatin = false;
        char[] symbols = textSMS.toCharArray();
        for (int i = 0; i < symbols.length; i++) {
            char symbol = symbols[i];
            countSymbols++;
            if (!isLatinSymbol(symbol)) {
                isLatin = true;
            } else if ((countSymbols) % (latinLenghtSMS) == 0) {
                countSendSMS++;
                countSymbols = 0;
            } else if (i == symbols.length - 1) {
                countSendSMS++;
            }

            if (isLatin && countSymbols <= lenghtSMS) {
                countSendSMS++;
                i = i + lenghtSMS - countSymbols;
                countSymbols = 0;
                isLatin = false;
            }
            if (isLatin && countSymbols > lenghtSMS) {
                countSendSMS++;
                countSendSMS++;
                i = i + lenghtSMS - 1;
                countSymbols = 0;
                isLatin = false;
            }
        }

        return countSendSMS;
    }

    private boolean isLatinSymbol(char symbol) {
        String symbols = String.valueOf(symbol);
        Pattern pattern = Pattern.compile("[a-zA-Z\\s]");
        Matcher matcher = pattern.matcher(symbols);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int lenghtSMS = scanner.nextInt();
        int latinLenghtSMS = scanner.nextInt();
        String textSMS = scanner.nextLine().trim();

        SMS sms = new SMS(lenghtSMS, latinLenghtSMS, textSMS);

        int countSMS = sms.send();
        System.out.println(countSMS);
    }
}
