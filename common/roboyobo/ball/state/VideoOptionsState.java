package roboyobo.ball.state;

import java.text.DecimalFormat;
import java.util.ArrayList;

import mdes.slick.sui.Display;
import mdes.slick.sui.ScrollBar;
import mdes.slick.sui.Slider;
import mdes.slick.sui.event.ChangeEvent;
import mdes.slick.sui.event.ChangeListener;
import mdes.slick.sui.event.MouseEvent;
import mdes.slick.sui.event.MouseListener;

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

import roboyobo.ball.FileWriter;
import roboyobo.ball.FontHelper;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.GameInfo;

public class VideoOptionsState extends BasicGameState {

	private int stateID;
	
	private int x = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private int y = GameInfo.SCREEN_HEIGHT / 2 - (GameInfo.GAME_OVER_PANEL_HEIGHT / 2);
	
	public static int length = 10;
	
	private Slider slider;
	private float lastX = 19 * 5, sliderVal = 1;
	
	private ArrayList<MouseOverArea> buttons;
	
	private UnicodeFont font, font2, font3;
	
	public VideoOptionsState(int stateID) throws SlickException {
		this.stateID = stateID;
		
		font = FontHelper.setupAndReturnNewFont("font", 36);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
		font3 = FontHelper.setupAndReturnNewFont("font", 18);
	}
	
	Display disp;
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		buttons = new ArrayList<MouseOverArea>();
		
		sliderVal = GameInfo.settings.hudScale;
		lastX = GameInfo.settings.lastX;
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), 0, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_GENERAL_OPTIONS_ID);
			}
		}));
		
		buttons.add(new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH - 300, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		}));
		
		buttons.get(0).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		buttons.get(1).setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		
		buttons.get(0).setMouseDownSound(Sounds.select);
		buttons.get(1).setMouseDownSound(Sounds.select);
	
		disp = new Display(gc);
		slider = new Slider(Slider.HORIZONTAL);
		slider.setBounds(GameInfo.SCREEN_WIDTH / 2 - 275, 170, 200, 20);
		slider.setValue(GameInfo.settings.hudScale / 2);
		slider.setThumbSize(0.05F);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if(slider.getThumbButton().getX() > lastX) {
					sliderVal += 0.2F;
				}
				if(slider.getThumbButton().getX() < lastX) {
					sliderVal -= 0.2F;
				}
				lastX = slider.getThumbButton().getX();
				GameInfo.settings.hudScale = sliderVal;
				GameInfo.settings.lastX = lastX;
				FileWriter.save("/resources/projectX/settings.dat", GameInfo.settings);
			}
		});
        disp.add(slider);
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		GameState.setFonts();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawImage(new Image("/resources/images/projectX/menuBackground.png"), 0, 0);
		
		for(MouseOverArea moa : buttons) {
			moa.render(gc, g);
		}
		
		font.drawString(GameInfo.SCREEN_WIDTH / 2 - (font.getWidth(GameInfo.language.videoS + " " + GameInfo.language.settings) / 2), 50, GameInfo.language.videoS + " " + GameInfo.language.settings);
		
		font2.drawString(GameInfo.SCREEN_WIDTH - 300 + FontHelper.getWidthDifference(font2, GameInfo.language.backToMenu), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.backToMenu), GameInfo.language.backToMenu);
		font2.drawString(FontHelper.getWidthDifference(font2, GameInfo.language.back), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.back), GameInfo.language.back);
	
		g.drawImage(new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH / 2 - 325, 100);
		font2.drawString(GameInfo.SCREEN_WIDTH / 2 - 325 + FontHelper.getWidthDifference(font2, GameInfo.language.hud), 100 + ((70 - font2.getHeight(GameInfo.language.hud)) / 2), GameInfo.language.hud);
		
		DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
		String val = oneDigit.format(GameInfo.settings.hudScale);
		font3.drawString(GameInfo.SCREEN_WIDTH / 2 - 325 + FontHelper.getWidthDifference(font3, "" + val), 125 + ((70 - font3.getHeight("" + val)) / 2), "" + val);
		
		disp.render(gc, g);
	}
	
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		disp.update(gc, delta);
	}

	@Override
	public int getID() {
		return stateID;
	}


}
