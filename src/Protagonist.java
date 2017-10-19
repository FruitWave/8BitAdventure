
import java.awt.Color;
import java.awt.Graphics;

public class Protagonist extends Object_Shell {
	int xspeed;
	int yspeed;
	boolean left;
	boolean up;
	boolean down;
	boolean right;
	int speedThroughBlock;
	int baseSpeed;
	int baseVertSpeed;

	public Protagonist(int x, int y, int width, int height, int baseSpeedo, int baseYSpeed) {
		super(x, y, width, height);
		// left = false;
		// up = false;
		// down = false;
		right = false;
		baseSpeed = baseSpeedo;
		xspeed = 0;
		// yspeed = 0;
		speedThroughBlock = 10;
		scrollAffected = false;
	}

	public void update() {
		right = xspeed > 0 ? true : false;
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

		/*
		 * x += xspeed;
		 */

		y += yspeed;

		// left = false;
		// right = false;
		// up = false;
		// down = false;
	}

	public void draw(Graphics g) {
		// g.fillRect(x, y, width, height);
		// System.out.println("its drawing");
		g.drawImage(Glasspane.playerImg, x, y, width, height, null);
		// test
	}
}
