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
	
	private boolean purchased = false, premium = false, canStack = false, locked = false;
	private String name, type;
	private int cost;
	private EnumType rank;
	private int stackSize, id;
	
	private Item otherItem;
	private int otherSize;
	
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
		this.id = getNextID();
	}
	
	
	public void buy() {
		if(canBuy()) {
			purchased = true;
			GameInfo.TOKEN_COUNT -= (getCost() * GameInfo.SHOP_CURRENT_BUY_SIZE);
			stackSize += GameInfo.SHOP_CURRENT_BUY_SIZE;
			FileWriter.save("/resources/projectX/itemsBought.dat", GameInfo.shopContents);
			FileWriter.save("/resources/projectX/tokenData.dat", GameInfo.TOKEN_COUNT);
		}
		if(premium && GameInfo.TOKEN_PRE_COUNT >= getCost() * GameInfo.SHOP_CURRENT_BUY_SIZE && (canStack == true ? true : stackSize < 1) && !isLocked()) {
			purchased = true;
			GameInfo.TOKEN_PRE_COUNT -= (getCost() * GameInfo.SHOP_CURRENT_BUY_SIZE);
			stackSize += GameInfo.SHOP_CURRENT_BUY_SIZE;
			FileWriter.save("/resources/projectX/itemsBought.dat", GameInfo.shopContents);
			FileWriter.save("/resources/projectX/tokenPreData.dat", GameInfo.TOKEN_PRE_COUNT);
		}
	}
	
	public boolean isPurchased() {
		return purchased;
	}
	
	public boolean canBuy() {
		return !premium && GameInfo.TOKEN_COUNT >= getCost() * GameInfo.SHOP_CURRENT_BUY_SIZE && (canStack == true ? true : stackSize < 1) && !isLocked();
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
	
	public void setLocked(Item par1, int par2) {
		locked = par1.getStackSize() < par2;
		otherItem = par1;
		otherSize = par2;
	}
	
	public void checkLocked() {
		if(otherItem != null && otherItem.getStackSize() >= otherSize) {
			locked = false;
		}
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	private int getNextID() {
		return GameInfo.lastID++;
	}
	
	public int getID() {
		return id;
	}
}
