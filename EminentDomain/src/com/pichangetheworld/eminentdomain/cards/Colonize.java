package com.pichangetheworld.eminentdomain.cards;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;

public class Colonize extends Card {

	@Override
	public void action(Player currentPlayer) {
		Planet target = (Planet) currentPlayer.chooseTarget(Planet.class);
		// if you can conquer it, conquer it
		if (!target.colonise(currentPlayer)) {
			List<Card> colonies = new ArrayList<Card>();
			colonies.add(this);
			target.addColony(colonies);
		}
	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.COLONISE) return 1;
		return 0;
	}
}
