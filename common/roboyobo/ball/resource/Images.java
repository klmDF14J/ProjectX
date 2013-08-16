package roboyobo.ball.resource;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {
	
	public static Image rock, ship, shipDead, shockwave, bullet, onethreethreeseven;
	
	public static Image logo, button, buttonMO, tooltip;
	
	public static SpriteSheet shopSheet;
	public static ArrayList<Image> shopItems;
	
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
		tooltip = createImage("tooltip");
		
		shopSheet = new SpriteSheet("/resources/images/projectX/shopSheet.png", 40, 40);
		shopItems = new ArrayList<Image>();
		
		for(int i = 0; i < 40; i++) {
			for(int j = 0; j < 40; j++) {
				shopItems.add(shopSheet.getSprite(i, j));
			}
		}
	}
	
	
	private static Image createImage(String path) throws SlickException {
		Image img = new Image("/resources/images/projectX/" + path + ".png");
		
		return img;
	}
}
