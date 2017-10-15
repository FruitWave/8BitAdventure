
import java.awt.Graphics;

public class Protagonist extends Object_Shell {
	int xspeed;
	int yspeed;
	boolean left;
	boolean up;
	boolean down;
	boolean right;

	public Protagonist(int x, int y, int width, int height) {
		super(x, y, width, height);
		left = false;
		up = false;
		down = false;
		right = false;

		xspeed = 0;
		yspeed = 0;
	}

	public void update() {
		if (left) {
			xspeed = -5;
		}
		if (right) {
			xspeed = 5;
		}
		if (up) {
			yspeed = -5;
		}
		if (down) {
			yspeed = 5;
		}

		if (!left && !right) {
			xspeed = 0;
		}
		if (!up && !down) {
			yspeed = 0;
		}
		// if(!up){
		// yspeed=0;
		// }
		// if(!down){
		// yspeed=0;
		// }
		x += xspeed;
		y += yspeed;

	}

	public void draw(Graphics g) {
		g.drawImage(Glasspane.playerImg, x, y, width, height, null);
		// test
	}
}
