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
public class Enemy {
	
	public double x,y;
	public int width,height;
    private double delay;
    
    public void setDelay(double delay) {
        this.delay = delay;
    }
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}

	public void tick() {
		x += (Pong.ball.x - x - 8)*delay;
	}
		
	

	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int)x,(int)y, width, height);
	}
     
        
	
}
