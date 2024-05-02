package utils;

public class PlayerConstants {
    public static class Constants{
        public static final int IDLE = 0,RUNNING = 1, ATTACK_1 = 2, ATTACK_2 = 3, JUMPING = 4, FALLING = 5, HIT = 6, DYING = 7;
        public static int GetSpriteAmount(int playerAction){
            switch (playerAction) {
                case IDLE:
                case RUNNING:
                    return 8;

                case FALLING:
                case ATTACK_1:
                case JUMPING:
                    return 4;

                case ATTACK_2:
                    return 3;
                case HIT:
                    return 2;
                case DYING:
                    return 14;
                default: return 1;
            }
        }

    }
}
