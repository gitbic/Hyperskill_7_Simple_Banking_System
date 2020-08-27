package banking;

import java.util.Random;

class CreditCard {
    private String cardNumber;
    private String cardPinCode;
    private int cardBalance;
    private Random rnd = new Random();
    private StringBuilder sb = new StringBuilder();

    CreditCard() {
        generateCardNumber();
        generateCardPinCode();
        cardBalance = 0;
    }

    private void generateCardNumber() {
        rnd.ints(9, 0, 9).forEach(sb::append);
        String cn = "400000" + sb.toString();
        sb.setLength(0);
        int checkSum = createCheckSum(cn);
        this.cardNumber = cn + checkSum;
    }

    private void generateCardPinCode() {
        rnd.ints(4, 0, 9).forEach(sb::append);
        this.cardPinCode = sb.toString();
    }

    private int createCheckSum(String str) {
        int sum = 0;

        for (int i = 0; i < 15; i++) {
            int num = Character.getNumericValue(str.charAt(i));
            if (i % 2 == 0) {
                num = num * 2 % 9;
            }
            sum += num;
        }
        return (10 - sum % 10) % 10;
    }

    boolean isCheckSum(String cardNumber) {
        if (cardNumber.toCharArray().length != 16) {
            return false;
        }
        int lastNumber = Integer.parseInt(cardNumber.substring(15));
        int checkSum = createCheckSum(cardNumber);
        return lastNumber == checkSum;
    }

    String getCardNumber() {
        return cardNumber;
    }

    String getCardPinCode() {
        return cardPinCode;
    }

    int getCardBalance() {
        return cardBalance;
    }
}
