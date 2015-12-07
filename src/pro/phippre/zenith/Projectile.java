package pro.phippre.zenith;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Projectile {
	
	static LinkedList<Projectile> projectiles = new LinkedList<>();
	
	double x, y;
	
	int width, height;
	int range = 700;
	
	static int speed = 1;
	
	static double dx;
	static double dy;
	static double dir;
	static double nx;
	static double ny;
	static double mouseX;
	static double mouseY;
	static double screenX = Main.WIDTH / 2;
	static double screenY = Main.HEIGHT / 2;

	static int timer = 1000;
	static int spawnTimer = 100;
	
	public Projectile(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void init() {
		
	}
	
	public void update(Input input) {
		if (range > 0) {
			x += nx;
			y += ny;
			range--;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, width, height);
	}
	
	public static void shootProjectile(Input input) {
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		
		dx = mouseX - (Main.WIDTH / 2);
		dy = mouseY - (Main.HEIGHT / 2);
		
		dir = Math.atan2(dy, dx);
		
		nx = speed * Math.cos(dir);
		ny = speed * Math.sin(dir);
	}

	public static void spawnProjectile(Input input) {
		
		if(timer > 0) {
			timer--;
		}
		
		if (spawnTimer > 0) {
			spawnTimer--;
		} else if (spawnTimer <= 0) {
			spawnTimer = 200;
		}
		
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if (projectiles.size() < 5 && timer <= 0) {
				if (spawnTimer == 0) projectiles.add(new Projectile(Player.x + 8, Player.y + 8, 8, 8));
			}
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile.shootProjectile(input);
			projectiles.get(i).update(input);
			if (projectiles.get(i).range == 0) {
				projectiles.remove();
			}
		}
	}
	
	public static void renderProjectile(Graphics g) {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(g);
		}
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

}
