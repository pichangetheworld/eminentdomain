package com.dimsum.eminentdomain.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dimsum.eminentdomain.player.Player;

public class Research extends Card {
	private static final Texture texture = new Texture(Gdx.files.internal("research.png"));

	@Override
	public void action(Player currentPlayer) {
		for (int i = 0; i < 2; ++i) {
			Object target = currentPlayer.chooseTarget(Card.class);
			if (target instanceof Card) {
				// remove from the game
				currentPlayer.removeCard((Card)target);
				
				if (target.equals(this))
					_active = false;
				
//			} else if (target instanceof Decision) {
//				if (((Decision) target).done) 
//					break;
			}
		}
	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.RESEARCH) return 1;
		return 0;
	}

	@Override
	public Texture getTexture() {
		return texture;
	}
}
