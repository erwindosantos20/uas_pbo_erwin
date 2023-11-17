package controller;

import model.games;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class gamecontroller {
    private Connection connection;

    public gamecontroller() {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db_uas", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<games> getGamesList() {
        List<games> gamesList = new ArrayList<>();

        try {
            String query = "SELECT * FROM Games";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                int price = resultSet.getInt("price");

                games game = new games(id, name, genre, price);
                gamesList.add(game);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gamesList;
    }
}
