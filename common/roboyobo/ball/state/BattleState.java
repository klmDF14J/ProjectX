package roboyobo.ball.state;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.resource.BackgroundManager;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.FontHelper;
import roboyobo.ball.util.GameInfo;
import roboyobo.ball.util.Language;
import roboyobo.ball.util.LanguageHandler;

public class BattleState extends BasicGameState {

	private int stateID;
	
	private int x = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private int y = GameInfo.SCREEN_HEIGHT / 2 - (GameInfo.GAME_OVER_PANEL_HEIGHT / 2);
	
	public static int length = 10;

	
	private ArrayList<MouseOverArea> buttons;
	
	private UnicodeFont font, font2;
	
	public BattleState(int stateID) throws SlickException {
		this.stateID = stateID;
		
		font = FontHelper.setupAndReturnNewFont("font", 36);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		buttons = new ArrayList<MouseOverArea>();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawImage(BackgroundManager.getBackgroundForState("game"), 0, 0);
		
		renderGrids(g);
		
	}
	
	private void renderGrids(Graphics g) {
		if(GameInfo.BATTLE_PLAYER_COUNT == 2) {
			for(int i = 0; i < 5; i++) {
				g.drawRect(0 + i, 0 + i, GameInfo.SCREEN_WIDTH / 2 - (i * 2), GameInfo.SCREEN_HEIGHT - (i * 2));
				g.drawRect(GameInfo.SCREEN_WIDTH / 2 + i, 0 + i, GameInfo.SCREEN_WIDTH / 2 - (i * 2), GameInfo.SCREEN_HEIGHT - (i * 2));
			}
		}
		if(GameInfo.BATTLE_PLAYER_COUNT == 3) {
			for(int i = 0; i < 5; i++) {
				g.drawRect(0 + i, 0 + i, GameInfo.SCREEN_WIDTH / 3 - (i * 2), GameInfo.SCREEN_HEIGHT - (i * 2));
				g.drawRect(GameInfo.SCREEN_WIDTH / 3 + i, 0 + i, ((GameInfo.SCREEN_WIDTH / 3)) - (i * 2), GameInfo.SCREEN_HEIGHT - (i * 2));
				g.drawRect(((GameInfo.SCREEN_WIDTH / 3) * 2) + i, 0 + i, (GameInfo.SCREEN_WIDTH / 3) - (i * 2), GameInfo.SCREEN_HEIGHT - (i * 2));
			}
		}
		if(GameInfo.BATTLE_PLAYER_COUNT == 4) {
			for(int i = 0; i < 5; i++) {
				g.drawRect(0 + i, 0 + i, GameInfo.SCREEN_WIDTH / 4 - (i * 2), GameInfo.SCREEN_HEIGHT - (i * 2));
				g.drawRect(GameInfo.SCREEN_WIDTH / 4 + i, 0 + i, GameInfo.SCREEN_WIDTH / 4 - (i * 2), GameInfo.SCREEN_HEIGHT - (i * 2));
				g.drawRect(((GameInfo.SCREEN_WIDTH / 4) * 2) + i, 0 + i, GameInfo.SCREEN_WIDTH / 4 - (i * 2), GameInfo.SCREEN_HEIGHT - (i * 2));
				g.drawRect(((GameInfo.SCREEN_WIDTH / 4) * 3) + i, 0 + i, GameInfo.SCREEN_WIDTH / 4 - (i * 2), GameInfo.SCREEN_HEIGHT - (i * 2));
			}
		}
	}
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}


}
