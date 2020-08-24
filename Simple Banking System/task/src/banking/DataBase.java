package banking;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

class DataBase {

    private String nameDB = "CreditCard.db";

    DataBase() {
        if (Arguments.getNameDB() != null) {
            nameDB = Arguments.getNameDB();
        }
        deleteDataBase();
        createNewTable();
    }

    void setNameDB(String nameDB) {
        this.nameDB = nameDB;
    }

    private Connection establishConnection(String nameDB) {
        String urlDB = "jdbc:sqlite:" + nameDB;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(urlDB);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    private void createNewTable() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS card (" +
                "id INTEGER PRIMARY KEY, " +
                "number TEXT NOT NULL, " +
                "pin TEXT NOT NULL, " +
                "balance INTEGER DEFAULT 0);";

        try (Connection connection = establishConnection(nameDB);
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteDataBase() {
        try {
            Files.deleteIfExists(Path.of(nameDB));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void createCreditCard(String number, String pin) {
        String sqlQuery = "INSERT INTO card(number, pin) VALUES(?, ?)";

        try (Connection connection = establishConnection(nameDB);
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

            pstmt.setString(1, number);
            pstmt.setString(2, pin);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void deleteCreditCard(int id) {
        String sqlQuery = "DELETE FROM card WHERE id = (?)";

        try (Connection connection = establishConnection(nameDB);
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    int getCreditCardID(String number, String pin) {
        String sqlQuery = "SELECT id " +
                "FROM card " +
                "WHERE number = (?) " +
                "AND pin = (?);";

        int id = 0;
        try (Connection connection = establishConnection(nameDB);
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {
            pstmt.setString(1, number);
            pstmt.setString(2, pin);

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    int getCreditCardID(String number) {
        String sqlQuery = "SELECT id " +
                "FROM card " +
                "WHERE number = (?)";

        int id = 0;
        try (Connection connection = establishConnection(nameDB);
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {
            pstmt.setString(1, number);

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    int getBalance(int id) {
        String sqlQuery = "SELECT balance " +
                "FROM card " +
                "WHERE id = (?);";

        int balance = 0;
        try (Connection connection = establishConnection(nameDB);
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            balance = resultSet.getInt("balance");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return balance;
    }

    void addToBalance(int id, int money) {
        String sqlQuery = "UPDATE card " +
                "SET balance = balance + ? " +
                "WHERE id = ?;";

        try (Connection connection = establishConnection(nameDB);
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

            pstmt.setInt(1, money);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

}
