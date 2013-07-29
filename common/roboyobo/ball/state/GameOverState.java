package roboyobo.ball.state;

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
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.util.GameInfo;

public class GameOverState extends BasicGameState {

	private int stateID;
	
	private UnicodeFont font, font2, font3;
	
	private float x = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private float y = GameInfo.SCREEN_HEIGHT;
	
	private int targetX = GameInfo.SCREEN_WIDTH / 2 - (GameInfo.GAME_OVER_PANEL_WIDTH / 2);
	private int targetY = GameInfo.SCREEN_HEIGHT / 2 - (GameInfo.GAME_OVER_PANEL_HEIGHT / 2);
	
	private float x2 = 0;
	private float y2 = targetY + 75;
	
	private int rockX = targetX - 50;
	
	private float x3 = 0;
	private float y3 = targetY + 125;
	
	private int timeX = targetX - 50;
	
	private float x4 = 0;
	private float y4 = targetY + 175;
	
	private int shockwaveX = targetX - 50;
	
	private float x5 = 0;
	private float y5 = targetY + 275;
	
	private int totalX = targetX - 50;
	
	private float rockAmount, timeAmount, shockwaveAmount, totalAmount;
	
	private boolean flag = false;
	private boolean renderInfo = false;
	
	private boolean canX = false, canX2 = false, canX3 = false, canX4 = false, canX5 = false;
	
	private boolean canRock = false, canTime = false, canShock = false, canTotal = false;
	
	private MouseOverArea info, play, menu, highscore;
	
	public GameOverState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		
		font = FontHelper.setupAndReturnNewFont("font", 48);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
		font3 = FontHelper.setupAndReturnNewFont("font", 16);
		
		info = new MouseOverArea(gc, new Image("/resources/images/projectX/buttonScore.png"), 654, (int) y5 + 22, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				renderInfo  = renderInfo == true ? false : true;
			}
		});
		info.setMouseOverImage(new Image("/resources/images/projectX/buttonScoreMO.png"));
		info.setMouseDownSound(Sounds.select);
		
		play = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), 0, (int) GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.balls.get(0).reset(sbg);
				renderInfo = false;
				sbg.enterState(GameInfo.STATE_GAME_ID);
			}
		});
		play.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		play.setMouseDownSound(Sounds.select);

		menu = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), (GameInfo.SCREEN_WIDTH / 2) - 150, (int) GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				GameInfo.balls.get(0).reset(sbg);
				renderInfo = false;
				sbg.enterState(GameInfo.STATE_MENU_ID);
			}
		});
		menu.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		menu.setMouseDownSound(Sounds.select);
		
		highscore = new MouseOverArea(gc, new Image("/resources/images/projectX/button.png"), GameInfo.SCREEN_WIDTH - 300, (int) GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_SUBMIT_HIGHSCORE_ID);
			}
		});
		highscore.setMouseOverImage(new Image("/resources/images/projectX/buttonMO.png"));
		highscore.setMouseDownSound(Sounds.select);
	}
	

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.drawImage(new Image("/resources/images/projectX/menuBackground.png"), 0, 0);
		font.drawString(x + FontHelper.getBigWidthDifference(font, GameInfo.language.gameOver), y, GameInfo.language.gameOver);
		
		if(canX2) {
			font2.drawString(x2, y2, GameInfo.language.rocksDestroyed);
			font2.drawString(655, y2, "" + Math.round(rockAmount));
		}
		if(canX3) {
			font2.drawString(x3, y3, GameInfo.language.timeLasted);
			font2.drawString(655, y3, "" + Math.round(timeAmount / 1000));
		}
		if(canX4) {
			font2.drawString(x4, y4, GameInfo.language.shockwavesUsed);
			font2.drawString(655, y4, "" + Math.round(shockwaveAmount));
		}
		if(canX5) {
			font2.drawString(x5, y5, GameInfo.language.totalScore);
			font2.drawString(655, y5, "" + Math.round(totalAmount));
			info.render(gc, g);
			play.render(gc, g);
			menu.render(gc, g);
			highscore.render(gc, g);
			
			font2.drawString(0 + FontHelper.getWidthDifference(font2, GameInfo.language.play), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.play), GameInfo.language.play);
			font2.drawString(((GameInfo.SCREEN_WIDTH / 2) - 150) + FontHelper.getWidthDifference(font2, GameInfo.language.backToMenu), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.backToMenu), GameInfo.language.backToMenu);
			font2.drawString((GameInfo.SCREEN_WIDTH - 300) + FontHelper.getWidthDifference(font2, GameInfo.language.submit), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.submit), GameInfo.language.submit);
		}
		
		if(renderInfo) {
			font3.drawString(680, y5 + 25, "(T x R) - (S x 3000)");
		}
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(y > targetY) {
			y -= 4.5F;
		}
		
		if(y <= targetY) {
			if(x2 < rockX) {
				canX2 = true;
				x2 += 3.5F;
			}
			if(x2 >= rockX) {
				if(x3 < timeX) {
					canX3 = true;
					x3 += 3.5F;
				}
				if(x3 >= timeX) {
					if(x4 < shockwaveX) {
						canX4 = true;
						x4 += 3.5F;
					}
					if(x4 >= shockwaveX) {
						if(x5 < totalX) {
							canX5 = true;
							x5 += 3.5F;
						}
					}
				}
			}
		}
		
		if(x5 >= totalX) {
			canRock = true;
		}
		if(canRock && rockAmount < GameInfo.DEAD_ROCKS) {
			rockAmount += 1.5F;
		}
		if(canRock && rockAmount >= GameInfo.DEAD_ROCKS) {
			canTime = true;
		}
		if(canTime && timeAmount < GameInfo.TIME_RUNNING) {
			timeAmount += 650F;
		}
		if(canTime && timeAmount >= GameInfo.TIME_RUNNING) {
			canShock = true;
		}
		if(canShock && shockwaveAmount < GameInfo.SHOCKWAVES_USED) {
			shockwaveAmount += 1F;
		}
		if(canShock && shockwaveAmount >= GameInfo.SHOCKWAVES_USED) {
			canTotal = true;
		}
		if(canTotal) {
			if(totalAmount < calcScore()) {
				playTotalSound();
				if(calcScore() < 1000) {
					totalAmount += 45F;
				}
				if(calcScore() >= 1000 && calcScore() < 3000) {
					totalAmount += 75F;
				}
				if(calcScore() >= 3000 && calcScore() < 10000) {
					totalAmount += 105F;
				}
				if(calcScore() >= 10000) {
					totalAmount += 350F;
				}
				
			}
		}
		
	}
	
	public static float calcScore() {
		return ((GameInfo.TIME_RUNNING / 1000) * GameInfo.DEAD_ROCKS) - (GameInfo.SHOCKWAVES_USED * 3000);
	}
	
	private void playTotalSound() {
		if(!flag) {
			flag = true;
			Sounds.total.play();
		}
	}

	@Override
	public int getID() {
		return stateID;
	}


}
