package main;

import javax.swing.*;

public class GameWindow extends JFrame {
    private int gameWindowHeight = 500, gameWindowWidth = 600;
    public GameWindow(GamePanel gamePanel){
        setSize(gameWindowWidth, gameWindowHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(gamePanel);
        setVisible(true);
    }
    public int getGameWindowHeight() {
        return gameWindowHeight;
    }
    public int getGameWindowWidth() {
        return gameWindowWidth;
    }
}
