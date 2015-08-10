package main;

import entity.EntityA;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject implements EntityA{

	
	
	@SuppressWarnings("unused")
	private  Game game;
	private  Textures tex;
	public Bullet(double x , double y , Textures tex, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		
		
	}
	
	

        @Override
	public void tick(){
		
		y -= 10;
		
	}
        @Override
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);
	}
	
        @Override
	public void render(Graphics g){
		
		g.drawImage(tex.missile, (int)x,(int)y,null);
		
	}
	
        @Override
	public double getY(){
		return y;
	}
	
        @Override
	public double getX(){
		return x;
	}
}
