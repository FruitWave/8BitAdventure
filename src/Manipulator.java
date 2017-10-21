import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Manipulator {

	ArrayList<Object_Shell> objects;
	Glasspane panelite;
	Color dark1 = new Color(100, 50, 50);
	Color dark2 = new Color(200, 50, 80);
	Color dark3 = new Color(250, 100, 10);
	Color dark4 = new Color(49, 0, 26);

	long enemyTimer = 0;
	int enemySpawnTime = 1;

	public Manipulator(Glasspane panelite) {
		objects = new ArrayList<Object_Shell>();
		this.panelite = panelite;
	}

	public void addObject(Object_Shell o) {
		objects.add(o);
	}

	public void update() {

		for (int i = 0; i < objects.size(); i++) {
			Object_Shell o = objects.get(i);
			o.update();
		}
		checkCollision();
		scroll(panelite.xeni.isgoingright, panelite.xeni.isgoingdown);
		// purgeObjects();
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			Object_Shell o = objects.get(i);
			// System.out.println("object manager drawn");
			o.draw(g);
		}
	}

	private void purgeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			if (!objects.get(i).isAlive) {
				objects.remove(i);
			}
		}
	}

	private void scroll(boolean isMovingRight, boolean isMovingDown) {
		for (int i = 0; i < objects.size(); i++) {
			Object_Shell o = objects.get(i);
			if (o.scrollAffected) {
				if (!panelite.xeni.stoppedx) {
					o.x += isMovingRight ? 0 - panelite.xeni.xspeed : panelite.xeni.xspeed;
					System.out.println("reached x move");
					// syso works^
					// because if stopped x, do not scroll x
				}
				if (!panelite.xeni.stoppedy) {

					o.y += isMovingDown ? 0 - panelite.xeni.yspeed : panelite.xeni.xspeed;
					System.out.println("reached y move");
					// syso works^

					// because if stopped y, do not scroll y
				}
			}
		}
	}

	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {

				// the code does get here!

				Object_Shell o1 = objects.get(i);
				Object_Shell o2 = objects.get(j);
				if ((o1.collisionArea.intersects(o2.collisionArea))
						|| (o2.collisionArea.intersects(o1.collisionArea))) {
					System.out.println("checkcollision");
					// LINE ABOVE DOESN'T DETECT ANY COLLISIONS
					// THE ISSUE HAS TO BE HERE, BECAUSE I HAVE CREATED A FEW GAMES WITH THE SAME
					// CODE YOU DO NOT SEE AND ALL THE INTERSECT STUFF IS THE SAME
					if ((o1 instanceof Protagonist && o2 instanceof Block)
							|| (o2 instanceof Protagonist && o1 instanceof Block)) {
						System.out.println("Protagonist & Block");
						Protagonist charlie = (o1 instanceof Protagonist) ? (Protagonist) o1 : (Protagonist) o2;
						Block cubicon = (o1 instanceof Block) ? (Block) o1 : (Block) o2;
						// if (cubicon.hardness == 0) {
						//
						// }
						charlie.speedThroughBlock = 10 - cubicon.hardness;
						// charlie.yspeed = charlie.speedThroughBlock;
						if (charlie.xspeed != 0) {
							if (charlie.xspeed > 0) {
								charlie.xspeed -= charlie.speedThroughBlock;
							} else if (charlie.xspeed < 0) {
								charlie.xspeed += charlie.speedThroughBlock;
							}
						}
						if (charlie.yspeed != 0) {
							if (charlie.yspeed > 0) {
								charlie.yspeed -= charlie.speedThroughBlock;
							} else if (charlie.yspeed < 0) {
								charlie.yspeed += charlie.speedThroughBlock;
							}
						}
					}
				}
			}
		}
	}
}