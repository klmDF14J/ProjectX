package roboyobo.ball;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.state.AudioOptionsState;
import roboyobo.ball.state.BasicState;
import roboyobo.ball.state.BattleMenuGamemodeState;
import roboyobo.ball.state.BattleMenuState;
import roboyobo.ball.state.BattleState;
import roboyobo.ball.state.GameOptionsState;
import roboyobo.ball.state.GameOverState;
import roboyobo.ball.state.GameState;
import roboyobo.ball.state.GeneralOptionsState;
import roboyobo.ball.state.HighscoreState;
import roboyobo.ball.state.LanguageState;
import roboyobo.ball.state.LoadingState;
import roboyobo.ball.state.MenuState;
import roboyobo.ball.state.OptionsState;
import roboyobo.ball.state.ShopState;
import roboyobo.ball.state.SubmitHighscoreState;
import roboyobo.ball.state.VideoOptionsState;
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
		addState(new LoadingState(GameInfo.STATE_LOADING_ID));
		enterState(GameInfo.STATE_LOADING_ID);
		
		addState(new MenuState(GameInfo.STATE_MENU_ID, gc, this));
		addState(new GameState(GameInfo.STATE_GAME_ID));
		addState(new OptionsState(GameInfo.STATE_OPTIONS_ID));
		addState(new LanguageState(GameInfo.STATE_LANGUAGE_ID));
		addState(new GameOverState(GameInfo.STATE_GAME_OVER_ID));
		addState(new SubmitHighscoreState(GameInfo.STATE_SUBMIT_HIGHSCORE_ID));
		addState(new HighscoreState(GameInfo.STATE_HIGHSCORE_ID));
		addState(new BattleMenuState(GameInfo.STATE_BATTLE_MENU_ID));
		addState(new BattleMenuGamemodeState(GameInfo.STATE_BATTLE_MENU_GAMEMODE_ID));
		addState(new BattleState(GameInfo.STATE_BATTLE_ID));
		addState(new GeneralOptionsState(GameInfo.STATE_GENERAL_OPTIONS_ID));
		addState(new VideoOptionsState(GameInfo.STATE_VIDEO_OPTIONS_ID));
		addState(new AudioOptionsState(GameInfo.STATE_AUDIO_OPTIONS_ID));
		addState(new GameOptionsState(GameInfo.STATE_GAME_OPTIONS_ID));
		addState(new ShopState(GameInfo.STATE_SHOP_ID));
		
		enterState(GameInfo.STATE_MENU_ID);
	}
	
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new BouncyBall());
		app.setDisplayMode(GameInfo.SCREEN_WIDTH, GameInfo.SCREEN_HEIGHT, false);
		app.start();
		app.setTargetFrameRate(60);
	}
	
	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		org.newdawn.slick.state.GameState gs = getCurrentState();
		if(gs != null) {
			gs.mouseMoved(oldX, oldY, newX, newY);
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		org.newdawn.slick.state.GameState gs = getCurrentState();
		if(gs != null) {
			gs.mousePressed(button, x, y);
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		org.newdawn.slick.state.GameState gs = getCurrentState();
		if(gs != null) {
			gs.keyPressed(key, c);
		}		
	}

	
}
