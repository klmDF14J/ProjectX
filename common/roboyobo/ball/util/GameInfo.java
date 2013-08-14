package roboyobo.ball.util;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

import roboyobo.ball.game.Ball;
import roboyobo.ball.game.Bullet;
import roboyobo.ball.game.Rock;
import roboyobo.ball.highscore.Entry;
import roboyobo.ball.shop.Item;

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
	public static final int STATE_BATTLE_MENU_ID = getNextID();
	public static final int STATE_BATTLE_MENU_GAMEMODE_ID = getNextID();
	public static final int STATE_BATTLE_ID = getNextID();
	public static final int STATE_GENERAL_OPTIONS_ID = getNextID();
	public static final int STATE_VIDEO_OPTIONS_ID = getNextID();
	public static final int STATE_AUDIO_OPTIONS_ID = getNextID();
	public static final int STATE_GAME_OPTIONS_ID = getNextID();
	public static final int STATE_SHOP_ID = getNextID();
	public static final int STATE_LOADING_ID = getNextID();
	
	public static final float BALL_SIZE = 20;
	
	public static final Color BALL_COLOUR = Color.blue;
	public static final Color BALL_DEAD_COLOUR = Color.red;
	public static final Color ROCK_COLOUR = Color.yellow;

	public static final float BALL_SPEED = 3F;
	
	public static final float BALL_MIN_DISTANCE_FOR_MOVE = 75;
	
	public static final int MIN_TIME_FOR_ROCK_MOVE = 5000;
	
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

	public static final float ROCK_SPEED = 3F;

	public static final float MIN_SHOCKWAVE_CHARGE = 2500;

	public static final int MAX_ROCKS = 25;

	public static final int MIN_DEATH_TIMER = 500;

	public static final int ROCK_REMOVAL_TIME = 300;

	public static ArrayList<Rock> rocks;
	public static ArrayList<Ball> balls;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Entry> scores;
	
	public static int DEAD_ROCKS = 0; 
	public static int DEAD_ROCKS_DEFAULT = 0; 
	public static int TIME_DEAD;
	public static int TIME_RUNNING = 3000;
	public static int SHOCKWAVES_USED = 0;
	public static int BATTLE_PLAYER_COUNT;
	public static int BATTLE_GAMEMODE;
	
	public static int TOKEN_COUNT = 100;
	public static final int TOKEN_STARTER_NUM = 100;
	
	public static ArrayList<Item> shopContents;
	
	public static Language language;
	public static Settings settings = new Settings();
	
	private static int getNextID() {
		return CURRENT_ID++;
	}
	
}
