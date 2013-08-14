package roboyobo.ball.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.newdawn.slick.Color;



public class Settings implements Serializable {
	
	/**
	 * Settings for video
	 */
	public float hudScale = 1F, lastX = 19 * 5;
	
	/**
	 * Settings for audio
	 */
	public boolean sound = true, music = true;
	
	
	/**
	 * Settings for game
	 */
	public boolean renderAimLine = false, showFPS = false;
	public Color[] lineColours = {Color.black, Color.white, Color.red, Color.blue, Color.yellow, Color.green};
	public int lineColour = 0;
	public int menuOrient = 1;
}
