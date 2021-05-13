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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Menu {
public String[] options = {"iniciar","sair"};
	
	public int currentOption = 0;
	public int maxOption = options.length - 1;
	
	public boolean up,down,enter;
	
	public boolean pause = false;
	
	
	public void tick() {
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0)
				currentOption = maxOption;
		}
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > maxOption)
				currentOption = 0;
		}
		if(enter) {
			enter = false;
			if(options[currentOption] == "iniciar" || options[currentOption] == "continuar") {
				Pong.gameState = "NORMAL";
				pause = false;
			}else if(options[currentOption] == "sair") {
				System.exit(1);
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(0, 0, Pong.WIDTH*Pong.SCALE, Pong.HEIGHT*Pong.SCALE);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.BOLD,36));
		
		g.drawString("PONG", (Pong.WIDTH*Pong.SCALE) / 2 - 40 , (Pong.HEIGHT*Pong.SCALE) / 2 - 160);
		

		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,24));
		if(pause == false) {
			g.drawString("iniciar", (Pong.WIDTH*Pong.SCALE) / 2 - 40, (Pong.HEIGHT*Pong.SCALE) / 2 - 100);
			}
		else {
			g.drawString("Resumir", (Pong.WIDTH*Pong.SCALE) / 2 - 40, (Pong.HEIGHT*Pong.SCALE) / 2 - 100);
			}
		g.drawString("Sair", (Pong.WIDTH*Pong.SCALE) / 2 - 40, (Pong.HEIGHT*Pong.SCALE) / 2 - 80);
		
		if(options[currentOption] == "iniciar") {
			g.drawString(">", (Pong.WIDTH*Pong.SCALE) / 2 - 60, (Pong.HEIGHT*Pong.SCALE) / 2 - 100);
		}else if(options[currentOption] == "sair") {
			g.drawString(">", (Pong.WIDTH*Pong.SCALE) / 2 - 60, (Pong.HEIGHT*Pong.SCALE) / 2 - 80);
		}
	}
	
}
