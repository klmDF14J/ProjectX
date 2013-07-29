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
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.FontHelper;
import roboyobo.ball.Language;
import roboyobo.ball.LanguageHandler;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.GameInfo;
import roboyobo.hoppityHop.util.Reference;

public class SubmitHighscoreState extends BasicGameState {

	private int stateID;
	
	private UnicodeFont font;
	
	private TextField username;
	
	private MouseOverArea submit, menu;
	
	public SubmitHighscoreState(int stateID) throws SlickException {
		this.stateID = stateID;
		
		font = FontHelper.setupAndReturnNewFont("font", 96);
		
		
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		username = new TextField(gc, font, (int) (GameInfo.SCREEN_WIDTH / 2 - 145), (int) (GameInfo.SCREEN_HEIGHT / 2 - 27.5), 290, 55, new ComponentListener() {
			public void componentActivated(AbstractComponent source) {
				username.setFocus(true);
			}
		});
		
		username.setTextColor(Color.white);
		username.setBorderColor(Color.red);
		username.setMaxLength(4);
		
		submit = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH / 2 - 350, GameInfo.SCREEN_HEIGHT - 200, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent arg0) {
				GameInfo.USERNAME = username.getText();
				sbg.enterState(GameInfo.STATE_HIGHSCORE_ID);
			}
		});
		
		submit.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		submit.setMouseDownSound(Sounds.select);
		
		menu = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH / 2 + 50, GameInfo.SCREEN_HEIGHT - 200, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent arg0) {
				username.setText("");
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		});
		
		menu.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		menu.setMouseDownSound(Sounds.select);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("/resources/images/projectX/menuBackground.png"), 0, 0);
		
		font.drawString((int) (GameInfo.SCREEN_WIDTH / 2 - (font.getWidth(GameInfo.language.enterUsername) / 2)), (int) (GameInfo.SCREEN_HEIGHT / 2 - (font.getHeight(GameInfo.language.enterUsername) / 2) - 75), GameInfo.language.enterUsername);
		
		g.setColor(Color.white);
		username.render(gc, g);
		
		submit.render(gc, g);
		menu.render(gc, g);
	}
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}


}
