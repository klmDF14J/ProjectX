package roboyobo.ball.state;

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
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.resource.Images;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.FileWriter;
import roboyobo.ball.util.FontHelper;
import roboyobo.ball.util.GameInfo;
import roboyobo.ball.util.Language;
import roboyobo.ball.util.LanguageHandler;

public class LanguageState extends BasicState {

	private int index;
	
	private ArrayList<MouseOverArea> buttons;
	
	private MouseOverArea back;
	
	private UnicodeFont font, font2;
	
	public LanguageState(int stateID) {
		super(stateID, "menu");
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		buttons = new ArrayList<MouseOverArea>();
		
		font = FontHelper.setupAndReturnNewFont("font", 36);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
		
		for(final Language language : LanguageHandler.languages) {
			buttons.add(new MouseOverArea(gc, Images.button, 350, 100 + (index * 110), new ComponentListener() {
				@Override
				public void componentActivated(AbstractComponent ac) {
					GameInfo.language = language;
					System.out.println(LanguageHandler.languages.indexOf(language));
					FileWriter.save("/resources/projectX/language.dat", LanguageHandler.languages.indexOf(language));
				}
			}));
			buttons.get(index).setMouseOverImage(Images.buttonMO);
			buttons.get(index).setMouseDownSound(Sounds.select);
			index++;
		}
		
		buttons.add(new MouseOverArea(gc, Images.button, GameInfo.SCREEN_WIDTH - 300, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		}));
		
		back = new MouseOverArea(gc, Images.button, 0, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_OPTIONS_ID);
			}
		});
		
		buttons.get(index).setMouseOverImage(Images.buttonMO);
		buttons.get(index).setMouseDownSound(Sounds.select);
		
		back.setMouseOverImage(Images.buttonMO);
		back.setMouseDownSound(Sounds.select);
	}

	@Override
	public void renderMain(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Color.blue);

		
		for(MouseOverArea moa : buttons) {
			moa.render(gc, g);
		}
		
		font.drawString(GameInfo.SCREEN_WIDTH / 2 - (font.getWidth(GameInfo.language.selectLanguage) / 2), 50, GameInfo.language.selectLanguage);
		
		back.render(gc, g);
		
		index = 0;
		
		for(Language language : LanguageHandler.languages) {
			font.drawString(350 + FontHelper.getWidthDifference(font, language.getName()), 100 + FontHelper.getHeightDifference(font, language.getName()) + (index * 110), language.getName());
			index++;
		}
		
		font2.drawString(GameInfo.SCREEN_WIDTH - 300 + FontHelper.getWidthDifference(font2, GameInfo.language.backToMenu), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.backToMenu), GameInfo.language.backToMenu);
		font2.drawString(FontHelper.getWidthDifference(font2, GameInfo.language.back), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.back), GameInfo.language.back);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}


}
