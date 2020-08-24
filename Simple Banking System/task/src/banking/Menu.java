package banking;

import java.util.LinkedHashMap;
import java.util.Map;

abstract class Menu {
    Map<Integer, String> menuMap = new LinkedHashMap<>();
    private String menuString;

    Menu() {
        collectMenu();
        createMenu();
    }

    private void createMenu() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : menuMap.entrySet()) {
            sb.append(entry.getKey())
                    .append(". ")
                    .append(entry.getValue())
                    .append(System.lineSeparator());
        }
        menuString = sb.toString();
    }

    abstract void collectMenu();

    void printMenu() {
        System.out.println(menuString);
    }
}
