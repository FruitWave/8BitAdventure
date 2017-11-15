import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//import static org.junit.Assert.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

//import org.junit.Test;

/*Ideas For Originality:
 * 1. Environmental effects at intervals.
 * 2. New worlds "caves", short-term quests per each cave. 
 * 3. "Home", where you respawn, show off trophies and run "free" (non-discovered) quests
 */
@SuppressWarnings("serial")
public class Glasspane extends JPanel implements ActionListener, KeyListener {

	static BufferedImage bouncebubble;
	static BufferedImage rockbubble;
	static BufferedImage dirtbubble;
	static BufferedImage powerbubble;
	static BufferedImage powerupbubble;
	static BufferedImage errorbubble;
	static BufferedImage homeworld;
	static BufferedImage playerImg;
	Timer gameSpeed;
	Manipulator mani;
	static final int MENU_STATE = 0;
	static final int GAME_STATE = 1;
	static final int END_STATE = 2;
	static int currentState = GAME_STATE;
	Block justdirt;
	Block yourbasicbounce;
	Protagonist xeni;

	public Glasspane() {
		// System.out.println("constructor reached");
		gameSpeed = new Timer(1000 / 120, this);
		// movementStopper = new Timer(7, this);
		// movementStopper.setInitialDelay(750);
		// arcade picture maker link
		// https://www.imgonline.com.ua/eng/8bit-picture.php
		try {
			bouncebubble = ImageIO.read(this.getClass().getResourceAsStream("bounce.jpg"));
			dirtbubble = ImageIO.read(this.getClass().getResourceAsStream("dirt.jpg"));
			playerImg = ImageIO.read(this.getClass().getResourceAsStream("playerImg.png"));
			errorbubble = ImageIO.read(this.getClass().getResourceAsStream("404_error.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Keycode: " + e.getKeyCode());
		// if ((e.getKeyCode() == KeyEvent.VK_W) || (e.getKeyCode() == KeyEvent.VK_A) ||
		// (e.getKeyCode() == KeyEvent.VK_S)
		// || (e.getKeyCode() == KeyEvent.VK_D)) {
		// movementStopper.stop();
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			xeni.yspeed = -xeni.baseVertSpeed;
			// System.out.println("Up true. Y speed is " + xeni.yspeed);

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			xeni.xspeed = -xeni.baseSpeed;
			// System.out.println("Left true. X speed is " + xeni.xspeed);

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (!xeni.floatingNecessaryToAvoidBlockCollision) {
				xeni.yspeed = xeni.baseVertSpeed;
			}

			// System.out.println("Dwn true. Y speed is " + xeni.yspeed);

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			xeni.xspeed = xeni.baseSpeed;
			// System.out.println("Right true. X speed is " + xeni.xspeed);

		}
		// }
		// if (e.getKeyCode() == KeyEvent.VK_E) {
		// System.out.println("X speed is " + xeni.xspeed);
		// System.out.println("Y speed is " + xeni.yspeed);
		// System.out.println("\n \n \n \n ");
		// }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		xeni.xspeed = 0;
		xeni.yspeed = 0;
	}

	private void updateEndState() {
		// TODO Auto-generated method stub

	}

	// @Test
	public void updateGameState() {
		// System.out.println("X speed is " + xeni.xspeed);
		// System.out.println("Y speed is " + xeni.yspeed);
		// System.out.println("\n \n \n ");
		if (mani != null) {

			mani.update();
			// assertEquals(dirty.bubble, dirtbubble);
		}
	}

	private void updateMenuState() {
		// TODO Auto-generated method stub

	}

	protected void paintComponent(Graphics delta) {
		// System.out.println("paintcomponent has been reached");
		if (currentState == MENU_STATE) {
			drawMenuState(delta);
		} else if (currentState == GAME_STATE) {
			drawGameState(delta);
		} else if (currentState == END_STATE) {
			drawEndState(delta);
		}

	}

	private void drawEndState(Graphics ilavait) {
		// TODO Auto-generated method stub

	}

	public void drawGameState(Graphics workhard) {
		if (mani != null) {
			mani.draw(workhard);
			// System.out.println("drawn");
		}
	}

	private void drawMenuState(Graphics delta) {
		// TODO Auto-generated method stub

	}

	// what is the purpose of the 'i' for loop enclosing the 'j' for
	// loop? ANSWER: 2-dimensional hashmap basically (think matrix of objects)
	public void startGame() {
		mani = new Manipulator(this);
		gameSpeed.start();
		int runwidth = Runner.width;
		for (int i = 0; runwidth > -4000/* 0 */; i++) {
			int r = new Random().nextInt(2);

			if (r == 0) {
				justdirt = new Block(i * dirtbubble.getWidth(), Runner.height - dirtbubble.getHeight(), 200, 200, 3,
						10);
				mani.addObject(justdirt);
				runwidth -= dirtbubble.getWidth();
			} else {
				yourbasicbounce = new Block(i * bouncebubble.getWidth(), Runner.height - bouncebubble.getHeight(), 200,
						200, 1, 10);
				mani.addObject(yourbasicbounce);
				runwidth -= bouncebubble.getWidth();
			}
		}
		xeni = new Protagonist(Runner.width / 2, Runner.height / 2, 200, 250, 5, 15, this);
		// Block dirtlike = new Block(Runner.width / 2, (Runner.height / 2) +
		// dirtbubble.getHeight(), 200, 200, 3, 10);
		// mani.addObject(dirtlike);
		mani.addObject(xeni);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("repaint");
		// if (e.getSource() == gameSpeed) {
		// System.out.println("action performed ");
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
		// }
		// else if (e.getSource() == movementStopper) {
		// if ((xeni.up) || (xeni.down) || (xeni.right) || (xeni.left)) {
		// xeni.up = false;
		// xeni.down = false;
		// xeni.right = false;
		// xeni.left = false;
		// }
		// }
	}
}
