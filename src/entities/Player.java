package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.*;

public class Player extends Entity {
    private BufferedImage[][] animations;

    private int animationTick, animationSpeed = 15, animationIndex;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean isMoving = false, attacking = false;
    private float playerSpeed = 2.0f;

    private boolean left, right, up, down;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePosition();
        updateAnimation();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 256, 256, null);

    }

    private void loadAnimations() {
        BufferedImage animationSprite = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[8][14];
        for (int j = 0; j <= animations.length - 1; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = animationSprite.getSubimage(i * 64, j * 64, 64, 64);
            }
        }
    }

private void updateAnimation() {
    animationTick++;
    if (animationTick >= animationSpeed) {
        animationTick = 0;
        animationIndex++;
        if (animationIndex >= GetSpriteAmount(playerAction)) {
            animationIndex = 0;
            attacking = false;
        }
    }
}


private void updatePosition() {
    isMoving = false;

    if (isLeft() && !isRight()) {
        x -= playerSpeed;
        isMoving = true;
    } else if (isRight() && !isLeft()) {
        x += playerSpeed;
        isMoving = true;
    }

    if (isUp() && !isDown()) {
        y -= playerSpeed;
        isMoving = true;
    } else if (isDown() && !isUp()) {
        y += playerSpeed;
        isMoving = true;
    }
}

private void setAnimation() {
    int startAnimation = playerAction;
    if (isMoving) {
        playerAction = RUNNING;
    } else {
        playerAction = IDLE;
    }
    if (attacking) {
        playerAction = ATTACK_1;
    }
    if (playerAction != startAnimation) {
        resetAnimation();
    }
}

private void resetAnimation() {
    animationIndex = 0;
    animationTick = 0;
}

public boolean isLeft() {
    return left;
}

public void setLeft(boolean left) {
    this.left = left;
}

public boolean isRight() {
    return right;
}

public void setRight(boolean right) {
    this.right = right;
}

public boolean isUp() {
    return up;
}

public void setUp(boolean up) {
    this.up = up;
}

public boolean isDown() {
    return down;
}

public void setDown(boolean down) {
    this.down = down;
}

public void setAttacking(boolean attacking) {
    this.attacking = attacking;
}

public void resetDirectionBooleans() {
    playerDirection = IDLE;
    this.setUp(false);
    this.setLeft(false);
    this.setRight(false);
    this.setDown(false);
}
}
