package com.dimsum.eminentdomain.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dimsum.eminentdomain.player.Player;

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

	public void doAction(Player currentPlayer) {
		this._active = true;
		action(currentPlayer);
	}
	public abstract void action(Player currentPlayer);
	public boolean isActive() { return _active; };
	public void doneAction() { _active = false; };
	
	public abstract int getSymbols(Role role);
	public abstract Texture getTexture();
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(getTexture(), this.getX(), getY(), this.getOriginX(),
				this.getOriginY(), this.getWidth(), this.getHeight(),
				this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
				getTexture().getWidth(), getTexture().getHeight(), false, false);
	}
}
