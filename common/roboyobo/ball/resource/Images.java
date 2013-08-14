package roboyobo.ball.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images {
	
	public static Image rock, ship, shipDead, shockwave, bullet;
	
	public static void init() throws SlickException {
		rock = createImage("asteroid");
		ship = createImage("ufo");
		shipDead = createImage("ufoDead");
		shockwave = createImage("shockwave");
		bullet = createImage("bullet");
	}
	
	
	private static Image createImage(String path) throws SlickException {
		Image img = new Image("/resources/images/projectX/" + path + ".png");
		
		return img;
	}
}
