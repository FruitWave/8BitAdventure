
import java.awt.Color;
import java.awt.Graphics;

public class Protagonist extends Object_Shell {
	Glasspane glassic;
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
	boolean inAir;
	int gravityAcceleration = 0;
	boolean onBlock;
	Block onWhichBlock;

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
		speedThroughBlock = 0;
		scrollAffected = false;
		inAir = true;
	}

	public void update() {
		super.update();
		inAir = onBlock ? false : true;
		if (glassic.mani != null) {

			for (int i = 0; i < glassic.mani.objects.size(); i++) {
				if (glassic.mani.objects.get(i) instanceof Block) {
					Block onWhichBlock = (Block) glassic.mani.objects.get(i);
					if (collisionArea.intersects(onWhichBlock.collisionArea)) {
						y = onWhichBlock.y - height + 1;
						onBlock = true;
					}
				}
			}
		}

		if (inAir && stoppedy) {
			stoppedy = false;
		}
		if (!inAir) {
			gravityAcceleration = 0;
		} else {
			if (yspeed <= 0) {

				if (gravityAcceleration >= 5) {
					gravityAcceleration = 5;
				}
				gravityAcceleration++;
				y += gravityAcceleration;

			}
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

	}

	public void draw(Graphics g) {
		g.drawImage(Glasspane.playerImg, x, y, width, height, null);
	}
}
