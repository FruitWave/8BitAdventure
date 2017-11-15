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
	String nothing = "nothing";
	int speedThroughBlock;
	boolean solid;

	public Block(int x, int y, int width, int height, int type_num, int speedThroughBlock) {
		super(x, y, width, height);
		scrollAffected = true;
		this.typeNum = type_num;
		setTypeBlowBubble(type_num);
		this.speedThroughBlock = speedThroughBlock;
		solid = false;
	}

	@Override
	public void update() {
		super.update();
		speedThroughBlock = setspeedThroughBlock(species);
	}

	private void setTypeBlowBubble(int typeNumber) {
		switch (typeNumber) {
		case 0:
			species = nothing;
		case 1:
			species = bounce;
			// System.out.println("its bouncy!");
			bubble = Glasspane.bouncebubble;
			break;
		case 2:
			species = rock;
			bubble = Glasspane.rockbubble;
			break;
		case 3:
			species = dirt;
			bubble = Glasspane.dirtbubble;
			// System.out.println("dirt block set");
			break;
		case 4:
			species = power;
			bubble = Glasspane.powerbubble;
			break;
		case 5:
			species = powerup;
			bubble = Glasspane.powerupbubble;
			break;
		case 6:
			species = cave;
			bubble = caveBubble();
			break;
		default:
			species = error;
			bubble = Glasspane.errorbubble;
			break;
		}
	}

	public int setspeedThroughBlock(String speciesName) {

		switch (species) {
		case "nothing":
			solid = true;
			return 0;
		case "bounce":
			solid = true;
			return 0;
		case "rock":
			solid = true;
			return 0;
		case "dirt":
			solid = false;
			return 1;
		case "power":
			solid = false;
			return 5;
		case "powerup":
			solid = false;
			return 10;
		default:
			species = error;
			bubble = Glasspane.errorbubble;
			return 0;
		}
	}

	private BufferedImage caveBubble() {
		int blower = new Random().nextInt(typeNum);
		BufferedImage vladimir;
		switch (blower) {
		case 0:
			vladimir = Glasspane.bouncebubble;
			speedThroughBlock = 0;
			break;
		case 1:
			vladimir = Glasspane.rockbubble;
			speedThroughBlock = 0;
			break;
		case 2:
			vladimir = Glasspane.dirtbubble;
			speedThroughBlock = 1;
			break;
		case 3:
			vladimir = Glasspane.powerbubble;
			speedThroughBlock = 5;
			break;
		case 4:
			vladimir = Glasspane.powerupbubble;
			speedThroughBlock = 10;
			break;
		default:
			vladimir = Glasspane.errorbubble;
			Error broken_link = new Error("Error! caveBubble method is not working!");
			broken_link.printStackTrace();
			break;
		}
		return vladimir;
	}

	@Override
	public void draw(Graphics ohboy) {

		if ((bubble != null)/* && (species != nothing) */) {
			width = bubble.getWidth();
			height = bubble.getHeight();
			ohboy.drawImage(bubble, x, y, width, height, null);
		}

	}
}
