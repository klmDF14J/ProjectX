package roboyobo.ball.util;

import org.newdawn.slick.Color;

public enum EnumType {
	
	WEAPON("weapon", Color.red, 0),
	SHIP("ship", Color.blue, 1),
	MISC("misc", Color.gray, 2),
	AMMO("ammo", Color.green, 3);
	
	
	private String val;
	private Color col;
	private int id;
	
	private EnumType(String val, Color col, int id) {
		this.val = val;
		this.col = col;
		this.id = id;
	}
	
	public String getRankValue() {
		return val;
	}
	
	public Color getColor() {
		return col;
	}
	
	public int getID() {
		return id;
	}
	
}
