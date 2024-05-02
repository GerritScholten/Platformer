package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import static main.Game.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;

    private Game game;

    GamePanel(Game game ){
        this.game = game;
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setScreenSize();
    }

    private void setScreenSize(){
        Dimension screenSize = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(screenSize);
        System.out.println("size: " + GAME_WIDTH + ":" + GAME_HEIGHT);

    }

    public void updateGame(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){
        return game;
    }
}
