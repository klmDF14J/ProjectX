package roboyobo.ball.game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.resource.Images;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.state.GameOverState;
import roboyobo.ball.state.GameState;
import roboyobo.ball.util.GameInfo;

public class Ball {
	
	public int x, y;
	public int mouseX, mouseY, gunDirection;
	private Circle bounds = new Circle(x, y, GameInfo.BALL_SIZE);
	private float health = 100;
	private boolean dead = false;
	private boolean shouldShockwave = false;
	private float shockwaveSize;
	private float shockwaveCharge = 2500;
	private boolean flag = false;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(GameInfo.BALL_COLOUR);
		
		if(shouldShockwave) {
			g.drawImage(Images.shockwave, x - 150, y- 150);
		}
		
		if(isDead()) {
			g.drawImage(Images.shipDead, x - 20, y - 20);
		}
		else {
			g.drawImage(Images.ship, x - 20, y - 20);
		}
		

		if(GameInfo.settings.renderAimLine) {
			g.setColor(GameInfo.settings.lineColours[GameInfo.settings.lineColour]);
			g.drawLine(x, y, mouseX, mouseY);
		}
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		if(getHealth() <= 0) {
			setDead(true, sbg);
		}
		
		if(shouldShockwave && !isDead()) {
			if(shockwaveSize >= 150) {
				shouldShockwave = false;
				shockwaveSize = 0;
			}
			else {
				shockwaveSize += 3F;
				if(shockwaveCharge >= GameInfo.MIN_SHOCKWAVE_CHARGE && !flag) {
					shockwaveCharge -= GameInfo.MIN_SHOCKWAVE_CHARGE;
					flag = true;
				}
			}
		}
		if(!shouldShockwave && !isDead()) {
			shockwaveCharge += 3F;
		}
		
		if(!isDead() && !shouldShockwave) {
			bounds = new Circle(x, y, GameInfo.BALL_SIZE);
		
			Point p1 = new Point(mouseX, mouseY);
			Point p2 = new Point(x, y);
			if(p1.getX() - p2.getX() >= GameInfo.BALL_MIN_DISTANCE_FOR_MOVE || p1.getY() - p2.getY() >= GameInfo.BALL_MIN_DISTANCE_FOR_MOVE || p2.getX() - p1.getX() >= GameInfo.BALL_MIN_DISTANCE_FOR_MOVE || p2.getY() - p1.getY() >= GameInfo.BALL_MIN_DISTANCE_FOR_MOVE) {
				if(x != mouseX) {
					if(mouseX > x) {
						x += GameInfo.BALL_SPEED;
					}
					if(mouseX < x) {
						x -= GameInfo.BALL_SPEED;
					}
				}
				if(y != mouseY) {
					if(mouseY > y) {
						y += GameInfo.BALL_SPEED;
					}	
					if(mouseY < y) {
						y -= GameInfo.BALL_SPEED;
					}
				}
			}
		}
	}
	
	public void setHealth(float amount) {
		health = amount;
	}
	
	public float getHealth() {
		return health;
	}

	public Circle getBounds() {
		return bounds;
	}
	
	public void setDead(boolean val, StateBasedGame sbg) {
		dead = val;
		GameInfo.TIME_DEAD += 3;
		if(GameInfo.TIME_DEAD >= GameInfo.MIN_DEATH_TIMER) { 
			GameInfo.rocks = new ArrayList<Rock>();
			GameInfo.TIME_DEAD = 0;
			GameOverState.reset();
			sbg.enterState(GameInfo.STATE_GAME_OVER_ID);
		}
	}
	
	public boolean isDead() {
		return dead;
	}

	public void fire(int button) {
		if(!GameState.isPaused) { 
			if(button == 0) {
				Point point = new Point(GameInfo.balls.get(0).mouseX, GameInfo.balls.get(0).mouseY);
				Point point2 = new Point(GameInfo.balls.get(0).x, GameInfo.balls.get(0).y);
			
				GameInfo.bullets.add(new Bullet(point, point2));
				Sounds.fire.play();
			}
			else if(button == 1 && shockwaveCharge >= GameInfo.MIN_SHOCKWAVE_CHARGE) {
				shouldShockwave = true;
				flag = false;
				Sounds.shockwave.play();
				GameInfo.SHOCKWAVES_USED++;
			}
		}
	}

	public Shape getShockwaveBounds() {
		return new Circle(x, y, shockwaveSize);
	}
	
	public void reset(StateBasedGame sbg) {
		GameInfo.TIME_RUNNING -= GameInfo.TIME_DEAD;
		shockwaveCharge = 2500;
		health = 100;
		
		GameInfo.DEAD_ROCKS = GameInfo.DEAD_ROCKS_DEFAULT;
		GameInfo.TIME_RUNNING = 0;
		GameInfo.SHOCKWAVES_USED = 0;
		setDead(false, sbg);
	}
}
