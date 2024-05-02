package main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;


    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double frameTime = 1000000000.0 / FPS_SET, lastCheck = System.currentTimeMillis();
        long lastTime = 0;
        long now;
        int frames = 0 ;

        while(true){
            now = System.nanoTime();
            if(now - lastTime >= frameTime){
                gamePanel.repaint();
                lastTime = now;
                frames++;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                lastCheck = System.currentTimeMillis();
            }
        }

    }
}
