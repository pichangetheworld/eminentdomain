package com.dimsum.eminentdomain.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dimsum.eminentdomain.player.Player;

public class Card extends Actor {
	protected boolean _active = false;
	public static final float cw = 192; // card width
	public static final float ch = 256; // card height
	
	private Texture _texture = new Texture(Gdx.files.internal("blank_card.png"));
	
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
	
	public Card() {
		this.setSize(cw, ch);
	}

	public void doAction(Player currentPlayer) {
		this._active = true;
		action(currentPlayer);
	}
	public void action(Player currentPlayer) {}
	public boolean isActive() { return _active; };
	public void doneAction() { _active = false; };
	
	public int getSymbols(Role role) {
		return 0;
	}
	
	public Texture getTexture() {
		return _texture;
	}

	public void setTexture(Texture texture) {
		this._texture = texture;
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(getTexture(), this.getX(), getY(), this.getOriginX(),
				this.getOriginY(), this.getWidth(), this.getHeight(),
				this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
				getTexture().getWidth(), getTexture().getHeight(), false, false);
	}
}
