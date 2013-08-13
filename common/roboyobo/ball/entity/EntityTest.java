package roboyobo.ball.entity;

import java.awt.Point;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import roboyobo.ball.util.GameInfo;

public class EntityTest extends BasicGame {
	
	public static Entity e;
	
	public EntityTest() {
		super("Entity Test");
	}

	public static AppGameContainer app;
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new EntityTest());
		app.setDisplayMode(GameInfo.SCREEN_WIDTH, GameInfo.SCREEN_HEIGHT, false);
		app.start();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setBackground(Color.white);
		e.render();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		e = new Entity(100, 100, 10, 180, new Image("/resources/images/projectX/Ship.png"));
		System.out.println("x: " + e.x + " y: " + e.y);	
		System.out.println(e.angle);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
	
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		e.setX(e.x + 10);
		e.setY(e.y + 10);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Keyboard.KEY_LEFT) {
			e.adjDegrees(-9);
		}
		if(key == Keyboard.KEY_RIGHT) {
			e.adjDegrees(9);
		}
		if(key == Keyboard.KEY_UP) {
			e.adjSpeed(1);
		}
		if(key == Keyboard.KEY_DOWN) {
			e.adjSpeed(-1);
		}
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		System.out.println(getAngle(new Point(100, 0), new Point(e.x, e.y)));
		/*System.out.println("From: " + e.x + " ," + e.y);
		System.out.println("To: " + newx + " ," + newy);*/
	}
	
	public float getAngle(Point mouse, Point object) {
		float num =  (float) Math.toDegrees(Math.atan2(mouse.x - object.x, mouse.y - object.y));
		//return num < 0 ? num += 360 : num;
		return num;
	}
	
}
