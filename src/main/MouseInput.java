package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		
		//PLAY BUTTON
		if(mx >= Game.WIDTH / 2 +120 && mx <= Game.WIDTH/2 + 220){
			if(my>=150 && my <=200){
				Game.State = Game.STATE.GAME;
				
			}
		}
		//QUIT BUTTON
		if(mx >= Game.WIDTH / 2 +120 && mx <= Game.WIDTH/2 + 220){
			if(my>=250 && my <=300){
				System.exit(1);
				
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
