package main;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(GamePanel gamePanel){
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(gamePanel);
        setVisible(true);
    }
}
