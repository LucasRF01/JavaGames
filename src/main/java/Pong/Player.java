/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Lucas
 */
public class Player {
	
	public boolean right,left;
	
	public int x,y,width,height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}


	public void tick() {
		if(right) {
			x+=2;
		}else if(left) {
			x-=2;
		}
		
		
		if(x+width > Pong.WIDTH) {
			x = Pong.WIDTH - width;
		}
		else if(x < 0) {
			x = 0;
		}
	}
		
	

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x,y, width, height);
	}
	
	
}
