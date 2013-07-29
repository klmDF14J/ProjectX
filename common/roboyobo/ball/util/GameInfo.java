package roboyobo.ball.util;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

import roboyobo.ball.Ball;
import roboyobo.ball.Bullet;
import roboyobo.ball.Language;
import roboyobo.ball.LanguageHandler;
import roboyobo.ball.Rock;
import roboyobo.ball.highscore.Entry;

public class GameInfo {
	
	private static int CURRENT_ID;
	
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 600;
	
	public static final int STATE_MENU_ID = getNextID();
	public static final int STATE_GAME_ID = getNextID();
	public static final int STATE_GAME_OVER_ID = getNextID();
	public static final int STATE_OPTIONS_ID = getNextID();
	public static final int STATE_LANGUAGE_ID = getNextID();
	public static final int STATE_SUBMIT_HIGHSCORE_ID = getNextID();
	public static final int STATE_HIGHSCORE_ID = getNextID();
	
	public static final float BALL_SIZE = 20;
	
	public static final Color BALL_COLOUR = Color.blue;
	public static final Color BALL_DEAD_COLOUR = Color.red;
	public static final Color ROCK_COLOUR = Color.yellow;

	public static final float BALL_SPEED = 3F;
	
	public static final float BALL_MIN_DISTANCE_FOR_MOVE = 75;
	
	public static final int MIN_TIME_FOR_ROCK_MOVE = 1000;
	
	public static final float ROCK_WIDTH = 50;
	public static final float ROCK_HEIGHT = 50;
	
	public static final int BULLET_WIDTH = 10;
	public static final int BULLET_HEIGHT = 10;
	
	public static final int GAME_OVER_PANEL_WIDTH = 600;
	public static final int GAME_OVER_PANEL_HEIGHT = 500;

	public static final int PLAY_BUTTON_X = 350;
	public static final int PLAY_BUTTON_Y = 200;

	public static final int MULTIPLAYER_BUTTON_X = 350;
	public static final int MULTIPLAYER_BUTTON_Y = 310;

	public static final int OPTIONS_BUTTON_X = 350;
	public static final int OPTIONS_BUTTON_Y = 420;

	public static final int LOGO_X = 225;
	public static final int LOGO_Y = 75;

	public static final int LANGUAGE_TEXT_X = 200;
	public static final int LANGUAGE_TEXT_Y = 50;

	public static final float ROCK_SPEED = 3F;

	public static final float MIN_SHOCKWAVE_CHARGE = 2500;

	public static final int MAX_ROCKS = 10;

	public static final int MIN_DEATH_TIMER = 3000;

	public static final int ROCK_REMOVAL_TIME = 300;
	
	public static ArrayList<Rock> rocks;
	public static ArrayList<Ball> balls;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Entry> scores;
	
	public static int DEAD_ROCKS = 410; 
	public static int TIME_DEAD;
	public static int TIME_RUNNING = 300000;
	public static int SHOCKWAVES_USED = 4;
	
	public static Language language;
	
	private static int getNextID() {
		return CURRENT_ID++;
	}
	
}
