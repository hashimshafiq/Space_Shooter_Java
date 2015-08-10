package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Elements {
	
	Game game;
	
	Elements(Game game){
		this.game = game;
	}
	
	
	Font f = new Font("arial",Font.BOLD,20);
	public void render(Graphics g){
		
		g.setColor(Color.gray);
		g.fillRect(5,5,200,10);
		//g.setColor(Color.green);
		if(game.getHEALTH() >= 150){
			g.setColor(Color.green);
		}else if(game.getHEALTH() >= 100 && game.getHEALTH() <= 149){
			g.setColor(Color.orange);
		}else if(game.getHEALTH() >= 50 && game.getHEALTH() <= 99){
			g.setColor(Color.yellow);
		}else if(game.getHEALTH() >= 0 && game.getHEALTH() <= 49){
			g.setColor(Color.red);
		}
		
		
		g.fillRect(5,5,game.getHEALTH(),10);
		g.setColor(Color.white);
		g.drawRect(5,5,200,10);
		g.setFont(f);
		g.drawString("Score : ",500,20);
		
		String s = ""+game.getSCORE();
		g.drawString(s,600 ,20);
	
	}
}
