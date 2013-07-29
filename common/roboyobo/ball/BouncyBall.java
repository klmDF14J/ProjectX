package roboyobo.ball;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.state.GameOverState;
import roboyobo.ball.state.GameState;
import roboyobo.ball.state.LanguageState;
import roboyobo.ball.state.MenuState;
import roboyobo.ball.state.OptionsState;
import roboyobo.ball.state.SubmitHighscoreState;
import roboyobo.ball.util.GameInfo;



public class BouncyBall extends StateBasedGame {
	
	/**
	 * @author Kyle Mandell
	 */
	
	
	public static AppGameContainer app;
	public static int stateID;
	
	public BouncyBall() {
		super("Project X");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MenuState(GameInfo.STATE_MENU_ID, gc, this));
		addState(new GameState(GameInfo.STATE_GAME_ID));
		addState(new OptionsState(GameInfo.STATE_OPTIONS_ID));
		addState(new LanguageState(GameInfo.STATE_LANGUAGE_ID));
		addState(new GameOverState(GameInfo.STATE_GAME_OVER_ID));
		addState(new SubmitHighscoreState(GameInfo.STATE_SUBMIT_HIGHSCORE_ID));
		addState(new HighscoreState(GameInfo.STATE_HIGHSCORE_ID));
		
		enterState(GameInfo.STATE_GAME_OVER_ID);
	}
	
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new BouncyBall());
		app.setDisplayMode(GameInfo.SCREEN_WIDTH, GameInfo.SCREEN_HEIGHT, false);
		app.start();
		app.setTargetFrameRate(60);
	}
	
	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		super.mouseMoved(oldX, oldY, newX, newY);
		
		GameInfo.balls.get(0).mouseX = newX;
		GameInfo.balls.get(0).mouseY = newY;
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
		if(!GameInfo.balls.get(0).isDead() && this.getCurrentStateID() == GameInfo.STATE_GAME_ID) {
			GameInfo.balls.get(0).fire(button);
		}
		if(this.getCurrentStateID() == GameInfo.STATE_OPTIONS_ID) {
			OptionsState.length += 10;
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(this.getCurrentStateID() == GameInfo.STATE_GAME_ID) {
			if(key == Keyboard.KEY_R) {
				GameInfo.balls.get(0).reset(this);
			}
		}
	}

	
}
