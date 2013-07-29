package roboyobo.ball.state;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.FontHelper;
import roboyobo.ball.highscore.Entry;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.GameInfo;
import roboyobo.hoppityHop.level.Level;
import roboyobo.hoppityHop.util.Chat;
import roboyobo.hoppityHop.util.Exception;
import roboyobo.hoppityHop.util.Reason;
import roboyobo.hoppityHop.util.Reference;

public class HighscoreState extends BasicGameState {

	private int stateID;
	
	private UnicodeFont font, font2, font3;
	
	private MouseOverArea menu;
	
	public HighscoreState(int stateID) throws SlickException {
		this.stateID = stateID;
		
		font = FontHelper.setupAndReturnNewFont("font", 96);
		font2 = FontHelper.setupAndReturnNewFont("font", 36);
		font3 = FontHelper.setupAndReturnNewFont("font", 24);
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		
		menu = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH / 2 + 50, GameInfo.SCREEN_HEIGHT - 200, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent arg0) {
				GameInfo.balls.get(0).reset(sbg);
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		});
		
		menu.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		menu.setMouseDownSound(Sounds.select);
		
		load();
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setColor(Color.white);
		
		g.drawImage(new Image("/resources/images/projectX/menuBackground.png"), 0, 0);
		
		font.drawString((int) (GameInfo.SCREEN_WIDTH / 2 - (font.getWidth(GameInfo.language.highscore) / 2)), (int) (GameInfo.SCREEN_HEIGHT / 2 - (font.getHeight(GameInfo.language.highscore) / 2) - 250), GameInfo.language.highscore);

		menu.render(gc, g);
		
		font2.drawString(100, 110, GameInfo.language.username);
		font2.drawString(250, 110, GameInfo.language.score);
		font2.drawString(430, 110, "R");
		font2.drawString(580, 110, "T");
		font2.drawString(730, 110, "S");
		
		g.drawLine(98, 108, 98, 450);
		g.drawLine(245, 108, 245, 450);
		g.drawLine(425, 108, 425, 450);
		g.drawLine(575, 108, 575, 450);
		g.drawLine(725, 108, 725, 450);
		g.drawLine(870, 108, 870, 450);
		
		g.drawLine(98, 108, 870, 108);
		g.drawLine(98, 148, 870, 148);
		
		for(int i = 0; i < GameInfo.scores.size(); i++) {
			if(i > 0) {
				if(GameInfo.scores.get(i).getScore() > GameInfo.scores.get(i - 1).getScore()) {
					Entry e = GameInfo.scores.get(i);
					Entry e2 = GameInfo.scores.get(i - 1);
					
					GameInfo.scores.set(i - 1, e);
					GameInfo.scores.set(i, e2);
					
					save();
				}
			}
		}
		
		for(int i = 0; i < 10; i++) {
			
			int x = 100;
			int y = 160 + (i * 30);
			
			if(i < GameInfo.scores.size()) {
				font3.drawString(x, y, "[" + (i + 1) + "]" + GameInfo.scores.get(i).getName());
				font3.drawString(247, y, "" + GameInfo.scores.get(i).getScore());
				font3.drawString(427, y, "" + GameInfo.scores.get(i).getR());
				font3.drawString(577, y, "" + GameInfo.scores.get(i).getT());
				font3.drawString(727, y, "" + GameInfo.scores.get(i).getS());
			}
			
			g.drawLine(98, y + 20, 870, y + 20);
		}
	}
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	@SuppressWarnings("unchecked")
	public void load() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("resources/projectX/high.scores"));
			GameInfo.scores =  (ArrayList<Entry>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void save() {
		System.out.println("Saving");
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("resources/projectX/high.scores"));
			oos.writeObject(GameInfo.scores);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


}
