package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;

    public Game(){
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses(){
         player = new Player(200, 200);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
         player.update();
    }

    public void render(Graphics g){
        player.render(g);
    }

    @Override
    public void run() {
        double frameTime = 1000000000.0 / FPS_SET;
        double updateTime = 1000000000.0 / UPS_SET;

        double lastCheck = System.currentTimeMillis();
        long now;
        int frames = 0 ;

        long previousTime = System.nanoTime();
        double deltaU = 0;
        double deltaF = 0;
        int updates = 0;

        while(true){
            now = System.nanoTime();

            deltaU += (now - previousTime) / updateTime;
            deltaF += (now - previousTime) / frameTime;
            previousTime = now;

            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            if(deltaF >= 1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                System.out.println("FPS: " + frames + " | Updates: " + updates);
                frames = 0;
                updates = 0;
                lastCheck = System.currentTimeMillis();
            }
        }

    }

    public Player getPlayer(){
        return player;
    }

    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }
}
