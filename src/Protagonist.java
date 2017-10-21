
import java.awt.Color;
import java.awt.Graphics;

public class Protagonist extends Object_Shell {
	int xspeed;
	int yspeed;
	/*
	 * boolean left; boolean up; boolean down;
	 */
	boolean isgoingright;
	boolean isgoingdown;
	int speedThroughBlock;
	int baseSpeed;
	int baseVertSpeed;
	boolean stoppedx;
	boolean stoppedy;

	public Protagonist(int x, int y, int width, int height, int baseSpeedo, int baseYSpeed) {
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
		speedThroughBlock = 10;
		scrollAffected = false;
	}

	public void update() {
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
		// if (left) {
		// xspeed = -1;
		// right = false;
		// }
		// if (right) {
		// xspeed = 1;
		// left = false;
		// }
		// if (up) {
		// yspeed = -1;
		// down = false;
		// }
		// if (down) {
		// yspeed = 1;
		// up = false;
		// }
		//
		// if (!left && !right) {
		// xspeed = 0;
		// }
		// if (!up && !down) {
		// yspeed = 0;
		// }
		// if (!up) {
		// yspeed = 0;
		// }
		// if (!down) {
		// yspeed = 0;
		// }

		x += xspeed;

		y += yspeed;

		
	}

	public void draw(Graphics g) {
		g.drawImage(Glasspane.playerImg, x, y, width, height, null);
	}
}
