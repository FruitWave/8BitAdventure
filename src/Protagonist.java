
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileReader;

public class Protagonist extends Object_Shell {
	Glasspane glassic;
	int xspeed;
	int yspeed;
	/*
	 * boolean left; boolean up; boolean down;
	 */
	boolean isgoingright;
	boolean isgoingdown;
	int protagonistSpeedThroughBlock;
	int baseSpeed;
	int baseVertSpeed;
	boolean stoppedx;
	boolean stoppedy;
	boolean inAir;
	int gravityAcceleration = 0;
	boolean onBlock;
	Block onWhichBlock;
	int sinkStep;

	public Protagonist(int x, int y, int width, int height, int baseSpeedo, int baseYSpeed, Glasspane glassic) {
		super(x, y, width, height);
		// left = false;
		// up = false;
		// down = false;
		stoppedx = true;
		stoppedy = true;
		isgoingright = false;
		baseSpeed = baseSpeedo;
		baseVertSpeed = baseYSpeed;
		xspeed = 0;
		yspeed = 0;
		protagonistSpeedThroughBlock = 0;
		scrollAffected = false;
		this.glassic = glassic;
		onBlock = false;
		isgoingdown = true;
		sinkStep = 0;
	}

	public void update() {
		

		super.update();
		System.out.println(onBlock);
		protagonistSpeedThroughBlock = onWhichBlock != null ? onWhichBlock.speedThroughBlock : 0;

		// if (yspeed > 0 /* going down */) {
		// yspeed -= 10 - protagonistSpeedThroughBlock;
		// } else if (yspeed < 0) /* going up */ {
		// yspeed += 10 - protagonistSpeedThroughBlock;
		// }

		// if (onBlock) {
		// gravityAcceleration = 0;
		// yspeed = 0;
		// } else {
		// gravityAcceleration++;
		// if (gravityAcceleration >= 5) {
		// gravityAcceleration = 5;
		// }
		if (onBlock) {
			System.out.println(onWhichBlock.species);

			System.out.println("Block height: " + onWhichBlock.bubble.getHeight());
			runONBLOCKprotocol();
		} else {
			onWhichBlock = null;
			gravityAcceleration = 1;

		}
		// }

		if (!onBlock && stoppedy) {
			stoppedy = false;
		}

		if (xspeed != 0) {
			stoppedx = false;
		} else {
			stoppedx = true;
		}

		if (yspeed != 0) {
			stoppedy = false;
		} else {
			stoppedy = true;
		}

		if (!stoppedy) {
			isgoingdown = yspeed > 0 ? true : false;
		}
		if (!stoppedx) {
			isgoingright = xspeed > 0 ? true : false;
		}
		if (!onBlock) {
			yspeed += gravityAcceleration;
		} else {
			if (yspeed > 0) {
				y += gravityAcceleration;
			}
		}
	}

	private void runONBLOCKprotocol() {
		int speed = onWhichBlock.speedThroughBlock;
		gravityAcceleration = 0;
		sinkStep++;
		if (sinkStep % 10 == 0) {
			y += speed;
		} else {
			yspeed = 0;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(Glasspane.playerImg, x, y, width, height, null);
	}
}
