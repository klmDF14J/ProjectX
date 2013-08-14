package roboyobo.ball.util;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;



public class FontHelper {
	public static UnicodeFont setupAndReturnNewFont(String par2, int par3) throws SlickException {
		if(par3 > 72) { par3 = 72; }
		UnicodeFont font = new UnicodeFont("/resources/images/projectX/" + par2 + ".ttf", par3, false, false);
		font.addAsciiGlyphs();
		font.addGlyphs(400, 600);
		font.getEffects().add(new ColorEffect());
		font.loadGlyphs();
		return font;
	}
	
	public static int getWidthDifference(UnicodeFont font, String par1) {
		return (300 - font.getWidth(par1)) / 2;
	}
	
	public static int getHeightDifference(UnicodeFont font, String par1) {
		return (100 - font.getHeight(par1)) / 2;
	}
	
	public static int getBigWidthDifference(UnicodeFont font, String par1) {
		return (GameInfo.GAME_OVER_PANEL_WIDTH - font.getWidth(par1)) / 2;
	}
	
	public static int getBigHeightDifference(UnicodeFont font, String par1) {
		return (GameInfo.GAME_OVER_PANEL_HEIGHT - font.getHeight(par1)) / 2;
	}
}
