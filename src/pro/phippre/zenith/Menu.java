package pro.phippre.zenith;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {
	
	private float transStart = 0.0f;
	private float transEnd = 1.0f;
	
	Font awtFont;
	TrueTypeFont font;
	
	private Image bgImg;

	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		bgImg = new Image("res/menu/bgImg2.png");
		awtFont = new Font("Trajan Pro 3", Font.PLAIN, 30);
		font = new TrueTypeFont(awtFont, false);
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();
		int mX = input.getMouseX();
		int mY = input.getMouseY();
		if (mouseOver(mX, mY, Main.WIDTH / 2 - 80, 50, 100, 50)) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(1);
			}
		}
		
		if (mouseOver(mX, mY, Main.WIDTH / 2 - 80, 125, 100, 50)) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				sbg.enterState(2);
			}
		}  
		
		if (mouseOver(mX, mY, Main.WIDTH / 2 - 80, 200, 100, 50)) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				System.exit(0);
			}
		} 
	}

	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(bgImg, 0, 0);
		
		g.setFont(font);
		
		//Play Button
		g.drawGradientLine(Main.WIDTH / 2 - 175, 100, 255, 255, 255, transStart, Main.WIDTH / 2 - 35, 100, 255, 255, 255, transEnd);
		g.drawGradientLine(Main.WIDTH / 2 - 35, 100, 255, 255, 255, transEnd, Main.WIDTH / 2 + 125, 100, 255, 255, 255, transStart);
		g.drawString("Play", Main.WIDTH / 2 - 70, 60);
		//
		//Help Button
		g.drawGradientLine(Main.WIDTH / 2 - 175, 175, 255, 255, 255, transStart, Main.WIDTH / 2 - 35, 175, 255, 255, 255, transEnd);
		g.drawGradientLine(Main.WIDTH / 2 - 35, 175, 255, 255, 255, transEnd, Main.WIDTH / 2 + 125, 175, 255, 255, 255, transStart);
		g.drawString("Help", Main.WIDTH / 2 - 70, 135);
		//
		//Quit Button
		g.drawGradientLine(Main.WIDTH / 2 - 175, 250, 255, 255, 255, transStart, Main.WIDTH / 2 - 35, 250, 255, 255, 255, transEnd);
		g.drawGradientLine(Main.WIDTH / 2 - 35, 250, 255, 255, 255, transEnd, Main.WIDTH / 2 + 125, 250, 255, 255, 255, transStart);
		g.drawString("Quit", Main.WIDTH / 2 - 70, 210);
		//
	}

	public int getID() {
		return 0;
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}

}
