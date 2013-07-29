package roboyobo.ball.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.BouncyBall;
import roboyobo.ball.FontHelper;
import roboyobo.ball.LanguageHandler;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.GameInfo;

public class MenuState extends BasicGameState {

	private int stateID;
	
	private int x = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private int y = GameInfo.SCREEN_HEIGHT / 2 - (GameInfo.GAME_OVER_PANEL_HEIGHT / 2);
	
	private UnicodeFont font, smallFont, fontToUse;
	
	private static MouseOverArea play, multiplayer, options;
	
	public MenuState(int stateID, GameContainer gc, final BouncyBall bb) throws SlickException {
		this.stateID = stateID;
		
		font = FontHelper.setupAndReturnNewFont("font", 48);
		smallFont = FontHelper.setupAndReturnNewFont("font", 36);
		fontToUse = font;
		
		LanguageHandler.init();
		Sounds.init();
		
		GameInfo.language = LanguageHandler.languages.get(0);
		
		play = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.PLAY_BUTTON_X, GameInfo.PLAY_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				bb.enterState(GameInfo.STATE_GAME_ID);
			}
		});
		
		multiplayer = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.MULTIPLAYER_BUTTON_X, GameInfo.MULTIPLAYER_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				bb.enterState(GameInfo.STATE_GAME_ID);
			}
		});
		
		options = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.OPTIONS_BUTTON_X, GameInfo.OPTIONS_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				bb.enterState(GameInfo.STATE_OPTIONS_ID);
			}
		});
		
		play.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		multiplayer.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		options.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		
		play.setMouseDownSound(Sounds.select);
		multiplayer.setMouseDownSound(Sounds.select);
		options.setMouseDownSound(Sounds.select);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("/resources/images/projectX/menuBackground.png"), 0, 0);
		g.setColor(Color.blue);
		
		g.drawImage(new Image("/resources/images/projectX/logo.png"), GameInfo.LOGO_X, GameInfo.LOGO_Y);
		
		play.render(gc, g);
		multiplayer.render(gc, g);
		options.render(gc, g);
		
		String[] names = {GameInfo.language.play, GameInfo.language.battle, GameInfo.language.options};
		
		for(int i = 0; i < 3; i++) {
			if(font.getWidth(names[i]) >= 292) {
				fontToUse = smallFont;
			}
			else {
				fontToUse = font;
			}
 		}
		
		fontToUse.drawString(GameInfo.PLAY_BUTTON_X + FontHelper.getWidthDifference(fontToUse, GameInfo.language.play), GameInfo.PLAY_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.play), GameInfo.language.play);
		fontToUse.drawString(GameInfo.MULTIPLAYER_BUTTON_X + FontHelper.getWidthDifference(fontToUse, GameInfo.language.battle), GameInfo.MULTIPLAYER_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.battle), GameInfo.language.battle);
		fontToUse.drawString(GameInfo.OPTIONS_BUTTON_X + FontHelper.getWidthDifference(fontToUse, GameInfo.language.options), GameInfo.OPTIONS_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.options), GameInfo.language.options);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
