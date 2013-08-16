package roboyobo.ball.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.resource.BackgroundManager;

public class BasicState extends BasicGameState {

	private int stateID;
	
	private String bgKey;
	
	public BasicState(int id, String bg) {
		stateID = id;
		
		bgKey = bg;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		BackgroundManager.getBackgroundForState(bgKey).draw(0, 0);
		renderMain(gc, sbg, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}
	
	/**
	 * Put render code in here and not in render as that handles generic rendering that is used by all states
	 */
	public void renderMain(GameContainer gc, StateBasedGame sbg, Graphics g) {
		
	}

	@Override
	public int getID() {
		return stateID;
	}
}
