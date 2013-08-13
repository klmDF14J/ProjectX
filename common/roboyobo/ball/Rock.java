package roboyobo.ball;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import roboyobo.ball.state.GameState;
import roboyobo.ball.util.GameInfo;

public class Rock {
	
	private float x, y, rotSpeed;
	private int timePassed, direction, rotDir;
	private boolean dead = false;
	private Image img;
	
	private Rectangle bounds = new Rectangle(x, y, GameInfo.ROCK_WIDTH, GameInfo.ROCK_HEIGHT);
	
	public Rock(int x, int y, int direction) throws SlickException {
		this.x = x;
		this.y = y;
		this.direction = direction;
		
		Random rand = new Random();
		float num = rand.nextFloat() + 1;
		rotSpeed = (float) (num > 1.5 ? 1.5 : num);
		
		rotDir = rand.nextInt(2);
		
		img = new Image("/resources/images/projectX/asteroid.png");
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for(Ball ball : GameInfo.balls) {
			if(!ball.isDead() && !isDead()) {
				g.setColor(GameInfo.ROCK_COLOUR);
				if(!GameState.isPaused) {
					if(rotDir == 0) {
						img.rotate(rotSpeed);
					}
					if(rotDir == 1) {
						img.rotate(-rotSpeed);
					}
				}
				g.drawImage(img, x, y);
			}
		}
	}
	
	public void update(int delta) {
		
		bounds = new Rectangle(x, y, GameInfo.ROCK_WIDTH, GameInfo.ROCK_HEIGHT);
		
		timePassed += delta;
		
		if(timePassed >= GameInfo.MIN_TIME_FOR_ROCK_MOVE) {
			timePassed = 0;
			changeDirection();
		}
		
		switch(direction) {
			case 0 :
				x += GameInfo.ROCK_SPEED;
				break;
			case 1 :
				x -= GameInfo.ROCK_SPEED;
				break;
			case 2 :
				y += GameInfo.ROCK_SPEED;
				break;
			case 3 :
				y -= GameInfo.ROCK_SPEED;
				break;
		}
		
		for(Rock rock : GameInfo.rocks) {
			if(bounds.intersects(rock.getBounds()) && rock.getBounds() != bounds && !isDead()) {
				Random rand = new Random();
				if(rand.nextInt(4) == 0) {
					x += 5;
				}
				if(rand.nextInt(4) == 1) {
					x -= 5;
				}
				if(rand.nextInt(4) == 2) {
					y += 5;
				}
				if(rand.nextInt(4) == 3) {
					y -= 5;
				}
				changeDirection();
			}
		}
		
		for(Ball ball : GameInfo.balls) {
			if(bounds.intersects(ball.getBounds()) && !isDead()) {
				changeDirection();
				ball.setHealth(ball.getHealth() - 0.15F);
			}
			if(bounds.intersects(ball.getShockwaveBounds()) && !isDead() && !bounds.intersects(ball.getBounds())) {
				dead = true;
			}
		}
		
		for(Bullet bullet : GameInfo.bullets) {
			if(bounds.intersects(bullet.getBounds()) && !isDead()) {
				setDead(true, 0);
			}
		}
		
		if(x > GameInfo.SCREEN_WIDTH || x < 0 || y > GameInfo.SCREEN_HEIGHT || y < 0) {
			setDead(true, 1);
		}
	}
	
	public void setDead(boolean val, int type) {
		if(!isDead()) {
			dead = val;
			if(type == 0) {
				GameInfo.DEAD_ROCKS++;
			}
		}
		
		
	}
	
	public boolean isDead() {
		return dead;
	}
	
	private Rectangle getBounds() {
		return bounds;
	}
	
	private void changeDirection() {

		Random rand = new Random();
		int val = rand.nextInt(4);
		
		direction = val;
	}
}
