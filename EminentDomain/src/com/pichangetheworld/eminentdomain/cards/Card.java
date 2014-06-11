package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.player.Player;

public abstract class Card {
	protected boolean _active = false;
	
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

	public void doAction(Player currentPlayer) {
		this._active = true;
		action(currentPlayer);
	}
	public abstract void action(Player currentPlayer);
	public boolean isActive() { return _active; };
	public void doneAction() { _active = false; };
	
	public abstract int getSymbols(Role role);
}
