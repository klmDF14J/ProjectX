package roboyobo.ball.state;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.Ball;
import roboyobo.ball.Bullet;
import roboyobo.ball.Rock;
import roboyobo.ball.util.GameInfo;

public class GameState extends BasicGameState {

	private int stateID;
	private int timeSinceNewRock, timeSinceStart;
	private boolean shouldTime = true;
	private Random rand;
	
	public GameState(int stateID) {
		this.stateID = stateID;
		//BouncyBall.app.setTargetFrameRate(60);
		
		GameInfo.rocks = new ArrayList<Rock>();
		GameInfo.balls = new ArrayList<Ball>();
		GameInfo.bullets = new ArrayList<Bullet>();
		
		rand = new Random();
		
		GameInfo.balls.add(new Ball(rand.nextInt(GameInfo.SCREEN_WIDTH), rand.nextInt(GameInfo.SCREEN_HEIGHT)));
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
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
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		timeSinceNewRock += delta;
		GameInfo.TIME_RUNNING += delta;
		
		if(shouldTime) {
			timeSinceStart += delta;
		}
		if(timeSinceStart >= 5000) {
			shouldTime = false;
		}
		
		int timeToWait = 500 - (GameInfo.TIME_RUNNING / 2);
		
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

	@Override
	public int getID() {
		return stateID;
	}

}
