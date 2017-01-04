package space;

import java.awt.Rectangle;

abstract class Ship {
	
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected boolean isAlive;
	
	public Ship(int xpos, int ypos){
		x = xpos;
		y = ypos;
		isAlive = true;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,height,width);
	}
	
	public void move(int dx, int dy){
		x+=dx;
		y+=dy;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void explode(){
		isAlive = false;
	}
	
	abstract void attack();
	
}