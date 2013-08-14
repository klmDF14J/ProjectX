package roboyobo.ball.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import roboyobo.ball.resource.Images;
import roboyobo.ball.util.GameInfo;

public class Bullet {
	
	private Point startPoint, mousePoint, endPoint;
	private int x, y;
	private Rectangle bounds = new Rectangle(x, y, GameInfo.BULLET_WIDTH, GameInfo.BULLET_HEIGHT);
	private int length = 1000;
	private boolean dead = false;
	
	public Bullet(Point point, Point point2) {
		this.mousePoint = point;
		this.x = (int) point2.getX();
		this.y = (int) point2.getY();
		startPoint = point2;
		endPoint = new Point(0, 0);
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(Images.bullet, x, y);
	}
	
	public void update(int delta) {
		test();
		
		bounds = new Rectangle(x, y, GameInfo.BULLET_WIDTH, GameInfo.BULLET_HEIGHT);
		
		if(x != endPoint.getX()) {
			if(endPoint.getX() > x) {
				x += 4F;
			}
			if(endPoint.getX() < x) {
				x -= 4F;
			}
		}
		if(y != endPoint.getY()) {
			if(endPoint.getY() > y) {
				y += 4F;
			}
			if(endPoint.getY() < y) {
				y -= 4F;
			}
		}
		
		if(x > GameInfo.SCREEN_WIDTH || x < 0 || y > GameInfo.SCREEN_HEIGHT || y < 0) {
			setDead(true);
		}
		
	}
	
	public void test() {
		
		Line AB = new Line(startPoint.getX(), startPoint.getY(), mousePoint.getX(), mousePoint.getY());
		
		float lenAB = AB.length();
		
		endPoint.setX(mousePoint.getX() + (mousePoint.getX() - startPoint.getX()) / lenAB * length);
		endPoint.setY(mousePoint.getY() + (mousePoint.getY() - startPoint.getY()) / lenAB * length);
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setDead(boolean val) {
		dead = val;
	}

	public boolean isDead() {
		return dead;
	}
}
