package main;

import entities.Player;
import levels.LevelManager;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public static final int DEFAULT_TILE_SIZE = 64;
    public static final float SCALE = 1.0f;
    public static final int TILES_IN_WIDTH = 15;
    public static final int TILES_IN_HEIGHT = 10 ;
    public static final int TILES_SIZE = (int)(DEFAULT_TILE_SIZE * SCALE);
    public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    private Player player;

    private LevelManager levelManager;

    public Game(){
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses(){
         player = new Player(200, 200);
         levelManager = new LevelManager(this);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
         player.update();
         levelManager.update();
    }

    public void render(Graphics g){
        player.render(g);
         levelManager.draw(g);
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
