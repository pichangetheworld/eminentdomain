package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;

public class Warfare extends Card {

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

}
