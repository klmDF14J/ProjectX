package roboyobo.ball.state;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.BouncyBall;
import roboyobo.ball.game.Ball;
import roboyobo.ball.game.Bullet;
import roboyobo.ball.game.HUD;
import roboyobo.ball.game.Rock;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.FontHelper;
import roboyobo.ball.util.GameInfo;

public class GameState extends BasicGameState {

	private int stateID;
	private int timeSinceNewRock, timeSinceStart;
	private boolean shouldTime = true;
	public static boolean isPaused = false;
	private Random rand;
	private MouseOverArea resume, shop, highscores, menu;
	private Image rocksDead;
	private static UnicodeFont statFont;
	private static UnicodeFont statFont2;
	private static UnicodeFont statFont3;
	
	public GameState(int stateID) {
		this.stateID = stateID;
		
		BouncyBall.app.setTargetFrameRate(60);
		
		GameInfo.balls = new ArrayList<Ball>();
		GameInfo.bullets = new ArrayList<Bullet>();
		
		rand = new Random();
		
		GameInfo.balls.add(new Ball(rand.nextInt(GameInfo.SCREEN_WIDTH), rand.nextInt(GameInfo.SCREEN_HEIGHT)));
	}

	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		shop = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), 350, 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_SHOP_ID);
			}
		});
		
		highscores = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), 350, 250, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				HighscoreState.mode = 1;
				sbg.enterState(GameInfo.STATE_HIGHSCORE_ID);
			}
		});
		
		resume = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), 0, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				isPaused = false;
			}
		});
		
		menu = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH - 300, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				isPaused = false;
				GameInfo.balls.get(0).reset(sbg);
				GameOverState.reset();
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		});
		
		shop.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		highscores.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		resume.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		menu.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		
		shop.setMouseDownSound(Sounds.select);
		highscores.setMouseDownSound(Sounds.select);
		resume.setMouseDownSound(Sounds.select);
		menu.setMouseDownSound(Sounds.select);
		
		rocksDead = new Image("/resources/images/projectX/asteroid.png");
		
		setFonts();
	}
	
	public static void setFonts() throws SlickException {
		statFont = FontHelper.setupAndReturnNewFont("font", (int) (24 * GameInfo.settings.hudScale));
		statFont2 = FontHelper.setupAndReturnNewFont("font", (int) (18 * GameInfo.settings.hudScale));
		statFont3 = FontHelper.setupAndReturnNewFont("font", (int) (12 * GameInfo.settings.hudScale));
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		GameInfo.rocks = new ArrayList<Rock>();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.drawImage(new Image("/resources/images/projectX/background.png"), 0, 0);
		
		for(Ball ball : GameInfo.balls) {
			ball.render(gc, g);
		}
		
		for(Rock rock : GameInfo.rocks) {
			rock.render(gc, g);
		}
		
		for(Bullet bullet : GameInfo.bullets) {
			bullet.render(gc, g);
		}
		
		if(isPaused) {
			shop.render(gc, g);
			highscores.render(gc, g);
			resume.render(gc, g);
			menu.render(gc, g);
		}
		
		HUD.render(g, statFont, statFont2, statFont3);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if(!isPaused) {
			timeSinceNewRock += delta;
			GameInfo.TIME_RUNNING += delta;
			
			if(shouldTime) {
				timeSinceStart += delta;
			}
			if(timeSinceStart >= 5000) {
				shouldTime = false;
			}
			
			int timeToWait = 500 - ((GameInfo.TIME_RUNNING / 1000));
			
			if(timeToWait < 300) {
				timeToWait = 300;
			}
			
			if(timeSinceNewRock >= timeToWait && !shouldTime && GameInfo.rocks.size() <= GameInfo.MAX_ROCKS) {
				Random rand = new Random();
				int x = rand.nextInt(GameInfo.SCREEN_WIDTH);
				int y = rand.nextInt(GameInfo.SCREEN_HEIGHT);
				GameInfo.rocks.add(new Rock(x, y, rand.nextInt(4)));
				timeSinceNewRock = 0;
			}
			
			for(Ball ball : GameInfo.balls) {
				ball.update(gc, sbg, delta);
			}
			
			for(int i = 0; i < GameInfo.rocks.size(); i++) {
				Rock rock = GameInfo.rocks.get(i);
				if(!rock.isDead()) {
					rock.update(delta);
				}
				if(rock.isDead()) {
					GameInfo.rocks.remove(rock);
				}
			}
			
			for(int i = 0; i < GameInfo.bullets.size(); i++) {
				Bullet bullet = GameInfo.bullets.get(i);
				if(!bullet.isDead()) {
					bullet.update(delta);
				}
				if(bullet.isDead()) {
					GameInfo.bullets.remove(bullet);
				}
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
