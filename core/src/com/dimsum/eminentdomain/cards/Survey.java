package com.dimsum.eminentdomain.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dimsum.eminentdomain.player.Player;

public class Survey extends Card {
	private static final Texture texture = new Texture(Gdx.files.internal("survey.png"));

	@Override
	public void action(Player currentPlayer) {
		currentPlayer.draw(2);
	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.SURVEY) return 1;
		return 0;
	}

	@Override
	public Texture getTexture() {
		return texture;
	}
}
