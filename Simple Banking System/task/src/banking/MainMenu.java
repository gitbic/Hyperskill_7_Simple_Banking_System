package banking;

class MainMenu extends Menu {

    @Override
    void collectMenu() {
        menuMap.put(1, "Create an account");
        menuMap.put(2, "Log into account");
        menuMap.put(0, "Exit");
    }
}
