package roboyobo.ball.state;

import java.util.Locale;

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
import roboyobo.ball.resource.BackgroundManager;
import roboyobo.ball.resource.Images;
import roboyobo.ball.resource.ResourceManager;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.FileWriter;
import roboyobo.ball.util.FontHelper;
import roboyobo.ball.util.GameInfo;
import roboyobo.ball.util.LanguageHandler;
import roboyobo.ball.util.Settings;

public class MenuState extends BasicState {
	
	private static int playX = GameInfo.PLAY_BUTTON_X;

	private static int multiX = GameInfo.MULTIPLAYER_BUTTON_X;

	private static int optX = GameInfo.OPTIONS_BUTTON_X;
	
	private UnicodeFont font, smallFont, fontToUse;
	
	private static MouseOverArea play, play2, play3, multiplayer, multiplayer2, multiplayer3, options, options2, options3;
	
	public MenuState(int stateID, GameContainer gc, final BouncyBall bb) throws SlickException {
		super(stateID, "menu");
		
		font = FontHelper.setupAndReturnNewFont("font", 48);
		smallFont = FontHelper.setupAndReturnNewFont("font", 36);
		fontToUse = font;
		
		LanguageHandler.init();
		ResourceManager.init();
		
		if(FileWriter.load("/resources/projectX/settings.dat") instanceof Settings) {
			GameInfo.settings = (Settings) FileWriter.load("/resources/projectX/settings.dat");
		}
		
		int i = 0;
		
		if(FileWriter.load("/resources/projectX/language.dat") instanceof Integer) {
			i = (int) FileWriter.load("/resources/projectX/language.dat");
		}
		
		if(i == 0 && Locale.getDefault().getDisplayLanguage() != Locale.ENGLISH.getDisplayLanguage()) {
			System.err.println("Language is set to English, however your default locale is " + Locale.getDefault().getDisplayLanguage());
			LanguageHandler.workOutAndSetLanguage();
		}
		else {
			LanguageHandler.changeLanguage(i);
		}
		
		play = new MouseOverArea(gc, Images.button, GameInfo.PLAY_BUTTON_X, GameInfo.PLAY_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.balls.get(0).reset(bb);
				bb.enterState(GameInfo.STATE_GAME_ID);
			}
		});
		
		play2 = new MouseOverArea(gc, Images.button, GameInfo.PLAY_BUTTON_X - 300, GameInfo.PLAY_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.balls.get(0).reset(bb);
				bb.enterState(GameInfo.STATE_GAME_ID);
			}
		});
		
		play3 = new MouseOverArea(gc, Images.button, GameInfo.PLAY_BUTTON_X + 300, GameInfo.PLAY_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.balls.get(0).reset(bb);
				bb.enterState(GameInfo.STATE_GAME_ID);
			}
		});
		
		multiplayer = new MouseOverArea(gc, Images.button, GameInfo.MULTIPLAYER_BUTTON_X, GameInfo.MULTIPLAYER_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				bb.enterState(GameInfo.STATE_BATTLE_MENU_ID);
			}
		});
		
		multiplayer2 = new MouseOverArea(gc, Images.button, GameInfo.MULTIPLAYER_BUTTON_X - 300, GameInfo.MULTIPLAYER_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				bb.enterState(GameInfo.STATE_BATTLE_MENU_ID);
			}
		});
		
		multiplayer3 = new MouseOverArea(gc, Images.button, GameInfo.MULTIPLAYER_BUTTON_X + 300, GameInfo.MULTIPLAYER_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				bb.enterState(GameInfo.STATE_BATTLE_MENU_ID);
			}
		});
		
		options = new MouseOverArea(gc, Images.button, GameInfo.OPTIONS_BUTTON_X, GameInfo.OPTIONS_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				bb.enterState(GameInfo.STATE_OPTIONS_ID);
			}
		});
		
		options2 = new MouseOverArea(gc, Images.button, GameInfo.OPTIONS_BUTTON_X - 300, GameInfo.OPTIONS_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				bb.enterState(GameInfo.STATE_OPTIONS_ID);
			}
		});
		
		options3 = new MouseOverArea(gc, Images.button, GameInfo.OPTIONS_BUTTON_X + 300, GameInfo.OPTIONS_BUTTON_Y, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				bb.enterState(GameInfo.STATE_OPTIONS_ID);
			}
		});
		
		play.setMouseOverImage(Images.buttonMO);
		multiplayer.setMouseOverImage(Images.buttonMO);
		options.setMouseOverImage(Images.buttonMO);
		
		play2.setMouseOverImage(Images.buttonMO);
		multiplayer2.setMouseOverImage(Images.buttonMO);
		options2.setMouseOverImage(Images.buttonMO);
		
		play3.setMouseOverImage(Images.buttonMO);
		multiplayer3.setMouseOverImage(Images.buttonMO);
		options3.setMouseOverImage(Images.buttonMO);
		
		play.setMouseDownSound(Sounds.select);
		multiplayer.setMouseDownSound(Sounds.select);
		options.setMouseDownSound(Sounds.select);
		
		play2.setMouseDownSound(Sounds.select);
		multiplayer2.setMouseDownSound(Sounds.select);
		options2.setMouseDownSound(Sounds.select);
		
		play3.setMouseDownSound(Sounds.select);
		multiplayer3.setMouseDownSound(Sounds.select);
		options3.setMouseDownSound(Sounds.select);
		
		BouncyBall.app.setShowFPS(GameInfo.settings.showFPS);
		BouncyBall.app.setSoundOn(GameInfo.settings.sound);
		BouncyBall.app.setMusicOn(GameInfo.settings.music);
		
		int offset = 300;
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		setButtonPos();
	}
	
	public static void setButtonPos() {
		
	}

	@Override
	public void renderMain(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Color.blue);
		
		g.drawImage(Images.logo, GameInfo.LOGO_X, GameInfo.LOGO_Y);
		
		if(GameInfo.settings.menuOrient == 0) {
			play2.render(gc, g);
			multiplayer2.render(gc, g);
			options2.render(gc, g);
		}
		
		if(GameInfo.settings.menuOrient == 1) {
			play.render(gc, g);
			multiplayer.render(gc, g);
			options.render(gc, g);
		}
		
		if(GameInfo.settings.menuOrient == 2) {
			play3.render(gc, g);
			multiplayer3.render(gc, g);
			options3.render(gc, g);
		}
		
		String[] names = {GameInfo.language.play, GameInfo.language.battle, GameInfo.language.options};
		
		for(int i = 0; i < 3; i++) {
			if(font.getWidth(names[i]) >= 292) {
				fontToUse = smallFont;
			}
			else {
				fontToUse = font;
			}
 		}
		
		if(GameInfo.settings.menuOrient == 0) {
			fontToUse.drawString(GameInfo.PLAY_BUTTON_X - 300 + FontHelper.getWidthDifference(fontToUse, GameInfo.language.play), GameInfo.PLAY_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.play), GameInfo.language.play);
			fontToUse.drawString(GameInfo.MULTIPLAYER_BUTTON_X - 300 + FontHelper.getWidthDifference(fontToUse, GameInfo.language.battle), GameInfo.MULTIPLAYER_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.battle), GameInfo.language.battle);
			fontToUse.drawString(GameInfo.OPTIONS_BUTTON_X - 300 + FontHelper.getWidthDifference(fontToUse, GameInfo.language.options), GameInfo.OPTIONS_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.options), GameInfo.language.options);
		}
		if(GameInfo.settings.menuOrient == 1) {
			fontToUse.drawString(GameInfo.PLAY_BUTTON_X + FontHelper.getWidthDifference(fontToUse, GameInfo.language.play), GameInfo.PLAY_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.play), GameInfo.language.play);
			fontToUse.drawString(GameInfo.MULTIPLAYER_BUTTON_X + FontHelper.getWidthDifference(fontToUse, GameInfo.language.battle), GameInfo.MULTIPLAYER_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.battle), GameInfo.language.battle);
			fontToUse.drawString(GameInfo.OPTIONS_BUTTON_X + FontHelper.getWidthDifference(fontToUse, GameInfo.language.options), GameInfo.OPTIONS_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.options), GameInfo.language.options);
		}
		if(GameInfo.settings.menuOrient == 2) {
			fontToUse.drawString(GameInfo.PLAY_BUTTON_X + 300 + FontHelper.getWidthDifference(fontToUse, GameInfo.language.play), GameInfo.PLAY_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.play), GameInfo.language.play);
			fontToUse.drawString(GameInfo.MULTIPLAYER_BUTTON_X + 300 + FontHelper.getWidthDifference(fontToUse, GameInfo.language.battle), GameInfo.MULTIPLAYER_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.battle), GameInfo.language.battle);
			fontToUse.drawString(GameInfo.OPTIONS_BUTTON_X + 300 + FontHelper.getWidthDifference(fontToUse, GameInfo.language.options), GameInfo.OPTIONS_BUTTON_Y + FontHelper.getHeightDifference(fontToUse, GameInfo.language.options), GameInfo.language.options);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

}
