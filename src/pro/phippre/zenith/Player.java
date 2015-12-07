package pro.phippre.zenith;

import java.awt.Rectangle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player {
	
	public static float x;
	public static float y;
	float dx, dy;
	
	int width, height;
	
	double velX = 0.4;
	double velY = 0.4;
	double bulletVel = 1.0;
	
	Image img;
	SpriteSheet sp;
	
	public Player(Image img, int x, int y, int width, int height) {
		this.img = img;
		this.width = width;
		this.height = height;
	}
	
	public void init() throws SlickException {
		sp = new SpriteSheet(img, 32, 32);
		img = sp.getSubImage(1, 0);
	}
	
	public void update(GameContainer container, int delta, Map map) {
		Input input = container.getInput();
		move(input, map, delta, dx, dy, velX, velY);
		Projectile.spawnProjectile(input);
	}
	
	public void render(Graphics g) {
		g.drawImage(img, x, y);
		Projectile.renderProjectile(g);
	}
	
	public void move(Input input, Map map, int delta, float dx, float dy, double velX, double velY) {
		dx = 0;
		dy = 0;

		if (input.isKeyDown(Input.KEY_D)) {
			img = sp.getSubImage(1, 2);
			dx += velX * delta;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			img = sp.getSubImage(1, 1);
			dx -= velX * delta;
		} 
		if (input.isKeyDown(Input.KEY_W)) { 
			img = sp.getSubImage(1, 3);
			dy -= velY * delta;
		} 
		if (input.isKeyDown(Input.KEY_S)) {
			img = sp.getSubImage(1, 0);
			dy += velY * delta;
		}  

		if (dx != 0 && dy != 0) {
			move(input, map, delta, dx, dy, velX, 0);
			move(input, map, delta, dx, dy, 0, velY);
			return;
		}
		
		if (!collision(map, dx, dy)) {
			x += dx;
			y += dy;
		}
	}
	
	public boolean collision(Map map, float dx, float dy) {
		boolean collision = false;
		Rectangle player = new Rectangle((int) x, (int) y, width, height);
		if (dx != 0 || dy != 0) {
			
			if (dx > 0) {
				//Right
				player.x = (int) (x + dx);
			}
			
			if (dx < 0) {
				//Left
				player.x = (int) (x + dx);
			}
			
			if (dy < 0) {
				//Up
				player.y = (int) (y + dy);
			}
			
			if (dy > 0) {
				//Down
				player.y = (int) (y + dy);
			}
			
			for (int i = 0; i < map.tiles.size(); i++) {
				if (player.intersects(map.tiles.get(i))) {
					collision = true;
				}
			}
		}
		return collision;
	}
	
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

}
