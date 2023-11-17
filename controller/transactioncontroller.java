package controller;

import model.transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class transactioncontroller {
    private Connection connection;

    public transactioncontroller() {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db_uas", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<transaction> getUserTransactions(int userId) {
        List<transaction> transactionsList = new ArrayList<>();

        try {
            String query = "SELECT Transactions.id, Transactions.user_id, Users.name AS user_name, " +
                    "Transactions.game_id, Games.name AS game_name, Games.price AS total_price " +
                    "FROM Transactions " +
                    "INNER JOIN Users ON Transactions.user_id = Users.id " +
                    "INNER JOIN Games ON Transactions.game_id = Games.id " +
                    "WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int gameId = resultSet.getInt("game_id");
                String gameName = resultSet.getString("game_name");
                int totalPrice = resultSet.getInt("total_price");

                transaction transaction = new transaction(id, userId, gameId);
                transactionsList.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionsList;
    }
}
