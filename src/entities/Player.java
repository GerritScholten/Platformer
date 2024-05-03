package entities;

import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int animationTick, animationIndex, animationSpeed = 25;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down;
	private float playerSpeed = 2.0f;
	private int[][] levelData;

	private float xDrawOffstet = 21 * Game.SCALE;
	private float yDrawOffstet = 4  * Game.SCALE;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
	}

	public void update() {
		updatePosition();

		updateAnimationTick();
		setAnimation();

	}

	public void render(Graphics g) {
		g.drawImage( animations[playerAction][animationIndex], (int) (hitbox.x - xDrawOffstet), (int) (hitbox.y - yDrawOffstet), width, height, null);
		drawHitbox(g);
	}

	private void updateAnimationTick() {
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

	private void setAnimation() {
		int startAni = playerAction;

		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;

		if (attacking)
			playerAction = ATTACK_1;

		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		animationTick = 0;
		animationIndex = 0;
	}

	private void updatePosition() {
		moving = false;
		if(!left && !up && !right && !down) {
			return;
		}
		float xSpeed = 0;
		float ySpeed = 0;

		if (left && !right) {
			xSpeed = -playerSpeed;

		} else if (right && !left) {
			xSpeed = playerSpeed;

		}
		if (up && !down) {
			ySpeed = -playerSpeed;

		} else if (down && !up) {
			ySpeed = playerSpeed;
		}
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height , levelData)){
			 hitbox.x += xSpeed;
			 hitbox.y += ySpeed;
			 moving = true;
		}
	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[9][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void loadLevelData(int[][] levelData) {
		this.levelData = levelData;
	}

}
