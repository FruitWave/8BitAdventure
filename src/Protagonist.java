
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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
	Rectangle belowRect;
	int xx;
	int yy;
	public boolean floatingNecessaryToAvoidBlockCollision;

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
		xx = (int) collisionArea.getX();
		yy = (int) collisionArea.getY();
		yy--;
		belowRect = new Rectangle(xx, yy, width, height);
		protagonistSpeedThroughBlock = onWhichBlock != null ? onWhichBlock.speedThroughBlock : 10;
		protagonistSpeedThroughBlock = 0;
		// if (onWhichBlock != null) {
		// yspeed += isgoingdown ? protagonistSpeedThroughBlock - 10 : 10 -
		// protagonistSpeedThroughBlock;
		// xspeed += isgoingright ? protagonistSpeedThroughBlock - 10 : 10 -
		// protagonistSpeedThroughBlock;
		// }
		if (onBlock) {
			gravityAcceleration = 0;
			// yspeed = 0;
		} else {
			// if (gravityAcceleration >= 5) {
			// gravityAcceleration = 5;
			// }
			// gravityAcceleration++;
			yspeed += gravityAcceleration;
		}
		// if (yspeed > 0 /* going down */) {
		// yspeed -= 10 - protagonistSpeedThroughBlock;
		// } else if (yspeed < 0) /* going up */ {
		// yspeed += 10 - protagonistSpeedThroughBlock;
		// }

		// (fix line 52 so that the protagonist can be slowed but not sent in the
		// opposite direction)

		super.update();
		if (glassic.mani != null) {
			int definitelyOnABlock = 0;
			for (int i = 0; i < glassic.mani.objects.size(); i++) {
				if (glassic.mani.objects.get(i) instanceof Block) {
					onWhichBlock = (Block) glassic.mani.objects.get(i);
					if (collisionArea.intersects(onWhichBlock.collisionArea)) {
						y = onWhichBlock.y - height + 1;
						definitelyOnABlock++;
					} else if (belowRect.intersects(onWhichBlock.collisionArea)) {
						floatingNecessaryToAvoidBlockCollision = true;
					}
				}

			}
			onBlock = definitelyOnABlock > 0 ? true : false;
			floatingNecessaryToAvoidBlockCollision = false;
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
