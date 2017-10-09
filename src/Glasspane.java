import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Glasspane extends JPanel implements ActionListener, KeyListener {

	static BufferedImage bouncebubble;
	static BufferedImage rockbubble;
	static BufferedImage dirtbubble;
	static BufferedImage powerbubble;
	static BufferedImage powerupbubble;
	Timer gameSpeed;
	Manipulator mani;
	static final int MENU_STATE = 0;
	static final int GAME_STATE = 1;
	static final int END_STATE = 2;
	static int currentState = GAME_STATE;
	Block dirty;
	Block bouncy;

	public Glasspane() {
		System.out.println("constructor reached");
		gameSpeed = new Timer(1000 / 150, this);
		// arcade picture maker link
		// https://www.imgonline.com.ua/eng/8bit-picture.php
		try {
			bouncebubble = ImageIO.read(this.getClass().getResourceAsStream("bounce.jpg"));
			dirtbubble = ImageIO.read(this.getClass().getResourceAsStream("dirt.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void updateEndState() {
		// TODO Auto-generated method stub

	}

	private void updateGameState() {
		mani.update();
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

	private void drawGameState(Graphics workhard) {
		mani.draw(workhard);
		System.out.println("drawn");
	}

	private void drawMenuState(Graphics delta) {
		// TODO Auto-generated method stub

	}

	public void startGame() {
		System.out.println("started game");
		gameSpeed.start();
		mani = new Manipulator(this);
		dirty = new Block(400, 200, 200, 200, 3);
		bouncy = new Block(600, 200, 200, 200, 0);
		mani.addObject(dirty);
		mani.addObject(bouncy);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("repaint");
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
	}
}
