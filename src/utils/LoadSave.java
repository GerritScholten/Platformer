package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "DarkSamurai (64x64).png";
    public static final String LEVEL_ATLAS = "The Sidescroller's Dungeon Tiles 32x32.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";

    public static BufferedImage GetSpriteAtlas(String filename) {
        InputStream is = LoadSave.class.getResourceAsStream("/" + filename);
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return sprite;
    }

    public static int[][] GetLevelData(){
        int[][] levelData = new int[Game.GAME_HEIGHT][Game.GAME_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                Color c = new Color(img.getRGB(x, y));
                int value = c.getRed();
                if (value >= 64){
                    value = 0;
                }else{
                    levelData[x][y] = value;
                }
            }
        }
        return levelData;
    }

}
