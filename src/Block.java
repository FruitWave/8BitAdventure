import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Block extends Object_Shell {
	int typeNum;
	BufferedImage bubble;
	String species;
	String bounce = "bounce";
	String rock = "rock";
	String dirt = "dirt";
	String power = "power";
	String powerup = "powerup";
	String cave = "cave";
	String error = "error";

	public Block(int x, int y, int width, int height, int type_num) {
		super(x, y, width, height);
		scrollAffected = true;
		this.typeNum = type_num;
		setTypeBlowBubble(type_num);
	}

	@Override
	public void update() {

	}

	private void setTypeBlowBubble(int typeNumber) {
		//System.out.println("settypeblowbubble reached");
		switch (typeNumber) {
		case 0:
			species = bounce;
			// System.out.println("its bouncy!");
			bubble = Glasspane.bouncebubble;
			break;
		case 1:
			species = rock;
			bubble = Glasspane.rockbubble;
			break;
		case 2:
			species = dirt;
			bubble = Glasspane.dirtbubble;
			// System.out.println("dirt block set");

			break;
		case 3:
			species = power;
			bubble = Glasspane.powerbubble;
			break;
		case 4:
			species = powerup;
			bubble = Glasspane.powerupbubble;
			break;
		case 5:
			species = cave;
			bubble = caveBubble();
			break;
		default:
			species = error;
			bubble = Glasspane.errorbubble;
			break;
		}
	}

	private BufferedImage caveBubble() {
		int blower = new Random().nextInt(typeNum);
		BufferedImage vladimir;
		switch (blower) {
		case 0:
			vladimir = Glasspane.bouncebubble;
			break;
		case 1:
			vladimir = Glasspane.rockbubble;
			break;
		case 2:
			vladimir = Glasspane.dirtbubble;
			break;
		case 3:
			vladimir = Glasspane.powerbubble;
			break;
		case 4:
			vladimir = Glasspane.powerupbubble;
			break;
		default:
			vladimir = Glasspane.bouncebubble;
			Error broken_link = new Error("Error! caveBubble method is not working!");
			break;
		}
		return vladimir;
	}

	@Override
	public void draw(Graphics ohboy) {
		// TODO Auto-generated method stub
		// System.out.println("Block draw call submitted by Block");
		if (bubble != null) {
			width = bubble.getWidth();
			height = bubble.getHeight();
			ohboy.drawImage(bubble, x, y, width, height, null);
			ohboy.setColor(Color.red);
			ohboy.drawRect(300, 500, 300, 300);
		}

	}
}
