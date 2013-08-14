package roboyobo.ball.state;

import java.util.ArrayList;

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
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.FileWriter;
import roboyobo.ball.util.FontHelper;
import roboyobo.ball.util.GameInfo;
import roboyobo.ball.util.LanguageHandler;

public class AudioOptionsState extends BasicGameState {

	private int stateID;
	
	private int x = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private int y = GameInfo.SCREEN_HEIGHT / 2 - (GameInfo.GAME_OVER_PANEL_HEIGHT / 2);
	
	public static int length = 10;
	
	private int languageCount;
	private int index;
	
	private ArrayList<MouseOverArea> buttons;
	
	private UnicodeFont font, font2;
	
	public AudioOptionsState(int stateID) throws SlickException {
		this.stateID = stateID;
		
		font = FontHelper.setupAndReturnNewFont("font", 36);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		languageCount = LanguageHandler.languages.size();
		buttons = new ArrayList<MouseOverArea>();
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH / 2 - 325, 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.settings.sound = GameInfo.settings.sound == true ? false : true;
				BouncyBall.app.setSoundOn(GameInfo.settings.sound);
				FileWriter.save("/resources/projectX/settings.dat", GameInfo.settings);
				if(GameInfo.settings.sound) {
					Sounds.select.play();
				}
			}
		}));
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH / 2 - 325, 250, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.settings.music = GameInfo.settings.music == true ? false : true;
				BouncyBall.app.setMusicOn(GameInfo.settings.music);
				FileWriter.save("/resources/projectX/settings.dat", GameInfo.settings);
				if(GameInfo.settings.sound) {
					Sounds.select.play();
				}
			}
		}));
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), 0, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_GENERAL_OPTIONS_ID);
			}
		}));
		
		
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH - 300, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		}));
		
		buttons.get(0).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		buttons.get(1).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		buttons.get(2).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		buttons.get(3).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		buttons.get(2).setMouseDownSound(Sounds.select);
		buttons.get(3).setMouseDownSound(Sounds.select);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawImage(BackgroundManager.getBackgroundForState("menu"), 0, 0);
		
		for(MouseOverArea moa : buttons) {
			moa.render(gc, g);
		}
		
		font.drawString(GameInfo.SCREEN_WIDTH / 2 - (font.getWidth(GameInfo.language.audioS + " " + GameInfo.language.settings) / 2), 50, GameInfo.language.audioS + " " + GameInfo.language.settings);
		
		font2.drawString(GameInfo.SCREEN_WIDTH - 300 + FontHelper.getWidthDifference(font2, GameInfo.language.backToMenu), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.backToMenu), GameInfo.language.backToMenu);
		font2.drawString(FontHelper.getWidthDifference(font2, GameInfo.language.back), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.back), GameInfo.language.back);
	
		String s = " " + (GameInfo.settings.sound == true ? GameInfo.language.on : GameInfo.language.off);
		
		font2.drawString(GameInfo.SCREEN_WIDTH / 2 - 325 + FontHelper.getWidthDifference(font2, GameInfo.language.sound + s), 100 + FontHelper.getHeightDifference(font2, GameInfo.language.sound + s), GameInfo.language.sound + s);
		
		String s2 = " " + (GameInfo.settings.music == true ? GameInfo.language.on : GameInfo.language.off);
		
		font2.drawString(GameInfo.SCREEN_WIDTH / 2 - 325 + FontHelper.getWidthDifference(font2, GameInfo.language.music + s2), 250 + FontHelper.getHeightDifference(font2, GameInfo.language.music + s2), GameInfo.language.music + s2);
	}
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}


}
