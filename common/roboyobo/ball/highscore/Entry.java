package roboyobo.ball.highscore;

import java.io.Serializable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

import roboyobo.ball.util.FontHelper;

public class Entry implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private int score, rock, time, shockwave;
	
	
	public Entry(String username, int score, int r, int t, int s) {
		
		this.username = username;
		this.score = score;
		
		rock = r;
		time = t;
		shockwave = s;
	}
	


	public void printInfo() {
		System.out.println(username + " got a score of " + score + " and got " + rock + " rocks, in " + time + " seconds, using " + shockwave + " shockwaves");
	}

	public int getScore() {
		return score;
	}
	
	public String getName() {
		return username;
	}
	
	public int getR() {
		return rock;
	}
	
	public int getT() {
		return Math.round(time / 1000);
	}
	
	public int getS() {
		return shockwave;
	}
}
