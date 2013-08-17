package roboyobo.ball.state;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.BouncyBall;
import roboyobo.ball.game.Ball;
import roboyobo.ball.game.Bullet;
import roboyobo.ball.game.HUD;
import roboyobo.ball.game.Rock;
import roboyobo.ball.resource.Images;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.FontHelper;
import roboyobo.ball.util.GameInfo;

public class GameState extends BasicState {
	private int timeSinceNewRock, timeSinceStart;
	private boolean shouldTime = true;
	public static boolean isPaused = false;
	private Random rand;
	private MouseOverArea resume, shop, highscores, menu;
	private Image rocksDead;
	private static UnicodeFont statFont;
	private static UnicodeFont statFont2;
	private static UnicodeFont statFont3;
	private static UnicodeFont font;
	
	private StateBasedGame sbg;
	
	public GameState(int stateID) {
		super(stateID, "game");
		
		BouncyBall.app.setTargetFrameRate(60);
		
		GameInfo.balls = new ArrayList<Ball>();
		GameInfo.bullets = new ArrayList<Bullet>();
		
		rand = new Random();
		
		GameInfo.balls.add(new Ball(rand.nextInt(GameInfo.SCREEN_WIDTH), rand.nextInt(GameInfo.SCREEN_HEIGHT)));
	}

	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		shop = new MouseOverArea(gc, Images.button, 350, 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_SHOP_ID);
			}
		});
		
		highscores = new MouseOverArea(gc, Images.button, 350, 250, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				HighscoreState.mode = 1;
				sbg.enterState(GameInfo.STATE_HIGHSCORE_ID);
			}
		});
		
		resume = new MouseOverArea(gc, Images.button, 0, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				isPaused = false;
			}
		});
		
		menu = new MouseOverArea(gc, Images.button, GameInfo.SCREEN_WIDTH - 300, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				isPaused = false;
				GameInfo.balls.get(0).reset(sbg);
				GameOverState.reset();
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		});
		
		shop.setMouseOverImage(Images.buttonMO);
		highscores.setMouseOverImage(Images.buttonMO);
		resume.setMouseOverImage(Images.buttonMO);
		menu.setMouseOverImage(Images.buttonMO);
		
		shop.setMouseDownSound(Sounds.select);
		highscores.setMouseDownSound(Sounds.select);
		resume.setMouseDownSound(Sounds.select);
		menu.setMouseDownSound(Sounds.select);
		
		rocksDead = new Image("/resources/images/projectX/asteroid.png");
		
		setFonts();
		
		this.sbg = sbg;
	}
	
	public static void setFonts() throws SlickException {
		statFont = FontHelper.setupAndReturnNewFont("font", (int) (24 * GameInfo.settings.hudScale));
		statFont2 = FontHelper.setupAndReturnNewFont("font", (int) (18 * GameInfo.settings.hudScale));
		statFont3 = FontHelper.setupAndReturnNewFont("font", (int) (12 * GameInfo.settings.hudScale));
		
		font = FontHelper.setupAndReturnNewFont("font", 24);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		GameInfo.rocks = new ArrayList<Rock>();
	}

	@Override
	public void renderMain(GameContainer gc, StateBasedGame sbg, Graphics g) {
		
		for(Ball ball : GameInfo.balls) {
			try {
				ball.render(gc, g);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		for(Rock rock : GameInfo.rocks) {
			try {
				rock.render(gc, g);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		for(Bullet bullet : GameInfo.bullets) {
			try {
				bullet.render(gc, g);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		if(isPaused) {
			shop.render(gc, g);
			highscores.render(gc, g);
			resume.render(gc, g);
			menu.render(gc, g);
			
			font.drawString(GameInfo.SCREEN_WIDTH - 300 + FontHelper.getWidthDifference(font, GameInfo.language.backToMenu), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font, GameInfo.language.backToMenu), GameInfo.language.backToMenu);
			font.drawString(FontHelper.getWidthDifference(font, GameInfo.language.back), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font, GameInfo.language.back), GameInfo.language.back);
		
			font.drawString(350 + FontHelper.getWidthDifference(font, GameInfo.language.shop), 100 + FontHelper.getHeightDifference(font, GameInfo.language.shop), GameInfo.language.shop);
			font.drawString(350 + FontHelper.getWidthDifference(font, GameInfo.language.highscore), 250 + FontHelper.getHeightDifference(font, GameInfo.language.highscore), GameInfo.language.highscore);
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
	public void mousePressed(int button, int x, int y) {
		if(!GameInfo.balls.get(0).isDead()) {
			GameInfo.balls.get(0).fire(button);
		}
	}
	
	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		if(!isPaused) {
			GameInfo.balls.get(0).mouseX = newX;
			GameInfo.balls.get(0).mouseY = newY;
		}
	}
	
	private int lastkey = 0, chain = 0;
	private boolean started = false;
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Keyboard.KEY_ESCAPE) {
			isPaused = isPaused == true ? false : true;
		}
		if(key == Keyboard.KEY_D) {
			GameInfo.balls.get(0).setHealth(0);
		}
		check1337(key);
	}
	
	private void check1337(int key) {
		if(key == Keyboard.KEY_1 && !started && chain == 0) {
			chain++;
		}
		if(lastkey == Keyboard.KEY_1 && key == Keyboard.KEY_3 && chain == 1) {
			chain++;
		}
		if(lastkey == Keyboard.KEY_3 && key == Keyboard.KEY_3 && chain == 2) {
			chain++;
		}
		if(lastkey == Keyboard.KEY_3 && key == Keyboard.KEY_7 && chain == 3) {
			System.out.println("1337");
			Images.rock = Images.onethreethreeseven;
			chain = 0;
		}
		lastkey = key;
	}
}
