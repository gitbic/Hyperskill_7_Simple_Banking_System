package banking;

import java.util.Scanner;

class MenuWalk {

    private Menu mainMenu = new MainMenu();
    private Menu accountMenu = new AccountMenu();
    private ProcessState processState = ProcessState.POWER_ON;
    private Scanner scanner = new Scanner(System.in);
    private CreditCardManage creditCardManage = new CreditCardManage();

    void walk() {
        while (true) {
            switch (processState) {

                case POWER_ON:
                    processState = ProcessState.MAIN_MENU;
                    break;

                case POWER_OFF:
                    System.out.println("Bye!");
                    System.exit(0);
                    break;

                case MAIN_MENU:
                    mainMenu.printMenu();
                    processState = ProcessState.CHOOSE_MAIN_MENU_ACTION;
                    break;

                case ACCOUNT_MENU:
                    accountMenu.printMenu();
                    processState = ProcessState.CHOOSE_ACCOUNT_MENU_ACTION;
                    break;

                case CHOOSE_MAIN_MENU_ACTION:
                    chooseMainMenuAction();
                    break;

                case CHOOSE_ACCOUNT_MENU_ACTION:
                    chooseAccountMenuAction();
                    break;

                case CREATE_CREDIT_CARD_ACCOUNT:
                    creditCardManage.createCCAccount();
                    processState = ProcessState.MAIN_MENU;
                    break;

                case CLOSE_ACCOUNT:
                    creditCardManage.closeCCAccount();
                    processState = ProcessState.MAIN_MENU;
                    break;

                case LOG_INTO_ACCOUNT:
                    if (creditCardManage.logIntoCCAccount()) {
                        processState = ProcessState.ACCOUNT_MENU;
                    } else {
                        processState = ProcessState.MAIN_MENU;
                    }
                    break;

                case LOG_OUT_ACCOUNT:
                    creditCardManage.logOutCCAccount();
                    processState = ProcessState.MAIN_MENU;
                    break;

                case SHOW_BALANCE:
                    creditCardManage.showBalanceCC();
                    processState = ProcessState.ACCOUNT_MENU;
                    break;

                case ADD_INCOME:
                    creditCardManage.addIncomeToCC();
                    processState = ProcessState.ACCOUNT_MENU;
                    break;

                case DO_TRANSFER:
                    creditCardManage.makeTransfer();
                    processState = ProcessState.ACCOUNT_MENU;
                    break;
            }
        }
    }

    private void chooseMainMenuAction() {
        String menuItem = scanner.next();
        System.out.println();

        switch (menuItem) {
            case "0":
                processState = ProcessState.POWER_OFF;
                break;
            case "1":
                processState = ProcessState.CREATE_CREDIT_CARD_ACCOUNT;
                break;
            case "2":
                processState = ProcessState.LOG_INTO_ACCOUNT;
                break;
            default:
                System.out.println("Invalid menu item." + System.lineSeparator());
                processState = ProcessState.MAIN_MENU;
                break;
        }
    }

    private void chooseAccountMenuAction() {
        String menuItem = scanner.next();
        System.out.println();

        switch (menuItem) {
            case "0":
                processState = ProcessState.POWER_OFF;
                break;
            case "1":
                processState = ProcessState.SHOW_BALANCE;
                break;
            case "2":
                processState = ProcessState.ADD_INCOME;
                break;
            case "3":
                processState = ProcessState.DO_TRANSFER;
                break;
            case "4":
                processState = ProcessState.CLOSE_ACCOUNT;
                break;
            case "5":
                processState = ProcessState.LOG_OUT_ACCOUNT;
                break;
            default:
                System.out.println("Invalid menu item." + System.lineSeparator());
                processState = ProcessState.ACCOUNT_MENU;
                break;
        }
    }
}

