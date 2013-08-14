package roboyobo.ball.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;

import roboyobo.ball.resource.Images;
import roboyobo.ball.util.GameInfo;

public class HUD {
	
	
	public static void render(Graphics g, UnicodeFont statFont, UnicodeFont statFont2, UnicodeFont statFont3) {
		g.setColor(Color.gray);
		g.fill(new Rectangle(0, 0, 150 * GameInfo.settings.hudScale, 10 * GameInfo.settings.hudScale));
		g.fill(new Rectangle(0, 55 * GameInfo.settings.hudScale, 150 * GameInfo.settings.hudScale, 10 * GameInfo.settings.hudScale));
		g.fill(new Rectangle(0, 0, 10 * GameInfo.settings.hudScale, 65 * GameInfo.settings.hudScale));
		g.fill(new Rectangle(140 * GameInfo.settings.hudScale, 0, 10 * GameInfo.settings.hudScale, 65 * GameInfo.settings.hudScale));
		
		g.setColor(new Color(0, 0, 0, 0.5F));
		g.fill(new Rectangle(10 * GameInfo.settings.hudScale, 10 * GameInfo.settings.hudScale, 130 * GameInfo.settings.hudScale, 45 * GameInfo.settings.hudScale));
		
		Images.rock.draw(15 * GameInfo.settings.hudScale, 15 * GameInfo.settings.hudScale, 0.7F * GameInfo.settings.hudScale);
		
		if(GameInfo.DEAD_ROCKS >= 0 && GameInfo.DEAD_ROCKS < 1000) { 
			statFont.drawString(70 * GameInfo.settings.hudScale, (((65 * GameInfo.settings.hudScale) - statFont.getHeight("" + GameInfo.DEAD_ROCKS)) / 2), "" + GameInfo.DEAD_ROCKS);
		}
		else if(GameInfo.DEAD_ROCKS >= 1000 && GameInfo.DEAD_ROCKS < 10000) {
			statFont2.drawString(70 * GameInfo.settings.hudScale, (((65 * GameInfo.settings.hudScale) - statFont2.getHeight("" + GameInfo.DEAD_ROCKS)) / 2), "" + GameInfo.DEAD_ROCKS);
		}
		else if(GameInfo.DEAD_ROCKS >= 10000) {
			statFont3.drawString(70 * GameInfo.settings.hudScale, (((65 * GameInfo.settings.hudScale) - statFont3.getHeight("" + GameInfo.DEAD_ROCKS)) / 2), "" + GameInfo.DEAD_ROCKS);
		}
	}
}
