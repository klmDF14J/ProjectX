package roboyobo.ball.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images {
	
	public static Image rock;
	
	public static void init() throws SlickException {
		rock = createImage("asteroid");
	}
	
	
	private static Image createImage(String path) throws SlickException {
		Image img = new Image("/resources/images/projectX/" + path + ".png");
		
		return img;
	}
}
