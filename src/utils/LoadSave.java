package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "DarkSamurai (64x64).png";

    public static BufferedImage GetSpriteAtlas(String filename) {
        InputStream is = LoadSave.class.getResourceAsStream("/" + filename);
        BufferedImage animationSprite = null;
        try {
            animationSprite = ImageIO.read(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return animationSprite;
    }
}
