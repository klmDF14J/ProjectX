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
import roboyobo.ball.FileWriter;
import roboyobo.ball.FontHelper;
import roboyobo.ball.LanguageHandler;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.GameInfo;

public class GameOptionsState extends BasicGameState {

	private int stateID;
	
	private int x = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private int y = GameInfo.SCREEN_HEIGHT / 2 - (GameInfo.GAME_OVER_PANEL_HEIGHT / 2);
	
	public static int length = 10;
	
	private int languageCount;
	private int index;
	
	private ArrayList<MouseOverArea> buttons;
	
	private UnicodeFont font, font2;
	
	public GameOptionsState(int stateID) throws SlickException {
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
				GameInfo.settings.renderAimLine = GameInfo.settings.renderAimLine == true ? false : true;
				FileWriter.save("/resources/projectX/settings.dat", GameInfo.settings);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH / 2 + 25, 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				if(GameInfo.settings.lineColour + 1 < GameInfo.settings.lineColours.length) {
					GameInfo.settings.lineColour++;
				}
				else {
					GameInfo.settings.lineColour = 0;
				}
				FileWriter.save("/resources/projectX/settings.dat", GameInfo.settings);
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
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH / 2 - 325, 250, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.settings.showFPS = GameInfo.settings.showFPS == true ? false : true;
				BouncyBall.app.setShowFPS(GameInfo.settings.showFPS);
				FileWriter.save("/resources/projectX/settings.dat", GameInfo.settings);
			}
		}));
		
		for(int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
			buttons.get(i).setMouseDownSound(Sounds.select);
		}	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawImage(new Image("/resources/images/projectX/menuBackground.png"), 0, 0);
		
		for(MouseOverArea moa : buttons) {
			if(moa == buttons.get(1)) {
				if(GameInfo.settings.renderAimLine) {
					moa.render(gc, g);
				}
			}
			else {
				moa.render(gc, g);
			}
		}
		
		font.drawString(GameInfo.SCREEN_WIDTH / 2 - (font.getWidth(GameInfo.language.gameS + " " + GameInfo.language.settings) / 2), 50, GameInfo.language.gameS + " " + GameInfo.language.settings);
		
		font2.drawString(GameInfo.SCREEN_WIDTH - 300 + FontHelper.getWidthDifference(font2, GameInfo.language.backToMenu), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.backToMenu), GameInfo.language.backToMenu);
		font2.drawString(FontHelper.getWidthDifference(font2, GameInfo.language.back), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.back), GameInfo.language.back);
		
		String s = " " + (GameInfo.settings.renderAimLine == true ? GameInfo.language.on : GameInfo.language.off);
		
		font2.drawString(GameInfo.SCREEN_WIDTH / 2 - 325 + FontHelper.getWidthDifference(font2, GameInfo.language.aimLine + s), 100 + FontHelper.getHeightDifference(font2, GameInfo.language.aimLine + s), GameInfo.language.aimLine + s);
		
		String s2 = " " + (GameInfo.settings.showFPS == true ? GameInfo.language.on : GameInfo.language.off);
		
		font2.drawString(GameInfo.SCREEN_WIDTH / 2 - 325 + FontHelper.getWidthDifference(font2, GameInfo.language.showFPS + s2), 250 + FontHelper.getHeightDifference(font2, GameInfo.language.showFPS + s2), GameInfo.language.showFPS + s2);
		
		if(GameInfo.settings.renderAimLine) {
			font2.drawString(GameInfo.SCREEN_WIDTH / 2 + 25 + FontHelper.getWidthDifference(font2, GameInfo.language.colour), 100 + FontHelper.getHeightDifference(font2, GameInfo.language.colour), GameInfo.language.colour);
			g.setColor(GameInfo.settings.lineColours[GameInfo.settings.lineColour]);
			g.fillRect(GameInfo.SCREEN_WIDTH / 2 + 25 + 100, 100 + 70, 100, 20);
		}
	}
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}


}
