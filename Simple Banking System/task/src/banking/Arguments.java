package banking;

class Arguments {
    static private String nameDB;

    void setArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-fileName":
                    nameDB = args[i + 1];
                    break;
            }
        }
    }

    static String getNameDB() {
        return nameDB;
    }
}
