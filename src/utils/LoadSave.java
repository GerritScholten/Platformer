package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	public static final String LEVEL_ONE_DATA = "level_one_data.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage spriteAtlas = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			spriteAtlas = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return spriteAtlas;
	}

	public static int[][] GetLevelData() {
		int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		BufferedImage levelSpriteAtlas = GetSpriteAtlas(LEVEL_ONE_DATA);

		for (int j = 0; j < levelSpriteAtlas.getHeight(); j++)
			for (int i = 0; i < levelSpriteAtlas.getWidth(); i++) {
				Color color = new Color(levelSpriteAtlas.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				levelData[j][i] = value;
			}
		return levelData;

	}
}
