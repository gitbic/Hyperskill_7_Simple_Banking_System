package banking;

import java.util.Scanner;

class CreditCardManage {

    private Scanner scanner = new Scanner(System.in);
    private DataBase dataBase = new DataBase();
    private int currentCreditCardID = 0;

    void createCCAccount() {
        CreditCard creditCard = new CreditCard();
        dataBase.createCreditCard(creditCard.getCardNumber(), creditCard.getCardPinCode());

        System.out.println("Your card has been created");
        System.out.printf("Your card number:%n%s%n", creditCard.getCardNumber());
        System.out.printf("Your card PIN:%n%s%n", creditCard.getCardPinCode());
        System.out.println();
    }

    void closeCCAccount() {
        dataBase.deleteCreditCard(currentCreditCardID);
        System.out.println("The account has been closed!" + System.lineSeparator());
    }

    boolean logIntoCCAccount() {
        System.out.println("Enter your card number:");
        String inputCardNumber = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String inputCardPinCode = scanner.nextLine();
        System.out.println();

        boolean isLogged = false;
        currentCreditCardID = dataBase.getCreditCardID(inputCardNumber, inputCardPinCode);

        if (currentCreditCardID > 0) {
            isLogged = true;
            System.out.println("You have successfully logged in!");
        } else {
            System.out.println("Wrong card number or PIN!");
        }

        System.out.println();
        return isLogged;
    }

    void logOutCCAccount() {
        System.out.println("You have successfully logged out!" + System.lineSeparator());
        currentCreditCardID = 0;
    }

    void showBalanceCC() {
        int balance = dataBase.getBalance(currentCreditCardID);
        System.out.printf("Balance: %s%n%n", balance);
    }

    void addIncomeToCC() {
        while (true) {
            System.out.println("Enter income:");
            String incomeMoney = scanner.nextLine();
            if (incomeMoney.matches("[0-9]{1,10}")) {
                dataBase.addToBalance(currentCreditCardID, Integer.parseInt(incomeMoney));
                System.out.println("Income was added!" + System.lineSeparator());
                break;
            } else {
                System.out.println("Incorrect income" + System.lineSeparator());
            }
        }
    }

    void makeTransfer() {
        CreditCard creditCard = new CreditCard();

        System.out.println();
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        String inputCardNumber = scanner.nextLine();

        // check for Luhn algorithm
        if (!creditCard.isCheckSum(inputCardNumber)) {
            System.out.println("Probably you made mistake in the card number. Please try again!"
                    + System.lineSeparator());
            return;
        }

        // checking existing credit card
        int targetCreditCardID = dataBase.getCreditCardID(inputCardNumber);
        if (targetCreditCardID == 0) {
            System.out.println("Such a card does not exist." + System.lineSeparator());
            return;
        }

        // checking same account
        if (targetCreditCardID == currentCreditCardID) {
            System.out.println("You can't transfer money to the same account!" + System.lineSeparator());
            return;
        }

        System.out.println("Enter how much money you want to transfer:" + System.lineSeparator());
        String transferSumStr = scanner.nextLine();

        // check correct transfer sum
        if (!transferSumStr.matches("[0-9]{1,10}")) {
            System.out.println("Incorrect transfer of the amount!" + System.lineSeparator());
            return;
        }

        int transferSum = Integer.parseInt(transferSumStr);

        // check enough money
        if (transferSum > dataBase.getBalance(currentCreditCardID)) {
            System.out.println("Not enough money!" + System.lineSeparator());
            return;
        }

        // make transfer
        dataBase.addToBalance(currentCreditCardID, -transferSum);
        dataBase.addToBalance(targetCreditCardID, transferSum);
        System.out.println("Success!" + System.lineSeparator());
    }
}
