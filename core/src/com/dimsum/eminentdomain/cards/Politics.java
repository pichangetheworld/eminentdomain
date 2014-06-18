package com.dimsum.eminentdomain.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dimsum.eminentdomain.player.Player;
import com.dimsum.eminentdomain.util.RoleStack;

public class Politics extends Card {
	private static final Texture texture = new Texture(Gdx.files.internal("cards.png")); // XXX

	@Override
	public void action(Player currentPlayer) {
		RoleStack obj = (RoleStack) currentPlayer.chooseTarget(RoleStack.class);
		
		// ensure that the role stack is not empty
		Card card = obj.chooseRole();
		while (card == null) {
			// choose another stack
			obj = (RoleStack) currentPlayer.chooseTarget(RoleStack.class);
			card = obj.chooseRole();
		}
		
		currentPlayer.addCard(card);
		currentPlayer.removeCard(this);
		_active = false;
	}

	@Override
	public int getSymbols(Role role) {
		return 0;
	}

	@Override
	public Texture getTexture() {
		return texture;
	}
}
