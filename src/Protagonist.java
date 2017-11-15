
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
	int protagonistSpeedThroughBlock;
	int baseSpeed;
	int baseVertSpeed;
	boolean stoppedx;
	boolean stoppedy;
	boolean inAir;
	int gravityAcceleration = 0;
	boolean onBlock;
	Block onWhichBlock;

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
	}

	public void update() {
		protagonistSpeedThroughBlock = onWhichBlock != null ? onWhichBlock.speedThroughBlock : 0;

		// if (yspeed > 0 /* going down */) {
		// yspeed -= 10 - protagonistSpeedThroughBlock;
		// } else if (yspeed < 0) /* going up */ {
		// yspeed += 10 - protagonistSpeedThroughBlock;
		// }

		yspeed += isgoingdown ? protagonistSpeedThroughBlock - 10 : 10 - protagonistSpeedThroughBlock;
		if (onBlock) {
			gravityAcceleration = 0;
			yspeed = 0;
		} else {
			if (gravityAcceleration >= 5) {
				gravityAcceleration = 5;
			}
			gravityAcceleration++;
			yspeed += gravityAcceleration;

		}
		super.update();
		if (glassic.mani != null) {
			int definitelyOnABlock = 0;
			for (int i = 0; i < glassic.mani.objects.size(); i++) {
				if (glassic.mani.objects.get(i) instanceof Block) {
					Block temp = (Block) glassic.mani.objects.get(i);
					if (collisionArea.intersects(temp.collisionArea)) {
						Block onWhichBlock = temp;
						y = onWhichBlock.y - height + 1;
						definitelyOnABlock++;
					}
				}

			}
			onBlock = definitelyOnABlock > 0 ? true : false;
		}

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

	}

	public void draw(Graphics g) {
		g.drawImage(Glasspane.playerImg, x, y, width, height, null);
	}
}
