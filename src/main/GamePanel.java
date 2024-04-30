package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100, frames = 0, xDir = .01f, yDir = .015f;
    private long lastCheck = 0;
    private int rectWidth = 200, rectHeight = 50;
    private Color color;
    private Random random = new Random();

    GamePanel(){
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        updateRect();
        g.fillRect((int)xDelta, (int)yDelta, rectWidth, rectHeight);
        repaint();
    }
    public void updateRect(){
        xDelta += xDir;
        yDelta += yDir;
        if(xDelta >= getWidth() - rectWidth || xDelta <= 0){
            color = getRandomColor();
            xDir *= -1;

        }
        if(yDelta >= getHeight() - rectHeight|| yDelta <= 0){
            color = getRandomColor();
            yDir *= -1;
        }
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
    public Color getRandomColor(){
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }
}
