package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.PlayerConstants.Constants.* ;


public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;

    private float xDelta = 0, yDelta = 0;
    private Dimension screenSize = new Dimension(1280, 768);

    private BufferedImage animationSprite;
    private BufferedImage[] idleAnimation;
    private BufferedImage[][] animations;

    private int animationTick, animationSpeed = 15, animationIndex;
    private int playerAction = IDLE;

    GamePanel(){
        mouseInputs = new MouseInputs(this);
        importImage();
        loadAnimation();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setScreenSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimation();
        g.drawImage(animations[playerAction][animationIndex], (int)xDelta, (int)yDelta, 256, 256, null);
    }

    private void updateAnimation(){
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= GetSpriteAmount(playerAction)){
                animationIndex = 0;
            }
        }
    }

    private void setScreenSize(){
        setMinimumSize(screenSize);
        setPreferredSize(screenSize);
        setMaximumSize(screenSize);
    }

    private void importImage(){
        InputStream is = getClass().getResourceAsStream("/DarkSamurai (64x64).png");
        try {
            animationSprite = ImageIO.read(is);
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
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
    private void loadAnimation(){
        animations = new BufferedImage[8][14 ];
        for (int j = 0; j <= animations.length - 1; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = animationSprite.getSubimage(i * 64, j * 64, 64, 64);
            }
        }
    }
}
