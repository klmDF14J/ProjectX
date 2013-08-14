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
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.resource.Images;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.FontHelper;
import roboyobo.ball.util.GameInfo;

public class BattleMenuGamemodeState extends BasicState {

	
	private int x = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private int y = GameInfo.SCREEN_HEIGHT / 2 - (GameInfo.GAME_OVER_PANEL_HEIGHT / 2);
	
	public static int length = 10;
	
	private ArrayList<MouseOverArea> buttons;
	
	private UnicodeFont font, font2;
	
	public BattleMenuGamemodeState(int stateID) throws SlickException {
		super(stateID, "menu");
		
		font = FontHelper.setupAndReturnNewFont("font", 36);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		buttons = new ArrayList<MouseOverArea>();
		
		buttons.add(new MouseOverArea(gc, Images.button, GameInfo.SCREEN_WIDTH - 300, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, Images.button, 0, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_BATTLE_MENU_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, Images.button, 350, 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.BATTLE_GAMEMODE = 0;
				sbg.enterState(GameInfo.STATE_BATTLE_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, Images.button, 350, 250, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.BATTLE_GAMEMODE = 1;
				sbg.enterState(GameInfo.STATE_BATTLE_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, Images.button, 350, 400, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.BATTLE_GAMEMODE = 2;
				sbg.enterState(GameInfo.STATE_BATTLE_ID);
			}
		}));
		
		buttons.get(0).setMouseOverImage(Images.buttonMO);
		buttons.get(1).setMouseOverImage(Images.buttonMO);
		buttons.get(2).setMouseOverImage(Images.buttonMO);
		buttons.get(3).setMouseOverImage(Images.buttonMO);
		buttons.get(4).setMouseOverImage(Images.buttonMO);
		
		buttons.get(0).setMouseDownSound(Sounds.select);
		buttons.get(1).setMouseDownSound(Sounds.select);
		buttons.get(2).setMouseDownSound(Sounds.select);
		buttons.get(3).setMouseDownSound(Sounds.select);
		buttons.get(4).setMouseDownSound(Sounds.select);
	}

	@Override
	public void renderMain(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Color.blue);
		
		for(MouseOverArea moa : buttons) {
			moa.render(gc, g);
		}
		
		font.drawString(GameInfo.SCREEN_WIDTH / 2 - (font.getWidth("WIP") / 2), 50, "WIP");
		
		font2.drawString(350 + FontHelper.getWidthDifference(font2, GameInfo.language.rocks), 100 + FontHelper.getHeightDifference(font2, GameInfo.language.rocks), GameInfo.language.rocks);
		font2.drawString(350 + FontHelper.getWidthDifference(font2, GameInfo.language.time), 250 + FontHelper.getHeightDifference(font2, GameInfo.language.time), GameInfo.language.time);
		font2.drawString(350 + FontHelper.getWidthDifference(font2, GameInfo.language.vs), 400 + FontHelper.getHeightDifference(font2, GameInfo.language.vs), GameInfo.language.vs);
		
		font2.drawString(GameInfo.SCREEN_WIDTH - 300 + FontHelper.getWidthDifference(font2, GameInfo.language.backToMenu), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.backToMenu), GameInfo.language.backToMenu);
		font2.drawString(FontHelper.getWidthDifference(font2, GameInfo.language.back), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.back), GameInfo.language.back);
	}
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}


}
