package roboyobo.ball.shop;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import roboyobo.ball.resource.Images;
import roboyobo.ball.util.EnumType;
import roboyobo.ball.util.FileWriter;
import roboyobo.ball.util.GameInfo;

public class Item implements Serializable {
	
	private boolean purchased = false, premium = false, canStack = false, unlocked = true;
	private String name, type;
	private int cost;
	private EnumType rank;
	private int stackSize;
	private Image img, typeImg;
	
	/**
	 * @param name The name of the item
	 * @param rank The quality of the item
	 * @param cost How many tokens will it cost
	 * @param premium Does this item require a premium account to purchase
	 */
	public Item(String name, EnumType rank, int cost, boolean premium, boolean canStack) {
		this.name = name;
		this.rank = rank;
		this.cost = cost;
		this.premium = premium;
		this.canStack = canStack;
		this.type = rank.getRankValue();
		
		try {
			setTypeImage(type);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	public void buy() {
		if(canBuy()) {
			purchased = true;
			GameInfo.TOKEN_COUNT -= getCost();
			stackSize += GameInfo.SHOP_CURRENT_BUY_SIZE;
			FileWriter.save("/resources/projectX/itemsBought.dat", GameInfo.shopContents);
		}
	}
	
	public boolean isPurchased() {
		return purchased;
	}
	
	public boolean canBuy() {
		return !premium && GameInfo.TOKEN_COUNT >= getCost() && (canStack == true ? true : stackSize < 1) && isPurchasable();
	}
	
	public int getCost() {
		return cost;
	}
	
	public EnumType getRank() {
		return rank;
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColorFromRank() {
		return getRank().getColor();
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public int getStackSize() {
		return stackSize;
	}
	
	public boolean canStack() {
		return canStack;
	}
	
	public void setImage(int x, int y) {
		img = Images.shopSheet.getSprite(x, y);
		if(type == "useImg") {
			typeImg = img;
		}
	}
	
	public void renderInSlot(int x, int y) {
		img.draw(x, y);
	}
	
	public void setTypeImage(String par1) throws SlickException {
		if(par1 != "useImg") {
			typeImg = Images.upgradeItems.get(rank.getID());
			System.out.println(rank.getID() + " for " + getName());
		}
	}
	
	public Image getTypeImage() {
		return typeImg;
	}
	
	public void setLocked(boolean val) {
		unlocked = val;
	}
	
	public boolean isPurchasable() {
		return unlocked;
	}
}
