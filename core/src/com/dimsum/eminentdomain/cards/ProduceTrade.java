package com.dimsum.eminentdomain.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dimsum.eminentdomain.planets.Planet;
import com.dimsum.eminentdomain.player.Player;

public class ProduceTrade extends Card {
	private static final Texture texture = new Texture(Gdx.files.internal("producetrade.png"));

	@Override
	public void action(Player currentPlayer) {
		Planet target = (Planet) currentPlayer.chooseTarget(Planet.class);
		
		// target must already be conquered
		if (!target._conquered) {
			// ERROR
			return;
		}
		
		// Let the user decide whether to produce or trade
		Object obj = currentPlayer.chooseTarget(Role.class);
		if (obj == Role.PRODUCE) {
			target.produce(1);
		} else if (obj == Role.TRADE) {
			target.trade(1);
		}
	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.PRODUCE) return 1;
		else if (role == Role.TRADE) return 1;
		return 0;
	}

	@Override
	public Texture getTexture() {
		return texture;
	}
}
