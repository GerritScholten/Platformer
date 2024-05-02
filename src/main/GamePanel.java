package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 0, yDelta = 0;
    private Dimension screenSize = new Dimension(1280, 768);
    private BufferedImage img, subImg;

    GamePanel(){
        mouseInputs = new MouseInputs(this);
        importImage();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setScreenSize();
    }
    private void setScreenSize(){
        setMinimumSize(screenSize);
        setPreferredSize(screenSize);
        setMaximumSize(screenSize);
    }

    private void importImage(){
        try {
            InputStream is = getClass().getResourceAsStream("/DarkSamurai (64x64).png");
            img = ImageIO.read(is);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImg = img.getSubimage(1 * 64, 2 * 64, 64, 64);
        g.drawImage(subImg, (int)xDelta, (int)yDelta, 128, 128, null);
    }

    public void changeXDelta(int value) {
        xDelta += value;
    }
    public void changeYDelta(int value) {
        yDelta += value;
    }
    public void setImgPosition(int x, int y){
        xDelta = x;
        yDelta = y;
    }
}
