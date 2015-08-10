/*            
 *-----------------------------------------------------           
 *           SPACE INVADERS GAME
 * Coded by : Muhammad Hashim Shafiq [SP14-BCS-142]
 * Programming Language : Java 
 * Required Software : Eclipse
 * Contact Email : hashimshafiq0@gmail.com
 * ----------------------------------------------------
 * 
 * This class contains the Main Method
 * 
 * */


package main;

// EntityA and EntityB Class Importing 
import entity.EntityA;
import entity.EntityB;


//Some Useful Libraries Importing
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

// Canvas control represents a rectangular area where application 
// can draw something or can receive inputs created by user.
// Runnable interface represent a Task which can be executed by 
// either plain Thread or Executors or any other means. 
public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	
	public final String TITLE = "Space Invaders [Muhammad Hashim Shafiq]";
	private boolean running = false;
	private Thread thread;
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	
	private Player p;
	private Controller c;
	private Menu menu;
	private Elements e;
	private boolean is_shooting = false;
	private int enemy_count = 5;
	private int enemy_killed = 0;
	private String pname;
	public static int HEALTH = 200;
	private int SCORE = 0;
	private Textures tex;
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	public static enum STATE{
		MENU,
		GAME
		
	};
	public static STATE State = STATE.MENU;
	
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			spriteSheet = loader.loadImage("sprite_sheet.png");
			background = loader.loadImage("spacebackground.png ");
			
		}catch(IOException e){
			e.printStackTrace();
			//System.out.println("Error : Image File Missing !");
			//JOptionPane.showMessageDialog(null,"Error !","Error Reading Image Files",1);
		}
		
		// Order is compulsory. Don't change it.
		
		tex = new Textures(this);
		c = new Controller(tex,this);
		p = new Player(200,200,tex,this,c);
		menu = new Menu();
		e = new Elements(this);
		ea = c.getEntityA();
		eb= c.getEntityB();
		c.createEnemy(enemy_count);
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		
		
	}
	
	private synchronized void start(){
		pname = JOptionPane.showInputDialog(null,"Enter your Name : ","Space Invaders", 1);
		
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	private synchronized void stop(){
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.exit(1);
		
		
	}
        @Override
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				
				timer += 1000;
				System.out.println(updates + " Ticks , fps "+frames);
				updates = 0;
				frames = 0;
			}
		
		
	}	
		stop();
	}
	
	private void tick(){
		if(State == STATE.GAME){
			p.tick();
			c.tick();
		}
		if(enemy_killed >= enemy_count){
			enemy_count +=2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
			
		}
		
	}
	
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs== null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//////////////////////////////////////////////
		
		
		g.drawImage(background,0,0,null);
		if(State == STATE.GAME){
			p.render(g);
			c.render(g);
			e.render(g);
			  
		}else{
			
			menu.render(g);
			
		}
		/////////////////////////////////////////////
		
		g.dispose();
		bs.show();
		
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(State == STATE.GAME){
			if(key == KeyEvent.VK_RIGHT){
				p.setVelX(5);
			}else if(key == KeyEvent.VK_LEFT){
				p.setVelX(-5);
			}else if(key == KeyEvent.VK_DOWN){
				p.setVelY(5);
			}else if(key == KeyEvent.VK_UP){
				p.setVelY(-5);
			}else if(key == KeyEvent.VK_SPACE && !is_shooting){
					is_shooting = true;
					c.addEntity(new Bullet(p.getX(),p.getY(),tex,this));
				}
			}
		}
	
	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			p.setVelX(0);
		}else if(key == KeyEvent.VK_LEFT){
			p.setVelX(0);
		}else if(key == KeyEvent.VK_DOWN){
			p.setVelY(0);
		}else if(key == KeyEvent.VK_UP){
			p.setVelY(0);
		}else if(key == KeyEvent.VK_SPACE){
			is_shooting = false;
		}
		
	}
	public static void main(String[] args){
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
                
		
		game.start();
		
		}
	
		public BufferedImage getSpriteSheet(){
			return spriteSheet;
		}

		public int getEnemy_count() {
			return enemy_count;
		}

		public void setEnemy_count(int enemy_count) {
			this.enemy_count = enemy_count;
		}

		public int getEnemy_killed() {
			return enemy_killed;
		}

		public void setEnemy_killed(int enemy_killed) {
			this.enemy_killed = enemy_killed;
		}
		
		public  int getHEALTH(){
			return HEALTH;
		}
		
		public int getSCORE(){
			return SCORE;
		}
		
		public void setSCORE(int score){
			this.SCORE = score;
		}
		
		public String getPNAME(){
			
			return pname;
		}
		
		
}
