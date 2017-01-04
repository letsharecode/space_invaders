package space;
import javax.swing.JFrame;

public class GameFrame extends JFrame {	

	public GameFrame(){
		super("Space Invaders!");
		this.add(new GamePanel(4,2));
		//this.setBackground(Color.BLUE);
		//makeToolbar();
		this.pack();
		this.validate();
		this.setResizable(false);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
}