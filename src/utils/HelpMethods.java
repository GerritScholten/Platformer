package utils;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {
        if (!IsSolid(x, y, levelData)) {
            if (!IsSolid(x + width, y + height, levelData)) {
                if (!IsSolid(x + width, y, levelData)) {
                    return !IsSolid(x, y + height, levelData);
                }
            }
        }
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] levelData) {
        if (x < 0 || x >= Game.GAME_WIDTH){
            return true;
        }if (y < 0 || y >= Game.GAME_HEIGHT){
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = levelData[(int)yIndex][(int)xIndex];

        if (value >= 48 || value < 0 || value != 11){
            return true;
        }return false;
    }
    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox,float xSpeed){
        int currentTile = (int)hitbox.x / Game.TILES_SIZE;
        if (xSpeed > 0){//right
            int tileXPosition = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPosition + xOffset -1;
        }else{//left
            return currentTile * Game.TILES_SIZE;
        }
    }
    public static float GetEntityYPosUnderRoofOrUnderFloor(Rectangle2D.Float hitbox,float airSpeed){
        int currentTile = (int)hitbox.y / Game.TILES_SIZE;
        if (airSpeed > 0){//falling - touching floor
            int tileYPosition = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPosition + yOffset - 1;
        }else{ //jumping
            return currentTile * Game.TILES_SIZE;
        }
    }
    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData){
        if(!IsSolid(hitbox.x, hitbox.y + hitbox.height + 2, levelData)){
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 2, levelData)){
                return false;
            }
        }
        return true;
    }
}
