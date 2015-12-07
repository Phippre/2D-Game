package pro.phippre.zenith;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	
	public static int WIDTH = 1366;
	public static int HEIGHT = 768;

	public Main(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main("Zenith"));

		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.setAlwaysRender(true);
		app.setTargetFrameRate(1500);
		//app.setVSync(true);
		app.start();
	}

	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new Menu());
		this.addState(new Game());
		this.addState(new Help());
	}

}
