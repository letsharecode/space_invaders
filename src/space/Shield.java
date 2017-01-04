package space;

import java.awt.Rectangle;

public class Shield {
	private int x;
	private int y;
	private int height;
	private int width;
	
	
	public Shield(int x, int y){
		this.x = x;
		this.y = y;
		height = 10;
		width = 10;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}
}


//http://staticvoidgames.com/tutorials/intermediateConcepts/collisionDetection
//http://zetcode.com/tutorials/javagamestutorial/collision/
//http://www.edu4java.com/en/game/game6.html
//http://stackoverflow.com/questions/335600/collision-detection-between-two-images-in-java
//http://stackoverflow.com/questions/15690846/java-collision-detection-between-two-shape-objects