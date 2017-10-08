
import javax.swing.JFrame;

public class Runner {
	JFrame frame;
	static final int width = 1920;
	static final int height = 1080;
	Glasspane panelite;

	public Runner() {
		frame = new JFrame();
		panelite = new Glasspane();
		run();
	}

	public static void main(String[] args) {
		Runner initiate_one = new Runner();
		System.out.println(
				"I am a doge, Daniel. If you can't accept me, you're a warwickiophobe and need to check your bloodlust priveleges.");
	}

	void run() {
		frame.add(panelite);
		frame.setTitle("8 Bit Adventure!");
		frame.addKeyListener(panelite);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelite.startGame();
	}
}
