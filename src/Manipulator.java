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
				if ((panelite.xeni.x != Runner.width / 2) && (!panelite.xeni.stoppedx)) {
					int priordist = panelite.xeni.x - o.x;
					panelite.xeni.x = Runner.width / 2;
					o.x = panelite.xeni.x - priordist;
				}
				if ((panelite.xeni.y != Runner.height / 2) && (!panelite.xeni.stoppedy)) {
					int priordisty = panelite.xeni.y - o.y;
					panelite.xeni.y = Runner.height / 2;
					o.y = panelite.xeni.y - priordisty;
				}
				if (!panelite.xeni.stoppedx) {
					o.x += -panelite.xeni.xspeed;
					// System.out.println("reached x move");
					// syso works^
					// because if stopped x, do not scroll x
				}
				if (!panelite.xeni.stoppedy) {

					o.y += -panelite.xeni.yspeed;
					// System.out.println("reached y move");
					// syso works^

					// because if stopped y, do not scroll y
				}

			}
		}
	}

	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				Object_Shell o1 = objects.get(i);
				Object_Shell o2 = objects.get(j);
				if ((o1.collisionArea.intersects(o2.collisionArea))
						|| (o2.collisionArea.intersects(o1.collisionArea))) {
					// System.out.println("checkcollision");
					if ((o1 instanceof Protagonist && o2 instanceof Block)
							|| (o2 instanceof Protagonist && o1 instanceof Block)) {
						// System.out.println("Protagonist & Block");
						Protagonist charlie = (o1 instanceof Protagonist) ? (Protagonist) o1 : (Protagonist) o2;
						Block cubicon = (o1 instanceof Block) ? (Block) o1 : (Block) o2;
						// charlie.onBlock = true;
						// charlie.inAir = false;
						// if (cubicon.hardness == 0) {
						//
						// }
						charlie.speedThroughBlock = charlie.xspeed / 2;
						if (charlie.xspeed > 0) {
							charlie.xspeed = charlie.speedThroughBlock;
						} else if (charlie.xspeed < 0) {
							charlie.xspeed = -charlie.speedThroughBlock;
						}
						if (charlie.yspeed > 0) {
							charlie.yspeed = charlie.speedThroughBlock;
						} else if (charlie.yspeed < 0) {
							charlie.yspeed = -charlie.speedThroughBlock;
						}
						/*
						 * charlie.speedThroughBlock = cubicon.solid ? (charlie.xspeed != 0 ?
						 * charlie.xspeed : charlie.yspeed) : 5; if (charlie.xspeed != 0) { if
						 * (charlie.xspeed > 0) { charlie.xspeed -= charlie.speedThroughBlock;
						 * System.out.println(charlie.xspeed); } else if (charlie.xspeed < 0) {
						 * charlie.xspeed += charlie.speedThroughBlock; } } if (charlie.yspeed != 0) {
						 * if (charlie.yspeed > 0) { charlie.yspeed -= charlie.speedThroughBlock; } else
						 * if (charlie.yspeed < 0) { charlie.yspeed += charlie.speedThroughBlock; } }
						 */

						// // charlie.yspeed = charlie.speedThroughBlock;
						/*
						 * if (cubicon.solid) { // if (charlie.onBlock = true) { // charlie.yspeed = 0;
						 * // } if ((charlie.y != cubicon.y + cubicon.height) && (charlie.y +
						 * charlie.height != cubicon.y)) {
						 * 
						 * if (charlie.y < ((cubicon.height / 2) + cubicon.y)) { charlie.y = cubicon.y -
						 * charlie.height; } else if (charlie.y > ((cubicon.height / 2) + cubicon.y)) {
						 * charlie.y = cubicon.y + cubicon.height; } } if (charlie.x != cubicon.x +
						 * cubicon.width) {
						 * 
						 * if (charlie.x < ((cubicon.width / 2) + cubicon.x)) { charlie.x = cubicon.x -
						 * charlie.width; } else if (charlie.x > ((cubicon.width / 2) + cubicon.x)) {
						 * charlie.x = cubicon.x + cubicon.width; } } }
						 */
					}
				}
			}
		}
	}
}