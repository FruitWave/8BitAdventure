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
	Timer movementStopper;

	public Glasspane() {
		// System.out.println("constructor reached");
		gameSpeed = new Timer(1000 / 150, this);
		movementStopper = new Timer(7, this);
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
		// if ((e.getKeyCode() == KeyEvent.VK_W) || (e.getKeyCode() == KeyEvent.VK_A) ||
		// (e.getKeyCode() == KeyEvent.VK_S)
		// || (e.getKeyCode() == KeyEvent.VK_D)) {
		// movementStopper.stop();
		if (e.getKeyCode() == KeyEvent.VK_W) {
			xeni.up = true;
			// movementStopper.start();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			xeni.left = true;
			// movementStopper.start();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			xeni.down = true;
			// movementStopper.start();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			xeni.right = true;
			// movementStopper.start();
		}
		// }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if ((xeni.up) || (xeni.down) || (xeni.right) || (xeni.left)) {
			xeni.up = false;
			xeni.down = false;
			xeni.right = false;
			xeni.left = false;
		}
	}

	private void updateEndState() {
		// TODO Auto-generated method stub

	}

	// @Test
	public void updateGameState() {
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

	public void startGame() {
		// System.out.println("started game");
		gameSpeed.start();

		mani = new Manipulator(this);
		xeni = new Protagonist(960, 540, 200, 250);
		int runwidth = Runner.width;
		for (int i = 0; runwidth > 0; i++) {
			runwidth -= dirtbubble.getWidth();
			int r = new Random().nextInt(2);
			r++;
			if (r == 1) {
				justdirt = new Block(i * dirtbubble.getWidth(), Runner.height - dirtbubble.getHeight(), 200, 200, 2);
				mani.addObject(justdirt);
			} else {
				yourbasicbounce = new Block(i * dirtbubble.getWidth(), Runner.height - dirtbubble.getHeight(), 200, 200,
						0);
				mani.addObject(yourbasicbounce);
			}
			mani.addObject(xeni);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("repaint");
		if (e.getSource() == gameSpeed) {
			repaint();
			if (currentState == MENU_STATE) {
				updateMenuState();
			} else if (currentState == GAME_STATE) {
				updateGameState();
			} else if (currentState == END_STATE) {
				updateEndState();
			}
		} else if (e.getSource() == movementStopper) {
			if ((xeni.up) || (xeni.down) || (xeni.right) || (xeni.left)) {
				xeni.up = false;
				xeni.down = false;
				xeni.right = false;
				xeni.left = false;
			}
		}
	}
}
