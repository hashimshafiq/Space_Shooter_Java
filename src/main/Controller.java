package main;


import entity.EntityB;
import entity.EntityA;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;


public class Controller {

	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	
	EntityA enta;
	EntityB entb;
	private Textures tex;
	private Game game;
	Random r = new Random();
	public Controller(Textures tex,Game game){
		
		this.tex = tex;
		this.game = game;
		
	}
	
	public void createEnemy(int enemy_count){
		for(int i=0;i<enemy_count;i++)
			addEntity(new Enemy(r.nextInt(640),-10,tex,this,game));
	}
	
	
	
	
	
	public void tick(){
		// A class
		for(int i =0; i<ea.size();i++){
			enta = ea.get(i);
			enta.tick();
		}
		
		// B class
				for(int i =0; i<eb.size();i++){
					entb = eb.get(i);
					entb.tick();
				}
	}
	
	public void render(Graphics g){
		// A Class
		for(int i =0; i<ea.size();i++){
			enta = ea.get(i);
			enta.render(g);
		}
		
		// B Class
				for(int i =0; i<eb.size();i++){
					entb = eb.get(i);
					entb.render(g);
				}
	}
	
	public void addEntity(EntityA block){
		
		ea.add(block);
	}
	
	public void removeEntity(EntityA block){
		
		ea.remove(block);
	}
	
	public void addEntity(EntityB block){
		
		eb.add(block);
	}
	
	public void removeEntity(EntityB block){
		
		eb.remove(block);
	}
	
	public LinkedList<EntityA> getEntityA(){
		return ea;
	}
	public LinkedList<EntityB> getEntityB(){
		return eb;
	}
	}
