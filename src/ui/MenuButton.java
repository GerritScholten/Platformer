package ui;

import gamestates.Gamestate;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.UI.Buttons.*;

public class MenuButton {
    private int xPosOffset = BUTTON_WIDTH / 2;
    private int xPos, yPos;
    private int rowIndex, index;
    private Gamestate gamestate;
    private BufferedImage[] buttonImages;
    private boolean mousOver, mousePressed;
    private Rectangle buttonBounds;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate gamestate) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.gamestate = gamestate;
        loadButtonImages();
        initBounds();
    }

    private void initBounds() {
        buttonBounds = new Rectangle(xPos - xPosOffset, yPos, BUTTON_WIDTH, BUTTON_HIGHT);
    }

    private void loadButtonImages() {
        buttonImages = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < buttonImages.length; i++) {
            buttonImages[i] = temp.getSubimage(i * BUTTON_WIDTH_DEFAULT, rowIndex * BUTTON_HIGHT_DEFAULT, BUTTON_WIDTH_DEFAULT  , BUTTON_HIGHT_DEFAULT);
        }
    }
    public void render(Graphics g) {
        g.drawImage(buttonImages[index], xPos - xPosOffset, yPos, BUTTON_WIDTH, BUTTON_HIGHT, null);
    }
    public void update() {
        index = 0;
        if (mousOver){
            index = 1;
        } else if (mousePressed) {
            index = 2;
        }
    }

    public boolean isMousOver() {
        return mousOver;
    }

    public void setMousOver(boolean mousOver) {
        this.mousOver = mousOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public void applyGamestate(){
        Gamestate.state = gamestate;
    }
    public void resetBools(){
        mousePressed = false;
        mousOver = false;
    }

    public Rectangle getButtonBounds() {
        return buttonBounds;
    }

    public void setButtonBounds(Rectangle buttonBounds) {
        this.buttonBounds = buttonBounds;
    }
}
