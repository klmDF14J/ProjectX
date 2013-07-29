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

import roboyobo.ball.FontHelper;
import roboyobo.ball.Language;
import roboyobo.ball.LanguageHandler;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.GameInfo;

public class LanguageState extends BasicGameState {

	private int stateID;
	
	private int x = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private int y = GameInfo.SCREEN_HEIGHT / 2 - (GameInfo.GAME_OVER_PANEL_HEIGHT / 2);
	
	private int languageCount;
	private int index;
	
	private ArrayList<MouseOverArea> buttons;
	
	private UnicodeFont font;
	
	public LanguageState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		languageCount = LanguageHandler.languages.size();
		buttons = new ArrayList<MouseOverArea>();
		
		font = FontHelper.setupAndReturnNewFont("font", 36);
		
		for(final Language language : LanguageHandler.languages) {
			buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), 350, 100 + (index * 110), new ComponentListener() {
				@Override
				public void componentActivated(AbstractComponent ac) {
					GameInfo.language = language;
					System.out.println("Language is now: " + GameInfo.language.getKey());
				}
			}));
			buttons.get(index).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
			buttons.get(index).setMouseDownSound(Sounds.select);
			index++;
		}
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH - 300, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		}));
		
		buttons.get(index).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		buttons.get(index).setMouseDownSound(Sounds.select);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawImage(new Image("/resources/images/projectX/menuBackground.png"), 0, 0);
		
		for(MouseOverArea moa : buttons) {
			moa.render(gc, g);
		}
		
		font.drawString(GameInfo.LANGUAGE_TEXT_X, GameInfo.LANGUAGE_TEXT_Y, GameInfo.language.selectLanguage);
		
		index = 0;
		
		for(Language language : LanguageHandler.languages) {
			font.drawString(370, 120 + (index * 110), language.getName());
			index++;
		}
		
		FontHelper.setupAndReturnNewFont("font", 24).drawString(GameInfo.SCREEN_WIDTH - 260, GameInfo.SCREEN_HEIGHT - 60, GameInfo.language.backToMenu);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}


}
