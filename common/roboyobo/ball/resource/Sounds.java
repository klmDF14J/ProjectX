package roboyobo.ball.resource;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds {
	
	public static Sound fire, select, shockwave, total;
	
	public static void init() throws SlickException {
		fire = new Sound("/resources/sounds/projectX/fire.wav");
		select = new Sound("/resources/sounds/projectX/select.wav");
		shockwave = new Sound("/resources/sounds/projectX/shockwave.wav");
		total = new Sound("/resources/sounds/projectX/total.wav");
	}
}
