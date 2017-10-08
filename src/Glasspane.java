import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Glasspane extends JPanel implements ActionListener, KeyListener {
	static int currentState = 1;
	Timer gameSpeed;
	Manipulator mani;
	int MENU_STATE;
	int GAME_STATE;
	int END_STATE;

	public Glasspane() {
		gameSpeed = new Timer(1000 / 150, this);
		// arcade picture maker link
		// https://www.imgonline.com.ua/eng/8bit-picture.php
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
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

	private void drawEndState(Graphics delta) {
		// TODO Auto-generated method stub

	}

	private void drawGameState(Graphics delta) {
		// TODO Auto-generated method stub

	}

	private void drawMenuState(Graphics delta) {
		// TODO Auto-generated method stub

	}

	public void startGame() {
		gameSpeed.start();
		mani = new Manipulator(this);
	}

}
