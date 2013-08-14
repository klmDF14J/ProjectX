package roboyobo.ball.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BackgroundManager {
	
	public static String[] backgroundNames = {"menu", "game"};
	public static Image[] backgrounds;
	
	public static Image bgNotFound;
	
	public static void init() throws SlickException {
		backgrounds = new Image[backgroundNames.length];
		
		for(int i = 0; i < backgrounds.length; i++) {
			backgrounds[i] = createBackground(backgroundNames[i]);
		}
		
		bgNotFound = createBackground("bgNotFound");
	}
	
	private static Image createBackground(String path) throws SlickException {
		Image img = new Image("/resources/images/projectX/backgrounds/" + path + ".png");
		
		return img;
	}
	
	public static Image getBackgroundForState(String state) {
		Image img = bgNotFound;
		for(int i = 0; i < backgrounds.length; i++) {
			if(state == backgroundNames[i]) {
				img  = backgrounds[i];
			}
		}
		return img;
	}
}
