/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong;

/**
 *
 * @author Lucas
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Pong.Menu;

public class Pong extends Canvas implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JFrame frame;
	
	public static int HEIGHT = 160;
	public static int WIDTH = 240;
	public static int SCALE = 3;
	
	public static String gameState = "MENU";
	private boolean showMessageGameOver = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;
	public Menu menu;
	
	public static double speed, delay;
	
	public BufferedImage layer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	

	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Pong(double speed,double delay) {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(100, HEIGHT-5);
		enemy = new Enemy(100,0); 
		ball = new Ball(120,HEIGHT/2 - 1);
		Pong.speed = speed;
        Pong.delay = delay;
        enemy.setDelay(delay);
        ball.setSpeed(speed);
        
        menu = new Menu();
	}
		
        
        public void StartPong(){
        
            Pong pong = new Pong(speed,delay);
            frame = new JFrame("Pong");
            frame.add(pong);
            frame.setResizable(false);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
			
            new Thread(pong).start();
        }
        
        
	
    	public void tick() {
    		
    		if(gameState == "NORMAL") {
    				this.restartGame = false;	
    				player.tick();
    				enemy.tick();
    				ball.tick();
    			}else if(gameState == "GAME_OVER") {
    				this.framesGameOver++;
    				if(this.framesGameOver == 30) {
    					this.framesGameOver = 0;
    					if(this.showMessageGameOver)
    						this.showMessageGameOver = false;
    						else {
    								this.showMessageGameOver = true;
    							}
    				}
    				
    				if(restartGame) {
    					this.restartGame = false;
    					this.gameState = "NORMAL";
    				}
    			}else if(gameState == "MENU") {
    				
    				menu.tick();
    			}	
    		
    			
    		}
    	public void render() {
    		BufferStrategy bs = this.getBufferStrategy();
    		if(bs == null) {
    			this.createBufferStrategy(3);
    			return;
    		}
    		Graphics g = layer.getGraphics();
    		g.setColor(Color.black);
    		g.fillRect(0, 0, WIDTH, HEIGHT);
    		player.render(g);
    		enemy.render(g);
    		ball.render(g);
    		
    		g = bs.getDrawGraphics();
    		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
    		if(gameState == "GAME_OVER") {
    			Graphics2D g2 = (Graphics2D) g;
    			g2.setColor(new Color(0,0,0,100));
    			g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
    			g.setFont(new Font("arial",Font.BOLD,36));
    			g.setColor(Color.white);
    			g.drawString("Game Over",(WIDTH*SCALE) / 2 - 90,(HEIGHT* SCALE) / 2 - 20);
    			g.setFont(new Font("arial",Font.BOLD,32));
    			if(showMessageGameOver)
    				g.drawString(">Pressione Enter para reiniciar<",(WIDTH*SCALE) / 2 - 230,(HEIGHT* SCALE) / 2 + 40);
    		}else if(gameState == "MENU") {
    			menu.render(g);
    		}
    			
    		bs.show();
    	}
    	

    	public void run() {
    		while(true) {
    			tick();
    			render();
    			try {
    				Thread.sleep(1000/60);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    	}

    	public void keyPressed(KeyEvent e) {
    		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
    			player.right = true;
    		} else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
    			player.left = true;
    		}
    		if(e.getKeyCode() == KeyEvent.VK_UP ||
    				e.getKeyCode() == KeyEvent.VK_W){
    			if(gameState == "MENU") {
    				menu.up = true;
    			}
    		}else if(e.getKeyCode() == KeyEvent.VK_DOWN ||
    				e.getKeyCode() == KeyEvent.VK_S) {		
    			if(gameState == "MENU") {
    				menu.down = true;
    			}
    			
    		}
    		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
    			this.restartGame = true;
    			if(gameState == "MENU") {
    				menu.enter = true;
    			}
    		}
    		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
    			gameState = "MENU";
    			menu.pause = true;
    		}
    		
    	}

    	public void keyReleased(KeyEvent e) {
    		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
    			player.right = false;
    		} else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
    			player.left = false;
    		}
    		
    	}
    	

    	public void keyTyped(KeyEvent e) {
    		
    	}
    	
    	
    	

    }