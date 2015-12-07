package pro.phippre.zenith;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	
	Player player;
	Camera cam;
	Map map = new Map();

	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		map.init();
		cam = new Camera(0, 0);
		player = new Player(new Image("res/textures/playerSheet.png"), Main.WIDTH / 2, Main.HEIGHT / 2, 32, 32);
		player.init();
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		cam.tick(player);
		player.update(container, delta, map);
	}

	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.scale(1.5f, 1.5f);
		g.translate(-cam.getX(), -cam.getY());
			map.render();
			player.render(g);
		g.translate(cam.getX(), cam.getY());
	}

	public int getID() {
		return 1;
	}

}
