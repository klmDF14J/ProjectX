package roboyobo.ball.state;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.resource.Images;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.shop.Item;
import roboyobo.ball.util.EnumType;
import roboyobo.ball.util.FileWriter;
import roboyobo.ball.util.FontHelper;
import roboyobo.ball.util.GameInfo;

public class ShopState extends BasicState {
	
	public static int length = 10;
	
	private ArrayList<MouseOverArea> buttons;
	private MouseOverArea minus, plus, minus10, plus10;
	
	private UnicodeFont font, font2, font3, font4, font5;
	
	public ShopState(int stateID) throws SlickException {
		super(stateID, "menu");
		
		font = FontHelper.setupAndReturnNewFont("font", 36);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
		font3 = FontHelper.setupAndReturnNewFont("font", 14);
		font4 = FontHelper.setupAndReturnNewFont("font", 12);
		font5 = FontHelper.setupAndReturnNewFont("font", 8);
		
		GameInfo.shopContents = new ArrayList<Item>();
		
		GameInfo.shopContents.add(new Item("Laser Gun", EnumType.WEAPON, 100, false, false));
		GameInfo.shopContents.add(new Item("Ship Armour", EnumType.SHIP, 100, false, false));
		GameInfo.shopContents.add(new Item("Metal Bullet", EnumType.AMMO, 100, false, true));
		GameInfo.shopContents.add(new Item("Rock Zapper", EnumType.WEAPON, 250, false, false));
		GameInfo.shopContents.add(new Item("BG Pack", EnumType.MISC, 2, true, false));
		
		GameInfo.shopContents.get(3).setLocked(GameInfo.shopContents.get(2), 10);
		
		ArrayList<Item> shopCopy = (ArrayList<Item>) GameInfo.shopContents.clone();
		
		if(FileWriter.load("/resources/projectX/itemsBought.dat") instanceof ArrayList<?>) {
			GameInfo.shopContents = (ArrayList<Item>) FileWriter.load("/resources/projectX/itemsBought.dat");
			
			if(GameInfo.shopContents.size() < shopCopy.size()) {
				for(int i = 0; i < shopCopy.size(); i++) {
					if(i > GameInfo.shopContents.size() - 1) {
						GameInfo.shopContents.add(shopCopy.get(i));
					}
				}
			}
		}
		
		System.out.println("There are currently " + GameInfo.shopContents.size() + " Items Available For Purchase");
		
		for(Item item : GameInfo.shopContents) {
			System.out.println(item.getName() + " is an item of " + item.getRank().getRankValue() + " quality. It costs " + item.getCost() + " tokens and is" + (item.isPremium() == false ? " not" : "") + " a premium item");
		}
		
		for(Item item : GameInfo.shopContents) {
			System.out.println(item.getName() + " has " + item.getStackSize() + " items in its stack. Just to check it " + (item.canStack() == true ? "can" : "cannot") + " stack");
		}
		
		System.out.println("Currently there are " + GameInfo.TOKEN_COUNT + " tokens");
	}
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		buttons = new ArrayList<MouseOverArea>();
		
