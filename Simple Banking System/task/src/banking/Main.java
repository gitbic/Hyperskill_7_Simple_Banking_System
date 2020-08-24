package banking;

public class Main {
    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        arguments.setArguments(args);

        MenuWalk menuWalk = new MenuWalk();
        menuWalk.walk();

    }
}
