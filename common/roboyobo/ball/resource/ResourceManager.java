package roboyobo.ball.resource;

import org.newdawn.slick.SlickException;

public class ResourceManager {
	
	
	public static void init() throws SlickException {
		Images.init();
		BackgroundManager.init();
		Sounds.init();
	}
}
