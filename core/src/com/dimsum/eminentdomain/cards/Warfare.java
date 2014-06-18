package com.dimsum.eminentdomain.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dimsum.eminentdomain.planets.Planet;
import com.dimsum.eminentdomain.player.Player;

public class Warfare extends Card {
	private static final Texture texture = new Texture(Gdx.files.internal("warfare.png"));

	@Override
	public void action(Player currentPlayer) {
		Planet target = (Planet) currentPlayer.chooseTarget(Planet.class);
		// if you can conquer it, conquer it
		if (!target.conquer(currentPlayer)) {
			currentPlayer.addFighters(1);
		}
	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.WARFARE) return 1;
		return 0;
	}

	@Override
	public Texture getTexture() {
		return texture;
	}
}