		buttons.add(new MouseOverArea(gc, Images.button, 0, GameInfo.SCREEN_HEIGHT - 100, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent ac) {
				sbg.enterState(GameInfo.STATE_GAME_ID);
			}
		}));
		
		minus = new MouseOverArea(gc, Images.buySizeButton, GameInfo.SCREEN_WIDTH / 2 - 100, 291, new ComponentListener()  {
			@Override
			public void componentActivated(AbstractComponent arg0) {
				GameInfo.SHOP_CURRENT_BUY_SIZE = GameInfo.SHOP_CURRENT_BUY_SIZE > 0 ? GameInfo.SHOP_CURRENT_BUY_SIZE - 1 : 0;
				System.out.println(GameInfo.SHOP_CURRENT_BUY_SIZE);
			}
		});
		
		plus = new MouseOverArea(gc, Images.buySizeButton, GameInfo.SCREEN_WIDTH / 2 + 75, 291, new ComponentListener()  {
			@Override
			public void componentActivated(AbstractComponent arg0) {
				GameInfo.SHOP_CURRENT_BUY_SIZE = GameInfo.SHOP_CURRENT_BUY_SIZE < 100 ? GameInfo.SHOP_CURRENT_BUY_SIZE + 1 : 100;
				System.out.println(GameInfo.SHOP_CURRENT_BUY_SIZE);
			}
		});
		
		minus10 = new MouseOverArea(gc, Images.buySizeButton, GameInfo.SCREEN_WIDTH / 2 - 130, 291, new ComponentListener()  {
			@Override
			public void componentActivated(AbstractComponent arg0) {
				GameInfo.SHOP_CURRENT_BUY_SIZE = GameInfo.SHOP_CURRENT_BUY_SIZE >= 10 ? GameInfo.SHOP_CURRENT_BUY_SIZE - 10 : 0;
				System.out.println(GameInfo.SHOP_CURRENT_BUY_SIZE);
			}
		});
		
		plus10 = new MouseOverArea(gc, Images.buySizeButton, GameInfo.SCREEN_WIDTH / 2 + 105, 291, new ComponentListener()  {
			@Override
			public void componentActivated(AbstractComponent arg0) {
				GameInfo.SHOP_CURRENT_BUY_SIZE = GameInfo.SHOP_CURRENT_BUY_SIZE <= 90 ? GameInfo.SHOP_CURRENT_BUY_SIZE + 10 : 100;
				System.out.println(GameInfo.SHOP_CURRENT_BUY_SIZE);
			}
		});
		
		buttons.get(0).setMouseOverImage(Images.buttonMO);
		buttons.get(0).setMouseDownSound(Sounds.select);
		
		minus.setMouseOverImage(Images.buySizeButtonMO);
		minus.setMouseDownSound(Sounds.select);
		
		plus.setMouseOverImage(Images.buySizeButtonMO);
		plus.setMouseDownSound(Sounds.select);
		
		minus10.setMouseOverImage(Images.buySizeButtonMO);
		minus10.setMouseDownSound(Sounds.select);
		
		plus10.setMouseOverImage(Images.buySizeButtonMO);
		plus10.setMouseDownSound(Sounds.select);
	}

	@Override
	public void renderMain(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Color.blue);

		
		for(MouseOverArea moa : buttons) {
			moa.render(gc, g);
		}
		font.drawString(GameInfo.SCREEN_WIDTH / 2 - (font.getWidth(GameInfo.language.shop) / 2), 50, GameInfo.language.shop);

		font2.drawString(FontHelper.getWidthDifference(font2, GameInfo.language.back), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.back), GameInfo.language.back);
		
		Images.tooltip.draw(GameInfo.SCREEN_WIDTH - 100, 0);
		Images.token.draw(GameInfo.SCREEN_WIDTH - 90, 10, 2F);
		font4.drawString(GameInfo.SCREEN_WIDTH - 55, 20, "" + GameInfo.TOKEN_COUNT);
		
		if(GameInfo.TOKEN_PRE_COUNT > 0) {
			Images.tooltip.draw(GameInfo.SCREEN_WIDTH - 100, 50);
			Images.tokenPre.draw(GameInfo.SCREEN_WIDTH - 90, 60, 2F);
			font4.drawString(GameInfo.SCREEN_WIDTH - 55, 70, "" + GameInfo.TOKEN_PRE_COUNT);
		}
		
		renderShop(gc, sbg, g);
		renderTooltip(gc, sbg, g);
		renderBuyBox(gc, sbg, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(loadTimer >= 1000) {
			loaded = true;
		}
		if(!loaded) {
			loadTimer += delta;
		}
	}
	
	private void renderShop(GameContainer gc, StateBasedGame sbg, Graphics g) {
		for(int i = 0; i < GameInfo.SHOP_BOX_ROW_SIZE; i++) {
			for(int j = 0; j < GameInfo.SHOP_BOX_COLUMN_SIZE; j++) {
				g.drawRect(155 + (i * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), 100 + (j * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), GameInfo.SHOP_BOX_SIZE, GameInfo.SHOP_BOX_SIZE);
				if(GameInfo.shopContents.size() > i + (j * GameInfo.SHOP_BOX_ROW_SIZE)) {
					Item item = GameInfo.shopContents.get(i + (j * GameInfo.SHOP_BOX_ROW_SIZE));
					Images.shopItems.get(item.getID()).draw(160 + (i * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), 105 + (j * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)));
					if(item.canStack()) {
						font4.drawString(190 + (i * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), 135 + (j * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), "" + item.getStackSize());
					}
					else {
						font5.drawString(160 + (i * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), 140 + (j * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), item.isPurchased() == true ? "Bought" : "");
					}
				}
			}
		}
	}
	
	private void renderTooltip(GameContainer gc, StateBasedGame sbg, Graphics g) {
		if(num < GameInfo.SHOP_BOX_ROW_SIZE * GameInfo.SHOP_BOX_COLUMN_SIZE && !drawBuyBox) {
			int x = 155 + (intersectionValI * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP));
			int y = 100 + (intersectionValJ * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP));
			int xDif = inX - x;
			int yDif = inY - y;
			Images.tooltip.draw(x + xDif, y + yDif, 200, 50);
			if(item != null) {
				item.checkLocked();
				if(!item.isLocked()) {
					font3.drawString(x + xDif + 10, y + yDif + 10, item.getName(), item.getColorFromRank());
					font4.drawString(x + xDif + 10, y + yDif + 30, item.getCost() + (item.isPremium() ? " Premium Tokens" : " Tokens") , item.isPremium() ? GameInfo.TOKEN_PRE_COUNT - item.getCost() >= 0 ? Color.green : Color.red : GameInfo.TOKEN_COUNT - item.getCost() >= 0 ? Color.green : Color.red);
				
					Images.upgradeItems.get(item.getRank().getID()).draw(x + xDif + 160, y + yDif + 10, 1F / (Images.upgradeItems.get(item.getID()).getWidth() / 30F));
				}
				else {
					renderUnknown(x, y, xDif, yDif);
				}
			}
			else {
				renderUnknown(x, y, xDif, yDif);
			}
		}
	}
	
	private void renderUnknown(int x, int y, int xDif, int yDif) {
		font3.drawString(x + xDif + 10, y + yDif + 10, item != null ? "Undiscovered" : "Unknown");
		font4.drawString(x + xDif + 10, y + yDif + 30, "? Tokens", Color.red);
	
		font2.drawString(x + xDif + 170, y + yDif + 10, "?");
	}
	
	private void renderBuyBox(GameContainer gc, StateBasedGame sbg, Graphics g) {
		if(drawBuyBox) {
			Color c = Color.darkGray;
			c.a = 0.85F;
			g.setColor(c);
			g.fillRect(GameInfo.SCREEN_WIDTH / 2 - 150, GameInfo.SCREEN_HEIGHT / 2 - 100, 300, 200);
			font2.drawString(GameInfo.SCREEN_WIDTH / 2 - 150 + (FontHelper.getWidthDifference(font2, "Purchase")), GameInfo.SCREEN_HEIGHT / 2 - 80, "Purchase");
			
			minus.render(gc, g);
			plus.render(gc, g);
			minus10.render(gc, g);
			plus10.render(gc, g);
			
			String str = "" + GameInfo.SHOP_CURRENT_BUY_SIZE;
			font2.drawString(GameInfo.SCREEN_WIDTH / 2 - 150 + ((300 - font2.getWidth(str)) / 2), GameInfo.SCREEN_HEIGHT / 2 - 100 + ((200 - font2.getHeight(str)) / 2), str);
			
			String name = item.getName() + (GameInfo.SHOP_CURRENT_BUY_SIZE > 1 ? "s" : GameInfo.SHOP_CURRENT_BUY_SIZE == 1 ? "" : "s");
			font2.drawString(GameInfo.SCREEN_WIDTH / 2 - 150 + ((300 - font2.getWidth(name)) / 2), GameInfo.SCREEN_HEIGHT / 2 - 40 + ((200 - font2.getHeight(name)) / 2), name);
		
			if(!item.canStack()) {
				GameInfo.SHOP_CURRENT_BUY_SIZE = 1;
				minus.setAcceptingInput(false);
				plus.setAcceptingInput(false);
				minus10.setAcceptingInput(false);
				plus10.setAcceptingInput(false);
			}
			else {
				minus.setAcceptingInput(true);
				plus.setAcceptingInput(true);
				minus10.setAcceptingInput(true);
				plus10.setAcceptingInput(true);
			}
		}
	}
	
	private int inX, inY, intersectionValI, intersectionValJ, num, loadTimer;
	private Item item;
	private boolean drawBuyBox = false, loaded = false;
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		getIntersection(newx, newy);
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		getIntersection(x, y);
		if(GameInfo.shopContents.size() > intersectionValI + (intersectionValJ * 10) && loaded) {
			item = GameInfo.shopContents.get(intersectionValI + (intersectionValJ * 10));
			if((item.canStack() || (!item.canStack() && item.getStackSize() == 0)) && !item.isLocked()) {
				drawBuyBox = true;
			}
		}
	}	
	
	public void getIntersection(int newx, int newy) {
		if(!drawBuyBox) {
			intersectionValI = 0;
			intersectionValJ = 0;
			num = 0;
			item = null;
			for(int i = 0; i < GameInfo.SHOP_BOX_ROW_SIZE; i++) {
				for(int j = 0; j < GameInfo.SHOP_BOX_COLUMN_SIZE; j++) {
					Rectangle mouse = new Rectangle(newx, newy, 1, 1);
					Rectangle box = new Rectangle(155 + (i * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), 100 + (j * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), 50, 50);
					if(mouse.intersects(box)) {
						intersectionValI = i;
						intersectionValJ = j;
						if(GameInfo.shopContents.size() > i + (j * 10)) {
							item = GameInfo.shopContents.get(i + (j * 10));
						}
					}
					else {
						num++;
					}
					inX = newx;
					inY = newy;
	 			}
			}
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Keyboard.KEY_ESCAPE && drawBuyBox) {
			drawBuyBox = false;
		}
		if(key == Keyboard.KEY_RETURN && drawBuyBox) {
			drawBuyBox = false;
			item.buy();
		}
	}
}
