package view;

import controller.gamecontroller;
import controller.usercontroller;
//import controller.transactioncontroller;

import model.games;
import model.user;
//import model.transaction;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListMenu extends JFrame {

    private JTextArea gameListArea;
    private JButton transactionsButton;
    private usercontroller usercontroller;
    private gamecontroller gameController;
    private user loggedInUser;

    public ListMenu(user loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.usercontroller = new usercontroller();
        this.gameController = new gamecontroller();

        setTitle("Game List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGameListMenu();
    }

    private void createGameListMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        transactionsButton = new JButton("Transactions");
        transactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        panel.add(transactionsButton, BorderLayout.NORTH);

        gameListArea = new JTextArea();
        panel.add(new JScrollPane(gameListArea), BorderLayout.CENTER);

        fetchGameList();

        add(panel);
    }

    private void fetchGameList() {
        List<games> gamesList = gameController.getGamesList();

        StringBuilder gameList = new StringBuilder();
        for (games game : gamesList) {
            gameList.append("ID: ").append(game.getId()).append("\n");
            gameList.append("Name: ").append(game.getName()).append("\n");
            gameList.append("Genre: ").append(game.getGenre()).append("\n");
            gameList.append("Price: ").append(game.getPrice()).append("\n");
            gameList.append("----------------------------\n");
        }

        gameListArea.setText(gameList.toString());
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameListMenu(new user(1, "John", "john@example.com", "password")).setVisible(true);
            }
        });
    }
}
