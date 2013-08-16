package roboyobo.ball.shop;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import roboyobo.ball.resource.Images;
import roboyobo.ball.util.EnumRank;
import roboyobo.ball.util.FileWriter;
import roboyobo.ball.util.GameInfo;

public class Item implements Serializable {
	
	private boolean purchased = false, premium = false, canStack = false;
	private String name, type;
	private int cost;
	private EnumRank rank;
	private int buySize, stackSize;
	private Image img, typeImg;
	
	/**
	 * @param name The name of the item
	 * @param rank The quality of the item
	 * @param cost How many tokens will it cost
	 * @param premium Does this item require a premium account to purchase
	 */
	public Item(String name, EnumRank rank, int cost, boolean premium, int buySize, String type) {
		this.name = name;
		this.rank = rank;
		this.cost = cost;
		this.premium = premium;
		this.buySize = buySize;
		this.canStack = buySize > 1 ? true : false;
		this.type = type;
		
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
		if(type == "useImg") {
			typeImg = img;
		}
	}
	
	public void renderInSlot(int x, int y) {
		img.draw(x, y);
	}
	
	public void setTypeImage(String par1) throws SlickException {
		if(par1 != "useImg") {
			typeImg = Images.createImage(par1);
		}
	}
	
	public Image getTypeImage() {
		return typeImg;
	}
}
