package roboyobo.ball.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images {
	
	public static Image rock, ship, shipDead, shockwave, bullet, onethreethreeseven;
	
	public static Image logo, button, buttonMO;
	
	public static void init() throws SlickException {
		rock = createImage("asteroid");
		ship = createImage("ufo");
		shipDead = createImage("ufoDead");
		shockwave = createImage("shockwave");
		bullet = createImage("bullet");
		onethreethreeseven = createImage("1337");
		
		logo = createImage("logo");
		button = createImage("button");
		buttonMO = createImage("buttonMO");
	}
	
	
	private static Image createImage(String path) throws SlickException {
		Image img = new Image("/resources/images/projectX/" + path + ".png");
		
		return img;
	}
}
