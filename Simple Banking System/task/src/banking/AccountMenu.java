package banking;

class AccountMenu extends Menu {

    @Override
    void collectMenu() {
        menuMap.put(1, "Balance");
        menuMap.put(2, "Add income");
        menuMap.put(3, "Do transfer");
        menuMap.put(4, "Close account");
        menuMap.put(5, "Log out");
        menuMap.put(0, "Exit");
    }
}
