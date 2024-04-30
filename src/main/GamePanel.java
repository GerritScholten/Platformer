package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int xDelta = 100, yDelta = 100, frames = 0;
    private long lastCheck = 0;

    GamePanel(){
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(xDelta, yDelta, 200, 50);

        frames++;
        if (System.currentTimeMillis() - lastCheck >= 1000){
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }

        repaint();
    }
    public void changeXDelta(int value) {
        xDelta += value;
        repaint();
    }
    public void changeYDelta(int value) {
        yDelta += value;
        repaint();
    }
    public void setRectPosition(int x, int y){
        xDelta = x;
        yDelta = y;
        repaint();
    }
}
