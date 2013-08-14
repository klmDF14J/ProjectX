package roboyobo.ball.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadingState extends BasicState {
	
	public LoadingState(int id) {
		super(id, "menu");
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void renderMain(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.drawString("Loading", 10, 10);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

}
