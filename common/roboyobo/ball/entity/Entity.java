package roboyobo.ball.entity;

import org.newdawn.slick.Image;

public class Entity {
	
	public int x, y;
	public int speed, angle;
	public Image texture;
	private final int MAX_SPEED = 100;
	private final int MAX_ANGLE = 360;
	
	public Entity(int x, int y, int speed, int angle, Image texture) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.angle = angle;
		this.texture = texture;
		
		this.texture.setRotation(this.angle);
	}
	
	public void setX(int val) {
		x = val;
	}
	
	public void setY(int val) {
		y = val;
	}
	
	public void setSpeed(int val) {
		speed = val;
	}
	
	public void setAngle(int val) {
		angle = val;
	}
	
	public void setTexture(Image val) {
		texture = val;
	}
	
	public void render() {
		texture.draw(x, y);
	}
	
	public void adjSpeed(int val) {
		speed += val;
		if(speed > MAX_SPEED) {
			speed = MAX_SPEED;
		}
		else if(speed < 0) {
			speed = 0;
		}
		System.out.println("Speed: " + speed);
	}
	
	public void adjDegrees(int val) {
		angle = ((angle + val) % MAX_ANGLE + MAX_ANGLE) % MAX_ANGLE;
		texture.setRotation(angle);
		System.out.println("Angle: " + angle);
	}
}
