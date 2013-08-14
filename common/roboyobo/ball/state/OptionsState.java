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

public class OptionsState extends BasicState {

	private int x = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private int y = GameInfo.SCREEN_HEIGHT / 2 - (GameInfo.GAME_OVER_PANEL_HEIGHT / 2);
	
	public static int length = 10;
	
	private ArrayList<MouseOverArea> buttons;
	
	private UnicodeFont font, font2;
	
	public OptionsState(int stateID) throws SlickException {
		super(stateID, "menu");
		
		font = FontHelper.setupAndReturnNewFont("font", 36);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		buttons = new ArrayList<MouseOverArea>();
		
		buttons.add(new MouseOverArea(gc, Images.button, 350, 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_LANGUAGE_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, Images.button, 350, 250, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_HIGHSCORE_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, Images.button, 350, 400, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_GENERAL_OPTIONS_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, Images.button, GameInfo.SCREEN_WIDTH - 300, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		}));
		
		buttons.get(0).setMouseOverImage(Images.buttonMO);
		buttons.get(1).setMouseOverImage(Images.buttonMO);
		buttons.get(2).setMouseOverImage(Images.buttonMO);
		buttons.get(3).setMouseOverImage(Images.buttonMO);
		
		buttons.get(0).setMouseDownSound(Sounds.select);
		buttons.get(1).setMouseDownSound(Sounds.select);
		buttons.get(2).setMouseDownSound(Sounds.select);
		buttons.get(3).setMouseDownSound(Sounds.select);
	}

	@Override
	public void renderMain(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Color.blue);

		
		for(MouseOverArea moa : buttons) {
			moa.render(gc, g);
		}
		
		font.drawString(GameInfo.SCREEN_WIDTH / 2 - (font.getWidth(GameInfo.language.options) / 2), 50, GameInfo.language.options);
		
		font2.drawString(350 + FontHelper.getWidthDifference(font2, GameInfo.language.languages), 100 + FontHelper.getHeightDifference(font2, GameInfo.language.languages), GameInfo.language.languages);
		font2.drawString(350 + FontHelper.getWidthDifference(font2, GameInfo.language.highscore), 250 + FontHelper.getHeightDifference(font2, GameInfo.language.highscore), GameInfo.language.highscore);
		font2.drawString(350 + FontHelper.getWidthDifference(font2, GameInfo.language.settings), 400 + FontHelper.getHeightDifference(font2, GameInfo.language.settings), GameInfo.language.settings);
		
		font2.drawString(GameInfo.SCREEN_WIDTH - 300 + FontHelper.getWidthDifference(font2, GameInfo.language.backToMenu), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.backToMenu), GameInfo.language.backToMenu);
	}
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}


}
