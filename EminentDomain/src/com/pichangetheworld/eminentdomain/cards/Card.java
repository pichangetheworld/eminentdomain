package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.player.Player;

public abstract class Card {
	public enum Role {
		SURVEY(0),
		WARFARE(1),
		COLONISE(2),
		PRODUCE(3),
		TRADE(3),
		RESEARCH(4);
		
		private final int id;
	    Role(int id) { this.id = id; }
	    public int getValue() { return id; }
	}
	
	public abstract void doAction(Player active);
	
	public abstract int getSymbols(Role role);
}
