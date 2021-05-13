/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Cave.main.Game;

/**
 *
 * @author Lucas
 */
public class Ball {

	protected double x,y;
	private int width,height;
	
	private boolean enemyscore = false;
	private boolean playerscore = false;
	
	private double dx,dy;
	private double speed;
	
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		int angle = new Random().nextInt(120 - 45) + 45;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
    public void setSpeed(double speed) {
    	this.speed = speed;
    }
    
	public void tick() {
		
		if(x+(dx*speed) + width >= Pong.WIDTH) {
			dx *= -1;
		}else if(x+(dx*speed) + width <= 0) {
			dx *= -1;
		}
		
		if(y > Pong.HEIGHT) {
			enemyscore = true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			new Pong(Pong.speed, Pong.delay);
			return;
		}else if(y < 0) {
			playerscore = true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new Pong(Pong.speed, Pong.delay);
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
		Rectangle boundsPlayer = new Rectangle(Pong.player.x,Pong.player.y,Pong.player.width,Pong.player.height);	
		Rectangle boundsEnemy = new Rectangle((int)Pong.enemy.x,(int)Pong.enemy.y,Pong.enemy.width,Pong.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			
			int angle = new Random().nextInt(120 - 45) + 45;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			
			if(dy > 0) {
				dy*=-1;
			}
			
		}
		else if (bounds.intersects(boundsEnemy)){
			
			int angle = new Random().nextInt(120 - 45) + 45;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			
			if(dy < 0) {
				dy*=-1;
			}
		}
		
		x += dx*speed;
		y += dy*speed;
			
	}
	
        
	

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y, width, height);
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("arial",Font.BOLD,24));
		
		if(playerscore == true) {
			g.drawString("seu ponto", (Game.WIDTH*Game.SCALE) / 2, (Game.HEIGHT*Game.SCALE) / 2);
		}else if(enemyscore == true) {
			g.drawString("ponto inimigo", (Game.WIDTH*Game.SCALE) / 2, (Game.HEIGHT*Game.SCALE) / 2);
		}
		
	}
	
	
	public void renderPoint() {
		
	}
	
	
}
