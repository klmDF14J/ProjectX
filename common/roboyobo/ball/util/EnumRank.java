package roboyobo.ball.util;

import org.newdawn.slick.Color;

public enum EnumRank {
	
	BAD("Bad", Color.red),
	AVERAGE("Average", Color.gray),
	GOOD("Good", Color.green),
	EPIC("Epic", Color.magenta);
	
	
	private String val;
	private Color col;
	
	private EnumRank(String val, Color col) {
		this.val = val;
		this.col = col;
	}
	
	public String getRankValue() {
		return val;
	}
	
	public Color getColor() {
		return col;
	}
}
