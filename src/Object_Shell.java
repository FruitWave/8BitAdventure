import java.awt.Graphics;
import java.awt.Rectangle;

public class Object_Shell {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive = true;
	boolean scrollAffected;
	Rectangle collisionArea = new Rectangle(x, y, width, height);

	public Object_Shell(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		scrollAffected = true;
	}

	public void update() {
		collisionArea.setBounds(x, y, width, height);

	}

	public void wallBoundaries() {
		if (y < 0) {
			y = 0;
		} else if (y > Runner.height - height) {
			y = Runner.height - height;
		}
		if (x < 0) {
			x = 0;
		} else if (x > Runner.width - width) {
			x = Runner.width - width;
		}
		// fix sinking into floor D:
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
