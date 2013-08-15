package roboyobo.ball.shop;

import java.io.Serializable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import roboyobo.ball.resource.Images;
import roboyobo.ball.util.EnumRank;
import roboyobo.ball.util.FileWriter;
import roboyobo.ball.util.GameInfo;

public class Item implements Serializable {
	
	private boolean purchased = false, premium = false, canStack = false;
	private String name;
	private int cost;
	private EnumRank rank;
	private int buySize, stackSize;
	private Image img;
	
	/**
	 * @param name The name of the item
	 * @param rank The quality of the item
	 * @param cost How many tokens will it cost
	 * @param premium Does this item require a premium account to purchase
	 */
	public Item(String name, EnumRank rank, int cost, boolean premium, int buySize) {
		this.name = name;
		this.rank = rank;
		this.cost = cost;
		this.premium = premium;
		this.buySize = buySize;
		this.canStack = buySize > 1 ? true : false;
	}
	
	
	public void buy() {
		if(canBuy()) {
			purchased = true;
			GameInfo.TOKEN_COUNT -= getCost();
			stackSize += buySize;
			FileWriter.save("/resources/projectX/itemsBought.dat", GameInfo.shopContents);
		}
	}
	
	public boolean isPurchased() {
		return purchased;
	}
	
	public boolean canBuy() {
		return !premium && GameInfo.TOKEN_COUNT >= getCost() && (canStack == true ? true : stackSize < 1);
	}
	
	public int getCost() {
		return cost;
	}
	
	public EnumRank getRank() {
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
	
	public int getBuySize() {
		return buySize;
	}
	
	public boolean canStack() {
		return canStack;
	}
	
	public void setImage(int x, int y) {
		img = Images.shopSheet.getSprite(x, y);
	}
	
	public void renderInSlot(int x, int y) {
		img.draw(x, y);
	}
}
