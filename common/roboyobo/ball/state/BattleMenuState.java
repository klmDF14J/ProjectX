package roboyobo.ball.state;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.FontHelper;
import roboyobo.ball.Language;
import roboyobo.ball.LanguageHandler;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.GameInfo;

public class BattleMenuState extends BasicGameState {

	private int stateID;
	
	private int x = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private int y = GameInfo.SCREEN_HEIGHT / 2 - (GameInfo.GAME_OVER_PANEL_HEIGHT / 2);
	
	public static int length = 10;
	
	private int languageCount;
	private int index;
	
	private ArrayList<MouseOverArea> buttons;
	
	private UnicodeFont font, font2;
	
	public BattleMenuState(int stateID) throws SlickException {
		this.stateID = stateID;
		
		font = FontHelper.setupAndReturnNewFont("font", 36);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		languageCount = LanguageHandler.languages.size();
		buttons = new ArrayList<MouseOverArea>();
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH - 300, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), 350, 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.BATTLE_PLAYER_COUNT = 2;
				sbg.enterState(GameInfo.STATE_BATTLE_MENU_GAMEMODE_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), 350, 250, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.BATTLE_PLAYER_COUNT = 3;
				sbg.enterState(GameInfo.STATE_BATTLE_MENU_GAMEMODE_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), 350, 400, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.BATTLE_PLAYER_COUNT = 4;
				sbg.enterState(GameInfo.STATE_BATTLE_MENU_GAMEMODE_ID);
			}
		}));
		
		buttons.get(0).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		buttons.get(1).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		buttons.get(2).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		buttons.get(3).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		
		buttons.get(0).setMouseDownSound(Sounds.select);
		buttons.get(1).setMouseDownSound(Sounds.select);
		buttons.get(2).setMouseDownSound(Sounds.select);
		buttons.get(3).setMouseDownSound(Sounds.select);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawImage(new Image("/resources/images/projectX/menuBackground.png"), 0, 0);
		
		for(MouseOverArea moa : buttons) {
			moa.render(gc, g);
		}
		
		font.drawString(GameInfo.SCREEN_WIDTH / 2 - (font.getWidth("WIP") / 2), 50, "WIP");
		
		font2.drawString(GameInfo.SCREEN_WIDTH - 300 + FontHelper.getWidthDifference(font2, GameInfo.language.backToMenu), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.backToMenu), GameInfo.language.backToMenu);
	
		font2.drawString(350 + FontHelper.getWidthDifference(font2, "2 " + GameInfo.language.player), 100 + FontHelper.getHeightDifference(font2, "2 " + GameInfo.language.player), "2 " + GameInfo.language.player);
		font2.drawString(350 + FontHelper.getWidthDifference(font2, "3 " + GameInfo.language.player), 250 + FontHelper.getHeightDifference(font2, "3 " + GameInfo.language.player), "3 " + GameInfo.language.player);
		font2.drawString(350 + FontHelper.getWidthDifference(font2, "4 " + GameInfo.language.player), 400 + FontHelper.getHeightDifference(font2, "4 " + GameInfo.language.player), "4 " + GameInfo.language.player);
	}
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}


}
