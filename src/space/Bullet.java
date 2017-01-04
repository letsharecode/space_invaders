package space;
import java.awt.Rectangle;

public class Bullet {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int number;
	
	public Bullet(int x, int y, int number){
		this.x = x;
		this.y = y;
		this.number = number; //0=player, 1=invader, 2=mother ship
		width = 7;
		height = 18;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void move(int x, int y){
		this.x+=x;
		this.y+=y;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}
	
}