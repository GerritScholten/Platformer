package gamestates;

import main.Game;
import ui.MenuButton;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static gamestates.Gamestate.*;

public class Menu extends State implements StateMethods {
    private MenuButton[] menuButtons = new MenuButton[3];
    private BufferedImage background;
    private int menuX, menuY, menuWidth, menuHeight;


    public Menu(Game game) {
        super(game);
        loadBackground();
        loadButtons();
    }

    private void loadBackground() {
        background = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (background.getWidth() * Game.SCALE);
        menuHeight = (int) (background.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int)(45 * Game.SCALE);
    }

    private void loadButtons() {
        menuButtons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int)(150 * Game.SCALE), 0 , PLAYING );
        menuButtons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int)(220 * Game.SCALE), 1 , OPTIONS );
        menuButtons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int)(290 * Game.SCALE), 2 , QUIT );
    }

    @Override
    public void update() {
        for(MenuButton button : menuButtons) {
            button.update();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(background, menuX, menuY, menuWidth, menuHeight,  null);
        for(MenuButton button : menuButtons) {
            button.render(g);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton button : menuButtons) {
            button.setMousOver(false);
        }
        for(MenuButton button : menuButtons) {
            if (isInButton(e, button)){
                button.setMousOver(true);
                break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton button : menuButtons) {
            if(isInButton(e, button)){
                 button.setMousePressed(true);
                 break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton button : menuButtons) {
            if(isInButton(e, button)){
                if(button.isMousePressed()){
                    button.applyGamestate();
                }
                break;
            }
        }
         resetButtons();
    }

    private void resetButtons() {
        for(MenuButton button : menuButtons) {
            button.resetBools();
        }
    }
}
