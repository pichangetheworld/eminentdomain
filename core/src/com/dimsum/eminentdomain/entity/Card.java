package com.dimsum.eminentdomain.entity;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Card extends Actor {
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

	public boolean isActive() { return _active; };
	public void doneAction() { _active = false; };
	
	public abstract int getSymbols(Role role);
}
