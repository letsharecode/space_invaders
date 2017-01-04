package space;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{
	
	private static final int DEF_START_X = 370;
	private static final int DEF_START_Y = 550;
	private static final int BULLET_DELAY = 12;
	private static final int iInc = 50;
	private static final int mInc = 500;
	private static final int bDec = -10;
	private static final int sDec = -25;
	private static final int RADIUS = 20;
	private static int INIT_ALIENS;
	private static int INIT_ROWS;
	public static int LEVEL = 1;
	
	DefenderShip player;
	List<InvaderShip> invaders = new ArrayList<InvaderShip>();
	List<Bullet> invaderBullets = new ArrayList<Bullet>(),
				 defenderBullets = new ArrayList<Bullet>();
	List<Shield> shieldList = new ArrayList<Shield>();
	MotherShip m;
	
	Image invader, mothership, defender, shield;
	Image defender_bullet, invader_bullet, mothership_bullet, explosion;
	
	static int numClicks, numBullets;
	static int numAliens, score;
	
	int relaunch = 0;
	
	boolean motherShipDead;
	boolean hasBegun;
	boolean gameOver;
	Timer gameTimer;
	Random rand = new Random();
	
	private int dx=2;
	private int mdx=1;
	private int dy=10;
	
	ACalculusGame a;
	
	public GamePanel(int total, int rows) {
		a = new ACalculusGame();
		a.showSet1();
		this.setPreferredSize(new Dimension(950,600));//740
		setBackground(Color.BLACK);
		numClicks = 0;
		hasBegun = false;
		gameOver = false;
		motherShipDead = true;
		gameTimer = new Timer(10, this);
		
		try {invader = ImageIO.read(this.getClass().getResource("/invader.gif"));} catch (IOException e) {}
		try {mothership = ImageIO.read(this.getClass().getResource("/mothership.gif"));} catch (IOException e) {}
		try {defender = ImageIO.read(this.getClass().getResource("/defender.gif"));} catch (IOException e) {}
		try {explosion = ImageIO.read(this.getClass().getResource("/explosion.gif"));} catch (IOException e) {}
		try {defender_bullet = ImageIO.read(this.getClass().getResource("/defender_bullet.gif"));} catch (IOException e) {}
		try {invader_bullet = ImageIO.read(this.getClass().getResource("/invader_bullet.gif"));} catch (IOException e) {}
		try {mothership_bullet = ImageIO.read(this.getClass().getResource("/mothership_bullet.gif"));} catch (IOException e) {}
		try {shield = ImageIO.read(this.getClass().getResource("/shield.gif"));} catch (IOException e) {}
		
		setUpKeyBindings();
		addInvaders(total,rows);
		INIT_ALIENS = total;
		INIT_ROWS = rows;
		addDefender();
		addShield();
		
	}
	
	private void setUpShield(Graphics g){
		for(Shield s : shieldList)
			g.drawImage(shield,s.getX(),s.getY(),15,15,null);
	}
	
	private void addShield() {
		for(int c = 0; c < 4; c++)
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					shieldList.add(new Shield(150*c+15*i+100,15*j+450));
				}
			}
	}

	private void addDefender() {
		 player = new DefenderShip(DEF_START_X,DEF_START_Y);
	}

	private void addInvaders(int total, int row) {
		numAliens = total+1;
		for(int i=0; i<total; i++){
			int spacing = 640/(total/row);
			int r = i/row;
			int c = i%row;
			invaders.add(new InvaderShip(spacing*r+50,40*c+50));
		}
		if(this.LEVEL>=4)
			m = new MotherShip(200, 25);
	}

	private void setUpInvaders(Graphics g) {
		for(InvaderShip i : invaders)
			g.drawImage(invader, i.getX(), i.getY(), i.getWidth(), i.getHeight(), null);
		if(this.LEVEL>=4)
			g.drawImage(mothership, m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
	}
	
	private void setUpDefender(Graphics g) {
		if(gameOver==false)
			g.drawImage(defender, player.getX(), player.getY(), 32, 32, null);
	}
	
	private void setUpBullets(Graphics g){
		for(Bullet b : defenderBullets)
			g.drawImage(defender_bullet, b.getX(), b.getY(), 7, 18, null);
		for(Bullet b : invaderBullets)
			if(b.getNumber()==1)
				g.drawImage(invader_bullet, b.getX(), b.getY(), 7, 18, null);
			else if(b.getNumber()==2)
				g.drawImage(mothership_bullet, b.getX(), b.getY(), 7, 18, null);
	}
	
	private void setUpKeyBindings() {
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"fire");
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
		this.getActionMap().put("fire",
				new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						launchWeapon();
					}
		});
		this.getActionMap().put("right",
				new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setDefenderDir(1);// 1 moves right, 0 moves left
					}
		});
		this.getActionMap().put("left", 
				new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setDefenderDir(0);
					}
		});
	}

	protected void setDefenderDir(int i) { //why protected
		if(i == 0) {
			//System.out.println("Ship will move left");
			if(player.getX()>50){
				player.move(-3, 0);
				if(hasBegun==false){
					hasBegun=true;
					start();
				}
			}
		}
		
		else if(i == 1) {
			//System.out.println("Ship will move right");
			if((player.getX()+player.getWidth())<690){
				player.move(3, 0);
				if(hasBegun==false){
					hasBegun=true;
					start();
				}
			}
		}
			
	}
	
	protected void launchWeapon() {
		//defender launches
		//System.out.println("Launching weapon now!!");
	    if (relaunch <= 0) {
	        relaunch = BULLET_DELAY;
	    	defenderBullets.add(new Bullet(player.getX()+15,player.getY()-10,0));
			numBullets++;
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// what do you want to do every 100th of a second?
		//System.out.println("just clicked! "+numClicks);
		relaunch--;
		checkForCollision();
		moveEverything();
		repaint();
		if(gameOver){
			gameTimer.stop();
			repaint();
			a.showAnswers();
			JOptionPane.showMessageDialog(this, "Game Over");
		}
		else if(invaders.size()==0&&motherShipDead){
			gameTimer.stop();
			restartGame();
		}
	}
	
	private void restartGame() {
		this.LEVEL++;
		invaderBullets.clear();
		defenderBullets.clear();
		shieldList.clear();
		switch(this.LEVEL){
			case 2: addInvaders(8,2); numAliens+=5; motherShipDead = true; break;
			case 3: addInvaders(16,4); numAliens+=9; motherShipDead = true; break;
			case 4: addInvaders(32,4); numAliens+=17; motherShipDead = false; break;
			case 5: addInvaders(32,2); numAliens+=33; motherShipDead = false; break;
			case 6: JOptionPane.showMessageDialog(this, "You Won!", "Game Over", JOptionPane.INFORMATION_MESSAGE); break;
		}
		
		switch(this.LEVEL){
		case 2: a.showSet2(); break;
		case 3: a.showSet3(); break;
		case 4: a.showSet4(); break;
		case 5: a.showSet5(); break;
		}
		
		
		if(a.getQuestionNumber()>5&&a.findPercent()<50)
			gameOver = true;
		else{
			gameOver = false;
			addDefender();
			addShield();
			hasBegun = false;
		}
		
		gameTimer = new Timer(10, this);
		gameTimer.start();
	}
	
	private void printStatistics() {
		System.out.println("You played for "+numClicks/100+" seconds!");
		System.out.println("Shots Fired " + numBullets);
		System.out.println("Hits "+(numAliens-1-invaders.size()));
		System.out.println("Hit-Miss Ratio " + (numAliens-1-invaders.size())/(double)numBullets);
		System.out.println("Final Score " + score); //-10 bullet, +50 invader
	}
	
	private void checkForCollision() {
		
		Rectangle rPlayer, rInvader, rPlayerBullet, rInvaderBullet, rShield;
		Rectangle rMotherShip = new Rectangle(1000,1000,1,1);
		
		rPlayer = player.getBounds(); //invader hits player
		for (int j=invaders.size()-1; j>=0; j--) {
            InvaderShip i = invaders.get(j);
            rInvader = i.getBounds();
            if (rPlayer.intersects(rInvader)) {
            	invaders.remove(j);
            	gameOver = true;
            	System.out.println("You were hit by an invader!");
            }
        }
		
		for (int j=invaderBullets.size()-1; j>=0; j--) { //invader bullet hits player, player bullet, shield
            rInvaderBullet = invaderBullets.get(j).getBounds();
            if (rPlayer.intersects(rInvaderBullet)){
            	invaderBullets.remove(j);
            	gameOver = true;
            	System.out.println("You were hit by a bullet!");
            }
            else{
            	for(int k=shieldList.size()-1; k>=0; k--){
            		rShield = shieldList.get(k).getBounds();
            		if(rShield.intersects(rInvaderBullet)){
                    	invaderBullets.remove(j);
                    	shieldList.remove(k);
                    	score+=sDec;
                    	//System.out.println("Part of shield destroyed!");
            		}
            		else{
                    	for(int l=defenderBullets.size()-1; l>=0; l--){
                    		rPlayerBullet = defenderBullets.get(l).getBounds();
                    		if(rPlayerBullet.intersects(rInvaderBullet)){
                    			invaderBullets.remove(j);
                    			defenderBullets.remove(l);
                    			score+=bDec;
                    		}
                    	}
            		}
            	}
            }
		}

        for (int i=0; i<defenderBullets.size(); i++) { //player's bullet hits invader
        	Bullet b = defenderBullets.get(i);
        	rPlayerBullet = b.getBounds();
        	for (int j = 0; j<invaders.size(); j++) {
        		InvaderShip ship = invaders.get(j);
        		rInvader = ship.getBounds();
        		if(this.LEVEL>=4)
        			rMotherShip = m.getBounds();
        		
        		if (rPlayerBullet.intersects(rInvader)) {
        			invaders.remove(j);
        			defenderBullets.remove(i);
        			score+=iInc;
        			break;
                }
        		else if(this.LEVEL>=4&&rPlayerBullet.intersects(rMotherShip)){
        			motherShipDead = true;
        			m.setPosition(825, 125);
        			defenderBullets.remove(i);
        			score+=mInc;
        			break;
        		}
            }
        }
        
	}
	
	private void moveEverything() {
		numClicks++;
		if(this.LEVEL>=4)
			if(!motherShipDead&&numClicks%2==0){ //moves mother ship left AND right
				if(m.getX()-player.getX()>RADIUS){
					if(m.getX()>690||m.getX()<50){
						mdx = -mdx;
						m.move(mdx, 0);
					}
					else{
						m.move(-Math.abs(mdx), 0);
					}
				}
				else if(m.getX()-player.getX()<-RADIUS){
					if(m.getX()>690||m.getX()<50){
						mdx = -mdx;
						m.move(mdx, 0);
					}
					else{
						m.move(Math.abs(mdx), 0);
					}
				}
			}
		
		if(numClicks%5==0){ //moves invaders left AND right
			for(InvaderShip a : invaders)
				if(a.getX()>690||a.getX()<50){
					dx = -dx;
					break;
				}
			for(InvaderShip a : invaders)
				a.move(dx, 0);
		}
		
		if(numClicks%(200)==0) //moves invaders down
			for(InvaderShip a : invaders)
				a.move(0,2*dy);
		
		
		if(numClicks%20==0){ //creates bullet originating from a random invader
			int endRow=0;
			for(int i = 0; i < invaders.size(); i++){
				int r = invaders.get(i).getY();
				if(r>endRow)
					endRow = r;
			}
			
			if(invaders.size()>0){
				List<InvaderShip> endRowInvaders = new ArrayList<InvaderShip>();
				for(InvaderShip s :invaders)
					if(s.getY()==endRow)
						endRowInvaders.add(s);
				int fighterIndex = rand.nextInt(endRowInvaders.size());
				InvaderShip i = endRowInvaders.get(fighterIndex);
				invaderBullets.add(new Bullet(i.getX()+i.getWidth()/2,i.getY()+5,1));
			}
		}
		
		if(this.LEVEL>=4)
			if(numClicks%15==0&&!motherShipDead){ //mother ship bullet
				invaderBullets.add(new Bullet(m.getX()+m.getWidth()/2,m.getY()+5,2));
			}
		
		for(int i = defenderBullets.size()-1; i >= 0; i--){ //moves defender bullet up
			Bullet b = defenderBullets.get(i);
			if(b.getY()>25)
				b.move(0,-10);
			else
				defenderBullets.remove(i);
		}
		
		if(numClicks%2==0) //moves invader bullet down
			for(int i = invaderBullets.size()-1; i >= 0; i--){
				Bullet b = invaderBullets.get(i);
				if(b.getY()<575)
					b.move(0,3);
				else
					invaderBullets.remove(i);
			}
		}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setUpDefender(g);
		setUpBullets(g);
		setUpInvaders(g);
		setUpShield(g);
		drawStrings(g);
	}
	
	private void drawStrings(Graphics g) {
		g.setFont(new Font("Serif", Font.BOLD, 24));
		g.setColor(Color.WHITE);
		g.drawString(new String("SPACE INVADERS"), 740, 75);
		g.drawString(new String("Score"), 740, 125);
		g.drawString(new String(Integer.toString(score)), 740, 150);
		/*if((invaders.size()==0&&motherShipDead)||gameOver){
			g.setFont(new Font("Serif", Font.BOLD, 36));
			int yFin = 600;
			for(int i=0;i<invaders.size();i++){
				if(invaders.get(i).getY()<yFin)
					yFin = invaders.get(i).getY();
			}
			g.drawString("GAME OVER", 370-75, yFin-30);
		
			g.setFont(new Font("Serif", Font.BOLD, 18));
			g.drawString("You played for " + numClicks/100 + " seconds!", 740, 200);
			g.drawString("Shots Fired " + numBullets, 740, 220);		
			g.drawString("Alien Hits "+(numAliens-1-invaders.size()), 740, 240);
			g.drawString("Hit-Miss Ratio " + String.format("%.4f", (numAliens-1-invaders.size())/(double)numBullets), 740, 260);
		}*/
	}
	
	public void start() {
		//System.out.println("Just started a new game...");
		this.gameTimer.start();
		
	}
	
	public class Scheduler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			moveEverything();
			checkForCollision();
			repaint();
			if((invaders.size()==0&&motherShipDead)||gameOver){
				gameTimer.stop();
				repaint();
				printStatistics();
			}
		}
	}
	
}