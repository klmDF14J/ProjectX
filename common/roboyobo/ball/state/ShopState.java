package roboyobo.ball.state;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import roboyobo.ball.resource.Images;
import roboyobo.ball.resource.Sounds;
import roboyobo.ball.shop.Item;
import roboyobo.ball.util.EnumRank;
import roboyobo.ball.util.FileWriter;
import roboyobo.ball.util.FontHelper;
import roboyobo.ball.util.GameInfo;

public class ShopState extends BasicState {
	
	public static int length = 10;
	
	private ArrayList<MouseOverArea> buttons;
	
	private UnicodeFont font, font2;
	
	public ShopState(int stateID) throws SlickException {
		super(stateID, "menu");
		
		font = FontHelper.setupAndReturnNewFont("font", 36);
		font2 = FontHelper.setupAndReturnNewFont("font", 24);
		
		GameInfo.shopContents = new ArrayList<Item>();
		
		GameInfo.shopContents.add(new Item("Laser Beam", EnumRank.EPIC, 100, false, 1));
		
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
			System.out.println(item.getName() + " has " + item.getStackSize() + " items in its stack. You buy it in a size of " + item.getBuySize() + " and just to check it " + (item.canStack() == true ? "can" : "cannot") + " stack");
		}
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 5; j++) {
				if(GameInfo.shopContents.size() > i + (j * 10)) {
					Item item = GameInfo.shopContents.get(i + (j * 10));
					item.setImage(i, j);
				}
			}
		}
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
		
		buttons.get(0).setMouseOverImage(Images.buttonMO);
		
		buttons.get(0).setMouseDownSound(Sounds.select);
	}

	@Override
	public void renderMain(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Color.blue);

		
		for(MouseOverArea moa : buttons) {
			moa.render(gc, g);
		}
		font.drawString(GameInfo.SCREEN_WIDTH / 2 - (font.getWidth(GameInfo.language.shop) / 2), 50, GameInfo.language.shop);

		font2.drawString(FontHelper.getWidthDifference(font2, GameInfo.language.back), GameInfo.SCREEN_HEIGHT - 100 + FontHelper.getHeightDifference(font2, GameInfo.language.back), GameInfo.language.back);
		
		renderShop(gc, sbg, g);
		renderTooltip(gc, sbg, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}
	
	private void renderShop(GameContainer gc, StateBasedGame sbg, Graphics g) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 5; j++) {
				g.drawRect(155 + (i * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), 100 + (j * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), GameInfo.SHOP_BOX_SIZE, GameInfo.SHOP_BOX_SIZE);
				if(GameInfo.shopContents.size() > i + (j * 10)) {
					Item item = GameInfo.shopContents.get(i + (j * 10));
					item.renderInSlot(160 + (i * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), 105 + (j * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)));
				}
			}
		}
	}
	
	private void renderTooltip(GameContainer gc, StateBasedGame sbg, Graphics g) {
		if(num < 50) {
			int x = 155 + (intersectionValI * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP));
			int y = 100 + (intersectionValJ * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP));
			int xDif = inX - x;
			int yDif = inY - y;
			Images.tooltip.draw(x + xDif, y + yDif, 200, 50);
		}
	}
	
	private int inX, inY, intersectionValI, intersectionValJ, num;
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		intersectionValI = 0;
		intersectionValJ = 0;
		num = 0;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 5; j++) {
				Rectangle mouse = new Rectangle(newx, newy, 1, 1);
				Rectangle box = new Rectangle(155 + (i * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), 100 + (j * (GameInfo.SHOP_BOX_SIZE + GameInfo.SHOP_BOX_GAP)), 50, 50);
				if(mouse.intersects(box)) {
					System.out.println("Intersecting at: " + (i + (j * 10)));
					intersectionValI = i;
					intersectionValJ = j;
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
