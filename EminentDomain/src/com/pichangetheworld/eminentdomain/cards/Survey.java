package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.player.Player;

public class Survey extends Card {

	@Override
	public void action(Player currentPlayer) {
		currentPlayer.draw(2);
	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.SURVEY) return 1;
		return 0;
	}
}
