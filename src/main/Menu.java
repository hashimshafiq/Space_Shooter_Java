package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH/2 +120,150,100,50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2 +120,250,100,50);
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("Cambria",Font.BOLD,50);
		g.setFont(font0);
		g.setColor(Color.orange );
		g.drawString("Space Invaders", Game.WIDTH/2, 100);
		
		Font font1 = new Font("arial",Font.BOLD,30);
		Font font2 = new Font("Times New Roman",Font.BOLD,25);
		g.setFont(font1);
		g.setColor(Color.green);
		g.drawString("Play",playButton.x + 20, playButton.y+32);
		g2d.draw(playButton);
		g.setColor(Color.red );
		g.drawString("Quit",quitButton.x + 20, quitButton.y+32);
		g2d.draw(quitButton);
		g.setFont(font2);
		g.setColor(Color.white);
		g.drawString("Coded by",quitButton.x, quitButton.y+120);
		g.drawString("Muhammad Hashim Shafiq",quitButton.x - 75, quitButton.y+150);
		
	}

}
