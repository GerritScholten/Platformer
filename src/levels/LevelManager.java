package levels;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[64];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                 int index = x * 8 + y;
                 levelSprite[index] = img.getSubimage(x*32, y*32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < Game.GAME_HEIGHT; i++){
            for (int j = 0; j < Game.GAME_WIDTH; j++){
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j ,Game.TILES_SIZE, Game.TILES_SIZE,  null);
            }
        }

    }
    public void update(){

    }

}
