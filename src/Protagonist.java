
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

	public Protagonist(int x, int y, int width, int height) {
		super(x, y, width, height);
		left = false;
		up = false;
		down = false;
		right = false;
		xspeed = 0;
		yspeed = 0;
		speedThroughBlock = 10;
		scrollAffected = true;
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
		if (!up) {
			yspeed = 0;
		}
		if (!down) {
			yspeed = 0;
		}
		x += xspeed;
		y += yspeed;

	}

	public void draw(Graphics g) {
<<<<<<< HEAD
		// g.drawImage(Glasspane.playerImg, x, y, width, height, null);
=======
		//g.drawImage(Glasspane.playerImg, x, y, width, height, null);
		g.setColor(Color.red);
>>>>>>> c98b9dae7a60f255c188d06d3d624cdb98a5b559
		g.fillRect(x, y, width, height);
		System.out.println("its drawing");
		
		// test
	}
}
