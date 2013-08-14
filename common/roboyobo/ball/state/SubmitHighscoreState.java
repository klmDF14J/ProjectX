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
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.highscore.Entry;
import roboyobo.ball.resource.Images;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.FileWriter;
import roboyobo.ball.util.FontHelper;
import roboyobo.ball.util.GameInfo;

public class SubmitHighscoreState extends BasicState {
	
	private UnicodeFont font, font2;
	
	private TextField username;
	
	private MouseOverArea submit, menu;
	
	public SubmitHighscoreState(int stateID) throws SlickException {
		super(stateID, "menu");
		
		font = FontHelper.setupAndReturnNewFont("font", 96);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
		
		GameInfo.scores = new ArrayList<Entry>();
		
		
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
		username.setMaxLength(3);
		
		submit = new MouseOverArea(gc, Images.button, GameInfo.SCREEN_WIDTH / 2 - 350, GameInfo.SCREEN_HEIGHT - 200, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent arg0) {
				GameInfo.scores.add(new Entry(username.getText(), Math.round(GameOverState.calcScore()), GameInfo.DEAD_ROCKS, GameInfo.TIME_RUNNING, GameInfo.SHOCKWAVES_USED));
				for(int i = 0; i < GameInfo.scores.size(); i++) {
					GameInfo.scores.get(i).printInfo();
				}
				FileWriter.save("/resources/projectX/highscores.dat", GameInfo.scores);
				sbg.enterState(GameInfo.STATE_HIGHSCORE_ID);
			}
		});
		
		submit.setMouseOverImage(Images.buttonMO);
		submit.setMouseDownSound(Sounds.select);
		
		menu = new MouseOverArea(gc, Images.button, GameInfo.SCREEN_WIDTH / 2 + 50, GameInfo.SCREEN_HEIGHT - 200, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent arg0) {
				username.setText("");
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		});
		
		menu.setMouseOverImage(Images.buttonMO);
		menu.setMouseDownSound(Sounds.select);
	}

	@Override
	public void renderMain(GameContainer gc, StateBasedGame sbg, Graphics g) {

		
		font.drawString((int) (GameInfo.SCREEN_WIDTH / 2 - (font.getWidth(GameInfo.language.enterUsername) / 2)), (int) (GameInfo.SCREEN_HEIGHT / 2 - (font.getHeight(GameInfo.language.enterUsername) / 2) - 75), GameInfo.language.enterUsername);
		
		g.setColor(Color.white);
		username.render(gc, g);
		
		submit.render(gc, g);
		menu.render(gc, g);
		
		font2.drawString(((GameInfo.SCREEN_WIDTH / 2) - 350) + FontHelper.getWidthDifference(font2, GameInfo.language.submit), ((GameInfo.SCREEN_HEIGHT - 200) + FontHelper.getHeightDifference(font2, GameInfo.language.submit)), GameInfo.language.submit);
		
		font2.drawString(((GameInfo.SCREEN_WIDTH / 2) + 50) + FontHelper.getWidthDifference(font2, GameInfo.language.backToMenu), ((GameInfo.SCREEN_HEIGHT - 200) + FontHelper.getHeightDifference(font2, GameInfo.language.backToMenu)), GameInfo.language.backToMenu);
	}
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}



}
