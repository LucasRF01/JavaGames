/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cave.entities;

/**
 *
 * @author Lucas
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Cave.entities.Bullet;
import Cave.entities.BulletShoot;
import Cave.entities.Entity;
import Cave.entities.Lifepack;
import Cave.entities.Weapon;
import Cave.graphics.Spritesheet;
import Cave.main.Game;
import Cave.world.Camera;
import Cave.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	public int up_dir = 0, left_dir = 1, right_dir = 2, down_dir = 3;
	public int dir = down_dir;
	public double speed = 1.5;
	
	private int frames = 0,maxFrames = 5,index = 0,maxIndex = 3;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	
	private BufferedImage playerDamage;
	
	private boolean arma = false;
	
	public int ammo;
	
	public boolean isDamaged = false;
	private int damageFrames = 0;
	
	public boolean shoot = false,mouseShoot = false;
	
	public double life = 100,maxlife=100;
	private double heal = Game.heal;
	public int mx,my;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		
		playerDamage = Game.spritesheet.getSprite(0, 16,16,16);
		
		for(int i =0; i < 4; i++){
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 16);
		}
		
		for(int i =0; i < 4; i++){
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 16, 16, 16);
		}
		
		for(int i =0; i < 4; i++){
			upPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 48, 16, 16);
		}
		
		for(int i =0; i < 4; i++){
			downPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 32, 16, 16);
		}
	}
	
	
	public void tick(){
		moved = false;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			moved = true;
			dir = right_dir;
			x+=speed;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			moved = true;
			dir = up_dir;
			y-=speed;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			moved = true;
			dir = down_dir;
			y+=speed;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		}
		
		checkCollisionLifePack();
		checkCollisionAmmo();
		checkCollisionGun();
		
		if(isDamaged) {
			this.damageFrames++;
			if(this.damageFrames == 8) {
				this.damageFrames = 0;
				isDamaged = false;
			}
		}
		
		if(shoot) {
			shoot = false;
			if(arma && ammo > 0) {
			ammo--;
			
			int dx = 0;
			int px = 0;
			int py = 6;
			if(dir == right_dir) {
				px = 18;
				dx = 1;
			}else if(dir == left_dir){
				px = -8;
				dx = -1;
			}else if(dir == up_dir){
				px = -8;
				dx = -1;
			}else if(dir == down_dir) {
				px = 18;
				dx = 1;
			}
			
			BulletShoot bullet = new BulletShoot(this.getX()+px,this.getY()+py,3,3,null,dx,0);
			Game.bullets.add(bullet);
			}
		}
		
		if(mouseShoot) {
			
			mouseShoot = false;
			
			
			if(arma && ammo > 0) {
			ammo--;

			int px = 0,py = 8;
			double angle = 0;
			if(dir == right_dir) {
				px = 18;
				angle = Math.atan2(my - (this.getY()+py - Camera.y),mx - (this.getX()+px - Camera.x));
			}else if(dir == left_dir){
				px = -8;
				angle = Math.atan2(my - (this.getY()+py - Camera.y),mx - (this.getX()+px - Camera.x));
			}else if(dir == up_dir){
				px = -8;
				angle = Math.atan2(my - (this.getY()+py - Camera.y),mx - (this.getX()+px - Camera.x));
			}
			else if(dir == down_dir){
				px = 18;
				angle = Math.atan2(my - (this.getY()+py - Camera.y),mx - (this.getX()+px - Camera.x));
			}
			
			double dx = Math.cos(angle);
			double dy = Math.sin(angle);
			
			BulletShoot bullet = new BulletShoot(this.getX()+px,this.getY()+py,3,3,null,dx,dy);
			Game.bullets.add(bullet);
			}
		}
		
		if(life<=0) {
			life = 0;
			Game.gameState = "GAME_OVER";
		}
		
		updateCamera();
	
	}
	
	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}
	
	public void checkCollisionGun() {
		for(int i = 0; i < Game.entities.size(); i++){
			Entity atual = Game.entities.get(i);
			if(atual instanceof Weapon) {
				if(Entity.isColidding(this, atual)) {
					arma = true;			
					Game.entities.remove(atual);
				}
			}
		}
	}
	
	public void checkCollisionAmmo() {
		for(int i = 0; i < Game.entities.size(); i++){
			Entity atual = Game.entities.get(i);
			if(atual instanceof Bullet) {
				if(Entity.isColidding(this, atual)) {
					ammo+=50;
					Game.entities.remove(atual);
				}
			}
		}
	}
	
	public void checkCollisionLifePack(){
		for(int i = 0; i < Game.entities.size(); i++){
			Entity atual = Game.entities.get(i);
			if(atual instanceof Lifepack) {
				if(Entity.isColidding(this, atual)) {
					life+=heal;
					if(life > 100)
						life = 100;
					Game.entities.remove(atual);
				}
			}
		}
	}
	
	

	public double getHeal() {
		return heal;
	}


	public void setHeal(double heal) {
		this.heal = heal;
	}


	public void render(Graphics g) {
		if(!isDamaged) {
			if(dir == right_dir) {
				g.drawImage(rightPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				if(arma) {
					g.drawImage(Entity.GUN_RIGHT, this.getX()+8 - Camera.x,this.getY() - Camera.y, null);
				}
			}else if(dir == left_dir) {
				g.drawImage(leftPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				if(arma) {
					g.drawImage(Entity.GUN_LEFT, this.getX()-8 - Camera.x,this.getY() - Camera.y, null);
				}
			}else if(dir == up_dir) {
				g.drawImage(upPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				if(arma) {
					g.drawImage(Entity.GUN_LEFT, this.getX()-8 - Camera.x,this.getY() - Camera.y, null);
				}
			}else if(dir == down_dir) {
				g.drawImage(downPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				if(arma) {
					g.drawImage(Entity.GUN_RIGHT, this.getX()+8 - Camera.x,this.getY() - Camera.y, null);
				}
			}
		}else {
			g.drawImage(playerDamage, this.getX()-Camera.x, this.getY() - Camera.y,null);
			if(arma) {
				if(dir == left_dir) {
					g.drawImage(Entity.GUN_DAMAGE_LEFT, this.getX()-8 - Camera.x,this.getY() - Camera.y, null);
				}else if(dir == right_dir){
					g.drawImage(Entity.GUN_DAMAGE_RIGHT, this.getX()+8 - Camera.x,this.getY() - Camera.y, null);
				}else if(dir == up_dir){
					g.drawImage(Entity.GUN_DAMAGE_LEFT, this.getX()-8 - Camera.x,this.getY() - Camera.y, null);
				}else if(dir == down_dir){
					g.drawImage(Entity.GUN_DAMAGE_RIGHT, this.getX()+8 - Camera.x,this.getY() - Camera.y, null);
				}
			}
		}
	}

}